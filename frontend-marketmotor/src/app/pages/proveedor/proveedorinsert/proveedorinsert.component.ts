import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

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
    razonSocial:new FormControl('', [Validators.required]),
    nombreComercial:new FormControl('', [Validators.required]),
    numeroRuc:new FormControl('', [Validators.required, Validators.pattern('^[0-9]{11}$')]),
    correo:new FormControl('', [Validators.required, Validators.email]),
    direccion:new FormControl('', [Validators.required]),
    departamento:new FormControl('', [Validators.required]),
    telefonoProveedor:new FormControl('', [Validators.required,Validators.pattern('^[0-9]{9}$')]),
    idUsuario:new FormControl('', [Validators.required]),
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
 
  formularioUsuario: FormGroup = this.formbuilder.group({

  
    alias:new FormControl('', [Validators.required]),
    contrasena:new FormControl('', [Validators.required]),
    idRol:new FormControl('', [Validators.required]),
  })
  get aliasNoValido(){
    return this.formularioUsuario.get('alias')?.invalid && this.formularioUsuario.get('alias')?.touched;
  }
  get contrasenaNoValido(){
    return this.formularioUsuario.get('contrasena')?.invalid && this.formularioUsuario.get('contrasena')?.touched;
  }
  get rolNoValido(){
    return this.formularioUsuario.get('idRol')?.invalid && this.formularioUsuario.get('idRol')?.touched;
  }

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
        
        this.router.navigate(['proveedores']);
      },
      error: (e) => alert("Campos incompletos")
    })

   
  }
}
