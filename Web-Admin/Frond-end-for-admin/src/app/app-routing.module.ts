import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent } from './Admin/Pages/login/login.component';
import { DashboardLayoutComponent } from './Admin/dashboard-layout/dashboard-layout.component';
import { CategoryComponent } from './Admin/Pages/category/category.component';
import { ProductsComponent } from './Admin/Pages/products/products.component';
import { UserslistComponent } from './Admin/Pages/userslist/userslist.component';
import { OrderComponent } from './Admin/Pages/order/order.component';
import { OrderDetailComponent } from './Admin/Pages/order-detail/order-detail.component';
import { DasboardPageComponent } from './Admin/Pages/dasboard-page/dasboard-page.component';

import { authGuard } from './Admin/Services/auth.guard';


const routes: Routes = [

  { path: '', component: LoginComponent },

  {
    path: 'dashboard', component: DashboardLayoutComponent, canActivate: [authGuard],
    children: [
      {
        path: '',
        component: DasboardPageComponent

      },
      {
        path: 'category',
        component: CategoryComponent

      },
      {
        path: 'products',
        component: ProductsComponent

      },
      {
        path: 'userslist',
        component: UserslistComponent

      },
      {
        path: 'orders',
        component: OrderComponent

      },
      {
        path: 'order-detail/:id',
        component: OrderDetailComponent

      }
    ]
  },
  { path: '**', redirectTo: '' }



];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

