import { Injectable } from '@angular/core';
import baserUrl from '../globalurl/UrlApi';
import { HttpClient, HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class EmailService {

  private readonly apiUrl = baserUrl+"/emails";

  constructor(private http: HttpClient) {  }


  enviarAviso(id:number){
    return this.http.get("localhost:8080/emails/sendEmail/"+id)
  }

}
