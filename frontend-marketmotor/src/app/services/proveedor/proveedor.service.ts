import { Proveedor } from './../../models/dtos/Proveedor'
import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ProveedorInsert } from 'src/app/models/commands/proveedor/ProveedorInsert'
import { ProveedorUpdate } from 'src/app/models/commands/proveedor/ProveedorUpdate';
import baserUrl from '../globalurl/UrlApi';

@Injectable({
  providedIn: 'root'
})
export class ProveedorService {

  private readonly apiUrl = baserUrl+"/proveedores";

  constructor(private http:HttpClient) { }


  getAll() {
    return this.http.get<Proveedor[]>(`${this.apiUrl}`)
  }

  getProveedor(){
    return this.http.get<Proveedor[]>(this.apiUrl);
  }

  createProveedor(proveedor: ProveedorInsert){
    return this.http.post<Proveedor>(this.apiUrl,proveedor);
  }

  getProveedorId(id: number){
    return this.http.get<Proveedor>(this.apiUrl+'/'+id);
  }
  updateProveedor(proveedor:any) {
    //return this.http.put<ProveedorUpdate>(`${this.apiUrl}/update`,proveedor)
    return this.http.put<Proveedor>(this.apiUrl,proveedor)
  }

  deleteProveedor(proveedor: Proveedor){
    return this.http.delete<Proveedor>(this.apiUrl+"/"+proveedor.id);
  }

  getAllByPaginable(pageNo: number = 0,pageSize: number = 10, sortBy: string= "id",sortDir: string="asc"){
    return this.http.get(`${this.apiUrl}/pagination`,{
      params: new HttpParams().set('pageNo', pageNo).set('pageSize',pageSize)
    })
  }
  borrarLogicProveedor(id: number){
    return this.http.patch(this.apiUrl +"/" + id,null)
  }



  setProveedorToStorage(proveedor: Proveedor): void {

    var newProveedor: Proveedor = new Proveedor();
    newProveedor = proveedor
    var toJsonProveedor= JSON.stringify(newProveedor)
    sessionStorage.setItem("proveedorTemporal", toJsonProveedor)
  }

  cleanProveedorService(){
    sessionStorage.removeItem("proveedorTemporal")
  }
}
