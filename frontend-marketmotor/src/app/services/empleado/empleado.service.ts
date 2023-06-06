import { Empleado } from './../../models/dtos/Empleado';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { EmpleadoInsert } from 'src/app/models/commands/empleado/EmpleadoInsert';
import { EmpleadoUpdate } from 'src/app/models/commands/empleado/EmpleadoUpdate';

@Injectable({
  providedIn: 'root'
})
export class EmpleadoService {
  save(values: any) {
    throw new Error('Method not implemented.');
  }

  private readonly apiUrl = 'http://localhost:8080/empleados';

  constructor(private http: HttpClient) {  }



  getAll() {
    return this.http.get(`${this.apiUrl}`)
  }

  getEmpleado(){
    return this.http.get<Empleado[]>(this.apiUrl);
  }

  actualizar(empleado:EmpleadoUpdate) {
    return this.http.put(`${this.apiUrl}/empleados`,empleado)
  }

  createEmpleado(empleado: EmpleadoInsert){
    return this.http.post<EmpleadoInsert>(this.apiUrl,empleado);
  }

  getEmpleadoId(id: number){
    return this.http.get<Empleado>(this.apiUrl+"/"+id);
  }

  deleteEmpleado(empleado: Empleado){
    return this.http.delete<Empleado>(this.apiUrl+"/"+empleado.id);
  }

  getAllByPaginable(pageNo: number = 0,pageSize: number = 10, sortBy: string= "id",sortDir: string="asc"){
    return this.http.get(`${this.apiUrl}/pagination`,{
      params: new HttpParams().set('pageNo', pageNo).set('pageSize',pageSize)
    })
  }
}
