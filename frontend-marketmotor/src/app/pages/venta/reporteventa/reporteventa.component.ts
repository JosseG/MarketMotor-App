import { Component } from '@angular/core';
import { DetalleVenta } from 'src/app/models/dtos/DetalleVenta';
import { DetalleVentaResponse } from 'src/app/models/responseapi/DetalleVentaResponse';
import { DetalleventaService } from 'src/app/services/detalleVenta/detalleventa.service';
import { Router } from '@angular/router';
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

  detalleVentaPaginable: DetalleVentaResponse= new DetalleVentaResponse();
  ngOnInit(): void {
    
    this.getPaginableDetalleVenta();
  }

  getPaginableDetalleVenta() {

    this.detalleVentaService.getAllByPaginable(this.currentPage,this.itemsPerPage).subscribe({
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

  pageChangeEvent(event: number){
    this.currentPage = event;
    this.getPaginableDetalleVenta();
}

}
