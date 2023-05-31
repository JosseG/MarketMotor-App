import { HttpClient,HttpHeaders  } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';

const AUTH_API = 'http://localhost:8080/auth/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http:HttpClient) { }

  login(alias: string, contrasena: string):Observable<any> {
    return this.http.post(
      AUTH_API + 'signin',
      {
        alias,
        contrasena,
      },
      httpOptions
    );
  }

  /*register(username: string, email: string, password: string): Observable<any> {
    return this.http.post(
      AUTH_API + 'signup',
      {
        username,
        email,
        password,
      }
    );
  }*/

  logout(): Observable<any> {
    return this.http.post(AUTH_API + 'signout', { },httpOptions);
  }


  refreshToken(token: string) {
    return this.http.post(AUTH_API + 'refresh-token', {
      refreshToken: token
    },httpOptions);
  }

}
