import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

import { Router } from '@angular/router';
import { Proveedor } from 'src/app/models/dtos/Proveedor';
import { UsuarioService } from 'src/app/services/usuario/usuario.service';
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

  formularioUsuario: FormGroup = this.formbuilder.group({

    alias:[],
    contrasena:[],
    idRol:[],
  })


  idUsuario = 0;
  proveedores?: Proveedor[];

  constructor(private proveedorService: ProveedorService,private router:Router, private usuarioService:UsuarioService,private formbuilder:FormBuilder) { }

  ngOnInit(): void {
  }

  guardarUsuario(){
    const values = this.formularioUsuario.value
    this.usuarioService.createUsuario(values)
    .subscribe({
      next: (data) => {
        this.idUsuario = data.id 
        this.formularioUsuario.get("alias")?.reset()
        this.formularioUsuario.get("contrasena")?.reset()
        this.formularioUsuario.get("idRol")?.reset()
        this.formularioProveedor.controls['idUsuario'].setValue(this.idUsuario);
      },
      error: (e) => console.log(e)
    })
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
