import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ProductService } from '../product.service';
import { Product } from 'src/app/model/product';
import { Category } from 'src/app/model/category';
import { CategoryService } from '../../category/category.service';

@Component({
  selector: 'app-product-update',
  templateUrl: './product-update.component.html',
  styleUrls: ['./product-update.component.scss']
})
export class ProductUpdateComponent implements OnInit{

  productUpdate!: FormGroup;
  productId!: any;
  product!: Product;
  category: Category[] = [];

  constructor(
    private actiatedRoute: ActivatedRoute,
    private service: ProductService,
    private formBuilder: FormBuilder,
    private router: Router,
    private categoryservice: CategoryService
    ){}

  ngOnInit(): void {
    this.actiatedRoute.params.subscribe(param => {
      this.productId = param['id'];
    })
    this.findById();
    this.productUpdate = this.formBuilder.group({
      name: this.formBuilder.control(null),
      categoryId: this.formBuilder.control(null),
      stock: this.formBuilder.control(null),
      price: this.formBuilder.control(null),

    })
    this.loadCategories();
  }
  findById(){
    this.service.findById(Number(this.productId)).subscribe(resp => {

      this.product=resp.body!;
      this.productUpdate.patchValue({
        name: this.product.name,
        categoryId: this.product.categoryId,
        stock: this.product.stock,
        price: this.product.price
      })
    })
  }
  loadCategories() {
    this.categoryservice.findAll().subscribe(
      (response) => {
        this.category = response.body || [];
      },
      (error) => {
        console.error('Failed to load categories:', error);
      }
    );
  }

  Update(){
    console.log(this.productUpdate.value);
    this.service.update(this.productId,this.productUpdate.value).subscribe(resp => {
      if(resp.status == 200){
        console.log('berhasil')
        this.router.navigate(['/', 'dashboard', 'product']);
      }
    })
  }
}
