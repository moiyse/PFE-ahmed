import { Component, HostBinding } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { BudgetInitial } from '../../../../model/budgetInitial';
import { BudgetService } from '../../../../services/budget.service';
import { TokenStorageService } from '../../../../services/token-storage.service';

@Component({
  selector: 'ngx-news-post-placeholder',
  templateUrl: 'news-post-placeholder.component.html',
  styleUrls: ['news-post-placeholder.component.scss'],
})
export class NewsPostPlaceholderComponent {

  BudgetInitial:BudgetInitial=new BudgetInitial();


  constructor(private tokenStorage:TokenStorageService,private _router:Router,private dialogRef:MatDialogRef<NewsPostPlaceholderComponent>,private budgetService:BudgetService) { }

 ngOnInit(): void {
    this.budgetService.$eventEmit.subscribe((data)=> {
      this.BudgetInitial=data;
      console.log(this.BudgetInitial);
    },err => {
      this._router.navigateByUrl("/auth");
      this.tokenStorage.signOut();
    });
  }

  updateBudgetInitial(){
    
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
