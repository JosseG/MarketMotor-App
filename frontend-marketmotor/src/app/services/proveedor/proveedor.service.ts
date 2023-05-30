import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Proveedor } from 'src/app/models/dtos/Proveedor';

@Injectable({
  providedIn: 'root'
})
export class ProveedorService {

  constructor(private http:HttpClient) { }
  url = 'http://localhost:8080/proveedores';

  getProveedor(){
    return this.http.get<Proveedor[]>(this.url);
  }

  createProveedor(){
    return this.http.post<Proveedor>(this.url,Proveedor);
  }

  getProveedorId(id: number){
    return this.http.get<Proveedor>(this.url+'/'+id);
  }

  updateProveedor(id: number, proveedor: Proveedor){
    return this.http.put<Proveedor>(this.url,proveedor);
  }

  deleteProveedor(proveedor: Proveedor){
    return this.http.delete<Proveedor>(this.url+"/"+proveedor.id);
  }


}
