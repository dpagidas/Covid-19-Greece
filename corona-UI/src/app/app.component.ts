import { Component } from '@angular/core';
import { PrimeNGConfig } from 'primeng/api';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})

export class AppComponent {

  menuMode = 'static';

    topbarColor = 'layout-topbar-light';

    inputStyle = 'outlined';

    ripple = false;

    lightMenu = true;

    constructor(private primengConfig: PrimeNGConfig) {
    }


  title = 'Corona-UI';


  ngOnInit() {
    this.primengConfig.ripple = true;
    this.ripple = true;
}

}
