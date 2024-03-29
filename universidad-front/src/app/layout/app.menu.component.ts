import { OnInit } from '@angular/core';
import { Component } from '@angular/core';
import { LayoutService } from './service/app.layout.service';

@Component({
    selector: 'app-menu',
    templateUrl: './app.menu.component.html'
})
export class AppMenuComponent implements OnInit {

    model: any[] = [];

    constructor(public layoutService: LayoutService) { }

    ngOnInit() {
        this.model = [
            {
                label: 'Home',
                items: [
                    { label: 'Home', icon: 'pi pi-fw pi-home', routerLink: ['/home'] }
                ]
            },
            {
                label: 'UI Components',
                items: [
                    { label: 'Usuarios', icon: 'pi pi-fw pi-id-card', routerLink: ['/usuarios'] },
                    { label: 'Productos', icon: 'pi pi-fw pi-check-square', routerLink: ['/products'] },
                    { label: 'Proveedores', icon: 'pi pi-fw pi-bookmark', routerLink: ['/suplier'] },
                    { label: 'Transacciones', icon: 'pi pi-fw pi-bookmark', routerLink: ['/transactions'] },
                ]
            }
        ];
    }
}
