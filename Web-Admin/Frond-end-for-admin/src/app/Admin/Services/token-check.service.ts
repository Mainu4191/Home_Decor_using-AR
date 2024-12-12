import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TokenCheckService {

  localStorage: any;
  token: any;

  constructor() {
    console.log("TokenCheckService");
  }



  checkToken() {

  

    try {
      this.token = localStorage.getItem('token');
    }
    catch (error) {
      return false;
    }




    if (this.token) {
      return true;
    }
    return false;

  }

  setToken(token: any) {
    try {
      localStorage.setItem('token', token);
      return true;
    }
    catch (error) {
      return false;
    }

  }

  getToken() {
    return localStorage.getItem('token');
  }

  removeToken() {
    localStorage.removeItem('token');
    return true;
  }

}
