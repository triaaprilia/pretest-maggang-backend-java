import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { User } from 'src/app/model/user';

@Component({
  selector: 'app-user-update',
  templateUrl: './user-update.component.html',
  styleUrls: ['./user-update.component.scss']
})
export class UserUpdateComponent implements OnInit{

  userId!:number;
  user!: User;
  userUpdate!: FormGroup;

  constructor(
    private actiatedRoute: ActivatedRoute,
    private service: UserService,
    private formBuilder: FormBuilder,
    private router: Router,
    ){}

  ngOnInit(): void {
    this.actiatedRoute.params.subscribe(param => {
      this.userId = param['id']
    })

    this.findById();
    this.userUpdate = this.formBuilder.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      phone: ['', [Validators.required, Validators.minLength(12)]],
    })
  }

  findById(){
    this.service.findById(Number(this.userId)).subscribe(resp => {

      this.user=resp.body!;
      this.userUpdate.patchValue({
        username: this.user.username,
        email: this.user.email,
        phone: this.user.phone
      })
    })
  }

  Update(){
    console.log(this.userUpdate.value);
    this.service.update(this.userId,this.userUpdate.value).subscribe(resp => {
      if(resp.status == 200){
        console.log('berhasil')
        this.router.navigate(['/', 'dashboard', 'user']);
      }
    })
  }

}
