import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth/auth.service';
import { ShortinfouserService } from 'src/app/services/shortinfouser/shortinfouser.service';
import { StorageService } from 'src/app/services/storage/storage.service';



@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {

  cargo = "";
  usuario = "";

  ngOnInit():void{
    this.showCargo();
    this.showUsuario();
  }

  constructor(private authService: AuthService,private sesionService:StorageService,private shortInfo:ShortinfouserService,private router:Router){
    
  }

  logout():void{
    this.authService.logout()
    sessionStorage.clear()
    this.router.navigate(['login'])
  }

  showCargo(){
    this.cargo = this.shortInfo.getNombreCargo()
  }

  showUsuario(){
    this.usuario = this.shortInfo.getAlias()
  }

}
