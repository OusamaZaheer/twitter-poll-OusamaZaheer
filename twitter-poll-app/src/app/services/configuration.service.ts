import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ConfigurationService {
  public server = 'http://localhost:8080';
  public apiUrl = '/api/';
  public serverWithApiUrl = this.server + this.apiUrl;


}
