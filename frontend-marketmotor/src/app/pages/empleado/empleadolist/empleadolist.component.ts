import { PaginationComponent } from './../../../components/pagination/pagination.component';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { EmpleadoUpdate } from 'src/app/models/commands/empleado/EmpleadoUpdate';
import { Empleado } from 'src/app/models/dtos/Empleado';
import { EmpleadoFull } from 'src/app/models/dtos/EmpleadoFull';
import { EmpleadoResponse } from 'src/app/models/responseapi/EmpleadoResponse';
import { EmpleadoService } from 'src/app/services/empleado/empleado.service';

@Component({
  selector: 'app-empleadolist',
  templateUrl: './empleadolist.component.html',
  styleUrls: ['./empleadolist.component.css'],
})
export class EmpleadolistComponent {
  currentPage = 1;
  total = 0;
  itemsPerPage = 5;
  constructor(
    private empleadoService: EmpleadoService,
    private router: Router
  ) { }

  empleados: Empleado[] = [];

  empleadosPaginable: EmpleadoResponse = new EmpleadoResponse();
  ngOnInit(): void {
    this.getPaginableEmpleados();
  }

  getAllEmpleados() {
    this.empleadoService.getAll().subscribe((empleados: any) => {
      console.log(empleados);
    });
  }

  editar(empleado: EmpleadoUpdate): void {
    localStorage.setItem('id', empleado.id.toString());
    this.router.navigate(['empleados/actualizar']);
  }
  getPaginableEmpleados() {
    this.empleadoService
      .getAllByPaginable(this.currentPage, this.itemsPerPage)
      .subscribe({
        next: (data: any) => {
          this.empleadosPaginable = data;
          this.total = this.empleadosPaginable.totalElements;
          this.empleados = this.empleadosPaginable.content;
          console.log(data);
        },
        error: (e) => console.log('Error ' + e),
      });
  }
  eliminar(empleado: Empleado): void {
    this.empleadoService.borrarLogicEmpleado(empleado.id).subscribe((data) => {
      this.getPaginableEmpleados();
      // Actualizar el estado a "inactivo"
    });
  }

  pageChangeEvent(event: number) {
    this.currentPage = event;
    this.getPaginableEmpleados();
  }

  activar(id:number){
    this.empleadoService.activarEmpleado(id).subscribe({
      next: ()=> {
        this.getPaginableEmpleados();
      }
    })
  }
}
