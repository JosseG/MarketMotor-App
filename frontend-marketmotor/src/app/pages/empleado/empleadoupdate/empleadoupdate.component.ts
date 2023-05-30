import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Empleado } from 'src/app/models/dtos/Empleado';
import { EmpleadoService } from 'src/app/services/empleado/empleado.service';

@Component({
  selector: 'app-empleadoupdate',
  templateUrl: './empleadoupdate.component.html',
  styleUrls: ['./empleadoupdate.component.css']
})
export class EmpleadoupdateComponent {

  empleados?: Empleado[];

  constructor(private empleadoService: EmpleadoService, private router:Router) { }

  editar(empleado: Empleado): void{
    localStorage.setItem("id",empleado.id.toString());
      this.router.navigate(['editarEmpleado']);
  }
}
