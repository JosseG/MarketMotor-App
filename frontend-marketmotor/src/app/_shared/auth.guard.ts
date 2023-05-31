import { Injectable } from '@angular/core';
import {  CanActivate, Router } from '@angular/router';
import { StorageService } from '../services/storage/storage.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private service:StorageService,private route:Router){
  }

  canActivate(){
    if(this.service.existToken()){
    return true;
    }else{
      this.route.navigate(['login'])
      return false;
    }
  }
  
}
