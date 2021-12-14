import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { VaccinationLastDateInterface } from 'src/app/shared/models/vaccinationLastDate.interface';
import { VaccinationService } from 'src/app/shared/services/vaccination.service';
import * as moment from 'moment';

@Component({
  selector: 'app-vaccinations-table-region-list',
  templateUrl: './vaccinations-table-region-list.component.html',
  styleUrls: ['./vaccinations-table-region-list.component.scss']
})
export class VaccinationsTableRegionListComponent implements OnInit {
  vaccinationListByAreaGr: VaccinationLastDateInterface[];
  rows : number = 10;
  searchCriteriaForm: FormGroup;
  date : Date;

  constructor(private vaccinationSrv : VaccinationService, private fb: FormBuilder) { }

  ngOnInit(): void {
    this.searchCriteriaForm = this.fb.group({
      fromDate: [null]
    });
    
    this.searchVaccinationListRegions();
  }


  searchVaccinationListRegions(){
    this.vaccinationSrv.fetchAllVaccinationListByRegion(this.searchCriteriaForm?.value).subscribe(result => {
      if(result?.length > 0){
        this.date = result[0]?.referenceDate;
      }
      this.vaccinationListByAreaGr = result;
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



  dateFrom(){
    let dateFrom24Hours = new Date(Date.UTC(this.searchCriteriaForm?.value['fromDate']?.getFullYear(), this.searchCriteriaForm?.value['fromDate']?.getMonth(), this.searchCriteriaForm?.value['fromDate']?.getDate()));

this.searchCriteriaForm.patchValue ({
  fromDate: dateFrom24Hours
});
    this.searchVaccinationListRegions();
  }


  inputFromDate(event){
    if(moment(this.searchCriteriaForm?.value['fromDate'], "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",true).isValid()){
      let dateFrom24Hours = new Date(Date.UTC(this.searchCriteriaForm?.value['fromDate']?.getFullYear(), this.searchCriteriaForm?.value['fromDate']?.getMonth(), this.searchCriteriaForm?.value['fromDate']?.getDate()));
this.searchCriteriaForm.patchValue ({
  fromDate: dateFrom24Hours
});
this.searchVaccinationListRegions();
    }else if((event.target as HTMLInputElement).value == ''){
      this.searchVaccinationListRegions();
    }
  }

  onClear(){
    this.searchVaccinationListRegions();

  }

}
