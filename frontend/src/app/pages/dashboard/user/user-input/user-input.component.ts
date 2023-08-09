import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-input',
  templateUrl: './user-input.component.html',
  styleUrls: ['./user-input.component.scss']
})

export class UserInputComponent{

  userForm! : FormGroup

  constructor(private formBuilder: FormBuilder, private service:UserService, private router:Router){}

  ngOnInit(): void{
    this.userForm = this.formBuilder.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      phone: ['', [Validators.required, Validators.minLength(11), Validators.maxLength(13)]],
    })
  }

  save() {
    this.userForm.markAllAsTouched();
    if (this.userForm.valid) {
      this.service.save(this.userForm.value).subscribe((resp) => {
        if (resp.status == 200) {
          console.log('berhasil');
          this.router.navigate(['/', 'dashboard', 'user']);
        }
      });
    }
  }
}
