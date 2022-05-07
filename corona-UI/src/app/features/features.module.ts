import { SharedModule } from './../shared/shared.module';
import { PrimengSharedModule } from './../shared/primeng-shared/primeng-shared.module';
import { NgModule } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { FeaturesRoutingModule } from './features-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { DialogService } from 'primeng/dynamicdialog';
import { HomeComponent } from './home/home.component';
import { VerticalMenuComponent } from '../core/vertical-menu/vertical-menu.component';
import { CovidNewsComponent } from './covid-news/covid-news.component';
import { PointReplacerPipe } from '../shared/pipes/point-replacer.pipe';
import { VaccinationsComponent } from './vaccinations/vaccinations.component';
import { VaccinationsTableRegionListComponent } from './vaccinations/vaccinations-table-region-list/vaccinations-table-region-list.component';
import { VaccinationsLineChartComponent } from './vaccinations/vaccinations-line-chart/vaccinations-line-chart.component';
import { CasesBarChartComponent } from './home/cases-bar-chart/cases-bar-chart.component';
import { CasesTableListComponent } from './home/cases-table-list/cases-table-list.component';


@NgModule({
  declarations: [
    HomeComponent,
    CovidNewsComponent,
    PointReplacerPipe,
    VaccinationsComponent,
    VaccinationsTableRegionListComponent,
    VaccinationsLineChartComponent,
    CasesBarChartComponent,
    CasesTableListComponent
  ],
  imports: [
    CommonModule,
    PrimengSharedModule,
    FeaturesRoutingModule,
    ReactiveFormsModule,
    SharedModule,
    FormsModule
  ],
  providers: [DialogService, DatePipe, PointReplacerPipe]
})
export class FeaturesModule {
}
