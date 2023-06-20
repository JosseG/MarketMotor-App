import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ProductoInsert } from 'src/app/models/commands/producto/ProductoInsert';
import { ProductoService } from 'src/app/services/producto/producto.service';
import { Producto } from 'src/app/models/dtos/Producto';

@Component({
  selector: 'app-productoinsert',
  templateUrl: './productoinsert.component.html',
  styleUrls: ['./productoinsert.component.css']
})
export class ProductoinsertComponent implements OnInit{

  formularioProducto: FormGroup = this.formbuilder.group({
    descripcion:[],
    tipo:[],
    serial:[],
    marca:[],
    precio:[],
  })

  producto?: Producto[];


  constructor(private productoService: ProductoService, private router:Router, private formbuilder:FormBuilder) { }

  ngOnInit(): void{
  }

  /* guardar(producto: ProductoInsert) {
    this.productoService.createProducto(producto).subscribe(data=>{
      this.router.navigate(['productos']);
    });
  } */

  guardarProducto():void {
    const values = this.formularioProducto.value
    this.productoService.createProducto(values).subscribe({
      next: () => {
        this.formularioProducto.get("descripcion")?.reset()
        this.formularioProducto.get("tipo")?.reset()
        this.formularioProducto.get("serial")?.reset()
        this.formularioProducto.get("marca")?.reset()
        this.formularioProducto.get("precio")?.reset()

        this.router.navigate(['producto']);
      },
      error: (e) => alert("Campos incompletos")
    })
  }

  cancelar(){
    console.log("Esta cancelando");
  }
}
