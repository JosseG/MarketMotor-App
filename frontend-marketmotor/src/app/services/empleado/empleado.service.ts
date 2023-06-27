import { Empleado } from './../../models/dtos/Empleado';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { EmpleadoInsert } from 'src/app/models/commands/empleado/EmpleadoInsert';
import { EmpleadoUpdate } from 'src/app/models/commands/empleado/EmpleadoUpdate';
import baserUrl from '../globalurl/UrlApi';

@Injectable({
  providedIn: 'root'
})
export class EmpleadoService {


  private readonly apiUrl = baserUrl+"/empleados";

  constructor(private http: HttpClient) {  }



  getAll() {
    return this.http.get(`${this.apiUrl}`)
  }

  getEmpleado(){
    return this.http.get<Empleado[]>(this.apiUrl);
  }

  actualizar(empleado:EmpleadoUpdate) {
    return this.http.put(this.apiUrl,empleado)
  }

  createEmpleado(empleado: EmpleadoInsert){
    return this.http.post<EmpleadoInsert>(this.apiUrl,empleado);
  }

  getEmpleadoId(id: number){
    return this.http.get<Empleado>(this.apiUrl+"/"+id);
  }

  getEmpleadoByUserAlias(alias: string){
    return this.http.get<Empleado>(this.apiUrl+"/usuario/"+alias);
  }

  deleteEmpleado(empleado: Empleado){
    return this.http.delete<Empleado>(this.apiUrl+"/"+empleado.id);
  }

  getAllByPaginable(pageNo: number = 0,pageSize: number = 10, sortBy: string= "id",sortDir: string="asc"){
    return this.http.get(`${this.apiUrl}/pagination`,{
      params: new HttpParams().set('pageNo', pageNo).set('pageSize',pageSize)
    })
  }

  setEmpleadoToSession(empleado: Empleado){
    sessionStorage.setItem("Empleado",JSON.stringify(empleado))
  }

  getEmpleadoFromSession():Empleado{
    var sessionItem = sessionStorage.getItem("Empleado")
    var empleado:Empleado = JSON.parse(sessionItem ?? "")
    return empleado
  }

  borrarLogicEmpleado(id: number){
    return this.http.patch(this.apiUrl +"/" + id,null)
  }



}
