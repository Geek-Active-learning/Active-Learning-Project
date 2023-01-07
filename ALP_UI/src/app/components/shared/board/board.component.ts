import {
  CdkDragDrop,
  moveItemInArray,
  transferArrayItem,
} from '@angular/cdk/drag-drop';
import { HttpClient } from '@angular/common/http';
import {
  Component,
  ViewChild,
  AfterViewInit,
  ChangeDetectionStrategy,
} from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort, SortDirection } from '@angular/material/sort';
import { merge, Observable, of as observableOf } from 'rxjs';
import { catchError, map, startWith, switchMap } from 'rxjs/operators';
import { Constants } from 'src/app/helpers/constants';
import Filter from 'src/app/helpers/filter';
import { Card } from 'src/app/interfaces/cards';
import { Users } from 'src/app/interfaces/users';
import { Board } from 'src/app/models/board/board.model';
import { CardStatus } from 'src/app/models/card/cardStatus';
import { Column } from 'src/app/models/board/column.model';
// import { AccountService } from 'src/app/services/account';

const ELEMENT_DATA: Card[] = [
  {
    id: '1',
    name: 'Add a little ajax',
    flavours: ['js', 'jquery'],
    githubUrl: 'mudzanani@github.com',
    prerequisite: [],
    reviewers: ['mudzanani27@gmail.com'],
    status: 'IN_BACKLOG',
  },

  {
    id: '2',
    name: 'Random email inspiration',
    flavours: ['nodejs', 'expressjs'],
    githubUrl: 'mudzanani27@github.com',
    prerequisite: [],
    reviewers: ['mudzanani27@gmail.com'],
    status: 'IN_BACKLOG',
  },
  {
    id: '3',
    name: 'Expose express js',
    flavours: ['js', 'mongoDb'],
    githubUrl: 'mudzanani@github.com',
    prerequisite: ['Random email inspiration'],
    reviewers: ['mudzanani27@gmail.com'],
    status: 'IN_PROGRESS',
  },
  {
    id: '4',
    name: 'Expose express js',
    flavours: ['js', 'mongoDb'],
    githubUrl: 'mudzanani@github.com',
    prerequisite: ['Random email inspiration'],
    reviewers: ['mudzanani27@gmail.com'],
    status: 'IN_PROGRESS',
  },
  {
    id: '5',
    name: 'Expose express js',
    flavours: ['js', 'mongoDb'],
    githubUrl: 'mudzanani@github.com',
    prerequisite: ['Random email inspiration'],
    reviewers: ['mudzanani27@gmail.com'],
    status: 'IN_PROGRESS',
  },
];

@Component({
  selector: 'plt-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css'],
})
export class BoardComponent {
  keys = Object.keys;

  inBacklogColumn = new Column(
    Constants.IN_BACKLOG,
    CardStatus.BACKLOG,
    new Filter(ELEMENT_DATA, CardStatus.BACKLOG).filter()
  );
  inProgressColumn = new Column(
    Constants.IN_PROGRESS,
    CardStatus.PROGRESS,
    new Filter(ELEMENT_DATA, CardStatus.PROGRESS).filter()
  );

  inReviewRequestColumn = new Column(
    Constants.IN_REQUESTED_REVIEW,
    CardStatus.REQUESTED_REVIEW,
    new Filter(ELEMENT_DATA, CardStatus.REQUESTED_REVIEW).filter()
  );

  inReviewColumn = new Column(
    Constants.IN_REVIEW,
    CardStatus.REVIEW,
    new Filter(ELEMENT_DATA, CardStatus.REVIEW).filter()
  );

  inCompletedColumn = new Column(
    Constants.IN_COMPLETED,
    CardStatus.COMPLETED,
    new Filter(ELEMENT_DATA, CardStatus.COMPLETED).filter()
  );

  public board: Board = new Board('BOARD', [
    this.inBacklogColumn,
    this.inProgressColumn,
    this.inReviewRequestColumn,
    this.inReviewColumn,
    this.inCompletedColumn
  ]);

  resultsLength = 0;
  isLoadingResults = false;
  isRateLimitReached = false;

  constructor() {}

  public ngOnInit(): void {
    // console.log('I started here');
    // console.log(this.board.columns);
    // console.log('I ended here');
  }

  public dropGrid(event: CdkDragDrop<Card[]>): void {
    moveItemInArray(
      this.board.columns,
      event.previousIndex,
      event.currentIndex
    );
  }

  public drop(event: CdkDragDrop<Card[]>): void {
    if (event.previousContainer === event.container) {
      moveItemInArray(
        event.container.data,
        event.previousIndex,
        event.currentIndex
      );
    } else {
      transferArrayItem(
        event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex
      );
    }
  }
}
