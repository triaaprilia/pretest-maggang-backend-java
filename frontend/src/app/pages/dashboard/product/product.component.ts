import { Component, OnInit } from '@angular/core';
import { ProductService } from './product.service';
import { Product } from 'src/app/model/product';
import { faInfoCircle, faEdit } from '@fortawesome/free-solid-svg-icons';
import { faTrashAlt,  } from '@fortawesome/free-regular-svg-icons';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss']
})
export class ProductComponent implements OnInit{
  faInfoCircle = faInfoCircle;
  faTrashAlt = faTrashAlt;
  faEdit = faEdit

  productList: Product[] = [];

  constructor(private service: ProductService){
  }

  ngOnInit(): void {
      this.getTableList();
  }
  getTableList(){
    this.service.findAll().subscribe((resp) =>{
      this.productList = resp.body!;
      console.log(`status code : ${resp.status}`)
      console.log(this.productList)
    })
  }
  delete(id: number){
    console.log(id)
    this.service.delete(id).subscribe(resp =>{
      if (resp.status == 200){
        this.productList = this.productList.filter(data => data.id !=id)
      }
    })
  }
}

