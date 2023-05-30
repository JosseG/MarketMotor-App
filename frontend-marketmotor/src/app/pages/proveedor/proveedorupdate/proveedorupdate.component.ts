import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Proveedor } from 'src/app/models/dtos/Proveedor';

import { ProveedorService } from 'src/app/services/proveedor/proveedor.service';

@Component({
  selector: 'app-proveedorupdate',
  templateUrl: './proveedorupdate.component.html',
  styleUrls: ['./proveedorupdate.component.css']
})
export class ProveedorupdateComponent {

  constructor(private proveedorService: ProveedorService, private router:Router) { }

  editar(proveedor: Proveedor): void{
    localStorage.setItem("id",proveedor.id.toString());
      this.router.navigate(['editarProveedor']);
  }
}
