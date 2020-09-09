import { Component, OnInit, ViewChild, OnDestroy } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { Product } from 'src/app/models/product';
import {ProductService} from './../../services/product.service';
import {UploadService} from './../../services/upload.service';
import { ProductType } from 'src/app/models/product-type';
import {ProductTypeService} from './../../services/product-type.service';
import {ActivatedRoute} from '@angular/router';
import {Subscription} from 'rxjs';
import {PnotifyService} from './../../utils/pnotify.service';
import { Brand } from 'src/app/models/brand';
import { BrandService } from './../../services/brand.service';
import { Paging } from './../../models/paging';
import { IpService } from './../../services/ip.service';
import { LogService } from './../../services/log.service';
import { Log } from './../../models/log';
import { CookieService } from 'ngx-cookie-service';



@Component({
  selector: 'app-product-admin',
  templateUrl: './product-admin.component.html',
  styleUrls: ['./product-admin.component.css']
})
export class ProductAdminComponent implements OnInit {
  @ViewChild('addModal', { static: false }) addModal: ModalDirective;
  saveForm: FormGroup;
  action: string;
  title = 'angular-datatables';
  paging = { page: 0, pageLimit: 5, totalItems: 3 } as Paging; 

  //File rỗng
  arrayOfBlob = new Array<Blob>();
  applicationZip = new File(this.arrayOfBlob, "empty.zip", { type: 'application/zip' });

  colors = [{value: 'Xanh', name: 'Xanh'}, {value: 'Đỏ', name: 'Đỏ'}, {value: 'Tím', name: 'Tím'}];
  mainFile: File = this.applicationZip;
  subFile: File = this.applicationZip;
  subFile2: File = this.applicationZip;
  mainName: string;
  subName: string;
  sub1Name: string;
  type: ProductType[] = [];
  brand: Brand[] = [];
  products: Product[] = [];
  product: Product = {id: 0, type: {id: 0}, brand: {id: 0}} as Product;
  productType: ProductType ;
  rows = [];
  private querySub: Subscription;
  page: any;
  log: Log;
  ip: string;
  user: string;

  constructor(private productService: ProductService, private pnotifyService: PnotifyService,
              private productTypeService: ProductTypeService,  private route: ActivatedRoute,
              private fb: FormBuilder, private brandService: BrandService, private ipService: IpService,
              private cookieService: CookieService, private logService: LogService)
              {
              this.saveForm = this.fb.group({
                  name: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(100)]],
                  price: ['', Validators.required, ],
                  color:['', Validators.required],
                  type: ['', Validators.required],
                  brand: ['', Validators.required],
                  stock: ['', Validators.required],
                  sale: ['', Validators.required],
                  main: [],
                  sub:[],
                  sub1:[]
                  });
              }
  ngOnInit() {
    this.ipService.getIPAddress().subscribe((res: any) =>{
      console.log(res);
      this.ip = res.ip  + ''; });
    this.user = this.cookieService.get('account');
    this.loadProduct(null);
    this.productTypeService.list().subscribe(res => {
      this.type = res;
    });
    this.brandService.list().subscribe(res => {
      this.brand = res;
    });
    // this.querySub = this.route.queryParams.subscribe(() => {
    //   this.update();
    // });
  }

  // ngOnDestroy(): void {
  //   this.querySub.unsubscribe();
  // }

  loadProduct(page){
    if (page != null) {
      this.paging.page = page.offset;
    }
    this.productService.getAllInPage(this.paging.page, this.paging.pageLimit).subscribe(res => {
      this.products = res.content;
      console.log(this.products);
      this.paging.page = res.number;
      this.paging.pageLimit = res.size;
      this.paging.totalItems = res.totalElements;
      this.paging.totalPages = res.totalPages;
    });
  }

