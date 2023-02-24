import { Component, Input, OnInit } from '@angular/core';
import { BudgetInitial } from '../../../../model/budgetInitial';
import { Router } from '@angular/router';
import { MatDialogRef } from '@angular/material/dialog';
import { BudgetService } from '../../../../services/budget.service';
import { TokenStorageService } from '../../../../services/token-storage.service';


@Component({
  selector: 'ngx-news-post',
  templateUrl: 'news-post.component.html',
})
export class NewsPostComponent implements OnInit {

  BudgetInitial:BudgetInitial=new BudgetInitial();

  constructor(private tokenStorage:TokenStorageService,private _router:Router,private dialogRef:MatDialogRef<NewsPostComponent>,private budgetService: BudgetService) { }

  ngOnInit(): void {
  }
  ajouterBudgetInitial(){
    
    this.budgetService.ajouterBudgetInitial(this.BudgetInitial).subscribe(()=>{
      this.dialogRef.close();
      this._router.navigateByUrl('/pages/layout/infinite-list', { skipLocationChange: true }).then(() => {
        this._router.navigate(['/pages/layout/infinite-list']);
      }); 
    },err => {
      this._router.navigateByUrl("/auth");
      this.tokenStorage.signOut();
    })
    
  }
}
