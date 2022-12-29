import { Component, OnInit } from '@angular/core';

import { Roles,Streams } from '../models';

@Component({
  selector: 'plt-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {
  values = Object.values;
  roles = Roles;
  streams = Streams;
  constructor() {}

  ngOnInit(): void {
    console.log('I am on init' + this.roles.ADMIN);
  }

  validRole(role: string): Boolean {
    return (
      role == this.roles.ADMIN ||
      role === this.roles.SUPER_ADMIN ||
      role == this.roles.TRAINEE ||
      role === this.roles.TRAINER
    );
  }
}
