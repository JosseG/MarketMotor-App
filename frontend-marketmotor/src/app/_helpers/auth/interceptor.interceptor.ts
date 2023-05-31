import { HTTP_INTERCEPTORS, HttpEvent  } from '@angular/common/http';
import { Injectable} from '@angular/core';
import { HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';

import {Observable } from 'rxjs';
import { StorageService } from 'src/app/services/storage/storage.service';

@Injectable()
export class InterceptorAuth implements HttpInterceptor {

  constructor(private tokenService: StorageService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<Object>> {

    let jwtToken = req.clone({
      setHeaders: {
        Authorization: 'Bearer '+this.tokenService.getToken()
      }
    });
    return next.handle(jwtToken);

  }
}
export const authInterceptorProviders = [
  { provide: HTTP_INTERCEPTORS, useClass: InterceptorAuth, multi: true }
];