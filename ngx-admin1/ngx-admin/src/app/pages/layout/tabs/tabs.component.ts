import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Employe } from '../../../model/employe';
import { EmployeService } from '../../../services/employe.service';
import { TokenStorageService } from '../../../services/token-storage.service';

@Component({
  selector: 'ngx-tab1',
  template: `
    <p>Early home automation began with labor-saving machines. Self-contained electric or gas powered
      <a target="_blank" href="https://en.wikipedia.org/wiki/Home_appliances">home appliances</a>
      became viable in the 1900s with the introduction of
      <a target="_blank" href="https://en.wikipedia.org/wiki/Electric_power_distribution">electric power distribution
      </a> and led to the introduction of washing machines (1904), water heaters (1889), refrigerators, sewing machines,
      dishwashers, and clothes dryers.
    </p>
  `,
})
export class Tab1Component { }

@Component({
  selector: 'ngx-tab2',
  template: `
    <p>Tab 2 works!</p>
  `,
})
export class Tab2Component { }

@Component({
  selector: 'ngx-tabs',
  styleUrls: ['./tabs.component.scss'],
  templateUrl: './tabs.component.html',
})
export class TabsComponent {
  listemploye:Employe[];
  employe:Employe;

  
  constructor(private _router:Router,private tokenStorage:TokenStorageService,private employeService:EmployeService){}
  tabs: any[] = [
    {
      title: 'Route tab #1',
      route: '/pages/layout/tabs/tab1',
    },
    {
      title: 'Route tab #2',
      route: '/pages/layout/tabs/tab2',
    },
  ];
  public getUsers(): void {
    this.employeService.getEmployes().subscribe((data)=>{
      this.listemploye=data;
      console.log(data);
    },err => {
      this._router.navigateByUrl("/auth");
      this.tokenStorage.signOut();
    });
  }
}
