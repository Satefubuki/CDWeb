import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AdminActionComponent} from './admin-action/admin-action.component';
import {UserAdminComponent} from './user-admin/user-admin.component';
import {ProductAdminComponent} from './product-admin/product-admin.component';
import {OrderAdminComponent} from './order-admin/order-admin.component';
import { AuthGuard } from './../_guard/auth.guard';
import { Role } from './../enum/Role';
import { LogComponent } from './log/log.component';

const routes: Routes = [
  {
    path: 'admin', component: AdminActionComponent, canActivate: [AuthGuard], data: {roles: [Role.Manager]} ,
    children: [
      { path: '', component: ProductAdminComponent},
      { path: 'user', component: UserAdminComponent},
      { path: 'order', component: OrderAdminComponent},
      { path: 'log', component: LogComponent},

    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminPagesRoutingModule { }
