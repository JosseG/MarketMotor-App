import { Component } from '@angular/core';
import { DetalleVenta } from 'src/app/models/dtos/DetalleVenta';
import { DetalleVentaResponse } from 'src/app/models/responseapi/DetalleVentaResponse';
import { DetalleventaService } from 'src/app/services/detalleVenta/detalleventa.service';
import { Router } from '@angular/router';
import { Venta } from 'src/app/models/dtos/Venta';
@Component({
  selector: 'app-reporteventa',
  templateUrl: './reporteventa.component.html',
  styleUrls: ['./reporteventa.component.css']
})
export class ReporteventaComponent {
  currentPage = 1;
  total = 0;
  itemsPerPage = 6;

  constructor(private detalleVentaService: DetalleventaService, private router: Router) { }

  detalleVentas: DetalleVenta[] = [];
  detalleVentasConFiltro: DetalleVenta[] = [];

  detalleVentaPaginable: DetalleVentaResponse = new DetalleVentaResponse();
  ngOnInit(): void {
    this.getAllDetalleVentas();
    this.getPaginableDetalleVenta();
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
    this.detalleVentaService.getAll().subscribe({
      next: (data : DetalleVenta[]) => {
        this.detalleVentasConFiltro = data
      }
    })
  }
  pageChangeEvent(event: number) {
    this.currentPage = event;
    this.getPaginableDetalleVenta();
  }

  getTemplateReporteVenta() {

    console.log(this.detalleVentas)
    for(let element of this.detalleVentasConFiltro){
      this.mySet.set(element.venta.id,element)
    }

    console.log(this.mySet)


    sessionStorage.setItem("reporteVentasFiltred",JSON.stringify(Array.from(this.mySet)))

    this.router.navigate(["/reportetemplateventa"])
  }

}
