import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [{
   path: '', redirectTo: '/home', pathMatch: 'full',
},
   { path: 'client-pages', loadChildren: () => import('./client-pages/client-pages.module').then(m => m.ClientPagesModule) },
   { path: 'admin-pages', loadChildren: () => import('./admin-pages/admin-pages.module').then(m => m.AdminPagesModule) }];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
