
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Usuario } from 'src/app/models/dtos/Usuario';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  constructor(private http:HttpClient) { }
  url = 'http://localhost:8080/usuarios';

  getUsuario(){
    return this.http.get<Usuario[]>(this.url);
  }

  createUsuario(){
    return this.http.post<Usuario>(this.url,Usuario);
  }

  getUsuarioId(id: number){
    return this.http.get<Usuario>(this.url+'/'+id);
  }

  updateUsuario(id: number, Usuario: Usuario){
    return this.http.put<Usuario>(this.url,Usuario);
  }

  deleteUsuario(Usuario: Usuario){
    return this.http.delete<Usuario>(this.url+"/"+Usuario.id);
  }
}
