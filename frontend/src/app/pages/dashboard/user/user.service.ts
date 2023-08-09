import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Product } from 'src/app/model/product';
import { LoginInput, User, userInput } from 'src/app/model/user';
import { environment } from 'src/environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpclient:HttpClient) { }

  findAll(){
    return this.httpclient.get<User[]>(`${environment.api}api/user`,{observe: "response"});
  }

  findById(id: number){
    return this.httpclient.get<User>(`${environment.api}api/user/${id}`,{observe: "response"});
  }
  findProduct(){
    return this.httpclient.get<Product[]>(`${environment.api}/api/product`, {observe: "response"});
  }

  save(userInput: User){
    return this.httpclient.post(`${environment.api}api/user`, userInput, { observe: "response" });
  }

  update(id: number, userUpdate: userInput){
    return this.httpclient.put(`${environment.api}api/user/${id}`, userUpdate,{observe: "response"});
  }
  login(loginInput: LoginInput){
    return this.httpclient.post(`${environment.api}api/user/login`, loginInput, { observe: "response" });
  }

  delete(id: number){
    return this.httpclient.delete(`${environment.api}api/user/${id}`,{observe: "response"});
  }
}
