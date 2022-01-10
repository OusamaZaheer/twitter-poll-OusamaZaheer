
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClientService } from '../services/http-client.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  user: any = {};
  constructor(private router: Router, private httpClientService: HttpClientService) { }
  ngOnInit(): void {
  }

  validateLogin(): boolean {
    if (this.user.username === undefined || this.user.username === null || this.user.username.length === 0) {
      return false;
    } if (this.user.password === undefined || this.user.password === null || this.user.password.length === 0) {
      return false;
    } else {
      return true;
    }
  }

  login() {
    if (this.validateLogin()) {
      const url = 'public/login?userName=' + this.user.username + '&password=' + this.user.password;
      this.httpClientService.login(url).subscribe(
        (data) => {
          this.httpClientService.setUserData(this.user.username, this.user.password, data['id'])
          this.router.navigate(['poll']);
        },
        (err) => {
          console.log(err);
        }
      );
    }
  }

  formSubmit(event: any) {

    if (event.keyCode === 13) {
      this.login();
    }
  }

}
