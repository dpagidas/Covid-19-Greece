import { Component, OnInit } from '@angular/core';
import { MainPageComponent } from '../main-page/main-page.component';

@Component({
  selector: 'app-vertical-menu',
  templateUrl: './vertical-menu.component.html',
  styleUrls: ['./vertical-menu.component.scss']
})
export class VerticalMenuComponent implements OnInit {

    model: any[];

    constructor(public app: MainPageComponent) { }

    ngOnInit() {
        console.log('test');
        this.model = [
            { label: 'Dashboard', icon: 'pi  pi-fw pi-home', routerLink: ['/pages/home']},
            { label: 'Covid19-News', icon: 'pi  pi-fw pi-book', routerLink: ['/pages/covid-19-news']},
            { label: 'Vaccination Info', icon: ' pi pi-fw custom-icon', routerLink: ['/pages/vaccinations']}
            // {
            //     label: 'UI Kit', icon: 'pi pi-fw pi-star', routerLink: ['/uikit'],
            //     items: [
            //         {label: 'Form Layout', icon: 'pi pi-fw pi-id-card', routerLink: ['/uikit/formlayout']},
            //         {label: 'Input', icon: 'pi pi-fw pi-check-square', routerLink: ['/uikit/input']},
            //         {label: 'Float Label', icon: 'pi pi-fw pi-bookmark', routerLink: ['/uikit/floatlabel']},
            //         {label: 'Invalid State', icon: 'pi pi-exclamation-circle', routerLink: ['/uikit/invalidstate']},
            //         {label: 'Button', icon: 'pi pi-fw pi-mobile', routerLink: ['/uikit/button'], class: 'rotated-icon'},
            //         {label: 'Table', icon: 'pi pi-fw pi-table', routerLink: ['/uikit/table']},
            //         {label: 'List', icon: 'pi pi-fw pi-list', routerLink: ['/uikit/list']},
            //         {label: 'Tree', icon: 'pi pi-fw pi-share-alt', routerLink: ['/uikit/tree']},
            //         {label: 'Panel', icon: 'pi pi-fw pi-tablet', routerLink: ['/uikit/panel']},
            //         {label: 'Overlay', icon: 'pi pi-fw pi-clone', routerLink: ['/uikit/overlay']},
            //         {label: 'Media', icon: 'pi pi-fw pi-image', routerLink: ['/uikit/media']},
            //         {label: 'Menu', icon: 'pi pi-fw pi-bars', routerLink: ['/uikit/menu']},
            //         {label: 'Message', icon: 'pi pi-fw pi-comment', routerLink: ['/uikit/message']},
            //         {label: 'File', icon: 'pi pi-fw pi-file', routerLink: ['/uikit/file']},
            //         {label: 'Chart', icon: 'pi pi-fw pi-chart-bar', routerLink: ['/uikit/charts']},
            //         {label: 'Misc', icon: 'pi pi-fw pi-circle-off', routerLink: ['/uikit/misc']}
            //     ]
            // },
            // {
            //     label: 'Utilities', icon: 'pi pi-fw pi-compass', routerLink: ['utilities'],
            //     items: [
            //         {label: 'Display', icon: 'pi pi-fw pi-desktop', routerLink: ['utilities/display']},
            //         {label: 'Elevation', icon: 'pi pi-fw pi-external-link', routerLink: ['utilities/elevation']},
            //         {label: 'FlexBox', icon: 'pi pi-fw pi-directions', routerLink: ['utilities/flexbox']},
            //         {label: 'Icons', icon: 'pi pi-fw pi-search', routerLink: ['utilities/icons']},
            //         {label: 'Text', icon: 'pi pi-fw pi-pencil', routerLink: ['utilities/text']},
            //         {label: 'Widgets', icon: 'pi pi-fw pi-star-o', routerLink: ['utilities/widgets']},
            //         {label: 'Grid System', icon: 'pi pi-fw pi-th-large', routerLink: ['utilities/grid']},
            //         {label: 'Spacing', icon: 'pi pi-fw pi-arrow-right', routerLink: ['utilities/spacing']},
            //         {label: 'Typography', icon: 'pi pi-fw pi-align-center', routerLink: ['utilities/typography']}
            //     ]
            // },
            // {
            //     label: 'Pages', icon: 'pi pi-fw pi-copy', routerLink: ['/pages'],
            //     items: [
            //         { label: 'Crud', icon: 'pi pi-fw pi-pencil', routerLink: ['/pages/crud'] },
            //         { label: 'Calendar', icon: 'pi pi-fw pi-calendar-plus', routerLink: ['/pages/calendar'] },
            //         { label: 'Timeline', icon: 'pi pi-fw pi-calendar', routerLink: ['/pages/timeline'] },
            //         { label: 'Landing', icon: 'pi pi-fw pi-globe', url: 'assets/pages/landing.html', target: '_blank' },
            //         { label: 'Login', icon: 'pi pi-fw pi-sign-in', routerLink: ['/login'], target: '_blank' },
            //         { label: 'Error', icon: 'pi pi-fw pi-exclamation-triangle', routerLink: ['/error'], target: '_blank' },
            //         { label: '404', icon: 'pi pi-fw pi-times', routerLink: ['/404'], target: '_blank' },
            //         { label: 'Access Denied', icon: 'pi pi-fw pi-ban', routerLink: ['/accessdenied'], target: '_blank' },
            //         { label: 'Empty', icon: 'pi pi-fw pi-clone', routerLink: ['/pages/empty'] }
            //     ]
            // },
            // {
            //     label: 'Hierarchy', icon: 'pi pi-fw pi-sitemap',
            //     items: [
            //         {
            //             label: 'Submenu 1', icon: 'pi pi-fw pi-sign-in',
            //             items: [
            //                 {
            //                     label: 'Submenu 1.1', icon: 'pi pi-fw pi-sign-in',
            //                     items: [
            //                         { label: 'Submenu 1.1.1', icon: 'pi pi-fw pi-sign-in' },
            //                         { label: 'Submenu 1.1.2', icon: 'pi pi-fw pi-sign-in' },
            //                         { label: 'Submenu 1.1.3', icon: 'pi pi-fw pi-sign-in' },
            //                     ]
            //                 },
            //                 {
            //                     label: 'Submenu 1.2', icon: 'pi pi-fw pi-sign-in',
            //                     items: [
            //                         { label: 'Submenu 1.2.1', icon: 'pi pi-fw pi-sign-in' }
            //                     ]
            //                 },
            //             ]
            //         },
            //         {
            //             label: 'Submenu 2', icon: 'pi pi-fw pi-sign-in',
            //             items: [
            //                 {
            //                     label: 'Submenu 2.1', icon: 'pi pi-fw pi-sign-in',
            //                     items: [
            //                         { label: 'Submenu 2.1.1', icon: 'pi pi-fw pi-sign-in' },
            //                         { label: 'Submenu 2.1.2', icon: 'pi pi-fw pi-sign-in' },
            //                     ]
            //                 },
            //                 {
            //                     label: 'Submenu 2.2', icon: 'pi pi-fw pi-sign-in',
            //                     items: [
            //                         { label: 'Submenu 2.2.1', icon: 'pi pi-fw pi-sign-in' },
            //                     ]
            //                 },
            //             ]
            //         }
            //     ]
            // },
            // {
            //     label: 'Docs', icon: 'pi pi-fw pi-file', routerLink: ['/documentation']
            // },
            // {
            //     label: 'Buy Now', icon: 'pi pi-fw pi-money-bill', url: ['https://www.primefaces.org/store']
            // }
        ];
    }

    onMenuClick(event) {
        if (!this.app.isHorizontal()) {
        }
        this.app.onMenuClick(event);
    }

}
