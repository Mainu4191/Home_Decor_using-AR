import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { TokenCheckService } from '../Services/token-check.service';

import { HttpClient } from '@angular/common/http';
@Component({
  selector: 'app-dashboard-layout',
  templateUrl: './dashboard-layout.component.html',
  styleUrl: './dashboard-layout.component.css'
})
export class DashboardLayoutComponent {

  constructor(private router: Router, private tokenCheckService: TokenCheckService, private http: HttpClient
  ) { }



  logout() {
    console.log("logout");
    this.tokenCheckService.removeToken();
    this.router.navigate(['']);
  }


  test() {
    console.log("test");
    const API_URL = 'http://192.168.0.159:8080/api/hello';

    this.http.get(API_URL).subscribe((res) => {
      console.log(res);
    });

  }




}
