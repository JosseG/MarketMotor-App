import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

import { Router } from '@angular/router';
import { Proveedor } from 'src/app/models/dtos/Proveedor';

import { ProveedorService } from 'src/app/services/proveedor/proveedor.service';

@Component({
  selector: 'app-proveedorinsert',
  templateUrl: './proveedorinsert.component.html',
  styleUrls: ['./proveedorinsert.component.css']
})
export class ProveedorinsertComponent 
implements OnInit {

  formularioProveedor: FormGroup = this.formbuilder.group({
    razonSocial:[],
    nombreComercial:[],
    numeroRuc:[],
    correo:[],
    direccion:[],
    departamento:[],
    telefonoProveedor:[],
    idUsuario:[],
  })
  idUsuario = 0;
  proveedores?: Proveedor[];

  constructor(private proveedorService: ProveedorService,private router:Router, private formbuilder:FormBuilder) { }

  ngOnInit(): void {
  }

  guardarProveedor():void {
    const values = this.formularioProveedor.value
    this.proveedorService.createProveedor(values).subscribe({
      next: () => {
        this.formularioProveedor.get("razonSocial")?.reset()
        this.formularioProveedor.get("nombreComercial")?.reset()
        this.formularioProveedor.get("numeroRuc")?.reset()
        this.formularioProveedor.get("correo")?.reset()
        this.formularioProveedor.get("direccion")?.reset()
        this.formularioProveedor.get("departamento")?.reset()
        this.formularioProveedor.get("telefonoProveedor")?.reset()
        this.formularioProveedor.get("idUsuario")?.reset()
        alert("Agregado con exito")
        this.router.navigate(['proveedores']);
      },
      error: (e) => alert("Campos incompletos")
    })

   
  }
}
