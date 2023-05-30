import { Empleado } from './../../models/dtos/Empleado';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EmpleadoService {

  private readonly apiUrl = 'http://localhost:8080/empleados';

  constructor(private Http: HttpClient) {  }



  getAll() {
    return this.Http.get(`${this.apiUrl}`)
  }

  getEmpleado(){
    return this.Http.get<Empleado[]>(this.apiUrl);
  }

  actualizar(a:any) {
    return this.Http.put(`${this.apiUrl}/empleados`,a)
  }

  createEmpleado(empleado: Empleado){
    return this.Http.post<Empleado>(this.apiUrl,empleado);
  }

  getEmpleadoId(id: number){
    return this.Http.get<Empleado>(this.apiUrl+"/"+id);
  }

  deleteEmpleado(empleado: Empleado){
    return this.Http.delete<Empleado>(this.apiUrl+"/"+empleado.id);
  }
}
