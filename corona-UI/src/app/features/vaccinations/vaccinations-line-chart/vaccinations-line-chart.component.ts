import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { VaccinationService } from 'src/app/shared/services/vaccination.service';
import * as moment from 'moment';

@Component({
  selector: 'app-vaccinations-line-chart',
  templateUrl: './vaccinations-line-chart.component.html',
  styleUrls: ['./vaccinations-line-chart.component.scss']
})
export class VaccinationsLineChartComponent implements OnInit {
  dates : string[] = [];
  totalDistinctDoses : number[] = [];
  totalDoses2: number[] = [];
  totalVaccinatios: number []= [];
  totalDoses3: number[] = [];
  searchCriteriaForm: FormGroup;
  basicData: any;

  constructor(private vaccinationSrv : VaccinationService,  private fb: FormBuilder) { }

  ngOnInit(): void {
    this.searchCriteriaForm = this.fb.group({
      fromDate: [null],
      toDate: [null]
    });

    this.searchLineChartVaccinatios();
  }


  searchLineChartVaccinatios(){
    let pipe =  new DatePipe('en-US');
    this.vaccinationSrv.fetchAllVaccinationResultsByDate(this.searchCriteriaForm?.value).subscribe(result => {
      console.log(result.length);
      if(result?.length > 0){
        result.forEach(x=>{
          this.dates.push(pipe.transform(x?.referenceDate, 'MMM d y'));
          this.totalDistinctDoses.push(x?.sumTotalDistinctPersons);
          this.totalDoses2.push(x?.sumTotalDose2);
          this.totalDoses3.push(x?.sumTotalDose3);
          this.totalVaccinatios.push(x?.sumTotalVaccinations);
          this.basicData = {
            labels: this.dates,
            datasets: [
                {
                    label: 'Συνολικοί εμβολιασμοί με τουλάχιστον 1 δόση',
                    data: this.totalDistinctDoses,
                    fill: false,
                    borderColor: '#FFA726',
                    tension: .4
                },
                {
                    label: 'Συνολικοί ολοκληρωμένοι εμβολιασμοί',
                    data: this.totalDoses2,
                    fill: false,
                    borderColor: '#42A5F5',
                    tension: .4
                },
                {
                    label: 'Συνολικοί εμβολιασμοί',
                    data: this.totalVaccinatios,
                    fill: false,
                    borderColor: 'green',
                    tension: .4
                },
                {
                  label: 'Εμβολιασμοί αναμνηστικής δόσης',
                  data: this.totalDoses3,
                  fill: false,
                  borderColor: '#B150E2',
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
      this.totalVaccinatios = [];
      this.totalDoses2 = [];
      this.totalDoses3 = [];
      this.totalDistinctDoses = [];
      this.dates = [];
      let dateFrom24Hours = new Date(Date.UTC(this.searchCriteriaForm?.value['fromDate']?.getFullYear(), this.searchCriteriaForm?.value['fromDate']?.getMonth(), this.searchCriteriaForm?.value['fromDate']?.getDate()));
this.searchCriteriaForm.patchValue ({
  fromDate: dateFrom24Hours
});
      this.searchLineChartVaccinatios();
    }else if((event.target as HTMLInputElement).value == ''){
      this.totalVaccinatios = [];
      this.totalDoses2 = [];
      this.totalDoses3 = [];
      this.totalDistinctDoses = [];
      this.dates = [];
      this.searchLineChartVaccinatios();
    }
  }

  inputToDate(event){
    if(moment(this.searchCriteriaForm?.value['toDate'], "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",true).isValid()){
      this.totalVaccinatios = [];
      this.totalDoses2 = [];
      this.totalDoses3 = [];
      this.totalDistinctDoses = [];
      this.dates = [];
      let dateTo24Hours = new Date(Date.UTC(this.searchCriteriaForm?.value['toDate']?.getFullYear(), this.searchCriteriaForm?.value['toDate']?.getMonth(), this.searchCriteriaForm?.value['toDate']?.getDate()));
    this.searchCriteriaForm.patchValue ({
      toDate: dateTo24Hours
    })
      this.searchLineChartVaccinatios();
    }else if((event.target as HTMLInputElement).value == ''){
      this.totalVaccinatios = [];
      this.totalDoses2 = [];
      this.totalDoses3 = [];
      this.totalDistinctDoses = [];
      this.dates = [];
      this.searchLineChartVaccinatios();
    }

    
    
  }



  dateFrom(){
    this.totalVaccinatios = [];
    this.totalDoses2 = [];
    this.totalDoses3 = [];
    this.totalDistinctDoses = [];
    this.dates = [];
    let dateFrom24Hours = new Date(Date.UTC(this.searchCriteriaForm?.value['fromDate']?.getFullYear(), this.searchCriteriaForm?.value['fromDate']?.getMonth(), this.searchCriteriaForm?.value['fromDate']?.getDate()));

this.searchCriteriaForm.patchValue ({
  fromDate: dateFrom24Hours
})
    this.searchLineChartVaccinatios();
  }

  dateTo(){
    this.totalVaccinatios = [];
    this.totalDoses2 = [];
    this.totalDoses3 = [];
    this.totalDistinctDoses = [];
    this.dates = [];
    let dateTo24Hours = new Date(Date.UTC(this.searchCriteriaForm?.value['toDate']?.getFullYear(), this.searchCriteriaForm?.value['toDate']?.getMonth(), this.searchCriteriaForm?.value['toDate']?.getDate()));
    this.searchCriteriaForm.patchValue ({
      toDate: dateTo24Hours
    })
    this.searchLineChartVaccinatios();
  }

  onClear(){
    this.totalVaccinatios = [];
    this.totalDoses2 = [];
    this.totalDoses3 = [];
    this.totalDistinctDoses = [];
    this.dates = [];
    this.searchLineChartVaccinatios();

  }

}
