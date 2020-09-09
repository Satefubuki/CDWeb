import { NgModule,  CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ClientPagesRoutingModule } from './client-pages-routing.module';
import { HomeComponent } from './home/home.component';
import { SignupComponent } from './signup/signup.component';
import { CartComponent } from './cart/cart.component';
import {NavigationComponent} from './../parts/navigation/navigation.component';
import { LoginComponent } from './login/login.component';
import { ShopComponent } from './shop/shop.component';
import { DetailComponent } from './detail/detail.component';
import { ProfileComponent } from './profile/profile.component';
import { OrderUserComponent } from './order-user/order-user.component';
import { UnauthorizedComponent } from './unauthorized/unauthorized.component';


@NgModule({
  declarations: [HomeComponent, SignupComponent, CartComponent, NavigationComponent, LoginComponent, ShopComponent, DetailComponent, ProfileComponent, OrderUserComponent, UnauthorizedComponent],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    ClientPagesRoutingModule
  ],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ]
})
export class ClientPagesModule { }