//   update() {
//     if (this.route.snapshot.queryParamMap.get('page')) {
//         const currentPage = +this.route.snapshot.queryParamMap.get('page');
//         const size = +this.route.snapshot.queryParamMap.get('size');
//         this.getProds(currentPage, size);
//     } else {
//         this.getProds(1, 3);
//     }
// }

  // getProds(page: number = 1, size: number = 5) {
  //     this.productService.getAllInPage(+page, +size)
  //         .subscribe( page => {
  //             this.page = page;
  //         });

  // }

  hideModal() {
    this.addModal.hide();
  }

  // show modal
  openAdd() {
    this.action = 'Add';
    this.saveForm.reset();
    this.mainName = 'Chọn File';
    this.subName = 'Chọn File';
    this.sub1Name = 'Chọn File';
    this.product = {id: 0, type: {id: 0}, brand: {id: 0}} as Product;
    this.mainFile = this.applicationZip;
    this.subFile = this.applicationZip;
    this.subFile2 = this.applicationZip;
    
    this.addModal.show();
  }

  openEdit(id) {
    event.preventDefault();
    this.action = 'Edit';
    this.mainName = 'Chọn File';
    this.subName = 'Chọn File';
    this.sub1Name = 'Chọn File';
    this.mainFile = this.applicationZip;
    this.subFile = this.applicationZip;
    this.subFile2 = this.applicationZip;
    this.productService.getOne(id).subscribe(res =>{
      this.product = res.data;
      this.addModal.show();
    });
  }

  delete(id) {
    event.preventDefault();
    this.pnotifyService.showConfirm('Warnning', 'Are you sure?', yes => {
      if (yes) {
       this.productService.delete(id).subscribe(res =>{
        this.log = {id: 0, createdTime: null, user: this.user, ip: this.ip+ '', level: 'Cao',
                    description: 'Xóa sản phẩm ID ' + this.product.name + ' ra khỏi danh sách'};
        console.log(this.log);

        this.logService.saveLog(this.log).subscribe(res => {
          console.log(res);
        });
        this.pnotifyService.success('Info', 'delete susessfully');
        this.loadProduct(null);
       });
      }
    });
  }

  //Lấy tên hình và file hình
  selectMain(event) {
    this.mainFile = event.target.files.item(0);
    this.product.mainImg =  this.mainFile.name;
    this.mainName = this.mainFile.name;
  }
  selectSub(event) {
    this.subFile = event.target.files.item(0);
    this.product.subImg =  this.subFile.name;
    this.subName = this.subFile.name;
  }
  selectSub2(event) {
    this.subFile2 = event.target.files.item(0);
    this.product.subImg2 =  this.subFile2.name;
    this.sub1Name = this.subFile2.name;
  }

  save() {
    this.productType = this.product.type;
    console.log(this.product);
    if(this.action === 'Add'){

    this.productService.addProduct(this.mainFile, this.subFile, this.subFile2, this.product).subscribe(res => {
    // this.update();
    this.loadProduct(null);
    });
    this.log = {id: 0, createdTime: null, user: this.user, ip: this.ip + '', level: 'Thấp',
               description: 'Thêm ' + this.product.name + ' vào danh sách'};
    this.logService.saveLog(this.log).subscribe(res => {
      console.log(res);
    });
    this.pnotifyService.success('Info', 'Added susessfully');
    }
    if(this.action === 'Edit'){
      console.log(this.product);
      this.productService.editProduct(this.mainFile, this.subFile, this.subFile2, this.product, this.product.id).subscribe(res => {
       // console.log(res);
      // this.update();
      this.loadProduct(null);

      });
      console.log(this.ip);

      this.log = {id: 0, createdTime: null, user: this.user, ip: this.ip + '', level: 'Trung bình',
               description: 'Sửa ' + this.product.name + ' trong danh sách'};
      console.log(this.log);
      this.logService.saveLog(this.log).subscribe(res => {
        console.log(res);
      });
      this.pnotifyService.success('Info', 'Edited susessfully');
      }

  }
}
