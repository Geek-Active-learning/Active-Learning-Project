import { User } from './../../../../../models/account/user';
import { Inject, Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { Users } from 'src/app/interfaces/users';
import { AppConfig } from 'src/app/shared/services/AppConfig/app-config..interface';
import { APP_SERVICE_CONFIG } from 'src/app/shared/services/AppConfig/app-config.service';

@Injectable({
  providedIn: 'root',
})
export class AccountService {
  [x: string]: any;
  private userSubject!: BehaviorSubject<Users>;
  public users!: Observable<Users>;

  constructor(
    @Inject(APP_SERVICE_CONFIG) private _config: AppConfig,
    private router: Router,
    private http: HttpClient
  ) {
    this.userSubject = new BehaviorSubject<Users>(
      JSON.parse(localStorage.getItem('user')!)
    );
    this.users = this.userSubject.asObservable();
  }

  public get userValue(): Users {
    return this.userSubject.value;
  }

  login(email: string, password: string) {
    return this.http
      .post<Users>(`${this._config.apiRoute}/authenticate`, {
        email,
        password,
      })
      .pipe(
        map((user) => {
          // store user details and jwt token in local storage to keep user logged in between page refreshes
          localStorage.setItem('user', JSON.stringify(user));
          this.userSubject.next(user);

          return user;
        })
      );
  }

  logout() {
    // remove user from local storage and set current user to null
    localStorage.removeItem('user');
    // this.userSubject.next(null);s
    this.router.navigate(['/account/login']);
  }

  register(user: User) {
    //Something is wrong to fix, working for now
    const userModel = {
      name: user.name,
      surname: user.surname,
      dob: user.dob,
      github: user.github,
      email: user.email,
      phoneNumber: user.phoneNumber,
      role: user.roles,
      startDate: user.startDate,
      firstCourse: user.firstCourse,
      secondCourse: user.secondCourse
    };

    return this.http.post(
      `${this._config.apiRoute}/register`,
      userModel,
    );
  }

  getAllUser(): Observable<Users[]> {
    return this.http.get<Users[]>(`${this._config.apiRoute}`);
  }

  getById(userId: string): Observable<Users> {
    return this.http.get<Users>(this._config.apiRoute + '/' + userId);
  }

  update(id: string, params: Users) {
    return this.http.put(`${this._config.apiRoute}/${id}`, params);
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
    return this.http.delete(`${this._config.apiRoute}/${id}`);
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
