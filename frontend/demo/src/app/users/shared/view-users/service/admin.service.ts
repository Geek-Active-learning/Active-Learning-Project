import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Users } from 'src/app/interfaces/users';
import { environment } from 'environment';

@Injectable({
  providedIn: 'root'
})
export class AdminService{


  constructor(private _httpClient: HttpClient) { }

  getAllUser(): Observable<Users[]>{
    return this._httpClient.get<Users[]>(`${environment.apiUrl}`);
  }

  getUser(userId:String): Observable<Users>{
    return this._httpClient.get<Users>(`${environment.apiUrl + userId}`);
  }

}
