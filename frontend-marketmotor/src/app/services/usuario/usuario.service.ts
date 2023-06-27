
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Usuario } from 'src/app/models/dtos/Usuario';
import baserUrl from '../globalurl/UrlApi';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  constructor(private http:HttpClient) { }
  url = baserUrl+"/usuarios";

  getUsuario(){
    return this.http.get<Usuario[]>(this.url);
  }

  createUsuario(usuario: Usuario){
    return this.http.post<Usuario>(this.url,usuario);
  }

  getUsuarioId(id: number){
    return this.http.get<Usuario>(this.url+'/'+id);
  }

  updateUsuario(id: number, usuario: Usuario){
    return this.http.put<Usuario>(this.url,usuario);
  }

  deleteUsuario(usuario: Usuario){
    return this.http.delete<Usuario>(this.url+"/"+usuario.id);
  }
}
