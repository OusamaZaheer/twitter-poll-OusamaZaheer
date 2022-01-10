import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PollResultBarChartComponent } from './poll-result-bar-chart.component';

describe('PollResultBarChartComponent', () => {
  let component: PollResultBarChartComponent;
  let fixture: ComponentFixture<PollResultBarChartComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PollResultBarChartComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PollResultBarChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
