import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { EnvService } from '../../../env';

@Injectable({
  providedIn: 'root'
})
export class UsersService {
  env = new EnvService();

  API_URL = this.env.API_URL;



  constructor(
    private http: HttpClient
  ) { }



  getAllUsers() {
    return this.http.get(`${this.API_URL}/api/users/get-all`);
  }

  deleteUser(user: any) {
    return this.http.post(`${this.API_URL}/api/users/delete`, user);
  }
}
