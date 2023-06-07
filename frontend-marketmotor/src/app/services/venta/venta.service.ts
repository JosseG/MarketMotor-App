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


  setCliente(id: number): void {


    var clienteSession = sessionStorage.getItem("clienteTemporal")
    if (clienteSession == null) {
      var cliente: Cliente = new Cliente();


      this.clienteService.getClienteId(id).subscribe({
        next: (data: any) => {
          cliente = data

          var toJsonCliente = JSON.stringify(cliente)
          sessionStorage.setItem("clienteTemporal", toJsonCliente)
        },
        error: (e) => console.log(e)
      });

    } else {
      //var clienteObtained: Cliente = JSON.parse(clienteSession)
      var clienteForChangedata= new Cliente()
      this.clienteService.getClienteId(id).subscribe({
        next: (data: any) => {
          clienteForChangedata = data

          var toJsonCliente = JSON.stringify(clienteForChangedata)
          sessionStorage.setItem("clienteTemporal", toJsonCliente)
        },
        error: (e) => console.log(e)
      });
    }

  }
}
