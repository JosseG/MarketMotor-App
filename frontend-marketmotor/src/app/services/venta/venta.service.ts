import { Injectable } from '@angular/core';
import { Cliente } from 'src/app/models/dtos/Cliente';
import { ClienteService } from '../cliente/cliente.service';
import { HttpClient } from '@angular/common/http';
import baserUrl from '../globalurl/UrlApi';

@Injectable({
  providedIn: 'root'
})
export class VentaService {

  private readonly url = baserUrl+"/ventas";
  constructor(private clienteService: ClienteService,private http:HttpClient) { }



  setInactiveVenta(): void {
    sessionStorage.setItem("activeVenta", "false")
  }


  isActiveVenta(): boolean {
    var active = sessionStorage.getItem("activeVenta")

    var isActive = false
    if (active != null) {
      isActive = JSON.parse(active!)
      if (isActive) {
        return true;
      } else {
        return false;
      }
    } else {
      sessionStorage.setItem("activeVenta", "false")
      return false
    }
  }


  setActiveVenta(): void {
    sessionStorage.setItem("activeVenta", "true")
  }

  guardarVenta(venta: any){
    return this.http.post(this.url, venta);
  }


  realizarVentaTransaccion(venta: any, detalles:any[]){
    var newObject:any = new Object();
    newObject.venta = venta;
    newObject.detallesVenta = detalles;
    return this.http.post<boolean>(this.url+"/realizarVenta",newObject)
  }


}
