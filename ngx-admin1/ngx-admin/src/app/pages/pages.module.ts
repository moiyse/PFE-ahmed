import { NgModule } from '@angular/core';
import { NbMenuModule } from '@nebular/theme';

import { ThemeModule } from '../@theme/theme.module';
import { PagesComponent } from './pages.component';
import { DashboardModule } from './dashboard/dashboard.module';
import { ECommerceModule } from './e-commerce/e-commerce.module';
import { PagesRoutingModule } from './pages-routing.module';
import { MiscellaneousModule } from './miscellaneous/miscellaneous.module';
import { EntrepriseComponent } from './entreprise/entreprise.component';
import { AddEntrepriseComponent } from './entreprise/add-entreprise/add-entreprise.component';
import { MatDialogModule } from '@angular/material/dialog';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UpdateEntrepriseComponent } from './entreprise/update-entreprise/update-entreprise.component';
import { DirectionComponent } from './direction/direction.component';
import { ContratComponent } from './contrat/contrat.component';
import { AddContratComponent } from './contrat/add-contrat/add-contrat.component';
import { UpdateContratComponent } from './contrat/update-contrat/update-contrat.component';
import { EmployeComponent } from './employe/employe.component';
import { AddDirectionComponent } from './direction/add-direction/add-direction.component';
import { AddEmployeComponent } from './employe/add-employe/add-employe.component';
import { UpdateEmployeComponent } from './employe/update-employe/update-employe.component';
import { UpdateDirectionComponent } from './direction/update-direction/update-direction.component';
import { authInterceptorProviders } from '../services/auth.interceptor';


@NgModule({
  imports: [
    PagesRoutingModule,
    ThemeModule,
    NbMenuModule,
    DashboardModule,
    ECommerceModule,
    MiscellaneousModule,
    MatDialogModule,
    FormsModule,
    ReactiveFormsModule,
    
    
  ],
  declarations: [
    PagesComponent,
    EntrepriseComponent,
    AddEntrepriseComponent,
    UpdateEntrepriseComponent,
    DirectionComponent,
    ContratComponent,
    AddContratComponent,
    UpdateContratComponent,
    EmployeComponent,
    AddDirectionComponent,
    AddEmployeComponent,
    UpdateEmployeComponent,
    UpdateDirectionComponent
  ],
  providers: [authInterceptorProviders]
})
export class PagesModule {
}
