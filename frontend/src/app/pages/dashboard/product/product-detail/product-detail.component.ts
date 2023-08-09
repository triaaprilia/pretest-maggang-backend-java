import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProductService } from '../product.service';
import { Product } from 'src/app/model/product';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.scss']
})
export class ProductDetailComponent implements OnInit{

  productId!:string;
  product!:Product;

  constructor(
    private actiatedRoute: ActivatedRoute,
    private service: ProductService){}

  ngOnInit(): void {

      this.actiatedRoute.params.subscribe(param => {
        this.productId = param['id'];
      })
      this.findById();
  }

  findById(){
    this.service.findById(Number(this.productId)).subscribe(resp => {
      this.product = resp.body!;
      console.log(`status code : ${resp.status}`)
      console.log(this.product)
    })
  }
}
