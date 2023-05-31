import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AccessService } from '../services/access/access.service';

@Injectable({
  providedIn: 'root'
})
export class ProveedorGuard implements CanActivate {
  constructor(private accessService:AccessService,private route:Router){
  }

  canActivate(){
    if(this.accessService.accessProv()){
     return true;
    }else{
      this.route.navigate(['']);
      return false;
    }
   }
  
}
