import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Producto } from 'src/app/models/dtos/Producto';
import { ProductoService } from 'src/app/services/producto/producto.service';



@Component({
  selector: 'app-productolist',
  templateUrl: './productolist.component.html',
  styleUrls: ['./productolist.component.css']
})
export class ProductolistComponent {

  productos?: Producto[];

  constructor(private productoService: ProductoService, private router:Router) { }

  ngOnInit(): void {
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
  }

}
