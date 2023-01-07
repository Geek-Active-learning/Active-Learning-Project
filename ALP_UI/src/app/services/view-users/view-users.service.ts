import { APP_SERVICE_CONFIG,APP_CONFIG } from './../../shared/services/AppConfig/appconfig.service';
import { HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { config, Observable } from 'rxjs';
import { Users } from 'src/app/interfaces/users';
import { environment } from 'environment';
import { AppConfig } from './../../shared/services/AppConfig/appconfig.interface';

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
