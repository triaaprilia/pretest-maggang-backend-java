import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserComponent } from './user/user.component';
import { ProductComponent } from './product/product.component';
import { CategoryComponent } from './category/category.component';
import { UserDetailComponent } from './user/user-detail/user-detail.component';
import { ProductDetailComponent } from './product/product-detail/product-detail.component';
import { UserInputComponent } from './user/user-input/user-input.component';
import { UserUpdateComponent } from './user/user-update/user-update.component';
import { ProductInputComponent } from './product/product-input/product-input.component';
import { CategoryDetailComponent } from './category/category-detail/category-detail.component';
import { CategoryInputComponent } from './category/category-input/category-input.component';
import { DashboardIndexComponent } from './dashboard-index/dashboard-index.component';
import { ProductUpdateComponent } from './product/product-update/product-update.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { DashboardUserComponent } from './dashboard-user/dashboard-user.component';


const routes: Routes =[
  {
    path: "",
    component: DashboardIndexComponent
  },
  {
    path: "user",
    component: UserComponent
  },
  {
    path: "user/add",
    component: UserInputComponent
  },
{
    path: "user/:id",
    component: UserDetailComponent
  },

  {
    path: "user/update/:id",
    component: UserUpdateComponent
  },

  {
    path: "product",
    component: ProductComponent
  },
  {
    path: "product/add",
    component: ProductInputComponent
  },

  {
    path: "product/:id",
    component: ProductDetailComponent
  },
  {
    path: "product/update/:id",
    component: ProductUpdateComponent
  },
  {
    path: "category",
    component: CategoryComponent
  },
  {
    path: "category/add",
    component: CategoryInputComponent
  },
  {
    path: "category/:id",
    component: CategoryDetailComponent
  },
  {
    path: "dashboard",
    component: DashboardUserComponent
  },


];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DashboardRoutingModule { }
