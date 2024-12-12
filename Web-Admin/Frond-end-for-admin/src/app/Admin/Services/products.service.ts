import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { EnvService } from '../../../env';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  env = new EnvService();
  url: string = this.env.API_URL + '/api/product/';

  constructor(
    private http: HttpClient
  ) { }


  createProduct(data: any) {
    return this.http.post(this.url + 'add', data);
  }


  updateProduct(data: any) {
    return this.http.post(this.url + 'update', data);
  }

  deleteProduct(data: any) {
    return this.http.post(this.url + 'delete', data);
  }

  getAllProduct() {
    return this.http.get(this.url + 'get-all');
  }
 
  getProductById(data: any) {
    return this.http.get(this.url + 'get-by-id/' + data);
  }


}
