import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { EnvService } from '../../../env';

@Injectable({
  providedIn: 'root'
})
export class AdminLoginService {
  env = new EnvService();
  API_URL = this.env.API_URL;
  constructor(private http: HttpClient) { }

  loginAdmin(adminLogin: any) {
    return this.http.post(`${this.API_URL}/admin/login`, adminLogin);
  }
}
