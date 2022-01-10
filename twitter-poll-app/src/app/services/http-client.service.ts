import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { ConfigurationService } from 'src/app/services/configuration.service';
import swal from 'sweetalert2';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HttpClientService {
  constructor(private httpclient: HttpClient, private config: ConfigurationService,
    private cookieService: CookieService, private router: Router) {

  }

  public setUserData(userName: string, password: string, userId: any) {
    this.cookieService.set('user_name', userName, 1, '/', '', false, "Lax");
    this.cookieService.set('password', password, 1, '/', '', false, "Lax");
    this.cookieService.set('user_id', userId, 1, '/', '', false, "Lax");
  }

  public getLogedInUserId(): any {
    console.log(this.getCookie('user_id'));
    return this.getCookie('user_id');
  }

  public login(url: string) {
    const httpOptions = {
      headers: ({
        'Content-Type': 'application/json',
      })
    };
    return this.httpclient.get(this.config.serverWithApiUrl + url, httpOptions);
  }
  public logout() {
    this.router.navigate(['']);
    this.cookieService.deleteAll();
  }

  public getCookie(key: string) {
    return this.cookieService.get(key);
  }

  public getData(url: string): any {
    let userName = this.getCookie('user_name');
    let password = this.getCookie('password');
    let combine = userName + ":" + password;
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Basic ' + btoa(combine)
      })
    };
    return this.httpclient.get(this.config.serverWithApiUrl + url, httpOptions);
  }

  public postData(url: string, data: any): any {
    let userName = this.getCookie('user_name');
    let password = this.getCookie('password');
    let combine = userName + ":" + password;
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Basic ' + btoa(combine)
      })
    };
    return this.httpclient.post(this.config.serverWithApiUrl + url, data, httpOptions);
  }


  public showWarningAlert(message: any) {
    swal({
      title: 'Alert',
      text: message,
      type: 'info',
      confirmButtonText: 'OK',
      allowOutsideClick: true
    }).catch(swal.noop);
  }

}
