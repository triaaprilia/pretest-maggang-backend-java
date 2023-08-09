import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Category, CategoryInput } from 'src/app/model/category';
import { environment } from 'src/environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private httpclient: HttpClient) {}

  findAll(){
    return this.httpclient.get<Category[]>(`${environment.api}/api/category`, {observe: "response"});
  }

  findById(id: number){
    return this.httpclient.get<Category>(`${environment.api}/api/category/${id}`, {observe: "response"});
  }

  save(categoryInput: Category){
    return this.httpclient.post(`${environment.api}/api/category`, categoryInput, {observe: "response"});

  }
  update(id: number, categoryUpdate: CategoryInput){
    return this.httpclient.put(`${environment.api}/api/category`, categoryUpdate, {observe: "response"});
  }

  delete(id: number){
    return this.httpclient.delete(`${environment.api}/api/category/${id}`, {observe: "response"});
  }
}
