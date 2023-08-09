import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DashboardComponent } from './dashboard/dashboard.component';
import { UserComponent } from './user/user.component';
import { BrowserModule } from '@angular/platform-browser';
import { DashboardRoutingModule } from './dashboard-routing.module';
import { ProductComponent } from './product/product.component';
import { CategoryComponent } from './category/category.component';
import { HttpClientModule} from '@angular/common/http';
import { UserService } from './user/user.service';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { CategoryService } from './category/category.service';
import { ProductService } from './product/product.service';
import { UserDetailComponent } from './user/user-detail/user-detail.component';
import { ProductDetailComponent } from './product/product-detail/product-detail.component';
import { UserInputComponent } from './user/user-input/user-input.component';
import { ReactiveFormsModule } from '@angular/forms';
import { UserUpdateComponent } from './user/user-update/user-update.component';
import { ProductInputComponent } from './product/product-input/product-input.component';
import { ProductUpdateComponent } from './product/product-update/product-update.component';
import { CategoryDetailComponent } from './category/category-detail/category-detail.component';
import { CategoryInputComponent } from './category/category-input/category-input.component';
import { CategoryUpdateComponent } from './category/category-update/category-update.component';
import { DashboardIndexComponent } from './dashboard-index/dashboard-index.component';
import { DashboardUserComponent } from './dashboard-user/dashboard-user.component';




@NgModule({
  declarations: [
    DashboardComponent,
    UserComponent,
    ProductComponent,
    CategoryComponent,
    UserDetailComponent,
    ProductDetailComponent,
    UserInputComponent,
    UserUpdateComponent,
    ProductInputComponent,
    ProductUpdateComponent,
    CategoryDetailComponent,
    CategoryInputComponent,
    CategoryUpdateComponent,
    DashboardIndexComponent,
    DashboardUserComponent,

  ],
  imports: [
    CommonModule,
    DashboardRoutingModule,
    HttpClientModule,
    FontAwesomeModule,
    ReactiveFormsModule
  ],
  providers:[
    UserService,
    CategoryService,
    ProductService
  ]
})
export class DashboardModule { }
