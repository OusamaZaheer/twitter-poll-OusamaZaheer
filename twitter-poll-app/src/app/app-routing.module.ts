import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddNewPollComponent } from './add-new-poll/add-new-poll.component';
import { LoginComponent } from './login/login.component';
import { PollResultBarChartComponent } from './poll-result-bar-chart/poll-result-bar-chart.component';
import { PollsComponent } from './polls/polls.component';

const routes: Routes = [
  {
    path: 'poll',
    component: PollsComponent,
  },
  {
    path: '',
    component: LoginComponent,
  },
  {
    path: 'new-poll',
    component: AddNewPollComponent,
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
