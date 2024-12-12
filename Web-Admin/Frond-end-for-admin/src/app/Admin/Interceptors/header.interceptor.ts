import { HttpInterceptor } from '@angular/common/http';
import { TokenCheckService } from '../Services/token-check.service';
import { inject } from '@angular/core';




export class headerInterceptor implements HttpInterceptor {

  tokenService = inject(TokenCheckService);

  intercept(req: any, next: any) {
    // console.log('headerInterceptor', req);

    const token = this.tokenService.getToken();

    if (token) {
      const newReq = req.clone({
        headers: req.headers.set('Authorization', 'Bearer ' + token)
      });

      // console.log('headerInterceptor', newReq);
      return next.handle(newReq);
    }








    return next.handle(req);
  }
}




