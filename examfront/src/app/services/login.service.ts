import { HttpClient } from '@angular/common/http';
import { tokenReference } from '@angular/compiler';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  public loginStatusObject = new Subject<boolean>();
  constructor(private http: HttpClient) { }

  //current user which is llogend in
  public getCurrentUser() {
    return this.http.get(`${environment.baseUrl}/current-user`)
  }

  //generate token
  public generateToken(loginData: any) {
    return this.http.post(`${environment.baseUrl}/generate-token`, loginData)
  }

  //Login user :set token in localstorage
  public loginUser(token: any) {
    localStorage.setItem("token", token);
    return true;

  }

  //islogin:user is logged in or not
  public isLoggedIn() {
    let tokenStr = localStorage.getItem('token');
    if (tokenStr == undefined || tokenStr == '' || tokenStr == null) {
      return false;
    } else {
      return true;
    }
  }
  //Logout:remove token from local storage
  public logout() {
    localStorage.removeItem("token");
    localStorage.removeItem("user");
    return true;
  }

  //get token 
  public getToken() {
    return localStorage.getItem("token");
  }

  //set user details
  public setUser(user: any) {
    localStorage.setItem("user", JSON.stringify(user));
  }

  //get user

  public getUser() {
    let userStr = localStorage.getItem("user");
    if (userStr != null) {
      return JSON.parse(userStr);
    }
    else {
      this.logout();
      return null;
    }
  }


  //get user role
  public getUserRole() {
    let user = this.getUser();
    return user.authorities[0].authority;
    //return user.authorities;

  }
}
