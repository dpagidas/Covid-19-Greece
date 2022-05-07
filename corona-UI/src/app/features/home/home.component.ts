import { Component, OnInit } from '@angular/core';
import { MessageService } from 'primeng/api';
import { ConfirmedDeaths } from 'src/app/shared/models/confirmed-deaths.interface';
import { GenderAgeDistribution } from 'src/app/shared/models/gender-age-distribution.interface';
import { IntensiveCare } from 'src/app/shared/models/intensive-care.intreface';
import { TodayCases } from 'src/app/shared/models/today-cases.interface';
import { TodayTests } from 'src/app/shared/models/today-tests.interface';
import { ConfirmedDeathsService } from 'src/app/shared/services/confirmed-deaths.service';
import { GenderAgeDistributionService } from 'src/app/shared/services/gender-age-distribution.service';
import { IntensiveCareService } from 'src/app/shared/services/intensive-care.service';
import { TodayCasesService } from 'src/app/shared/services/today-cases.service';
import { TodayTestsService } from 'src/app/shared/services/today-tests.service';
import * as pluginLabels from 'chartjs-plugin-labels';
declare var google: any;

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  overlays: any[];
  infoWindow: any;
  google: any;
  options: any;
  dialogVisible: boolean;
  selectedPosition: any;
draggable: boolean;
    markerTitle: string;
    todayCases: TodayCases;
     dataCases: any;
     dataDeaths: any;
     dataCritical: any;
     data1 : any
    todayTests: TodayTests;
    intensiveCare: IntensiveCare;
    confirmedDeaths: ConfirmedDeaths;
    genderAgeDistribution: GenderAgeDistribution;
    pieChartPlugins = [];

  constructor(private messageService: MessageService, private todayCaseService: TodayCasesService, private todayTestsService: TodayTestsService, private intensiveCareService: IntensiveCareService,
    private confirmedDeathsService : ConfirmedDeathsService, private genderAgeSrvc : GenderAgeDistributionService) { }

  ngOnInit(): void {
    this.pieChartPlugins = [pluginLabels];
    this.todayCaseService.fetchLastDateConfirmedCases().subscribe(result => {
      this.todayCases = result;
      
    },
      error => {
      });

      this.todayTestsService.fetchLastTodayTests().subscribe(result => {
        this.todayTests = result;
        
      },
        error => {
        });


        this.intensiveCareService.fetchTodayIntensiveCare().subscribe(result => {
          this.intensiveCare = result;
          
        },
          error => {
          });

          this.confirmedDeathsService.fetchConfirmedDeaths().subscribe(result => {
            this.confirmedDeaths = result;
            
          },
            error => {
            });

            this.genderAgeSrvc.fetchGenderAgeDistributionHistory().subscribe(result => {
              this.genderAgeDistribution = result;

              this.dataCases = {
                labels: ['0-17','18-39','65+', '40-64'],
                datasets: [{
                  data: [this.genderAgeDistribution.females.cases.age_0_17 + this.genderAgeDistribution.males.cases.age_0_17, this.genderAgeDistribution.females.cases.age_18_39 + this.genderAgeDistribution.males.cases.age_18_39, this.genderAgeDistribution.females.cases.age_65 + this.genderAgeDistribution.males.cases.age_65 ,this.genderAgeDistribution.females.cases.age_40_64 + this.genderAgeDistribution.males.cases.age_40_64],
                  backgroundColor: ['#0d1936', '#9b1f1f','#bd8600','#650538']
                }]
              },this.options = {
                legend: {display: false},
              responsive: true,
        maintainAspectRatio: true,
        plugins: {
          labels: {
            render: 'percentage',
            fontColor: ['white', 'white', 'white','white'],
            precision: 2
          }
        },
  
            }


            this.dataDeaths = {
              labels: ['0-17','18-39','65+', '40-64'],
              datasets: [{
                data: [this.genderAgeDistribution.females.deaths.age_0_17 + this.genderAgeDistribution.males.deaths.age_0_17, this.genderAgeDistribution.females.deaths.age_18_39 + this.genderAgeDistribution.males.deaths.age_18_39, this.genderAgeDistribution.females.deaths.age_65 + this.genderAgeDistribution.males.deaths.age_65 ,this.genderAgeDistribution.females.deaths.age_40_64 + this.genderAgeDistribution.males.deaths.age_40_64],
                backgroundColor: ['#0d1936', '#9b1f1f','#bd8600','#650538']
              }]
            },this.options = {
              legend: {display: false},
            responsive: true,
      maintainAspectRatio: true,
      plugins: {
        labels: {
          render: 'percentage',
          fontColor: ['white', 'white', 'white','white'],
          precision: 2
        },
      },

          }


          this.dataCritical = {
            labels: ['0-17','18-39','65+', '40-64'],
            datasets: [{
              data: [this.genderAgeDistribution.females.critical.age_0_17 + this.genderAgeDistribution.males.critical.age_0_17, this.genderAgeDistribution.females.critical.age_18_39 + this.genderAgeDistribution.males.critical.age_18_39, this.genderAgeDistribution.females.critical.age_65 + this.genderAgeDistribution.males.critical.age_65 ,this.genderAgeDistribution.females.critical.age_40_64 + this.genderAgeDistribution.males.critical.age_40_64],
              backgroundColor: ['#0d1936', '#9b1f1f','#bd8600','#650538']
            }]
          },this.options = {
            legend: {display: false},
          responsive: true,
    maintainAspectRatio: true,
    plugins: {
      labels: {
        render: 'percentage',
        fontColor: ['white', 'white', 'white','white'],
        precision: 2
      },
    },

        }
              
            },
              error => {
              });



    
  }

}
