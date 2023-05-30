import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UsuarioupdateComponent } from './usuario/usuarioupdate/usuarioupdate.component';
import { UsuarioinsertComponent } from './usuario/usuarioinsert/usuarioinsert.component';
import { UsuariolistComponent } from './usuario/usuariolist/usuariolist.component';
import { ProveedorlistComponent } from './proveedor/proveedorlist/proveedorlist.component';
import { ProveedorinsertComponent } from './proveedor/proveedorinsert/proveedorinsert.component';
import { ProveedorupdateComponent } from './proveedor/proveedorupdate/proveedorupdate.component';
import { ProductoinsertComponent } from './producto/productoinsert/productoinsert.component';
import { ProductoupdateComponent } from './producto/productoupdate/productoupdate.component';
import { ProductolistComponent } from './producto/productolist/productolist.component';
import { EmpleadoupdateComponent } from './empleado/empleadoupdate/empleadoupdate.component';
import { EmpleadoinsertComponent } from './empleado/empleadoinsert/empleadoinsert.component';
import { EmpleadolistComponent } from './empleado/empleadolist/empleadolist.component';
import { LoginComponent } from './login/login.component';
import { InicioComponent } from './inicio/inicio.component';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    UsuarioupdateComponent,
    UsuarioinsertComponent,
    UsuariolistComponent,
    ProveedorlistComponent,
    ProveedorinsertComponent,
    ProveedorupdateComponent,
    ProductoinsertComponent,
    ProductoupdateComponent,
    ProductolistComponent,
    EmpleadoupdateComponent,
    EmpleadoinsertComponent,
    EmpleadolistComponent,
    LoginComponent,
    InicioComponent,

  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule
  ]
})
export class PagesModule { }
