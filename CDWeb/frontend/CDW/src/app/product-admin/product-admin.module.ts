import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProductAdminRoutingModule } from './product-admin-routing.module';
import { ProductComponent } from './product/product.component';


@NgModule({
  declarations: [ProductComponent],
  imports: [
    CommonModule,
    ProductAdminRoutingModule
  ]
})
export class ProductAdminModule { }
