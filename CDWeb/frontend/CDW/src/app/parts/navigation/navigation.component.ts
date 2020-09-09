import { Component, OnInit, ViewChild, OnChanges, SimpleChanges } from '@angular/core';
import {ProductTypeService} from './../../services/product-type.service';
import { ProductType } from 'src/app/models/product-type';
import { AuthService } from './../../services/auth.service';
import { ProductService } from './../../services/product.service';
import { CookieService } from 'ngx-cookie-service';
import { Router, ActivatedRoute } from '@angular/router';
import { CartService } from './../../services/cart.service';
import { from } from 'rxjs';
import { EventEmitterService } from './../../services/event-emitter.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.scss']
})
export class NavigationComponent implements OnInit {
  listType: ProductType[] = [];
  account: string;
  show = false;
  total = 0;


  constructor(private productTypeService: ProductTypeService, private authService: AuthService, private productService: ProductService,
              private cookieService: CookieService, private router: Router, private route: ActivatedRoute,
              private cartService: CartService, private eventEmitterService: EventEmitterService ) {}
  ngOnInit() {
    this.total = this.cartService.getTotal();
    const token = this.cookieService.get('token');
    if(token) {
      this.account = this.cookieService.get('account');
      this.show = true;
    } else {
      this.show = false;
    }
    this.productTypeService.list().subscribe(res =>{
      this.listType = res;
    });
  }

  logout(): void{
    this.authService.setLoggedIn(false);
    this.cookieService.deleteAll();
    this.show = false;
    this.router.navigate(['/']);
    localStorage.clear();
}

//redirect to shop with product type is id
redirectTo(id){
  this.router.navigateByUrl(`shop/${id}`).then(()=> {
    window.location.reload();
  });
}

toShop(id, name) {
  this.router.navigate(['/shop']);
  this.eventEmitterService.onTypeChange(id, name);
}

search(event) {
 // console.log(event.target.value);
 this.eventEmitterService.onSearch(event.target.value);
}
}
