import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Employe } from '../../model/employe';
import { EmployeService } from '../../services/employe.service';
import { ExcelService } from '../../services/excel.service';
import { TokenStorageService } from '../../services/token-storage.service';
import { AddEmployeComponent } from './add-employe/add-employe.component';
import { UpdateEmployeComponent } from './update-employe/update-employe.component';

@Component({
  selector: 'ngx-employe',
  templateUrl: './employe.component.html',
  styleUrls: ['./employe.component.scss']
})
export class EmployeComponent implements OnInit {
  listemploye:Employe[];
  employe:Employe;
  constructor(private _router:Router,private tokenStorage:TokenStorageService,private employeService:EmployeService,private matDialog:MatDialog,private excelService:ExcelService) { }

  ngOnInit(): void {
    this.getUsers();
  }
  onOpenDialogClick(){
    this.matDialog.open(AddEmployeComponent);
  }
  public getUsers(): void {
    this.employeService.getEmployes().subscribe((data)=>{
      this.listemploye=data;
      console.log(data);
    },err => {
      this._router.navigateByUrl("/auth");
      this.tokenStorage.signOut();
    });
  }
  deleteEmploye(id:number){
    this.employeService.deleteEmploye(id).subscribe(()=>{
      this.employeService.getEmployes().subscribe((data)=>{
        this.listemploye=data;
        console.log(data);
      },err => {
        this._router.navigateByUrl("/auth");
        this.tokenStorage.signOut();
      });
    },err => {
      this._router.navigateByUrl("/auth");
      this.tokenStorage.signOut();
    });
  }
  updateEmploye(id:number){
    this.employe=this.employeService.sendEventData(id);
    this.matDialog.open(UpdateEmployeComponent);
  }
  exportAsXLSX():void {
    this.excelService.exportAsExcelFile(this.listemploye, 'listemploye');
  }
}
