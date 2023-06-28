import { Component } from '@angular/core';
import { DetalleOrdenCompra } from 'src/app/models/dtos/DetalleOrdenCompra';
import { DetalleordencompraService } from 'src/app/services/detalleordencompra/detalleordencompra.service';
import { DetalleOrdenCompraResponse } from 'src/app/models/responseapi/DetalleOrdenCompraResponse';
import { Router } from '@angular/router';
import { Producto } from 'src/app/models/dtos/Producto';
import { ProductoService } from 'src/app/services/producto/producto.service';
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
  detalleOrdenComprasConFiltro: DetalleOrdenCompra[] = [];

  productos: Producto[] = []

  detalleOrdenCompraPaginable: DetalleOrdenCompraResponse= new DetalleOrdenCompraResponse();
  constructor(private detalleOrdenCompraService: DetalleordencompraService, private router: Router,private productoService: ProductoService) { }
  mySet = new Map<number,DetalleOrdenCompra>();  

  ngOnInit(): void {
    //this.getAllDetalleOrdenCompras();
    this.getPaginableDetalleOrdenCompra();
    this.getProductos();
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
        console.log(e)
    });
  }


  getPaginableDetalleOrdenCompraByProductoId(id: number){
    this.detalleOrdenCompraService.getAllByProductoIdPaginable(id,this.currentPage,this.itemsPerPage).subscribe({
      next: (data: any) => {
        this.detalleOrdenCompraPaginable = data;
        this.total = this.detalleOrdenCompraPaginable.totalElements
        this.detalleOrdenCompras = this.detalleOrdenCompraPaginable.content
      }
    })

    this.detalleOrdenCompraService.getAllByProductoId(id).subscribe({
      next: (data: any) => {
        console.log(data)
        this.detalleOrdenComprasConFiltro = data;
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
    if(object==0){
      //this.getAllDetalleVentas();
      this.getPaginableDetalleOrdenCompra();
      this.getProductos();
    }else{

      this.getPaginableDetalleOrdenCompraByProductoId(object)
    }
  }


  pageChangeEvent(event: number){
    this.currentPage = event;
    this.getPaginableDetalleOrdenCompra();
  }


  getTemplateReporteCompra(){

    this.mySet = new Map<number,DetalleOrdenCompra>();  

    if(this.detalleOrdenComprasConFiltro.length<1){
      for(let element of this.detalleOrdenCompras){
        this.mySet.set(element.ordenCompra.id,element)
      }
    }else{
      for(let element of this.detalleOrdenCompras){
        this.mySet.set(element.ordenCompra.id,element)
      }
    }

    sessionStorage.setItem("reporteOrdenComprasFiltred",JSON.stringify(Array.from(this.mySet)))

    this.router.navigate(["/reportetemplatecompra"])
  }
}
