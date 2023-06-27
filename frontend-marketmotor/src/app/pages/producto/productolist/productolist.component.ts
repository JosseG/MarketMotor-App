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
  itemsPerPage = 2;


  productos: Producto[] = [];


  constructor(private productoService: ProductoService, private router:Router) { }

  productoPaginable: productoResponse= new productoResponse();


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
