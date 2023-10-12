import { Component, Input } from '@angular/core';
import { Restaurant } from 'src/app/interfaces/RestaurantInterface';

@Component({
  selector: 'app-edit-restaurant',
  templateUrl: './edit-restaurant.component.html',
  styleUrls: ['./edit-restaurant.component.css']
})
export class EditRestaurantComponent {
  @Input() restaurant: Restaurant;
}
