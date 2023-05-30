import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Usuario } from 'src/app/models/dtos/Usuario';
import { UsuarioService } from 'src/app/services/usuario/usuario.service';

@Component({
  selector: 'app-usuariolist',
  templateUrl: './usuariolist.component.html',
  styleUrls: ['./usuariolist.component.css']
})
export class UsuariolistComponent implements OnInit {

  usuarios?: Usuario[];

  constructor(private usuarioService: UsuarioService, private router:Router) { }

  ngOnInit(): void {
    this.usuarioService.getUsuario().subscribe(
      data=>{
        this.usuarios=data;
        console.log(data);
      },
      error=>{
        console.log(error);
      }
    );
  }

  eliminar(usuario: Usuario):void {
    this.usuarioService.deleteUsuario(usuario).subscribe(data=>{
      this.usuarios=this.usuarios!.filter(u=>u!==usuario);
    });
  }
}
