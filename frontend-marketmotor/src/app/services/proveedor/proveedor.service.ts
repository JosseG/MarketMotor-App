import { Proveedor } from './../../models/dtos/Proveedor'
import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ProveedorInsert } from 'src/app/models/commands/proveedor/ProveedorInsert'

@Injectable({
  providedIn: 'root'
})
export class ProveedorService {

  private readonly apiUrl = 'http://localhost:8080/proveedores';

  constructor(private http:HttpClient) { }
 

  getAll() {
    return this.http.get(`${this.apiUrl}`)
  }

  getProveedor(){
    return this.http.get<Proveedor[]>(this.apiUrl);
  }

  createProveedor(proveedor: ProveedorInsert){
    return this.http.post<ProveedorInsert>(this.apiUrl,proveedor);
  }

  getProveedorId(id: number){
    return this.http.get<Proveedor>(this.apiUrl+'/'+id);
  }
// actualizar este metodo
  updateProveedor(id: number, proveedor: Proveedor){
    return this.http.put<Proveedor>(this.apiUrl,proveedor);
  }

  deleteProveedor(proveedor: Proveedor){
    return this.http.delete<Proveedor>(this.apiUrl+"/"+proveedor.id);
  }

  getAllByPaginable(pageNo: number = 0,pageSize: number = 10, sortBy: string= "id",sortDir: string="asc"){
    return this.http.get(`${this.apiUrl}/pagination`,{
      params: new HttpParams().set('pageNo', pageNo).set('pageSize',pageSize)
    })
  }

  
  setProveedorToStorage(proveedor: Proveedor): void {

    var newProveedor: Proveedor = new Proveedor();
    newProveedor = proveedor
    var toJsonProveedor= JSON.stringify(newProveedor)
    sessionStorage.setItem("proveedorTemporal", toJsonProveedor)
  }



}
