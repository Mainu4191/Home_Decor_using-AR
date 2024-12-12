import { Component, ViewChild } from '@angular/core';

import { NgForm } from '@angular/forms';

import { AdminLoginService } from './../../Services/admin-login.service';
import { TokenCheckService } from './../../Services/token-check.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  @ViewChild('loginForm') loginForm: NgForm | undefined;



  constructor(
    private adminLoginService: AdminLoginService,
    private tokenCheckService: TokenCheckService,
    private router: Router
  ) { }



  onSubmit() {
    console.log(this.loginForm?.value);

    this.tokenCheckService.removeToken();
    this.adminLoginService.loginAdmin(this.loginForm?.value).subscribe({
      next: (result: any) => {
        console.log(result);
        console.log(result.jwt);

        if (result.status == "Success") {
          console.log("Login Success");
          if (this.tokenCheckService.setToken(result.jwt)) {
            this.router.navigate(['dashboard']);
          }




        }
      },
      error: (error: any) => {
        console.log(error);
        alert("Login Failed");
      },

      complete: () => {
        console.log("Completed");
      }
    });
  }



}






