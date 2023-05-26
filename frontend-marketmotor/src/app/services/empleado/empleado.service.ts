import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EmpleadoService {
  
  private readonly apiUrl = 'http://localhost:8080';

  constructor(private Http: HttpClient) { }


  getAll() {
    return this.Http.get(`${this.apiUrl}/proveedores`)
  }

  actualizar(a:any) {
    return this.Http.put(`${this.apiUrl}/proveedores`,a)
  }
}
