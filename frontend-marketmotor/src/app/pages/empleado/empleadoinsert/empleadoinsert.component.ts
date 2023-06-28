import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Empleado } from 'src/app/models/dtos/Empleado';
import { EmpleadoService } from 'src/app/services/empleado/empleado.service';
import { UsuarioService } from 'src/app/services/usuario/usuario.service';

@Component({
  selector: 'app-empleadoinsert',
  templateUrl: './empleadoinsert.component.html',
  styleUrls: ['./empleadoinsert.component.css']
})
export class EmpleadoinsertComponent implements OnInit {

  formularioEmpleado: FormGroup = this.formbuilder.group({
    nombre: new FormControl('', [Validators.required]),
    apellidoPaterno:new FormControl('', [Validators.required]),
    apellidoMaterno:new FormControl('', [Validators.required]),
    telefono:new FormControl('', [Validators.required, Validators.pattern('^[0-9]{9}$')]),
    correo:new FormControl('', [Validators.required, Validators.email]),
    idUsuario:new FormControl('', [Validators.required]),
  })
  get correoNoValido(){
    return this.formularioEmpleado.get('correo')?.invalid && this.formularioEmpleado.get('correo')?.touched;
  }
  get telefonoNoValido(){
    return this.formularioEmpleado.get('telefono')?.invalid && this.formularioEmpleado.get('telefono')?.touched;
  }

  get apellidoPaternoNoValido(){
    return this.formularioEmpleado.get('apellidoPaterno')?.invalid && this.formularioEmpleado.get('apellidoPaterno')?.touched;
  }
  get apellidoMaternoNoValido(){
    return this.formularioEmpleado.get('apellidoMaterno')?.invalid && this.formularioEmpleado.get('apellidoMaterno')?.touched;
  }
  get usuarioNoValido(){
    return this.formularioEmpleado.get('idUsuario')?.invalid && this.formularioEmpleado.get('idUsuario')?.touched;
  }
  get nombreNoValido(){
    return this.formularioEmpleado.get('nombre')?.invalid && this.formularioEmpleado.get('nombre')?.touched;
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
  empleados?: Empleado[];

  constructor(private empleadoService: EmpleadoService, private usuarioService:UsuarioService,private router:Router, private formbuilder: FormBuilder) { }

  ngOnInit(): void {
  }


  guardarEmpleado(){
    const values = this.formularioEmpleado.value
    this.empleadoService.createEmpleado(values)
    .subscribe({
      next: () => {

        this.formularioEmpleado.get("nombre")?.reset()
        this.formularioEmpleado.get("apellidoPaterno")?.reset()
        this.formularioEmpleado.get("apellidoMaterno")?.reset()
        this.formularioEmpleado.get("telefono")?.reset()
        this.formularioEmpleado.get("correo")?.reset()
        this.formularioEmpleado.get("idUsuario")?.reset()
        this.router.navigate(['empleados'])
      },
      error: (e) => alert("Campos incompletos")
      
    })
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
        this.formularioEmpleado.controls['idUsuario'].setValue(this.idUsuario);
      },
      error: (e) => console.log(e)
    })
  }


}
