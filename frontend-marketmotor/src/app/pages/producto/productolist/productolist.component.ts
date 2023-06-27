import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Producto } from 'src/app/models/dtos/Producto';
import { ProductoService } from 'src/app/services/producto/producto.service';
import { productoResponse } from 'src/app/models/responseapi/ProductoResponse';



@Component({
  selector: 'app-productolist',
  templateUrl: './productolist.component.html',
  styleUrls: ['./productolist.component.css']
})
export class ProductolistComponent {

  currentPage = 1;
  total = 0;
  itemsPerPage = 5;

  /* productos?: Producto[]; */

  productos: Producto[] = [];


  constructor(private productoService: ProductoService, private router:Router) { }

  productoPaginable: productoResponse= new productoResponse();

/*   ngOnInit(): void {
    this.productoService.getProductos().subscribe(
      data=>{
        this.productos=data;
        console.log(data);
      },
      error=>{
        console.log(error);
      }
    );
  }

  eliminar(producto: Producto):void {
    this.productoService.deleteProducto(producto).subscribe(data=>{
      this.productos=this.productos!.filter(e=>e!==producto);
    });
  } */

  ngOnInit(): void {
    this.getPaginableProducto();
  }

  getPaginableProducto(){
    this.productoService.getAllByPaginable(this.currentPage, this.itemsPerPage).subscribe({
      next: (data: any) => {
        this.productoPaginable = data;
        this.total = this.productoPaginable.totalElements
        this.productos = this.productoPaginable.content
        console.log(data);
      },
      error: (e) =>
      console.log("Error "+e)
    });
  }

  pageChangeEvent(event: number){
    this.currentPage = event;
    this.getPaginableProducto();
  }

}
