import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Cliente } from 'src/app/models/dtos/Cliente';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  private readonly apiUrl = 'http://localhost:8080/clientes';

  constructor(private http: HttpClient) {  }

  getClienteId(id: number){
    return this.http.get<Cliente>(this.apiUrl+"/"+id);
  }

  cleanClienteVenta(){
    sessionStorage.removeItem("clienteTemporal")
  }

  setClienteToStorage(cliente: Cliente): void {

    var newCliente: Cliente = new Cliente();
    newCliente = cliente
    var toJsonCliente = JSON.stringify(newCliente)
    sessionStorage.setItem("clienteTemporal", toJsonCliente)
  }


}
