import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
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
    id:[],
    nombre:[],
    apellidoPaterno:[],
    apellidoMaterno:[],
    telefono:[],
    correo:[],
    estado:[],
  })

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
