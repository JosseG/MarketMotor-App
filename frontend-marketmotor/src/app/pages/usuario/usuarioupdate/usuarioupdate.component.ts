import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Usuario } from 'src/app/models/dtos/Usuario';
import { UsuarioService } from 'src/app/services/usuario/usuario.service';

@Component({
  selector: 'app-usuarioupdate',
  templateUrl: './usuarioupdate.component.html',
  styleUrls: ['./usuarioupdate.component.css']
})
export class UsuarioupdateComponent {

  constructor(private usuarioService: UsuarioService, private router:Router) { }

  editar(usuario: Usuario): void{
    localStorage.setItem("id",usuario.id.toString());
      this.router.navigate(['editarUsuario']);
  }

}
