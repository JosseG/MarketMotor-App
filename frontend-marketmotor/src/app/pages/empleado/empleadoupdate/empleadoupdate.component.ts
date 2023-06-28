import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { EmpleadoUpdate } from 'src/app/models/commands/empleado/EmpleadoUpdate';
import { EmpleadoService } from 'src/app/services/empleado/empleado.service';

@Component({
  selector: 'app-empleadoupdate',
  templateUrl: './empleadoupdate.component.html',
  styleUrls: ['./empleadoupdate.component.css']
})
export class EmpleadoupdateComponent implements OnInit {
  formularioEmpleado: FormGroup = this.formbuilder.group({
    id:(''),
    nombre: new FormControl('', [Validators.required]),
    apellidoPaterno:new FormControl('', [Validators.required]),
    apellidoMaterno:new FormControl('', [Validators.required]),
    telefono:new FormControl('', [Validators.required, Validators.pattern('^[0-9]{9}$')]),
    correo:new FormControl('', [Validators.required, Validators.email]),
    estado:new FormControl('', [Validators.required]),
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
  empleado: EmpleadoUpdate = new EmpleadoUpdate()
  constructor(private empleadoService: EmpleadoService, private router:Router,private formbuilder:FormBuilder) { }

  editar(){
    let id= JSON.parse(localStorage.getItem('id') as string);
    this.empleadoService.getEmpleadoId(id).subscribe(data=>{
    //  this.empleado=data;
     this.formularioEmpleado.patchValue(data);
    
    });
  }


  ngOnInit(): void {
    this.editar();
    
  }
 
  actualizarEmpleado() {
  const values = this.formularioEmpleado.value
    this.empleadoService.actualizar(values).subscribe(data=>{
    //  this.empleado=data;
      this.router.navigate(['/empleados']);
    })
}
}
