import { HttpClient } from '@angular/common/http';
import { Component, ViewChild, AfterViewInit } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort, SortDirection } from '@angular/material/sort';
import { Router } from '@angular/router';
import { merge, Observable, of as observableOf } from 'rxjs';
import { catchError, map, startWith, switchMap } from 'rxjs/operators';
import { Users } from 'src/app/interfaces/users';
import { AccountService } from 'src/app/components/account/shared/services/account';

@Component({
  selector: 'alp-view-users',
  templateUrl: './view-users.component.html',
  styleUrls: ['./view-users.component.css'],
})
export class ViewUsersComponent {
  displayedColumns: string[] = [
    'Name',
    'Surname',
    'DOB',
    'Email',
    'Github',
    'PhoneNumber',
    'StartDate',
    'Stream',
    'Role',
  ];
  data: Users[] = [];

  resultsLength = 0;
  isLoadingResults = true;
  isRateLimitReached = false;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private accountService: AccountService,private router: Router, private _httpClient: HttpClient) {}

  ngAfterViewInit() {

    this.sort.sortChange.subscribe(() => (this.paginator.pageIndex = 0));

    merge(this.sort.sortChange, this.paginator.page)
      .pipe(
        startWith({}),
        switchMap(() => {
          this.isLoadingResults = true;
          return this.accountService!.getAllUser().pipe(
            catchError(() => observableOf(null))
          );
        }),
        map((data) => {
          this.isLoadingResults = false;
          this.isRateLimitReached = data === null;

          if (data === null) {
            return [];
          }
          // this.resultsLength = datatotalCount.;
          this.resultsLength = 0;
          return data;
        })
      )
      .subscribe((data) => (this.data = data));
  }
}
