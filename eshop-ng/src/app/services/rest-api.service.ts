import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable()
export class RestAPIService {
  private BASE_URL = 'http://localhost:8080/api/';
  private username = '';
  private password = '';
  private token = '';

  constructor(private httpClient: HttpClient) {

  }

  // For basic Auth
  generateHeader() {
    const authorizationData = 'Basic' + btoa(this.username + ':' + this.password);
    const headerOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        // 'Authorization': authorizationData
      })
    };
    return headerOptions;
  }

  // forJwt
  generateHeaderForJwt() {
    const headerOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': this.token
      })
    };
    return headerOptions;
  }

  baseUrl(controllerName: string, actionName: string): string {
    return this.BASE_URL + controllerName + '/' + actionName;
  }
  get(controllerName: string, actionName: string) {
    return this.httpClient.get(this.baseUrl(controllerName, actionName), this.generateHeader());
  }
}
