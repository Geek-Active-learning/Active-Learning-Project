import { LoginComponent } from './components/account/login/login.component';
import { BoardComponent } from './components/shared/board/board.component';
import { ViewUsersComponent } from './components/shared/view-users/view-users.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterComponent } from './components/account/register/register.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'users', component: ViewUsersComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'board', component: BoardComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
