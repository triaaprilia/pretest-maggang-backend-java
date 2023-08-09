import { Component, OnInit } from '@angular/core';
import { CategoryService } from '../category.service';
import { ActivatedRoute } from '@angular/router';
import { Category } from 'src/app/model/category';

@Component({
  selector: 'app-category-detail',
  templateUrl: './category-detail.component.html',
  styleUrls: ['./category-detail.component.scss']
})
export class CategoryDetailComponent implements OnInit {

  categoryId!: Number;
  category!: Category;

  constructor(
    private actiatedRoute: ActivatedRoute,
    private service: CategoryService){}

  ngOnInit(): void {
    this.actiatedRoute.params.subscribe(param => {
      this.categoryId = param['id'];
    })
    this.findById();
  }

  findById(){
    this.service.findById(Number(this.categoryId)).subscribe(resp =>{
      this.category = resp.body!;
      console.log(`status code : ${resp.status}`)
      console.log(this.category)
    })
  }
}
