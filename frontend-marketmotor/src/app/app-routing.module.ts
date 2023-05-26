import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProductolistComponent } from './pages/producto/productolist/productolist.component';
import { EmpleadolistComponent } from './pages/empleado/empleadolist/empleadolist.component';
import { UsuariolistComponent } from './pages/usuario/usuariolist/usuariolist.component';
import { ProveedorlistComponent } from './pages/proveedor/proveedorlist/proveedorlist.component';
import { InicioComponent } from './pages/inicio/inicio.component';

const routes: Routes = [
  { path: 'inicio', component: InicioComponent },
  { path: 'productos', component: ProductolistComponent },
  { path: 'empleados', component: EmpleadolistComponent },
  { path: 'usuarios', component: UsuariolistComponent },
  { path: 'proveedores', component: ProveedorlistComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }