import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddNewPollComponent } from './add-new-poll.component';

describe('AddNewPollComponent', () => {
  let component: AddNewPollComponent;
  let fixture: ComponentFixture<AddNewPollComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddNewPollComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddNewPollComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
