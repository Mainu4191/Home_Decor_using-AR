import { CanActivateFn } from '@angular/router';
import { Router } from '@angular/router';
import { TokenCheckService } from './token-check.service';
import { inject } from '@angular/core';





export const authGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);
  const tokenCheckService = inject(TokenCheckService);


  if (tokenCheckService.checkToken()) {
    return true;
  }
  router.navigate(['']);
  return true;
};
