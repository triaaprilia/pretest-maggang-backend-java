import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '../user.service';
import { User } from 'src/app/model/user';

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.scss']
})
export class UserDetailComponent implements OnInit{

  userId!:string;
  user!:User;

  constructor(
    private actiatedRoute: ActivatedRoute,
    private service: UserService){}

  ngOnInit(): void {

      this.actiatedRoute.params.subscribe(param => {
        this.userId = param['id'];
      })
      this.findById();
  }

  findById(){
    this.service.findById(Number(this.userId)).subscribe(resp =>{
      this.user = resp.body!;
      console.log(`status code : ${resp.status}`)
      console.log(this.user)
    })
  }
}
