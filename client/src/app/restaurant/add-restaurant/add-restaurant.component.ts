import { Component, OnInit } from '@angular/core';
import { RestaurantService } from '../restaurant.service';
import { Router } from '@angular/router';

@Component({
    selector: 'app-add-restaurant',
    templateUrl: './add-restaurant.component.html',
    styleUrls: ['./add-restaurant.component.css'],
})
export class AddRestaurantComponent implements OnInit {
    constructor(
        private restaurantService: RestaurantService,
        private router: Router
    ) {}

    ngOnInit(): void {
        this.getMyRestaurant();
    }

    getMyRestaurant(): void {
        this.restaurantService.getMyRestaurant().subscribe({
            next: () => {
                this.router.navigate(['/my_restaurant']);
            },
        });
    }
}
