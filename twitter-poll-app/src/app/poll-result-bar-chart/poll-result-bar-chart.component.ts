import { Component, OnInit } from '@angular/core';
import { Chart } from 'chart.js';

@Component({
  selector: 'app-poll-result-bar-chart',
  templateUrl: './poll-result-bar-chart.component.html',
  styleUrls: ['./poll-result-bar-chart.component.scss']
})
export class PollResultBarChartComponent implements OnInit {
  options = [];
  vote = [];
  barchart: any = [];
  poll:any;
  constructor() { }
  ngOnInit() {
    for (const choices of this.poll.pollChoices) {
      this.options.push(choices.description);
      this.vote.push(choices.vote);
      
    }
    this.barchart = new Chart('canvas', {
      type: 'bar',

      data: {
        labels: this.options,
        datasets: [
          {
            data: this.vote,
            borderColor: '#3cba9f',
            backgroundColor: [
              "#3cb371",
              "#0000FF",
              "#9966FF",
              "#4C4CFF",
              "#00FFFF",
              "#f990a7",
              "#aad2ed",
              "#FF00FF",
              "Blue",
              "Red",
              "Blue"
            ],
            fill: true
          }
        ]
      },
      options: {
        legend: {
          display: false
        },
        title: {
          display: true,
          text: this.poll.name
        },
        scales: {
          xAxes: [{
            display: true
          }],
          yAxes: [{
            display: true
          }],
        }
      }
    });
  }

}
