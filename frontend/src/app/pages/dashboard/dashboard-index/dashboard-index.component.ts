import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { faShoppingCart } from '@fortawesome/free-solid-svg-icons';
import { Product } from 'src/app/model/product';
import { User } from 'src/app/model/user';
import { UserService } from '../user/user.service';

@Component({
  selector: 'app-dashboard-index',
  templateUrl: './dashboard-index.component.html',
  styleUrls: ['./dashboard-index.component.scss']
})
export class DashboardIndexComponent implements OnInit{

  faShoppingCart = faShoppingCart;

  productList: Product[] = [];
  product!: Product[];
  userId!: String;

  constructor(
    // private localStorageService: LocalStorageService,
    private service: UserService,
    private activateRoute: ActivatedRoute,
  ){}

  ngOnInit(): void {
    // this.product = this.localStorageService.getItem('cart') || [];
    this.activateRoute.params.subscribe(param => {
      this.userId = param['id']
    })
    this.getProducts();
  }

  getProducts(){
    this.service.findProduct().subscribe((resp)=>{
      this.productList = resp.body!;
      console.log(`status code : ${resp.status}`)
      console.log(this.productList)
    })
  }
}
