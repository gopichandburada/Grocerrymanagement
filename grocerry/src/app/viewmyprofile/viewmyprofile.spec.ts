import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Viewmyprofile } from './viewmyprofile';

describe('Viewmyprofile', () => {
  let component: Viewmyprofile;
  let fixture: ComponentFixture<Viewmyprofile>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [Viewmyprofile]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Viewmyprofile);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
