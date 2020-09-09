import { Component, OnInit, OnDestroy } from '@angular/core';
import { ProductService } from './../../services/product.service';
import { ActivatedRoute } from '@angular/router';
import { ProductType } from 'src/app/models/product-type';
import { ProductTypeService } from './../../services/product-type.service';
import { EventEmitterService } from './../../services/event-emitter.service';
import { BrandService } from 'src/app/services/brand.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.css']
})
export class ShopComponent implements OnInit, OnDestroy {
  typeId = 0;
  brandId = 0;
  listType = [];
  listBrand = [];
  products = [];
  total = 0;
  title = '';
  subscriptionListType: Subscription;
  subscriptionListBrand: Subscription;
  subListProductType: Subscription;
  subListProductBrand: Subscription;

  constructor(
    private productService: ProductService,
    private route: ActivatedRoute,
    private productTypeService: ProductTypeService,
    private brandService: BrandService,
    private eventEmitterService: EventEmitterService) { }

  ngOnInit() {
    this.subscriptionListType = this.productTypeService.list()
      .subscribe(res => {

        this.listType = res;
      });
    this.subscriptionListBrand = this.brandService.list()
      .subscribe(resBrand => {
      //  debugger
        this.listBrand = resBrand
        console.log(this.listBrand);

      })
    //lấy giá trị type id từ url trên navigation gọi ???
    this.typeId = Number(this.route.snapshot.paramMap.get('id'));
    this.brandId = Number(this.route.snapshot.paramMap.get('id'));
    this.loadProduct();
    if (this.eventEmitterService.subsVar == undefined) {
      this.eventEmitterService.subsVar = this.eventEmitterService.
        invokeSearchFunction.subscribe((text: string) => {
          this.onTextSearch(text);
        });
      this.eventEmitterService.subsVar = this.eventEmitterService.
        invokeChangeTypeFunction.subscribe((data: any) => {
          this.change(data.type, data.name);
        });
    }
  }

  loadProduct() {
    if (this.typeId > 0) {
      this.subListProductType = this.productService.getAllInType(this.typeId, 0, 10).subscribe(res => {
     //   debugger
        this.products = res.content;
        this.total = res.numberOfElements;
      })
    } else 
    if (this.brandId > 0) {
      this.subListProductBrand = this.productService.getAllInBrand(this.brandId, 0, 10).subscribe(res => {
        this.products = res.content;
        this.total = res.numberOfElements;
      })
    }
    else {
      this.productService.getAllInPage(0, 10).subscribe(res => {
        this.products = res.content;
        this.total = res.numberOfElements;
      }
      );
    }
  }
  //tính giá sau khi đã giảm
  countPrice(price, sale) {
    return Math.ceil(price - price * sale / 100);
  }

  //Thay đổi danh sách sản phẩm theo type
  change(id, name) {
    this.title = name;
    this.productService.getAllInType(id, 0, 10).subscribe(res => {
      this.products = res.content;
      this.total = res.numberOfElements;
    });
  }
  changeBrand(id, name) {
    this.title = name;
    this.subListProductBrand = this.productService.getAllInBrand(id, 0, 10).subscribe(res => {
      this.products = res.content;
      this.total = res.numberOfElements;
    })
  }

  //thay đổi danh sách sản phẩm theo text search
  onTextSearch(text) {
    this.productService.search(text).subscribe(res => {
      console.log(res);
      this.products = res.content;
      this.total = res.numberOfElements;
    });
  }

  ngOnDestroy() {
    if (this.subscriptionListType) {
      this.subscriptionListType.unsubscribe();
    }
    if ( this.subscriptionListBrand) {
      this.subscriptionListBrand.unsubscribe();
    }
    if (this.subListProductType) {
      this.subListProductType.unsubscribe();
    }
    if (this.subListProductBrand){
      this.subListProductBrand.unsubscribe();
    }
  }
}

