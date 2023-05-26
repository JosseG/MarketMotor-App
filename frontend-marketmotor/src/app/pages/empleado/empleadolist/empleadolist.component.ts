import { Component } from '@angular/core';
import { EmpleadoService } from 'src/app/services/empleado/empleado.service';


@Component({
  selector: 'app-empleadolist',
  templateUrl: './empleadolist.component.html',
  styleUrls: ['./empleadolist.component.css']
})
export class EmpleadolistComponent {

  constructor(private empleadoService: EmpleadoService){}

  empleados: any[] = [];
  ngOnInit():void{
    this.getAll();
  }

  getAll(){
    this.empleadoService.getAll()
    .subscribe((usuarios : any) => {
      console.log(usuarios)
      this.empleados = usuarios
    })
  }

}
