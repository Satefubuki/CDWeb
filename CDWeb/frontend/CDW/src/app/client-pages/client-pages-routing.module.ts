import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import {SignupComponent} from './signup/signup.component';
import {CartComponent} from './cart/cart.component';
import { LoginComponent } from './login/login.component';
import {ShopComponent} from './shop/shop.component'
import { DetailComponent } from './detail/detail.component';
import { ProfileComponent } from './profile/profile.component';
import { OrderUserComponent } from './order-user/order-user.component';
import { UnauthorizedComponent } from './unauthorized/unauthorized.component';
import { AuthGuard } from './../_guard/auth.guard';


const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'register', component: SignupComponent },
  { path: 'profile', component: ProfileComponent , canActivate: [AuthGuard]},
  { path: 'login', component: LoginComponent},
  { path: 'cart', component: CartComponent },
  { path: 'shop', component: ShopComponent },
  { path: 'shop/:id', component: ShopComponent },
  { path: 'shop/product/:id', component: DetailComponent },
  { path: 'shop/product/:id', component: DetailComponent },
  { path: 'user-order', component: OrderUserComponent , canActivate: [AuthGuard]},
  { path: 'unauthorized', component: UnauthorizedComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientPagesRoutingModule { }
