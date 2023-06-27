import { Component } from '@angular/core';
import { DetalleOrdenCompra } from 'src/app/models/dtos/DetalleOrdenCompra';
import { DetalleordencompraService } from 'src/app/services/detalleordencompra/detalleordencompra.service';
import { DetalleOrdenCompraResponse } from 'src/app/models/responseapi/DetalleOrdenCompraResponse';
import { Router } from '@angular/router';
@Component({
  selector: 'app-reporteordencompra',
  templateUrl: './reporteordencompra.component.html',
  styleUrls: ['./reporteordencompra.component.css']
})
export class ReporteordencompraComponent {
  currentPage = 1;
  total = 0;
  itemsPerPage = 6;
  detalleOrdenCompras: DetalleOrdenCompra[] = [];

  detalleOrdenCompraPaginable: DetalleOrdenCompraResponse= new DetalleOrdenCompraResponse();
  constructor(private detalleOrdenCompraService: DetalleordencompraService, private router: Router) { }


  ngOnInit(): void {
    
    this.getPaginableDetalleOrdenCompra();
  }

  getPaginableDetalleOrdenCompra() {

    this.detalleOrdenCompraService.getAllByPaginable(this.currentPage,this.itemsPerPage).subscribe({
      next: (data: any) => {
        this.detalleOrdenCompraPaginable = data;
        this.total = this.detalleOrdenCompraPaginable.totalElements
        this.detalleOrdenCompras = this.detalleOrdenCompraPaginable.content
        console.log(data);
      },
      error: (e) =>
        console.log("Error " + e)

    });
  }


  getPaginableDetalleOrdenCompraByProductoId(id: number){
    this.detalleOrdenCompraService.getAllByProductoIdPaginable(id).subscribe({
      next: (data: any) => {
        console.log(data)
      }
    })
  }









  pageChangeEvent(event: number){
    this.currentPage = event;
    this.getPaginableDetalleOrdenCompra();
  }


  getTemplateReporteCompra(){
    this.router.navigate(["/reportetemplatecompra"])
  }
}
