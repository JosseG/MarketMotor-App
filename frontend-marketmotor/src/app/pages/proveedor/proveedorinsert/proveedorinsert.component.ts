import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ProveedorService } from 'src/app/services/proveedor/proveedor.service';

@Component({
  selector: 'app-proveedorinsert',
  templateUrl: './proveedorinsert.component.html',
  styleUrls: ['./proveedorinsert.component.css']
})
export class ProveedorinsertComponent {

  constructor(private proveedorService: ProveedorService, private router:Router) { }

  nuevo():void {
    this.router.navigate(['nuevoProveedor']);
  }
}
