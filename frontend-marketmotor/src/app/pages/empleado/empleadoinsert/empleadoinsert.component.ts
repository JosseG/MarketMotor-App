import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Empleado } from 'src/app/models/dtos/Empleado';
import { EmpleadoService } from 'src/app/services/empleado/empleado.service';

@Component({
  selector: 'app-empleadoinsert',
  templateUrl: './empleadoinsert.component.html',
  styleUrls: ['./empleadoinsert.component.css']
})
export class EmpleadoinsertComponent implements OnInit {

  formulario: FormGroup = this.formbuilder.group({
    nombre:[],
    apellidoPat:[],
    apellidoMat:[],
    telefono:[],
    correo:[],
    idUsuario:[],
  })

  empleados?: Empleado[];

  constructor(private empleadoService: EmpleadoService, private router:Router, private formbuilder: FormBuilder) { }

  ngOnInit(): void {
    
  }





  editar(id: number): void{
    localStorage.setItem("id",id.toString());
      this.router.navigate(['editarEmpleado']);
  }

  guardar(){
    const values = this.formulario.value
    this.empleadoService.createEmpleado(values)
    .subscribe({
      next: () => {
        this.formulario.get("nombre")?.reset()
        this.formulario.get("apellidoPat")?.reset()
        this.formulario.get("apellidoMat")?.reset()
        this.formulario.get("telefono")?.reset()
        this.formulario.get("correo")?.reset()
        this.formulario.get("idUsuario")?.reset()
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
