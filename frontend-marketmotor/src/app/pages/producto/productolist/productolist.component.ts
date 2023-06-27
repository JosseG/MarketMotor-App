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
  itemsPerPage = 4;


  productos: Producto[] = [];


  constructor(private productoService: ProductoService, private router:Router) { }

  productoPaginable: productoResponse= new productoResponse();


  ngOnInit(): void {
    this.getPaginableProducto();
  }
  getAllProductos() {
    this.productoService.getProductos()
      .subscribe((productos: any) => {
        console.log(productos)
      })
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
  editar(producto:Producto): void{
    localStorage.setItem("id",producto.id.toString());
      this.router.navigate(['productos/actualizar']);
  }

  eliminar(producto:Producto): void {
    console.log(producto.id)
    this.productoService.borrarLogicProducto(producto.id).subscribe(data => {
     this.getPaginableProducto()
      producto.estado = false; // Actualizar el estado a "inactivo"
    });
  }
  pageChangeEvent(event: number){
    this.currentPage = event;
    this.getPaginableProducto();
  }

}
