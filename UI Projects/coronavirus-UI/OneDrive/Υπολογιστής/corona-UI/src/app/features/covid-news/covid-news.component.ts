import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Covid19News } from 'src/app/shared/models/covid-19.interface';
import { CovidNewsService } from 'src/app/shared/services/covid-news.service';

@Component({
  selector: 'app-covid-news',
  templateUrl: './covid-news.component.html',
  styleUrls: ['./covid-news.component.scss']
})
export class CovidNewsComponent implements OnInit {

    covid19News: Covid19News[];
    totalRecords: any;

    constructor(private covid19Newssrvc : CovidNewsService, private datepipe : DatePipe) { }

    ngOnInit() {
        this.searchCovid19News(0,5);
        
    }

    searchCovid19News(page: number , offset: number){
        this.covid19Newssrvc.searchPagedWithoutCriteria(page , offset, 'search').subscribe(result => {
            this.covid19News = result?.data;
            this.covid19News.forEach( x => {
                x.publishedAt = this.datepipe.transform(x.publishedAt, 'dd/MM/yyyy')
            })
            this.totalRecords = result?.totalElements;
          },
            error => {
            });
    }

    onPageChange(event){
        this.searchCovid19News(event?.page,event?.rows);
    }


    openWebsite(url){
        window.open(url);
    }
}