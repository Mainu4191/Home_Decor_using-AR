import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { FormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { headerInterceptor } from './Admin/Interceptors/header.interceptor';
import { responseInterceptor } from './Admin/Interceptors/response.interceptor';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './Admin/Pages/login/login.component';
import { DashboardLayoutComponent } from './Admin/dashboard-layout/dashboard-layout.component';
import { CategoryComponent } from './Admin/Pages/category/category.component';
import { ProductsComponent } from './Admin/Pages/products/products.component';
import { UserslistComponent } from './Admin/Pages/userslist/userslist.component';
import { OrderComponent } from './Admin/Pages/order/order.component';
import { OrderDetailComponent } from './Admin/Pages/order-detail/order-detail.component';
import { DasboardPageComponent } from './Admin/Pages/dasboard-page/dasboard-page.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DashboardLayoutComponent,
    CategoryComponent,
    ProductsComponent,
    UserslistComponent,
    OrderComponent,
    OrderDetailComponent,
    DasboardPageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    provideClientHydration(),
    {
      provide: HTTP_INTERCEPTORS, useClass: headerInterceptor, multi: true
    },
    {
      provide: HTTP_INTERCEPTORS, useClass: responseInterceptor, multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
