import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Proveedor } from 'src/app/models/dtos/Proveedor';

import { ProveedorService } from 'src/app/services/proveedor/proveedor.service';
import { ProveedorUpdate } from 'src/app/models/commands/proveedor/ProveedorUpdate';

@Component({
  selector: 'app-proveedorupdate',
  templateUrl: './proveedorupdate.component.html',
  styleUrls: ['./proveedorupdate.component.css']
})
export class ProveedorupdateComponent implements OnInit {

  formularioProveedor: FormGroup = this.formbuilder.group({
    id:(''),
    razonSocial:new FormControl('', [Validators.required]),
    nombreComercial:new FormControl('', [Validators.required]),
    numeroRuc:new FormControl('', [Validators.required, Validators.pattern('^[0-9]{11}$')]),
    correo:new FormControl('', [Validators.required, Validators.email]),
    direccion:new FormControl('', [Validators.required]),
    departamento:new FormControl('', [Validators.required]),
    telefonoProveedor:new FormControl('', [Validators.required,Validators.pattern('^[0-9]{9}$')]),
   
  })
  get razonNoValido(){
    return this.formularioProveedor.get('razonSocial')?.invalid && this.formularioProveedor.get('razonSocial')?.touched;
  }
  get nombreNoValido(){
    return this.formularioProveedor.get('nombreComercial')?.invalid && this.formularioProveedor.get('nombreComercial')?.touched;
  }
  get rucNoValido(){
    return this.formularioProveedor.get('numeroRuc')?.invalid && this.formularioProveedor.get('numeroRuc')?.touched;
  }
  get correoNoValido(){
    return this.formularioProveedor.get('correo')?.invalid && this.formularioProveedor.get('correo')?.touched;
  }
  get direccionNoValido(){
    return this.formularioProveedor.get('direccion')?.invalid && this.formularioProveedor.get('direccion')?.touched;
  }
  get departamentoNoValido(){
    return this.formularioProveedor.get('departamento')?.invalid && this.formularioProveedor.get('departamento')?.touched;
  }
  get telefonoNoValido(){
    return this.formularioProveedor.get('telefonoProveedor')?.invalid && this.formularioProveedor.get('telefonoProveedor')?.touched;
  }
  proveedor: ProveedorUpdate = new ProveedorUpdate()
  constructor(private proveedorService: ProveedorService, private router:Router,private formbuilder:FormBuilder) { }
  editar(){
    let id= JSON.parse(localStorage.getItem('id') as string);
    this.proveedorService.getProveedorId(id).subscribe(data=>{
   //   this.proveedor=data;
     this.formularioProveedor.patchValue(data);
    
    });
  }


  ngOnInit(): void {
    this.editar();
    
  }
 
  actualizarProveedor() {
  const values = this.formularioProveedor.value
    this.proveedorService.updateProveedor(values).subscribe(data=>{
    //  this.proveedor=data;
      this.router.navigate(['/proveedores']);
    })


    
  }
  
}


