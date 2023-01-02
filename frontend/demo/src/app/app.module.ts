import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FooterComponent } from './components/shared/footer/footer.component';
import { HeaderComponent } from './components/shared/header/header.component';
import { AdminComponent } from './components/admin/admin.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from './shared/modules/material-modules';
import { SidenavComponent } from './components/shared/sidenav/sidenav.component';
import { LoginComponent } from './components/account/login/login.component';
import { HttpClientModule } from '@angular/common/http';
import {RegisterComponent} from './components/account/register/register.component';
import { HomeComponent } from './components/home/home.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { BoardComponent } from './components/shared/board/board.component';
import { ViewUsersComponent } from './components/shared/view-users/view-users.component';
import { CardComponent } from './components/shared/board/card/card.component';
import { DragDropModule } from '@angular/cdk/drag-drop';


@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    HeaderComponent,
    AdminComponent,
    SidenavComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    DashboardComponent,
    BoardComponent,
    ViewUsersComponent,
    CardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule, 
    MaterialModule,
    HttpClientModule,
    FormsModule, 
    ReactiveFormsModule,
    DragDropModule 
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
