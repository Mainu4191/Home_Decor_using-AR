import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { EnvService } from '../../../env';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  env = new EnvService();

  url: string = this.env.API_URL + '/api/product-category/';

  constructor(
    private http: HttpClient
  ) { }

  addProductCategory(data: any) {
    return this.http.post(this.url + 'add', data);
  }

  updateProductCategory(data: any) {
    return this.http.post(this.url + 'update', data);
  }

  deleteProductCategory(data: any) {
    return this.http.post(this.url + 'delete', data);
  }

  getAllProductCategory() {
    return this.http.get(this.url + 'get-all');
  }

  getProductCategoryById(data: any) {
    return this.http.get(this.url + 'get-by-id/' + data);
  }
  
}
