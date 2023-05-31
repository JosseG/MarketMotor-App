import { Component } from '@angular/core';
import { StorageService } from './services/storage/storage.service';
import { Subscription } from 'rxjs';
import { Router } from '@angular/router';
import { AuthService } from './services/auth/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontend-marketmotor';



  private roles: string[] = [];
  isLoggedIn = false;
  displaymenu=false;

  eventBusSub?: Subscription;

  constructor(private tokenStorageService: StorageService,private authService: AuthService,private route:Router) { }

  ngDoCheck(): void {
    if (this.route.url == '/login') {
      this.displaymenu = false
    } else {
      this.displaymenu = true
    }
  }

  ngOnDestroy(): void {
    if (this.eventBusSub) {
      this.eventBusSub.unsubscribe();
    }
  }



  /*logout(): void {
    this.tokenStorageService.signOut();

    this.isLoggedIn = false;
    this.roles = [];
  }*/
}
