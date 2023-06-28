import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baserUrl from '../globalurl/UrlApi';
import { __param } from 'tslib';
import { DetalleOrdenCompra } from 'src/app/models/dtos/DetalleOrdenCompra';

@Injectable({
  providedIn: 'root'
})
export class OrdencompraService {

  private readonly url = baserUrl+"/ordencompra"

  constructor(private http:HttpClient) { }


  setInactiveOrden(): void{
    sessionStorage.setItem("activeOrden","false")
  }


  isActiveOrden() : boolean{
    var active = sessionStorage.getItem("activeOrden")

    var isActive = false
    if(active!=null){
      isActive = JSON.parse(active!)
      if(isActive){
        return true;
      }else{
        return false;
      }
    }else{
      sessionStorage.setItem("activeOrden","false")
      return false
    }
  }


  setActiveOrden(): void{
    sessionStorage.setItem("activeOrden","true")
  }






  getAllByPaginable(pageNo: number = 0,pageSize: number = 10, sortBy: string= "id",sortDir: string="asc"){
    return this.http.get(`${this.url}/pagination`,{
      params: new HttpParams().set('pageNo', pageNo).set('pageSize',pageSize)
    })
  }


  getAllPendientesByPaginable(pageNo: number = 0,pageSize: number = 10, sortBy: string= "id",sortDir: string="asc"){
    return this.http.get(`${this.url}/pendientes/pagination`,{
      params: new HttpParams().set('pageNo', pageNo).set('pageSize',pageSize)
    })
  }



  confirmarOrden(id:number, objects: DetalleOrdenCompra[]){
    return this.http.post(`${this.url}/confirmar`,objects,  {
      params: new HttpParams().set('id', id)
    })
  }




  guardarOrdenCompra(ordenCompra: any){
    return this.http.post(this.url, ordenCompra);
  }


  realizarOrdenCompraTransaccion(ordencompra: any, detalles:any[]){
    var newObject:any = new Object();
    newObject.ordenCompra = ordencompra;
    newObject.detallesOrdenCompra = detalles;
    return this.http.post<boolean>(this.url+"/realizarOrdenCompra",newObject)
  }

}
