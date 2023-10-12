import { Component, OnInit } from '@angular/core';
import { RestaurantService } from '../restaurant.service';
import { Router } from '@angular/router';
import { Restaurant } from 'src/app/interfaces/RestaurantInterface';

@Component({
    selector: 'app-detail-restaurant',
    templateUrl: './detail-restaurant.component.html',
    styleUrls: ['./detail-restaurant.component.css'],
})
export class DetailRestaurantComponent implements OnInit {
    constructor(
        private restaurantService: RestaurantService,
        private router: Router
    ) {}
    
    tab: number = 1;
    restaurant: Restaurant;

    ngOnInit(): void {
        this.getMyRestaurant();
    }

    setTab(newTab: number) {
        this.tab = newTab;
    }

    getMyRestaurant(): void {
        this.restaurantService.getMyRestaurant().subscribe({
            next: (restaurantResponse) => {
                console.log(restaurantResponse.data);
                this.restaurant = restaurantResponse.data;
            },
            error: () => {
                this.router.navigate(['/create_restaurant']);
            },
        });
    }
}
