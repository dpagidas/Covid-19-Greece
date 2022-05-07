import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { VaccinationLastDateInterface } from 'src/app/shared/models/vaccinationLastDate.interface';
import { VaccinationService } from 'src/app/shared/services/vaccination.service';
@Component({
  selector: 'app-vaccinations',
  templateUrl: './vaccinations.component.html',
  styleUrls: ['./vaccinations.component.scss']
})
export class VaccinationsComponent implements OnInit {
  basicData: any;
  checkIfNegative: boolean;
  date9: Date;
  date0 : Date
  rows : number = 10;
  dates : string[] = [];
  totalDistinctDoses : number[] = [];
  totalDoses2: number[] = [];
  totalVaccinatios: number []= [];
  totalDoses3: number[] = [];
  searchCriteriaForm: FormGroup;
  lastDateVaccinationData : VaccinationLastDateInterface;
  vaccinationListByAreaGr: VaccinationLastDateInterface[];

  constructor(private vaccinationSrv : VaccinationService,  private fb: FormBuilder) { }

  ngOnInit(): void {
    this.vaccinationSrv.fetchLastDateVaccinationData().subscribe(result => {
      this.lastDateVaccinationData = result;
    },
      error => {
      });

  }

  checkNegative(number: number) : boolean{
    if(number > 0){
      return true;
    }else{
      return false;
    }

  }

}
