import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Employe } from '../../../model/employe';
import { Direction } from '../../../model/direction';
import { EmployeService } from '../../../services/employe.service';
import { DirectionService } from '../../../services/direction.service';
import { TokenStorageService } from '../../../services/token-storage.service';

@Component({
  selector: 'ngx-add-employe',
  templateUrl: './add-employe.component.html',
  styleUrls: ['./add-employe.component.scss']
})
export class AddEmployeComponent implements OnInit {
  employe:Employe=new Employe();
  selectedDirectionId:number;
  directions:Direction[];
  constructor(private _router:Router,private tokenStorage:TokenStorageService,private dialogRef:MatDialogRef<AddEmployeComponent>,private serviceDirection:DirectionService,private serviceEmploye:EmployeService) { }

  ngOnInit(): void {
    this.serviceDirection.getDirections().subscribe((data)=>this.directions=data,err => {
      this._router.navigateByUrl("/auth");
      this.tokenStorage.signOut();
    });
  }
  addEmploye(){
    
    this.serviceEmploye.addEmploye(this.employe,this.selectedDirectionId).subscribe(()=>{
      this.dialogRef.close();
      this._router.navigateByUrl("/pages/employe").then(()=>window.location.reload());
      console.log(this.employe);
    },err => {
      this._router.navigateByUrl("/auth");
      this.tokenStorage.signOut();
    });
  }
}
