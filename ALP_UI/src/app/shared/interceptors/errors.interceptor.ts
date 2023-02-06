import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse,
} from '@angular/common/http';
import { catchError, Observable, throwError } from 'rxjs';

@Injectable()
export class ErrorsInterceptor implements HttpInterceptor {
  constructor() {}

  intercept(
    request: HttpRequest<unknown>,
    next: HttpHandler
  ): Observable<HttpEvent<unknown>> {
    console.log("I am request : ",request)
    return next.handle(request).pipe(
      catchError((error: HttpErrorResponse) => {
        console.log("I am error ",error)
        if (error.status === 0) {
          console.error('An error occurred:', error);
        } else {
          console.error(
            `Backend returned code ${error.status}, body was: `,
            error.error
          );
        }
        return throwError(
          () =>
            new Error(
              'Something bad happened; we are aware of this, please wait for us to resolve this'
            )
        );
      })
    );
  }
}
