import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BsModalRef, BsModalService } from 'ngx-bootstrap';
import { AddNewPollComponent } from '../add-new-poll/add-new-poll.component';
import { PollResultBarChartComponent } from '../poll-result-bar-chart/poll-result-bar-chart.component';
import { HttpClientService } from '../services/http-client.service';

@Component({
  selector: 'app-polls',
  templateUrl: './polls.component.html',
  styleUrls: ['./polls.component.scss']
})
export class PollsComponent implements OnInit {
  openPolls: any = [];
  closedPolls: any = [];
  bsModalRef: BsModalRef;
  userId: any;
  constructor(private router: Router, private httpClientService: HttpClientService, private modalService: BsModalService) { }

  ngOnInit() {
    this.userId = this.httpClientService.getLogedInUserId();
    this.populateOpenPolls();
    this.populateClosedPolls();
  }

  populateOpenPolls() {
    this.httpClientService.getData("poll/get-all-by-status?live=true").subscribe(
      (data) => {
        console.log(data);
        this.openPolls = data;

      },
      (err) => {
        console.log(err);
      }
    );

  }

  populateClosedPolls() {
    this.httpClientService.getData("poll/get-all-by-status?live=false").subscribe(
      (data) => {
        this.closedPolls = data;

      },
      (err) => {
        console.log(err);
      }
    );

  }

  newPoll() {
    this.bsModalRef = this.modalService.show(AddNewPollComponent, {});
    this.bsModalRef.content.emitData.subscribe((data) => {
      this.openPolls.push(data);
    });
  }

  vote(choiceId: any) {
    this.httpClientService.postData("poll/cast-vote?userId=" + this.userId + "&choiceId=" + choiceId, {}).subscribe(
      (data) => {
        this.populateOpenPolls();
      },
      (err) => {
        alert(err.error);
      }
    );
  }

  end(poll: any) {
    this.httpClientService.postData("poll/end?pollId=" + poll.id, {}).subscribe(
      (data) => {
        const index: number = this.openPolls.indexOf(poll);
        if (index !== -1) {
          this.openPolls.splice(index, 1);
        }
        this.closedPolls.push(data);
      },
      (err) => {
        console.log(err);
      }
    );
  }

  logout() {
    this.httpClientService.logout();
  }

  result(poll: any) {
    const initialState = {
      poll: poll
    }
    this.bsModalRef = this.modalService.show(PollResultBarChartComponent, {initialState});
  }

}
