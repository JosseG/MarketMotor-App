import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UsuarioService } from 'src/app/services/usuario/usuario.service';

@Component({
  selector: 'app-usuarioinsert',
  templateUrl: './usuarioinsert.component.html',
  styleUrls: ['./usuarioinsert.component.css']
})
export class UsuarioinsertComponent {

  constructor(private usuarioService: UsuarioService, private router:Router) { }


  nuevo():void {
    this.router.navigate(['nuevoUsuario']);
  }
}
