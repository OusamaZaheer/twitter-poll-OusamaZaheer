import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { PollsComponent } from './polls/polls.component';
import { AddNewPollComponent } from './add-new-poll/add-new-poll.component';
import { FormsModule } from '@angular/forms';
import { ModalModule } from 'ngx-bootstrap';
import { CookieService } from 'ngx-cookie-service';
import { HttpClientModule } from '@angular/common/http';
import { PollResultBarChartComponent } from './poll-result-bar-chart/poll-result-bar-chart.component';
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    PollsComponent,
    AddNewPollComponent,
    PollResultBarChartComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ModalModule.forRoot()
  ],
  entryComponents:[AddNewPollComponent, PollResultBarChartComponent],
  providers: [CookieService],
  bootstrap: [AppComponent]
})
export class AppModule { }
