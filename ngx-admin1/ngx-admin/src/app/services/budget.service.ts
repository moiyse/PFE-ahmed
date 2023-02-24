import { HttpClient } from '@angular/common/http';
import { EventEmitter, Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { take } from 'rxjs/operators';
import { environment } from '../../environments/environment';
import { BudgetInitial } from '../model/budgetInitial';
import { TokenStorageService } from './token-storage.service';

@Injectable({
  providedIn: 'root'
})
export class BudgetService {
  $eventEmit = new EventEmitter();
budgetinitial:BudgetInitial;
private apiServerUrl = environment.apiBaseUrl;

  constructor(private _router:Router,private tokenStorage:TokenStorageService,private http: HttpClient) { }
  public getBudgetInitial(): Observable<BudgetInitial[]> {
    return this.http.get<BudgetInitial[]>(`${this.apiServerUrl}/getBudgetInitial`);
  }

   public ajouterBudgetInitial(budgetInitial: BudgetInitial): Observable<BudgetInitial> {
    return this.http.post<BudgetInitial>(`${this.apiServerUrl}/ajouterBudgetInitial`, budgetInitial);
  }

  getBudgetInitialById(idbudgetInitial:number): Observable<BudgetInitial> {
    return this.http.get<BudgetInitial>(this.apiServerUrl+"/getBudgetInitialById/"+idbudgetInitial);
  }
  sendEventData(idbudgetInitial : number):any{
      
    this.getBudgetInitialById(idbudgetInitial).pipe(take(1)).subscribe(x=>{
      
      this.budgetinitial=x;
      this.$eventEmit.emit(this.budgetinitial);
      return x;
    },err => {
      this._router.navigateByUrl("/auth");
      this.tokenStorage.signOut();
    });
    
}
public deleteBudgetInitial(budgetInitialId: number): Observable<void> {
  return this.http.delete<void>(this.apiServerUrl+"/deleteBudgetInitialById/"+budgetInitialId);
}
}