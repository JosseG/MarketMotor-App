import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProductolistComponent } from './pages/producto/productolist/productolist.component';
import { EmpleadolistComponent } from './pages/empleado/empleadolist/empleadolist.component';
import { UsuariolistComponent } from './pages/usuario/usuariolist/usuariolist.component';
import { ProveedorlistComponent } from './pages/proveedor/proveedorlist/proveedorlist.component';
import { InicioComponent } from './pages/inicio/inicio.component';
import { ProductoinsertComponent } from './pages/producto/productoinsert/productoinsert.component';
import { EmpleadoinsertComponent } from './pages/empleado/empleadoinsert/empleadoinsert.component';
import { LoginComponent } from './pages/login/login.component';
import { AuthGuard } from './_shared/auth.guard';
import { AdministradorGuard } from './_shared/administrador.guard';
import { GenerarventaComponent } from './pages/venta/generarventa/generarventa.component';
import { GenerarordencompraComponent } from './pages/ordencompra/generarordencompra/generarordencompra.component';
import { ReporteordencompraComponent } from './pages/ordencompra/reporteordencompra/reporteordencompra.component';
import { ReporteventaComponent } from './pages/venta/reporteventa/reporteventa.component';

const routes: Routes = [
  { path: 'inicio', component: InicioComponent,canActivate:[AuthGuard] },
  { path: 'productos', component: ProductolistComponent,canActivate:[AuthGuard,AdministradorGuard] },
  { path: 'productos/insertar', component: ProductoinsertComponent,canActivate:[AuthGuard] },
  { path: 'empleados', component: EmpleadolistComponent,canActivate:[AuthGuard] },
  { path: 'empleados/insertar', component: EmpleadoinsertComponent,canActivate:[AuthGuard] },
  { path: 'empleados/actualizar/:id', component: EmpleadoinsertComponent,canActivate:[AuthGuard] },
  { path: 'usuarios', component: UsuariolistComponent,canActivate:[AuthGuard] },
  { path: 'proveedores', component: ProveedorlistComponent,canActivate:[AuthGuard] },

  { path: 'venta/reporte', component: ReporteventaComponent,canActivate:[AuthGuard] },
  { path: 'ordencompra/reporte', component: ReporteordencompraComponent,canActivate:[AuthGuard] },
  { path: 'ordencompra', component: GenerarordencompraComponent,canActivate:[AuthGuard] },
  { path: 'venta', component: GenerarventaComponent,canActivate:[AuthGuard] },
  { path: 'login', component: LoginComponent },
  { path: '', redirectTo: 'inicio', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }