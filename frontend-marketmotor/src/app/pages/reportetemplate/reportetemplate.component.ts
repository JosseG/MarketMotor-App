import { Component } from '@angular/core';
import { DetalleOrdenCompra } from 'src/app/models/dtos/DetalleOrdenCompra';
import { DetalleVenta } from 'src/app/models/dtos/DetalleVenta';
import { Empleado } from 'src/app/models/dtos/Empleado';
import { OrdenCompra } from 'src/app/models/dtos/OrdenCompra';
import { Producto } from 'src/app/models/dtos/Producto';
import { Proveedor } from 'src/app/models/dtos/Proveedor';
import { EmpleadoService } from 'src/app/services/empleado/empleado.service';
import { ProductoService } from 'src/app/services/producto/producto.service';
import { ProveedorService } from 'src/app/services/proveedor/proveedor.service';

@Component({
  selector: 'app-reportetemplate',
  templateUrl: './reportetemplate.component.html',
  styleUrls: ['./reportetemplate.component.css']
})
export class ReportetemplateComponent {
  constructor(
    private proveedorService: ProveedorService,
    private empleadoService: EmpleadoService,
    private productoService: ProductoService
  ) { }

  compras : DetalleOrdenCompra[] = [];



  productoMasVendido = new Producto();
  proveedorDestacado = new Proveedor();
  empleadoDestacado = new Empleado();
  detalleConMontoMinimo = new DetalleOrdenCompra();
  detalleConMontoMaximo = new DetalleOrdenCompra();
  montoMinimo = 0;
  montoMaximo = 0;
  fechaInicio = new Date();
  fechaFin = new Date();

  map = new Map<number, DetalleOrdenCompra>();
  ngOnInit() {
    this.getListReport();
  }

  total = 0;



  getListReport() {
    var elementos = sessionStorage.getItem('reporteOrdenComprasFiltred');
    if (elementos == null) {
    } else {
      this.map = new Map(JSON.parse(elementos));
      this.compras = Array.from(this.map.values());

      for (let i of this.compras) {
        this.total += i.ordenCompra.valorTotal;
      }
    }

    this.calcularMontoMinimo();
    this.calcularMontoMaximo();
    var idProveedor = this.getIdProveedorDestacado();
    this.proveedorService.getProveedorId(idProveedor).subscribe({
      next: (data) => {
        this.proveedorDestacado = data;
      },
    });
    this.empleadoService
      .getEmpleadoId(this.getIdEmpleadoDestacado())
      .subscribe({
        next: (data) => {
          this.empleadoDestacado = data;
        },
      });

    this.productoService
      .getProductoId(this.getIdProductoDestacado())
      .subscribe({
        next: (data) => {
          this.productoMasVendido = data;
        },
      });
    this.calcularFechaFin();
    this.calcularFechaInicio();
  }
















  getIdProveedorDestacado(): number {
    var hash = new Map();
    for (var i = 0; i < this.compras.length; i++) {
      if (hash.has(this.compras[i].ordenCompra.proveedor.id))
        hash.set(
          this.compras[i].ordenCompra.proveedor.id,
          hash.get(this.compras[i].ordenCompra.proveedor.id) + 1
        );
      else hash.set(this.compras[i].ordenCompra.proveedor.id, 1);
    }

    // find the max frequency
    var max_count = 0,
      res = -1;
    hash.forEach((value, key) => {
      if (max_count < value) {
        res = key;
        max_count = value;
      }
    });
    console.log('Proveedor destacado');
    console.log(res);
    return res;
  }







  getIdEmpleadoDestacado(): number {
    var hash = new Map();
    for (var i = 0; i < this.compras.length; i++) {
      if (hash.has(this.compras[i].ordenCompra.empleado.id))
        hash.set(
          this.compras[i].ordenCompra.empleado.id,
          hash.get(this.compras[i].ordenCompra.empleado.id) + 1
        );
      else hash.set(this.compras[i].ordenCompra.empleado.id, 1);
    }
    var max_count = 0,
      res = -1;
    hash.forEach((value, key) => {
      if (max_count < value) {
        res = key;
        max_count = value;
      }
    });
    return res;
  }



  
  getIdProductoDestacado(): number {
    var hash = new Map();
    for (var i = 0; i < this.compras.length; i++) {
      if (hash.has(this.compras[i].producto.id))
        hash.set(
          this.compras[i].producto.id,
          hash.get(this.compras[i].producto.id) + 1
        );
      else hash.set(this.compras[i].producto.id, 1);
    }

    var max_count = 0,
      res = -1;
    hash.forEach((value, key) => {
      if (max_count < value) {
        res = key;
        max_count = value;
      }
    });
    return res;
  }

  calcularMontoMinimo(): number {
    let res = this.compras[0].ordenCompra.valorTotal;

    for (let i = 1; i < this.compras.length; i++) {
      res = Math.min(res, this.compras[i].ordenCompra.valorTotal);
    }
    this.montoMinimo = res;
    return res;
  }

  calcularMontoMaximo(): number {
    let res = this.compras[0].ordenCompra.valorTotal;

    for (let i = 1; i < this.compras.length; i++) {
      res = Math.max(res, this.compras[i].ordenCompra.valorTotal);
    }

    this.montoMaximo = res;
    return res;
  }

  calcularFechaInicio() {
    let res = this.compras[0].ordenCompra.creadoEn;

    for (let i = 1; i < this.compras.length; i++) {
      res =
        res < this.compras[i].ordenCompra.creadoEn
          ? res
          : this.compras[i].ordenCompra.creadoEn;
    }

    this.fechaInicio = res;
    return res;
  }

  calcularFechaFin() {
    let res = this.compras[0].ordenCompra.creadoEn;

    for (let i = 1; i < this.compras.length; i++) {
      res =
        res > this.compras[i].ordenCompra.creadoEn
          ? res
          : this.compras[i].ordenCompra.creadoEn;
    }

    this.fechaFin = res;
    return res;
  }
  


}
