import { Component, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ProductService } from '../product.service';
import { Category } from 'src/app/model/category';
import { CategoryService } from '../../category/category.service';

@Component({
  selector: 'app-product-input',
  templateUrl: './product-input.component.html',
  styleUrls: ['./product-input.component.scss']
})

export class ProductInputComponent implements OnInit{

  productForm!: FormGroup;
  category: Category[] = [];

  constructor(private formBuilder: FormBuilder, private categoryService: CategoryService , private service:ProductService, private router: Router){}

  ngOnInit(): void {
    this.productForm = this.formBuilder.group({
      name: this.formBuilder.control(null),
      categoryId: this.formBuilder.control(null),
      stock: this.formBuilder.control(null),
      price: this.formBuilder.control(null),
    })
    this.loadCategories();
  }

  loadCategories() {
    this.categoryService.findAll().subscribe(
      (response) => {
        this.category = response.body || [];
      },
      (error) => {
        console.error('Failed to load categories:', error);
      }
    );
  }

  save(){
    console.log(this.productForm.value)
    this.productForm.value.categoryId = +  this.productForm.value.categoryId
    this.service.save(this.productForm.value).subscribe(resp => {
      if(resp.status == 200){
        console.log('berhasil')
        console.log(this.productForm.value)
        this.router.navigate(['/', 'dashboard', 'product'])
      }
    })
  }
}
