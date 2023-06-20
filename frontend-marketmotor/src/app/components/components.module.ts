import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from './navbar/navbar.component';
import { AppRoutingModule } from '../app-routing.module';
import { RouterModule } from '@angular/router';
import { PaginationComponent } from './pagination/pagination.component';
import { ReportetemplateComponent } from './reportetemplate/reportetemplate.component';



@NgModule({
  declarations: [
    NavbarComponent,
    PaginationComponent,
    ReportetemplateComponent
  ],
  imports: [
    CommonModule,
    RouterModule
  ],
  exports: [
    NavbarComponent,
    PaginationComponent
  ]
})
export class ComponentsModule { }
