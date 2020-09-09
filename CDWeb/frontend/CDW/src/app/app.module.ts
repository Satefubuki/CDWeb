import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import {AppInterceptor} from './_interceptor/app.interceptor';
import { AuthGuard } from './_guard/auth.guard';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {ProductAdminModule} from './product-admin/product-admin.module';
import {AdminPagesModule } from './admin-pages/admin-pages.module';
import {ClientPagesModule} from './client-pages/client-pages.module';
import { ModalModule } from 'ngx-bootstrap/modal';
import {CookieService} from 'ngx-cookie-service';
import { EventEmitterService } from './services/event-emitter.service';
//import { NavigationComponent } from './parts/navigation/navigation.component';

@NgModule({
  declarations: [
    AppComponent,
    //NavigationComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ProductAdminModule,
    HttpClientModule,
    ModalModule.forRoot(),
    AdminPagesModule,
    ClientPagesModule
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: AppInterceptor,
    multi: true,
  },
  CookieService,
  AuthGuard,
  EventEmitterService
],
  bootstrap: [AppComponent]
})
export class AppModule { }
