import { Component, OnInit } from '@angular/core';
import { Cliente } from 'src/app/models/dtos/Cliente';
import { DetalleVenta } from 'src/app/models/dtos/DetalleVenta';
import { Empleado } from 'src/app/models/dtos/Empleado';
import { Producto } from 'src/app/models/dtos/Producto';
import { Venta } from 'src/app/models/dtos/Venta';
import { ClienteService } from 'src/app/services/cliente/cliente.service';
import { EmpleadoService } from '../../services/empleado/empleado.service';

@Component({
  selector: 'app-reportetemplateventa',
  templateUrl: './reportetemplateventa.component.html',
  styleUrls: ['./reportetemplateventa.component.css']
})
export class ReportetemplateventaComponent implements OnInit {


  constructor(private clienteService: ClienteService, private empleadoService: EmpleadoService){
    
  }

  ventas: DetalleVenta[] = []

  productoMasVendido = new Producto();
  clienteDestacado = new Cliente();
  empleadoDestacado = new Empleado();
  detalleConMontoMinimo = new DetalleVenta();
  detalleConMontoMaximo = new DetalleVenta();
  montoMinimo = 0;
  montoMaximo = 0;
  fechaInicio = new Date();
  fechaFin = new Date();





  map = new Map<number, DetalleVenta>();
  ngOnInit() {
    this.getListReport();
  }

  total = 0;

  getListReport() {
    var elementos = sessionStorage.getItem('reporteVentasFiltred');
    if (elementos == null) {
    } else {
      this.map = new Map(JSON.parse(elementos));
      this.ventas = Array.from(this.map.values())

      for (let i of this.ventas) {
        this.total += i.venta.preciototal
      }
    }

    this.getIdEmpleadoDestacado();
    this.calcularMontoMinimo();
    this.calcularMontoMaximo();
  }


  getIdEmpleadoDestacado():number {
    var hash = new Map();
    for (var i = 0; i < this.ventas.length; i++) {
      if (hash.has(this.ventas[i].venta.empleado.id))
        hash.set(this.ventas[i].venta.empleado.id, hash.get(this.ventas[i].venta.empleado.id) + 1)
      else
        hash.set(this.ventas[i].venta.empleado.id, 1)
    }

    // find the max frequency
    var max_count = 0, res = -1;
    hash.forEach((value, key) => {

      if (max_count < value) {
        res = key;
        max_count = value;
      }

    });
    console.log("Empleado destacado")
    console.log(res)
    return res;
  }







  getIdClienteDestacado():number {
    var hash = new Map();
    for (var i = 0; i < this.ventas.length; i++) {
      if (hash.has(this.ventas[i].venta.cliente.id))
        hash.set(this.ventas[i].venta.cliente.id, hash.get(this.ventas[i].venta.cliente.id) + 1)
      else
        hash.set(this.ventas[i].venta.cliente.id, 1)
    }

    // find the max frequency
    var max_count = 0, res = -1;
    hash.forEach((value, key) => {

      if (max_count < value) {
        res = key;
        max_count = value;
      }

    });
    console.log("Cliente destacado")
    console.log(res)
    return res;
  }

  calcularMontoMinimo():number {


    let res = this.ventas[0].venta.preciototal;
      
    for(let i = 1; i < this.ventas.length; i++){

      res = Math.min(res, this.ventas[i].venta.preciototal);
    }
    
    console.log("Monto mínimo")
    this.montoMinimo = res
    console.log(res)
    return res;

    /*var hash = new Map();
    for (var i = 0; i < this.ventas.length; i++) {
      if (hash.has(this.ventas[i].venta.preciototal))
        hash.set(this.ventas[i].venta.preciototal, hash.get(this.ventas[i].venta.preciototal) + 1)
      else
        hash.set(this.ventas[i].venta.preciototal, 1)
    }

    // find the max frequency
    var max_count = 0, res = -1;
    hash.forEach((value, key) => {

      if (max_count < value) {
        res = key;
        max_count = value;
      }

    });
    console.log("Monto destacado")
    console.log(res)
    return res;*/
  }

  calcularMontoMaximo():number {
    let res = this.ventas[0].venta.preciototal;
      
    for(let i = 1; i < this.ventas.length; i++){

      res = Math.max(res, this.ventas[i].venta.preciototal);
    }
    
    console.log("Monto maximo")
    console.log(res)
    this.montoMaximo = res;
    return res;

  }



  calcularFechaInicio() {

  }

  calcularFechaFin() {

  }
}
