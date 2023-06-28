import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Producto } from 'src/app/models/dtos/Producto';
import { ProductoService } from 'src/app/services/producto/producto.service';

@Component({
  selector: 'app-productoupdate',
  templateUrl: './productoupdate.component.html',
  styleUrls: ['./productoupdate.component.css']
})
export class ProductoupdateComponent implements OnInit{
  formularioProducto: FormGroup = this.formbuilder.group({
    id:(''),
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
  productos?: Producto[];
  constructor(private productoService: ProductoService, private router:Router,private formbuilder:FormBuilder) { }

  editar(){
    let id= JSON.parse(localStorage.getItem('id') as string);
    this.productoService.getProductoId(id).subscribe(data=>{
     this.formularioProducto.patchValue(data);
    
    });
  }

  ngOnInit(): void {
    this.editar();
    
  }
 
  actualizarProducto() {
  const values = this.formularioProducto.value
  console.log(values)
    this.productoService.updateProducto(values).subscribe(data=>{
      this.router.navigate(['/productos']);
    })
}
}
