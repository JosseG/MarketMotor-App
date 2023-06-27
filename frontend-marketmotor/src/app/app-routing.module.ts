import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProductolistComponent } from './pages/producto/productolist/productolist.component';
import { EmpleadolistComponent } from './pages/empleado/empleadolist/empleadolist.component';
import { UsuariolistComponent } from './pages/usuario/usuariolist/usuariolist.component';
import { ProveedorlistComponent } from './pages/proveedor/proveedorlist/proveedorlist.component';
import { InicioComponent } from './pages/inicio/inicio.component';
import { ProductoinsertComponent } from './pages/producto/productoinsert/productoinsert.component';
import { EmpleadoinsertComponent } from './pages/empleado/empleadoinsert/empleadoinsert.component';
import { ProveedorinsertComponent } from './pages/proveedor/proveedorinsert/proveedorinsert.component';
import { ProveedorupdateComponent } from './pages/proveedor/proveedorupdate/proveedorupdate.component';
import { LoginComponent } from './pages/login/login.component';
import { AuthGuard } from './_shared/auth.guard';
import { AdministradorGuard } from './_shared/administrador.guard';
import { GenerarventaComponent } from './pages/venta/generarventa/generarventa.component';
import { GenerarordencompraComponent } from './pages/ordencompra/generarordencompra/generarordencompra.component';
import { ReporteordencompraComponent } from './pages/ordencompra/reporteordencompra/reporteordencompra.component';
import { ReporteventaComponent } from './pages/venta/reporteventa/reporteventa.component';
import { ReportetemplateComponent } from './pages/reportetemplate/reportetemplate.component';
import { EmpleadoupdateComponent } from './pages/empleado/empleadoupdate/empleadoupdate.component';
import { ValidarordenComponent } from './pages/proveedor/validar/validarorden/validarorden.component';
import { ProveedorGuard } from './_shared/proveedor.guard';
import { ProductoupdateComponent } from './pages/producto/productoupdate/productoupdate.component';

const routes: Routes = [
  { path: 'inicio', component: InicioComponent,canActivate:[AuthGuard] },
  { path: 'productos', component: ProductolistComponent,canActivate:[AuthGuard] },
  { path: 'productos/insertar', component: ProductoinsertComponent,canActivate:[AuthGuard,AdministradorGuard] },
  { path: 'productos/actualizar', component: ProductoupdateComponent,canActivate:[AuthGuard,AdministradorGuard] },

  { path: 'empleados', component: EmpleadolistComponent,canActivate:[AuthGuard,AdministradorGuard] },
  { path: 'empleados/insertar', component: EmpleadoinsertComponent,canActivate:[AuthGuard,AdministradorGuard] },
  { path: 'empleados/actualizar', component: EmpleadoupdateComponent,canActivate:[AuthGuard,AdministradorGuard] },
 
  { path: 'usuarios', component: UsuariolistComponent,canActivate:[AuthGuard,AdministradorGuard] },

  { path: 'proveedores', component: ProveedorlistComponent,canActivate:[AuthGuard,AdministradorGuard] },
  { path: 'proveedores/insertar', component: ProveedorinsertComponent,canActivate:[AuthGuard,AdministradorGuard] },
  { path: 'proveedores/update', component: ProveedorupdateComponent,canActivate:[AuthGuard,AdministradorGuard] },
  { path: 'proveedores/orden/validar', component: ValidarordenComponent,canActivate:[AuthGuard,ProveedorGuard] },
  
  { path: 'venta/reporte', component: ReporteventaComponent,canActivate:[AuthGuard] },
  { path: 'ordencompra/reporte', component: ReporteordencompraComponent,canActivate:[AuthGuard] },
  { path: 'ordencompra', component: GenerarordencompraComponent,canActivate:[AuthGuard,AdministradorGuard] },
  { path: 'reportetemplate', component: ReportetemplateComponent,canActivate:[AuthGuard,AdministradorGuard] },
  { path: 'venta', component: GenerarventaComponent,canActivate:[AuthGuard] },
  { path: 'login', component: LoginComponent },
  { path: '', redirectTo: 'inicio', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }