import { Component, OnInit } from '@angular/core';
import { CategoryService } from './category.service';
import { faEdit, faInfoCircle, faTrashAlt } from '@fortawesome/free-solid-svg-icons';
import { Category } from 'src/app/model/category';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.scss']
})
export class CategoryComponent implements OnInit{
  faInfoCircle = faInfoCircle;
  faTrashAlt = faTrashAlt;
  faEdit = faEdit

  categoryList: Category[] = [];

  constructor(private service:CategoryService){

  }
  ngOnInit(): void{
    this.getTableList();
  }

  getTableList(){
    this.service.findAll().subscribe(resp =>{
      this.categoryList = resp.body!;
      console.log('status code : ${resp.status}')
      console.log(resp.body)
    })
  }

  delete(id: number){
    console.log(id)
    this.service.delete(id).subscribe(resp => {
      if (resp.status == 200){
        this.categoryList = this.categoryList.filter(data => data.id !=id)
      }
    })
  }
}
