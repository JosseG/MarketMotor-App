import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
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
    nombre:[],
    apellidoPat:[],
    apellidoMat:[],
    telefono:[],
    correo:[],
    idUsuario:[],
  })

  formularioUsuario: FormGroup = this.formbuilder.group({

    alias:[],
    contrasena:[],
    idRol:[],
  })

  idUsuario = 0;
  empleados?: Empleado[];

  constructor(private empleadoService: EmpleadoService, private usuarioService:UsuarioService,private router:Router, private formbuilder: FormBuilder) { }

  ngOnInit(): void {
  }





  editar(id: number): void{
    localStorage.setItem("id",id.toString());
      this.router.navigate(['editarEmpleado']);
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
        alert("Agregado con exito")
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




  eliminar(empleado: Empleado):void {
    this.empleadoService.deleteEmpleado(empleado).subscribe(data=>{
      this.empleados=this.empleados!.filter(e=>e!==empleado);
    });
  }
}
