import { Injectable } from '@angular/core';
import baserUrl from '../globalurl/UrlApi';
import { HttpClient } from '@angular/common/http';
import { Rol } from 'src/app/models/dtos/Rol';

@Injectable({
  providedIn: 'root'
})
export class RolService {

  private readonly apiUrl = baserUrl+"/roles";

  constructor(private http:HttpClient) { }

  getAll() {
    return this.http.get<Rol[]>(`${this.apiUrl}`)
  }

}
