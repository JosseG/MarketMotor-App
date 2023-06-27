import { Component, OnInit } from '@angular/core';
import { Venta } from 'src/app/models/dtos/Venta';

@Component({
  selector: 'app-reportetemplateventa',
  templateUrl: './reportetemplateventa.component.html',
  styleUrls: ['./reportetemplateventa.component.css']
})
export class ReportetemplateventaComponent implements OnInit {


  ventas : Venta[] = []

  map = new Map<number,Venta>()
  ngOnInit(){
    this.getListReport();
  }

  total = 0;

  getListReport(){


    var elementos = sessionStorage.getItem('reporteVentasFiltred');
    if (elementos == null) {
    }else{
      this.map = new Map(JSON.parse(elementos));
      this.ventas = Array.from(this.map.values())

      for( let i of this.ventas){
        this.total += i.preciototal
      }
    }

    
  }
}
