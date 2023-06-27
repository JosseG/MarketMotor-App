import { PaginationComponent } from './../../../components/pagination/pagination.component';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Empleado } from 'src/app/models/dtos/Empleado';
import { EmpleadoFull } from 'src/app/models/dtos/EmpleadoFull';
import { EmpleadoResponse } from 'src/app/models/responseapi/EmpleadoResponse';
import { EmpleadoService } from 'src/app/services/empleado/empleado.service';

@Component({
  selector: 'app-empleadolist',
  templateUrl: './empleadolist.component.html',
  styleUrls: ['./empleadolist.component.css']
})
export class EmpleadolistComponent {

  currentPage = 1;
  total = 0;
  itemsPerPage = 5;
  constructor(private empleadoService: EmpleadoService, private router: Router) { }

  empleados: Empleado[] = [];

  empleadosPaginable: EmpleadoResponse= new EmpleadoResponse();
  ngOnInit(): void {
    this.getPaginableEmpleados();
  }

  getAllEmpleados() {
    this.empleadoService.getAll()
      .subscribe((empleados: any) => {
        console.log(empleados)
      })
  }

  eliminar(empleado: Empleado): void {
    this.empleadoService.deleteEmpleado(empleado).subscribe(data => {
      this.empleados = this.empleados!.filter(e => e !== empleado);
    });
  }

  getPaginableEmpleados() {

    this.empleadoService.getAllByPaginable(this.currentPage,this.itemsPerPage).subscribe({
      next: (data: any) => {
        this.empleadosPaginable = data;
        this.total = this.empleadosPaginable.totalElements
        this.empleados = this.empleadosPaginable.content
        console.log(data);
      },
      error: (e) =>
        console.log("Error " + e)

    });
  }

  pageChangeEvent(event: number){
    this.currentPage = event;
    this.getPaginableEmpleados();
}

}
