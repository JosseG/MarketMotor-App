import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from './navbar/navbar.component';
import { AppRoutingModule } from '../app-routing.module';
import { RouterModule } from '@angular/router';
import { PaginationComponent } from './pagination/pagination.component';



@NgModule({
  declarations: [
    NavbarComponent,
    PaginationComponent,
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
