import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { BsModalRef } from 'ngx-bootstrap';
import { HttpClientService } from '../services/http-client.service';

@Component({
  selector: 'app-add-new-poll',
  templateUrl: './add-new-poll.component.html',
  styleUrls: ['./add-new-poll.component.scss']
})
export class AddNewPollComponent implements OnInit {
  poll: any = {
    live:true,
    user:{},
    pollChoices: []
  };
  choiceDecTxt: any;
  @Output() emitData = new EventEmitter();
  constructor(private httpClientService: HttpClientService,public bsModalRef: BsModalRef) { }

  ngOnInit() {
  }

  add() {
    if (this.choiceDecTxt === undefined || this.choiceDecTxt === null || this.choiceDecTxt.length === 0) {
      alert("Type Description To Add")
      return;
    }
    let chioce = {
      description: this.choiceDecTxt
    }
    this.poll.pollChoices.push(chioce);
    this.choiceDecTxt = '';
  }

  save() {
    if (this.poll.name === undefined || this.poll.name === null || this.poll.name.length === 0) {
      alert("Type Name To Continue")
      return;
    } else if (this.poll.pollChoices.length < 2) {
      alert("Add Atleast Two Options To Continue")
      return;
    }
    this.poll.user['id']=this.httpClientService.getLogedInUserId();
    this.httpClientService.postData("poll/add",this.poll).subscribe(
      (data) => {
        this.emitData.next(data);
        this.cancel();

      },
      (err) => {
        console.log(err);
      }
    );
  }

  cancel() {
    this.bsModalRef.hide();

  }

}
