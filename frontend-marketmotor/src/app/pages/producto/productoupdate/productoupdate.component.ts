import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
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
    id:[],
    descripcion:[],
    tipo:[],
    serial:[],
    marca:[],
    precio:[0.0],
    stock:[],
  })
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
