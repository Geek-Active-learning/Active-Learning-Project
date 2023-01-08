import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';

import {
  AccountService,
  AlertService,
} from 'src/app/components/account/shared/services/account';

import { Roles, Courses } from 'src/app/models';

@Component({
  selector: 'alp-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {
  values = Object.values;
  roles = Roles;
  courses = Courses;
  registerForm!: FormGroup;
  loading = false;
  submitted = false;
  isAnonymous = false;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private accountService: AccountService,
    private alertService: AlertService
  ) {}

  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      name: ['', Validators.required, Validators],
      surname: ['', Validators.required],
      dob: ['', Validators.required],
      githubUsername: ['', Validators.required],
      phoneNumber: ['', Validators.required],
      startDate: ['', Validators.required],
      role: ['', Validators.required],
      course: ['', Validators.required],
    });
    this.resetToDefaultValues();
  }

  resetToDefaultValues() {
    setTimeout(() => {
      this.registerForm.controls['role'].setValue(this.roles.selectRole);
      this.registerForm.controls['course'].setValue(this.courses.selectCourse);
      this.registerForm.controls['startDate'].setValue(new Date().toISOString().split("T")[0]);
    }, 0);
  }

  isDefaultRole(role: Roles): boolean {
    return role === Roles.selectRole;
  }

  isDefaultCourse(course: Courses): boolean {
    return course === Courses.selectCourse;
  }

  validRole(role: string): Boolean {
    return (
      role === this.roles.ADMIN ||
      role === this.roles.SUPER_ADMIN ||
      role === this.roles.TRAINEE ||
      role === this.roles.TRAINER
    );
  }

  get controls() {
    return this.registerForm.controls;
  }

  onRegister() {
    this.submitted = true;

    // reset alerts on submit
    this.alertService.clear();

    // stop here if form is invalid
    if (this.registerForm.invalid) {
      return;
    }

    this.loading = true;
    this.accountService
      .register(this.registerForm.value)
      .pipe(first())
      .subscribe(
        (data) => {
          console.log('Registration successfully');
          this.alertService.success('Registration successful', {
            keepAfterRouteChange: true,
          });
          this.router.navigate(['../login'], { relativeTo: this.route });
        },
        (error) => {
          console.log('error happen : ' + error.message);
          this.alertService.error(error);
          this.loading = false;
        }
      );
  }
  onReset() {
    this.resetToDefaultValues();
    this.submitted = false;
    this.registerForm.reset();
  }
}
