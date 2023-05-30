import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProductoInsert } from 'src/app/models/commands/producto/ProductoInsert';
import { ProductoService } from 'src/app/services/producto/producto.service';

@Component({
  selector: 'app-productoinsert',
  templateUrl: './productoinsert.component.html',
  styleUrls: ['./productoinsert.component.css']
})
export class ProductoinsertComponent implements OnInit{

  modelProducto = new ProductoInsert();

  constructor(private productoService: ProductoService, private router:Router) { }

  ngOnInit(): void{
  }

  guardar(producto: ProductoInsert) {
    this.productoService.createProducto(producto).subscribe(data=>{
      this.router.navigate(['productos']);
    });
  }

  cancelar(){
    console.log("Esta cancelando");
  }
}
