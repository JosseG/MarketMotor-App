import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Proveedor } from 'src/app/models/dtos/Proveedor';
import { ProveedorService } from 'src/app/services/proveedor/proveedor.service';

@Component({
  selector: 'app-proveedorlist',
  templateUrl: './proveedorlist.component.html',
  styleUrls: ['./proveedorlist.component.css']
})
export class ProveedorlistComponent {

  proveedores?: Proveedor[];

  constructor(private proveedorService: ProveedorService, private router: Router) { }

  ngOnInit(): void {
    this.proveedorService.getProveedor().subscribe({
      next: (data: any) => {
        this.proveedores = data;
        console.log(data);
      },
      error: (e) =>
        console.log("Error " + e)
    });
  }

  eliminar(proveedor: Proveedor): void {
    this.proveedorService.deleteProveedor(proveedor).subscribe(data => {
      this.proveedores = this.proveedores!.filter(e => e !== proveedor);
    });
  }

}
