import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class OrdencompraService {

  constructor() { }


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

}
