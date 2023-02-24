import { Component, OnInit } from '@angular/core';

import { BudgetInitial } from '../../../model/budgetInitial';
import { HttpErrorResponse } from '@angular/common/http';
import { BudgetService } from '../../../services/budget.service';
import { SmartTableData } from '../../../@core/data/smart-table';
import { NewsPostComponent } from './news-post/news-post.component';
import { NewsPostPlaceholderComponent } from './news-post-placeholder/news-post-placeholder.component';
import { MatDialog } from '@angular/material/dialog';
import { ExcelService } from '../../../services/excel.service';
import { Router } from '@angular/router';
import { TokenStorageService } from '../../../services/token-storage.service';
@Component({
  selector: 'ngx-infinite-list',
  templateUrl: 'infinite-list.component.html',
  styleUrls: ['infinite-list.component.scss'],
})
export class InfiniteListComponent implements OnInit {



 
 BudgetInitials:BudgetInitial[];
 public editBudgetInitial: BudgetInitial;
  budgetInitial:BudgetInitial;

  constructor(private _router:Router,private tokenStorage:TokenStorageService,private service: SmartTableData, private budgetService:BudgetService,private matDialog:MatDialog,private excelService:ExcelService ) {}




  ngOnInit() {
    this.getBudgetInitial();
    console.log("hello");

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


  onOpenDialogClick(){
    this.matDialog.open(NewsPostComponent);
    
  }
  deleteBudI(id:number):void{
    
    this.budgetService.deleteBudgetInitial(id).subscribe(()=>{
      
      this.budgetService.getBudgetInitial().subscribe((data)=>this.BudgetInitials=data,err => {
        this._router.navigateByUrl("/auth");
        this.tokenStorage.signOut();
      });
    },err => {
      this._router.navigateByUrl("/auth");
      this.tokenStorage.signOut();
    })
}


  updateBudgetInitial(idBudgetInitial:number){
    
    this.budgetInitial=this.budgetService.sendEventData(idBudgetInitial);
    this.matDialog.open(NewsPostPlaceholderComponent);
  }

  exportAsXLSX():void {
    this.excelService.exportAsExcelFile(this.BudgetInitials, 'listBudgetInitial');
  }

}
