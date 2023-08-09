import { Component, OnInit } from '@angular/core';
import { UserService } from './user.service';
import { Observable } from 'rxjs';
import { HttpResponse } from '@angular/common/http';
import { User } from 'src/app/model/user';
import { faInfoCircle, faEdit, faShoppingCart } from '@fortawesome/free-solid-svg-icons';
import { faTrashAlt,  } from '@fortawesome/free-regular-svg-icons';


@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {
  faInfoCircle = faInfoCircle;
  faTrashAlt = faTrashAlt;
  faEdit = faEdit;
  faShoppingCart = faShoppingCart;

  userList: User[] = [];

  constructor(private service:UserService){

  }
  ngOnInit(): void{
    this.getTableList();
  }

  getTableList(){
    this.service.findAll().subscribe(resp =>{
      this.userList = resp.body!;
      console.log('status code : ${resp.status}')
      console.log(resp.body)
    })
  }

  delete(id: number){
    console.log(id)
    this.service.delete(id).subscribe(resp => {
      if (resp.status == 200){
        this.userList = this.userList.filter(data => data.id != id)
      }
    })
  }
}
