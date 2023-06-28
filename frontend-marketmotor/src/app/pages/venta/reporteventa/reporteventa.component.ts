import { Component } from '@angular/core';
import { DetalleVenta } from 'src/app/models/dtos/DetalleVenta';
import { DetalleVentaResponse } from 'src/app/models/responseapi/DetalleVentaResponse';
import { DetalleventaService } from 'src/app/services/detalleVenta/detalleventa.service';
import { Router } from '@angular/router';
import { Venta } from 'src/app/models/dtos/Venta';
import { ProductoService } from 'src/app/services/producto/producto.service';
import { Producto } from 'src/app/models/dtos/Producto';
@Component({
  selector: 'app-reporteventa',
  templateUrl: './reporteventa.component.html',
  styleUrls: ['./reporteventa.component.css']
})
export class ReporteventaComponent {
  currentPage = 1;
  total = 0;
  itemsPerPage = 6;

  productos: Producto[] = []

  constructor(private detalleVentaService: DetalleventaService, private productoService: ProductoService,private router: Router) { }

  detalleVentas: DetalleVenta[] = [];
  detalleVentasConFiltro: DetalleVenta[] = [];

  detalleVentaPaginable: DetalleVentaResponse = new DetalleVentaResponse();
  ngOnInit(): void {
    this.getAllDetalleVentas();
    this.getPaginableDetalleVenta();
    this.getProductos();
  }


  mySet = new Map<number,DetalleVenta>();  

  ventaReporteFiltred: Venta[] = []

  getPaginableDetalleVenta() {

    this.detalleVentaService.getAllByPaginable(this.currentPage, this.itemsPerPage).subscribe({
      next: (data: any) => {
        this.detalleVentaPaginable = data;
        this.total = this.detalleVentaPaginable.totalElements
        this.detalleVentas = this.detalleVentaPaginable.content
        console.log(data);
      },
      error: (e) =>
        console.log("Error " + e)

    });
  }

  getAllDetalleVentas(){
    this.mySet = new Map<number,DetalleVenta>();  
    this.detalleVentaService.getAll().subscribe({
      next: (data : DetalleVenta[]) => {
        console.log("dasta asdkfj")
        console.log(data)
        for(let element of data){
          this.mySet.set(element.venta.id,element)
        }
      }
    })
  }
  pageChangeEvent(event: number) {
    this.currentPage = event;
    this.getPaginableDetalleVenta();
  }

  getTemplateReporteVenta() {
    this.mySet = new Map<number,DetalleVenta>();  

    console.log(this.detalleVentas)
    if(this.detalleVentasConFiltro.length<1){
      for(let element of this.detalleVentas){
        this.mySet.set(element.venta.id,element)
      }
    }else{
      for(let element of this.detalleVentasConFiltro){
        this.mySet.set(element.venta.id,element)
      }
    }


    console.log(this.mySet)
    console.log("son obje")


    sessionStorage.setItem("reporteVentasFiltred",JSON.stringify(Array.from(this.mySet)))

    this.router.navigate(["/reportetemplateventa"])
  }


  getPaginableDetalleVentaByProductoId(id: number){
    this.detalleVentaService.getAllByProductoIdPaginable(id,this.currentPage,this.itemsPerPage).subscribe({
      next: (data: any) => {
        this.detalleVentaPaginable = data;
        this.total = this.detalleVentaPaginable.totalElements
        this.detalleVentas = this.detalleVentaPaginable.content
      }
    })

    this.detalleVentaService.getAllByProductoId(id).subscribe({
      next: (data: any) => {
        console.log("LISTA VENTA REEE")
        console.log(data)
        this.detalleVentasConFiltro = data;
      }
    })
  }


  getProductos(){
    this.productoService.getProductos().subscribe({
      next: (data) => {
        console.log(data)
        this.productos = data
      }
    })
  }


  onChange(object:any){
    this.getPaginableDetalleVentaByProductoId(object)
  }



}
