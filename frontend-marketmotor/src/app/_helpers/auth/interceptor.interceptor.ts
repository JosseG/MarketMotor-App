import { HTTP_INTERCEPTORS, HttpEvent, HttpErrorResponse } from '@angular/common/http';
import { Injectable,Injector } from '@angular/core';
import { HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';

import { BehaviorSubject, Observable, throwError } from 'rxjs';
import { catchError, filter, switchMap, take } from 'rxjs/operators';
import { StorageService } from 'src/app/services/storage/storage.service';
import { AuthService } from 'src/app/services/auth/auth.service';

const TOKEN_HEADER_KEY = 'bearer';    

@Injectable()
export class InterceptorAuth implements HttpInterceptor {

  private isRefreshing = false;
  private refreshTokenSubject: BehaviorSubject<any> = new BehaviorSubject<any>(null);

  constructor(private tokenService: StorageService, private authService: AuthService,private inject:Injector) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<Object>> {
    /*let authReq = req;
    const token = this.tokenService.getToken();
    if (token != null) {
      console.log(token)
      authReq = this.addTokenHeader(req, token);
    }*/

    let jwtToken = req.clone({
      setHeaders: {
        Authorization: 'Bearer '+this.tokenService.getToken()
      }
    });
    return next.handle(jwtToken);

    /*return next.handle(authReq).pipe(catchError(error => {
      if (error instanceof HttpErrorResponse && !authReq.url.includes('auth/signin') && error.status === 401) {
        console.log("no creoa");
        return this.handle401Error(authReq, next);
      }

      return throwError(error);
    }));*/
  }

  private handle401Error(request: HttpRequest<any>, next: HttpHandler) {
    if (!this.isRefreshing) {
      this.isRefreshing = true;
      this.refreshTokenSubject.next(null);

      const token = this.tokenService.getRefreshToken();

      if (token)
        return this.authService.refreshToken(token).pipe(
          switchMap((token: any) => {
            this.isRefreshing = false;

            this.tokenService.saveToken(token.accessToken);
            this.refreshTokenSubject.next(token.accessToken);
            
            return next.handle(this.addTokenHeader(request, token.accessToken));
          }),
          catchError((err) => {
            this.isRefreshing = false;
            console.log("refrescando")
            this.tokenService.signOut();
            return throwError(err);
          })
        );
    }

    return this.refreshTokenSubject.pipe(
      filter(token => token !== null),
      take(1),
      switchMap((token) => next.handle(this.addTokenHeader(request, token)))
    );
  }

  private addTokenHeader(request: HttpRequest<any>, token: string) {
    /* for Spring Boot back-end */
    console.log("fajkdsjfasjdfsafdfdasdfsa")
    return request.clone({ headers: request.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + token) });

    /* for Node.js Express back-end */
    /*return request.clone({ headers: request.headers.set(TOKEN_HEADER_KEY, token) });*/
  }
}
export const authInterceptorProviders = [
  { provide: HTTP_INTERCEPTORS, useClass: InterceptorAuth, multi: true }
];