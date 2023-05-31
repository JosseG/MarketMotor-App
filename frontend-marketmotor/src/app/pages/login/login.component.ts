import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { AuthService } from 'src/app/services/auth/auth.service';
import { StorageService } from '../../services/storage/storage.service';
import { Empleado } from '../../models/dtos/Empleado';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  formulario: FormGroup = this.formbuilder.group({
    alias:[],
    contrasena:[],
  })

  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';

  constructor(private authService:AuthService,private formbuilder: FormBuilder,private router:Router, private storageService: StorageService){
    localStorage.clear();
  }

  ngOnInit(): void {
    if (this.storageService.getToken()) {
      this.isLoggedIn = true;
    }
  }


  login(){
    this.authService.login(this.formulario.get("alias")?.value,this.formulario.get("contrasena")?.value).subscribe(result => {

        this.storageService.saveToken(result.access_token)
        this.storageService.saveRefreshToken(result.refresh_token)

        this.router.navigate([''])
      },
      err => {
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
      }
    );
  }

  reloadPage(): void {
    window.location.reload();
  }





}
