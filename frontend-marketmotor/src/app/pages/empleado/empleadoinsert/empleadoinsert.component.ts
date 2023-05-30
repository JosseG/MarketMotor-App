import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Empleado } from 'src/app/models/dtos/Empleado';
import { EmpleadoService } from 'src/app/services/empleado/empleado.service';

@Component({
  selector: 'app-empleadoinsert',
  templateUrl: './empleadoinsert.component.html',
  styleUrls: ['./empleadoinsert.component.css']
})
export class EmpleadoinsertComponent implements OnInit {

  empleados?: Empleado[];

  constructor(private empleadoService: EmpleadoService, private router:Router) { }

  ngOnInit(): void {
    this.empleadoService.getEmpleado().subscribe(
      data=>{
        this.empleados=data;
        console.log(data);
      },
      error=>{
        console.log(error);
      }
    );
  }


  nuevo():void {
    this.router.navigate(['nuevoEmpleado']);
  }

  editar(empleado: Empleado): void{
    localStorage.setItem("id",empleado.id.toString());
      this.router.navigate(['editarEmpleado']);
  }

  eliminar(empleado: Empleado):void {
    this.empleadoService.deleteEmpleado(empleado).subscribe(data=>{
      this.empleados=this.empleados!.filter(e=>e!==empleado);
    });
  }
}
