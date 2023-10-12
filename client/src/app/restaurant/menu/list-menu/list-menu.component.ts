import { Component, OnInit } from '@angular/core';
import { MenuService } from '../menu.service';
import { Menu } from 'src/app/interfaces/MenuInterface';
import { DishService } from '../../dish/dish.service';

@Component({
    selector: 'app-list-menu',
    templateUrl: './list-menu.component.html',
    styleUrls: ['./list-menu.component.css'],
})
export class ListMenuComponent implements OnInit {
    constructor(private menuService: MenuService, private dishService: DishService) {}

    menus: Menu[];

    ngOnInit(): void {
        this.getMenus();
        this.getDishs();
    }

    getMenus(): void {
        this.menuService.getMenus().subscribe({
            next: (menuResponse) => {
                this.menus = menuResponse.data;
            },
            error: (err) => {},
        });
    }

    getDishs(): void {
      this.dishService.getDishs().subscribe({
        
      });
    }

    addDish(): void {

    }
}
