import { Component } from '@angular/core';
import { OrdenCompra } from 'src/app/models/dtos/OrdenCompra';
import { ProductoService } from 'src/app/services/producto/producto.service';
import { OrdencompraService } from '../../../../services/ordencompra/ordencompra.service';
import { DetalleordencompraService } from 'src/app/services/detalleordencompra/detalleordencompra.service';
import { OrdenCompraResponse } from 'src/app/models/responseapi/OrdenCompraResponse';
import { DetalleOrdenCompra } from 'src/app/models/dtos/DetalleOrdenCompra';
import { DetalleOrdenCompraResponse } from 'src/app/models/responseapi/DetalleOrdenCompraResponse';

@Component({
  selector: 'app-validarorden',
  templateUrl: './validarorden.component.html',
  styleUrls: ['./validarorden.component.css']
})
export class ValidarordenComponent {

  constructor(private productoService: ProductoService, private ordencompraService: OrdencompraService, private detalleOrdenCompraService: DetalleordencompraService){}


  currentPageDetalle = 1;
  totalDetalle = 0;
  itemsPerPageDetalle = 6;
  detalleOrdenCompras: DetalleOrdenCompra[] = [];

  detalleOrdenCompraPaginable: DetalleOrdenCompraResponse= new DetalleOrdenCompraResponse();


  currentPage = 1;
  total = 0;
  itemsPerPage = 6;
  
  ordenCompras: OrdenCompra[] = [];

  ordenCompraPaginable: OrdenCompraResponse= new OrdenCompraResponse();


  ordenFocus: OrdenCompra = new OrdenCompra();


  ngOnInit(): void {
    
    this.getPaginableOrdenCompra();
  }

  getPaginableOrdenCompra() {

    this.ordencompraService.getAllPendientesByPaginable(this.currentPage,this.itemsPerPage).subscribe({
      next: (data: any) => {
        this.ordenCompraPaginable = data;
        this.total = this.ordenCompraPaginable.totalElements
        this.ordenCompras = this.ordenCompraPaginable.content
        console.log(data);
      },
      error: (e: any) =>
        console.log(e)

    });
  }

  pageChangeEvent(event: number){
    this.currentPage = event;
    this.getPaginableOrdenCompra();
  }















  getPaginableDetalleOrdenCompra() {

    this.detalleOrdenCompraService.getAllByOrdeCompraIdPaginable(this.id,this.currentPageDetalle,this.itemsPerPageDetalle).subscribe({
      next: (data: any) => {
        this.detalleOrdenCompraPaginable = data;
        this.totalDetalle = this.detalleOrdenCompraPaginable.totalElements
        this.detalleOrdenCompras = this.detalleOrdenCompraPaginable.content
        this.ordenFocus = this.detalleOrdenCompras[0].ordenCompra
        console.log(data);
      },
      error: (e) =>
        console.log("Error " + e)

    });
  }

  pageChangeEventDetalle(event: number){
    this.currentPageDetalle = event;
    this.getPaginableDetalleOrdenCompra();
  }



  id = 0;


  cargarDatos(id: number){
    this.id = id
    this.getPaginableDetalleOrdenCompra()
    console.log(id)
  }


  confirmado = false;

  async confirmarOrden(orden: OrdenCompra){

    this.ordencompraService.confirmarOrden(orden.id,this.detalleOrdenCompras).subscribe({
      next: async () => {
        alert("Se realizo la orden sin prob")
        this.getPaginableOrdenCompra();
      }
    })
    this.confirmado = true
  }

}
