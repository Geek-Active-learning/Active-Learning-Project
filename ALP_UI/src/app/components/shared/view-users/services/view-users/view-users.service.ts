import {
  APP_SERVICE_CONFIG,
  APP_CONFIG,
} from '../../../../../shared/services/AppConfig/app-config.service';
import { HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Users } from 'src/app/interfaces/users';
import { AppConfig } from 'src/app/shared/services/AppConfig/app-config..interface';

@Injectable({
  providedIn: 'root',
})
export class AdminService {
  constructor(
    @Inject(APP_SERVICE_CONFIG) private _config: AppConfig,
    private _httpClient: HttpClient
  ) {}

  getAllUser(): Observable<Users[]> {
    return this._httpClient.get<Users[]>(this._config.apiRoute);
  }

  getUser(userId: string): Observable<Users> {
    return this._httpClient.get<Users>(`${this._config.apiRoute + userId}`);
  }
}
