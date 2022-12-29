import { TestBed } from '@angular/core/testing';

import { AccountService } from './admin.service';

describe('AdminService', () => {
  let service: AccountService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AccountService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
