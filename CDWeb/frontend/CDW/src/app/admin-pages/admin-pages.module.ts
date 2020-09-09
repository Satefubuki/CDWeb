import { NgModule, CUSTOM_ELEMENTS_SCHEMA  } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AdminPagesRoutingModule } from './admin-pages-routing.module';
import { AdminActionComponent } from './admin-action/admin-action.component';
import { 
	IgxButtonModule,
	IgxIconModule,
	IgxNavigationDrawerModule,
	IgxRippleModule,
	IgxToggleModule
 } from "igniteui-angular";
import { ProductAdminComponent } from './product-admin/product-admin.component';
import { UserAdminComponent } from './user-admin/user-admin.component';
import { OrderAdminComponent } from './order-admin/order-admin.component';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { ModalModule } from 'ngx-bootstrap/modal';
import {PaginationComponent} from './../parts/pagination/pagination/pagination.component';
import { LogComponent } from './log/log.component';


@NgModule({
  declarations: [AdminActionComponent, ProductAdminComponent, UserAdminComponent, OrderAdminComponent, PaginationComponent, LogComponent],
  imports: [
    CommonModule,
    AdminPagesRoutingModule,
    BrowserModule,
    BrowserAnimationsModule,
    IgxButtonModule,
    IgxIconModule,
    IgxNavigationDrawerModule,
    IgxRippleModule,
    IgxToggleModule,
    NgxDatatableModule,
    ReactiveFormsModule,
    ModalModule.forRoot(),
    FormsModule,
    
  ],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ]
})
export class AdminPagesModule { }
