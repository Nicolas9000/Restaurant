import { Injectable } from '@angular/core';
import { Restaurant } from '../interfaces/RestaurantInterface';
import { Observable, throwError } from 'rxjs';
import { ApiResponse } from '../interfaces/ApiResponseInterface';
import { ApiService } from '../services/api/api.service';

@Injectable({
    providedIn: 'root',
})
export class RestaurantService {
    constructor(private apiService: ApiService) {}

    private apiUrl: string = '/restaurant';

    getMyRestaurant(): Observable<ApiResponse<Restaurant>> {
        return this.apiService.get<ApiResponse<Restaurant>>(
            `${this.apiUrl}/my_restaurant`
        );
    }

    createRestaurant(data: Restaurant): Observable<ApiResponse<Restaurant>> {
        return this.apiService.post<ApiResponse<Restaurant>, Restaurant>(
            this.apiUrl,
            data
        );
    }

    updateRestaurant(restaurandId: number, data: Restaurant): Observable<ApiResponse<Restaurant>> {
        return this.apiService.put<ApiResponse<Restaurant>, Restaurant>(
            `${this.apiUrl}/${restaurandId}`,
            data
        );
    }
}
