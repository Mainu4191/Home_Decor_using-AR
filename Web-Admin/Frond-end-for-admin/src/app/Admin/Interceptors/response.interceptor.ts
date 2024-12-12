import { HttpEvent, HttpInterceptorFn } from '@angular/common/http';
import { TokenCheckService } from '../Services/token-check.service';
import { inject } from '@angular/core';
import { Router } from '@angular/router';

import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

@Injectable()
export class responseInterceptor implements HttpInterceptor {
  constructor(
    private router: Router,

  ) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    console.log('responseInterceptor');

    return next.handle(req).pipe(
      tap(
        event => {
          if (event instanceof HttpResponse) {
            // console.log('event--->>>', event);
          }
        },
        error => {
        
          // console.log('error--->>>', error);
          if (error.status === 403 || error.status === 401) {
            this.router.navigate(['/login']);
          }
        }
      )
    );





  }
}



// intercept(req: any, next: any) {

//   console.log('responseInterceptor');


// if response is 403, then redirect to login page
// if response is 401, then redirect to login page
// if response is 200, then return response as it is

// return next.handle(req).subscribe({
//   next: (data: any) => {

//     console.log("data", data);






//     return data;
//   },
//   error: (err: any) => {
//     console.log(err);
//     if (err.status === 403 || err.status === 401) {
//       const router = inject(Router);
//       router.navigate(['/login']);
//     }
//   },
//   complete: () => {
//     console.log("Res Completed");
//   }

// });

// return next.handle(req);

// }
// }