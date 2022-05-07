import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { CasesListsByRegionInterface } from 'src/app/shared/models/casesListByRegion.interface';
import { RegionCasesHistoryService } from 'src/app/shared/services/region-cases-history.service';
import * as moment from 'moment';

@Component({
  selector: 'app-cases-table-list',
  templateUrl: './cases-table-list.component.html',
  styleUrls: ['./cases-table-list.component.scss']
})
export class CasesTableListComponent implements OnInit {
  searchCriteriaForm: FormGroup;
  date: Date;
  rows : number = 10;
  casesListResults: CasesListsByRegionInterface[];

  constructor(private fb: FormBuilder, private regionCaseHistorySrvc: RegionCasesHistoryService) { }

  ngOnInit(): void {
    this.searchCriteriaForm = this.fb.group({
      fromDate: [null]
    });
    this.searchCasesList();
  }

  searchCasesList(){
    this.regionCaseHistorySrvc.fetchAllCasesListByRegion(this.searchCriteriaForm?.value).subscribe(result => {
      if(result?.length > 0){
        this.date = result[0]?.date;
      }
      this.casesListResults = result;
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
    this.searchCasesList();
  }


  inputFromDate(event){
    if(moment(this.searchCriteriaForm?.value['fromDate'], "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",true).isValid()){
      let dateFrom24Hours = new Date(Date.UTC(this.searchCriteriaForm?.value['fromDate']?.getFullYear(), this.searchCriteriaForm?.value['fromDate']?.getMonth(), this.searchCriteriaForm?.value['fromDate']?.getDate()));
this.searchCriteriaForm.patchValue ({
  fromDate: dateFrom24Hours
});
this.searchCasesList();
    }else if((event.target as HTMLInputElement).value == ''){
      this.searchCasesList();
    }
  }

  onClear(){
    this.searchCasesList();

  }

}
