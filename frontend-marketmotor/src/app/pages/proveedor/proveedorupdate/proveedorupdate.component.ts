import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';
import { FormBuilder, FormGroup } from '@angular/forms';
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
    id:[],
    razonSocial:[],
    nombreComercial:[],
    numeroRuc:[],
    correo:[],
    direccion:[],
    departamento:[],
    telefonoProveedor:[],
   
  })

  proveedor: ProveedorUpdate = new ProveedorUpdate()
  constructor(private proveedorService: ProveedorService, private router:Router,private formbuilder:FormBuilder) { }
  editar(){
    let id= JSON.parse(localStorage.getItem('id') as string);
    this.proveedorService.getProveedorId(id).subscribe(data=>{
      this.proveedor=data;
     this.formularioProveedor.patchValue(data);
    
    });
  }


  ngOnInit(): void {
    this.editar();
    
  }
 
  actualizarProveedor(proveedor: ProveedorUpdate) {
  const values = this.formularioProveedor.value
    this.proveedorService.updateProveedor(values).subscribe(data=>{
      this.proveedor=data;
      this.router.navigate(['proveedores']);
    })


    
  }
  
}


