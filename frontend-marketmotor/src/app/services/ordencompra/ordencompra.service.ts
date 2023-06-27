import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baserUrl from '../globalurl/UrlApi';

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






  guardarOrdenCompra(ordenCompra: any){
    return this.http.post(this.url, ordenCompra);
  }

}
