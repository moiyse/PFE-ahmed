import { HttpErrorResponse } from '@angular/common/http';
import { Component, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { BudgetInitial } from '../../../model/budgetInitial';
import { Employe } from '../../../model/employe';
import { BudgetService } from '../../../services/budget.service';
import { EmployeService } from '../../../services/employe.service';
import { TokenStorageService } from '../../../services/token-storage.service';

@Component({
  selector: 'ngx-accordion',
  templateUrl: 'accordion.component.html',
  styleUrls: ['accordion.component.scss'],
})
export class AccordionComponent {
BudgetInitials:BudgetInitial[];
listemploye:Employe[];
  employe:Employe;
constructor(private _router:Router,private tokenStorage:TokenStorageService,private budgetService: BudgetService,private employeService:EmployeService){}
  @ViewChild('item', { static: true }) accordion;

  toggle() {
    this.accordion.toggle();

  }

  ngOnInit() {
    this.getBudgetInitial();
    console.log("hello");
    this.getUsers();

  }
  public getBudgetInitial(): void {
    this.budgetService.getBudgetInitial().subscribe(
      (response: BudgetInitial[]) => {
        this.BudgetInitials = response;
        console.log(this.BudgetInitials);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        this._router.navigateByUrl("/auth");
        this.tokenStorage.signOut();
       
      }
    );
  }
  public getUsers(): void {
    this.employeService.getEmployes().subscribe((data)=>{
      this.listemploye=data;
      console.log(data);
    },err => {
      this._router.navigateByUrl("/auth");
      this.tokenStorage.signOut();
    })
  }
}
