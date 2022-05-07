import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { TodayCases } from 'src/app/shared/models/today-cases.interface';
import { TodayCasesService } from 'src/app/shared/services/today-cases.service';
import * as moment from 'moment';

@Component({
  selector: 'app-cases-bar-chart',
  templateUrl: './cases-bar-chart.component.html',
  styleUrls: ['./cases-bar-chart.component.scss']
})
export class CasesBarChartComponent implements OnInit {
  searchCriteriaForm: FormGroup;
  dates : string[] = [];
  chartOptions: any;
  confirmedCases : number [] = [];
  confirmedDeaths : number [] = [];
  intensiveCare : number [] = [];
  positivityIndex : number [] = [];
  basicData: any;

  constructor(  private fb: FormBuilder, private todaySrvc : TodayCasesService) { }

  ngOnInit(): void {
    this.searchCriteriaForm = this.fb.group({
      fromDate: [null],
      toDate: [null]
    });
    this.searchBarChartCasesByDate();
  }


  searchBarChartCasesByDate(){
    let pipe =  new DatePipe('en-US');
    this.todaySrvc.fetchAllCasesListByDate(this.searchCriteriaForm?.value).subscribe(result => {
      console.log(result.length);
      if(result?.length > 0){
        result.forEach(x=>{
        this.dates.push(pipe.transform(x?.date, 'MMM d y'));
        this.confirmedCases.push(x?.confirmedCases);
        this.confirmedDeaths.push(x?.confirmedDeaths);
        this.intensiveCare.push(x?.intensiveCare);
        this.positivityIndex.push(x?.positivityIndex);


        this.basicData = {
          labels: this.dates,
          datasets: [
              {
                  label: 'Επιβεβαιωμένα Κρούσματα',
                  data: this.confirmedCases,
                  fill: false,
                  borderColor: '#FFA726',
                  tension: .4
              },
              {
                  label: 'Επιβεβαιωμένοι Θάνατοι',
                  data: this.confirmedDeaths,
                  fill: false,
                  borderColor: '#de5758',
                  tension: .4
              },
              {
                  label: 'Διασωληνωμένοι',
                  data: this.intensiveCare,
                  fill: false,
                  borderColor: 'green',
                  tension: .4
              },
              {
                label: 'Δείκτης Θετικότητας',
                data: this.positivityIndex,
                fill: false,
                borderColor: '#2e86de',
                tension: .4
            }
          ]
      };
        });
      }else{
        this.basicData = null;
        
      }
    },
      error => {
      });

  }



  inputFromDate(event){
    if(moment(this.searchCriteriaForm?.value['fromDate'], "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",true).isValid()){
      this.confirmedCases = [];
      this.confirmedDeaths = [];
      this.intensiveCare = [];
      this.positivityIndex = [];
      this.dates = [];
      let dateFrom24Hours = new Date(Date.UTC(this.searchCriteriaForm?.value['fromDate']?.getFullYear(), this.searchCriteriaForm?.value['fromDate']?.getMonth(), this.searchCriteriaForm?.value['fromDate']?.getDate()));
this.searchCriteriaForm.patchValue ({
  fromDate: dateFrom24Hours
});
      this.searchBarChartCasesByDate();
    }else if((event.target as HTMLInputElement).value == ''){
      this.confirmedCases = [];
      this.confirmedDeaths = [];
      this.intensiveCare = [];
      this.positivityIndex = [];
      this.dates = [];
      this.searchBarChartCasesByDate();
    }
  }

  inputToDate(event){
    if(moment(this.searchCriteriaForm?.value['toDate'], "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",true).isValid()){
      this.confirmedCases = [];
      this.confirmedDeaths = [];
      this.intensiveCare = [];
      this.positivityIndex = [];
      this.dates = [];
      let dateTo24Hours = new Date(Date.UTC(this.searchCriteriaForm?.value['toDate']?.getFullYear(), this.searchCriteriaForm?.value['toDate']?.getMonth(), this.searchCriteriaForm?.value['toDate']?.getDate()));
    this.searchCriteriaForm.patchValue ({
      toDate: dateTo24Hours
    })
      this.searchBarChartCasesByDate();
    }else if((event.target as HTMLInputElement).value == ''){
      this.confirmedCases = [];
      this.confirmedDeaths = [];
      this.intensiveCare = [];
      this.positivityIndex = [];
      this.dates = [];
      this.searchBarChartCasesByDate();
    }

    
    
  }



  dateFrom(){
    this.confirmedCases = [];
      this.confirmedDeaths = [];
      this.intensiveCare = [];
      this.positivityIndex = [];
      this.dates = [];
    let dateFrom24Hours = new Date(Date.UTC(this.searchCriteriaForm?.value['fromDate']?.getFullYear(), this.searchCriteriaForm?.value['fromDate']?.getMonth(), this.searchCriteriaForm?.value['fromDate']?.getDate()));

this.searchCriteriaForm.patchValue ({
  fromDate: dateFrom24Hours
})
    this.searchBarChartCasesByDate();
  }

  dateTo(){
    this.confirmedCases = [];
      this.confirmedDeaths = [];
      this.intensiveCare = [];
      this.positivityIndex = [];
      this.dates = [];
    let dateTo24Hours = new Date(Date.UTC(this.searchCriteriaForm?.value['toDate']?.getFullYear(), this.searchCriteriaForm?.value['toDate']?.getMonth(), this.searchCriteriaForm?.value['toDate']?.getDate()));
    this.searchCriteriaForm.patchValue ({
      toDate: dateTo24Hours
    })
    this.searchBarChartCasesByDate();
  }

  onClear(){
    this.confirmedCases = [];
      this.confirmedDeaths = [];
      this.intensiveCare = [];
      this.positivityIndex = [];
      this.dates = [];
    this.searchBarChartCasesByDate();

  }

}
