import {HttpClient} from '@angular/common/http';
import {Component, ViewChild, AfterViewInit} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort, SortDirection} from '@angular/material/sort';
import {merge, Observable, of as observableOf} from 'rxjs';
import {catchError, map, startWith, switchMap} from 'rxjs/operators';
import { Users } from 'src/app/interfaces/users';
import { AdminService } from './service/admin.service';

@Component({
  selector: 'plt-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent {

  displayedColumns: string[] = ['Name', 'Surname', 'DOB', 'Email','Github', 'PhoneNumber','StartDate', 'Stream', 'Role'];
  exampleDatabase !: AdminService | null;
  data: Users[] = [];

  resultsLength = 0;
  isLoadingResults = true;
  isRateLimitReached = false;

  @ViewChild(MatPaginator) paginator !: MatPaginator;
  @ViewChild(MatSort) sort !: MatSort;

  constructor(private _httpClient: HttpClient) {}

  ngAfterViewInit() {
    this.exampleDatabase = new AdminService(this._httpClient);

    this.sort.sortChange.subscribe(() => (this.paginator.pageIndex = 0));

    merge(this.sort.sortChange, this.paginator.page)
      .pipe(
        startWith({}),
        switchMap(() => {
          this.isLoadingResults = true;
          return this.exampleDatabase!.getAllUser().pipe(catchError(() => observableOf(null)));
        }),
        map(data => {
          this.isLoadingResults = false;
          this.isRateLimitReached = data === null;

          if (data === null) {
            return [];
          }
         // this.resultsLength = datatotalCount.;
          this.resultsLength = 0;
          console.log("I am data start")
          console.log(data)
          console.log("I am data done")
          return data;
        }),
      )
      .subscribe(data => (this.data = data));
  }

}