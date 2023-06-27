import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ProveedorUpdate } from 'src/app/models/commands/proveedor/ProveedorUpdate';
import { Proveedor } from 'src/app/models/dtos/Proveedor';
import { ProveedorResponse } from 'src/app/models/responseapi/ProveedorResponse';
import { ProveedorService } from 'src/app/services/proveedor/proveedor.service';

@Component({
  selector: 'app-proveedorlist',
  templateUrl: './proveedorlist.component.html',
  styleUrls: ['./proveedorlist.component.css']
})
export class ProveedorlistComponent {

  currentPage = 1;
  total = 0;
  itemsPerPage = 6;

  proveedores: Proveedor[] = [];
  proveedorPaginable: ProveedorResponse = new ProveedorResponse();


  constructor(private proveedorService: ProveedorService, private router: Router) { }

  ngOnInit(): void {
    this.getPaginableProveedor();
  }

  getPaginableProveedor() {
    this.proveedorService
      .getAllByPaginable(this.currentPage, this.itemsPerPage)
      .subscribe({
        next: (data: any) => {
          this.proveedorPaginable = data;
          this.total = this.proveedorPaginable.totalElements;
          this.proveedores = this.proveedorPaginable.content;
          console.log(data);
        },
        error: (e: string) => {
          console.log('Error ' + e);
        },
      });
  }

  pageChangeEvent(event: number) {
    this.currentPage = event;
    this.getPaginableProveedor();
  }

  editar(proveedor:ProveedorUpdate): void{
    localStorage.setItem("id",proveedor.id.toString());
      this.router.navigate(['proveedores/update']);
  }
  eliminar(proveedor:Proveedor):void{
    this.proveedorService.deleteProveedor(proveedor).subscribe(data=>{
          this.proveedores=this.proveedores!.filter(p=>p!=proveedor);
        
    });
  }

 
}
