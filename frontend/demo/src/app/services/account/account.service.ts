import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { HttpClient } from "@angular/common/http";
import { BehaviorSubject, Observable } from "rxjs";
import { map } from "rxjs/operators";
import { environment } from "environment";
import { Users } from "src/app/interfaces/users";


@Injectable({
  providedIn: 'root'
})
export class AccountService {
  private userSubject!: BehaviorSubject<Users>;
  public users!: Observable<Users>;

  constructor(private router: Router, private http: HttpClient) {
    this.userSubject = new BehaviorSubject<Users>(
      JSON.parse(localStorage.getItem("user")!)
    );
   this.users = this.userSubject.asObservable();
  }

  public get userValue(): Users {
    return this.userSubject.value;
  }

  login(email:string, password:string) {
    return this.http
      .post<Users>(`${environment.apiUrl}/authenticate`, {
        email,
        password
      })
      .pipe(
        map(user => {
          // store user details and jwt token in local storage to keep user logged in between page refreshes
          localStorage.setItem("user", JSON.stringify(user));
          this.userSubject.next(user);
          
          return user;
        })
      );
  }

  logout() {
    // remove user from local storage and set current user to null
    localStorage.removeItem("user");
    // this.userSubject.next(null);s
    this.router.navigate(["/account/login"]);
  }

  register(user: Users) {
    return this.http.post(`${environment.apiUrl}/register`, user);
  }

  getAllUser(): Observable<Users[]>{
    return this.http.get<Users[]>(`${environment.apiUrl}`);
  }



  getById(userId: string):Observable<Users> {
    return this.http.get<Users>(environment.apiUrl + "/"+userId);
  }

  update(id:string, params:Users) {
    return this.http.put(`${environment.apiUrl}/${id}`, params);
    //Admin should have a privilege to delete admin
    //Only super_admin can do this and Admin
    // return this.http.put(`${environment.apiUrl}/users/${id}`, params).pipe(
    //   map(x => {
    //     // update stored user if the logged in user updated their own record
    //     if (id == this.userValue.id) {
    //       // update local storage
    //       const user = { ...this.userValue, ...params };
    //       localStorage.setItem("user", JSON.stringify(user));

    //       // publish updated user to subscribers
    //       this.userSubject.next(user);
    //     }
    //     return x;
    //   })
    // );
  }

  delete(id: string) {
    console.log(id);
    return this.http.delete(`${environment.apiUrl}/${id}`);
    // return this.http.delete(`${environment.apiUrl}/users/${id}`).pipe(
    //   map(x => {
    //     // auto logout if the logged in user deleted their own record
    //     if (id == this.userValue.id) {
    //       this.logout();
    //     }
    //     return x;
    //   })
    // );
  }
}
