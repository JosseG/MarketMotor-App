import { Injectable } from '@angular/core';
import { Cliente } from 'src/app/models/dtos/Cliente';
import { ClienteService } from '../cliente/cliente.service';

@Injectable({
  providedIn: 'root'
})
export class VentaService {

  constructor(private clienteService: ClienteService) { }



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


  setCliente(cliente: Cliente): void {

    var newCliente: Cliente = new Cliente();
    newCliente = cliente
    var toJsonCliente = JSON.stringify(newCliente)
    sessionStorage.setItem("clienteTemporal", toJsonCliente)
  }


}
