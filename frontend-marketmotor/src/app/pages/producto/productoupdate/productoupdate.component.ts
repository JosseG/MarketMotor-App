import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Producto } from 'src/app/models/dtos/Producto';
import { ProductoService } from 'src/app/services/producto/producto.service';

@Component({
  selector: 'app-productoupdate',
  templateUrl: './productoupdate.component.html',
  styleUrls: ['./productoupdate.component.css']
})
export class ProductoupdateComponent {


  constructor(private productoService: ProductoService, private router:Router) { }

  editar(producto: Producto): void{
    localStorage.setItem("id",producto.id.toString());
      this.router.navigate(['editarProducto']);
  }
}
