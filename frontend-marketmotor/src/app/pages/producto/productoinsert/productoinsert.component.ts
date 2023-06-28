import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
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
    descripcion:new FormControl('', [Validators.required]),
    tipo:new FormControl('', [Validators.required]),
    serial:new FormControl('', [Validators.required]),
    marca:new FormControl('', [Validators.required]),
    precio:new FormControl('', [Validators.required]),
    stock:new FormControl('', [Validators.required]),
  })

  get descripcionNoValido(){
    return this.formularioProducto.get('descripcion')?.invalid && this.formularioProducto.get('descripcion')?.touched;
  }
  get tipoNoValido(){
    return this.formularioProducto.get('tipo')?.invalid && this.formularioProducto.get('tipo')?.touched;
  }
  get serialNoValido(){
    return this.formularioProducto.get('serial')?.invalid && this.formularioProducto.get('serial')?.touched;
  }
  get marcaNoValido(){
    return this.formularioProducto.get('marca')?.invalid && this.formularioProducto.get('marca')?.touched;
  }
  get precioNoValido(){
    return this.formularioProducto.get('precio')?.invalid && this.formularioProducto.get('precio')?.touched;
  }
  get stockNoValido(){
    return this.formularioProducto.get('stock')?.invalid && this.formularioProducto.get('stock')?.touched;
  }
  producto?: Producto[];


  constructor(private productoService: ProductoService, private router:Router, private formbuilder:FormBuilder) { }

  ngOnInit(): void{
  }

  
  guardarProducto():void {
    const values = this.formularioProducto.value
    this.productoService.createProducto(values).subscribe({
      next: () => {
        this.formularioProducto.get("descripcion")?.reset()
        this.formularioProducto.get("tipo")?.reset()
        this.formularioProducto.get("serial")?.reset()
        this.formularioProducto.get("marca")?.reset()
        this.formularioProducto.get("precio")?.reset()
        this.formularioProducto.get("stock")?.reset()
        this.router.navigate(['/productos']);
      },
      error: (e) => alert("Campos incompletos")
    })
  }

  cancelar(){
    console.log("Esta cancelando");
  }
}
