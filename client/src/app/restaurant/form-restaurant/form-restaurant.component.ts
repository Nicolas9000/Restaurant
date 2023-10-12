import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Restaurant } from 'src/app/interfaces/RestaurantInterface';
import { RestaurantService } from '../restaurant.service';
import { FileService } from 'src/app/services/file/file.service';

@Component({
    selector: 'app-form-restaurant',
    templateUrl: './form-restaurant.component.html',
    styleUrls: ['./form-restaurant.component.css'],
})
export class FormRestaurantComponent implements OnInit {
    constructor(
        private fb: FormBuilder,
        private restaurantService: RestaurantService,
        private fileService: FileService
    ) {}

    @Input() restaurant: Restaurant;

    errorMessage: string;
    isPictureValid: boolean;
    picture: File;
    picturePath: string;

    addRestaurantForm: FormGroup;

    ngOnInit(): void {
        this.addRestaurantForm = this.fb.group(
            {
                name: [this.restaurant?.name || null, Validators.required],
                email: [
                    this.restaurant?.email || null,
                    [Validators.required, Validators.email],
                ],
                phone: [this.restaurant?.phone || null, Validators.required],
                description: [
                    this.restaurant?.description || null,
                    Validators.required,
                ],
                picture: [null],
                street: [this.restaurant?.street || null, Validators.required],
                city: [this.restaurant?.city || null, Validators.required],
                zipcode: [
                    this.restaurant?.zipcode || null,
                    [Validators.required, Validators.pattern('^[0-9]{5}$')],
                ],
                country: [
                    this.restaurant?.country || null,
                    Validators.required,
                ],
            },
            { updateOn: 'submit' }
        );
    }

    onFileChange(event: Event): void {
        const inputElement = event.target as HTMLInputElement;
        if (inputElement.files && inputElement.files.length) {
            const file = inputElement.files[0];
            this.picture = file;
            const formData = new FormData();
            formData.append('file', file);
            this.fileService.uploadImage(formData).subscribe({
                next: (fileResponse) => {
                    this.picturePath = fileResponse.data;
                },
                error: (error) => {
                    this.errorMessage = error;
                },
            });
        }
    }

    onSubmit(): void {
        if (!this.picturePath && !this.restaurant.picture) {
            this.isPictureValid = false;
            return;
        }

        this.isPictureValid = true;

        if (this.addRestaurantForm.valid) {
            const restaurantData: Restaurant = {
                name: this.addRestaurantForm.value.name,
                email: this.addRestaurantForm.value.email,
                phone: this.addRestaurantForm.value.phone,
                description: this.addRestaurantForm.value.description,
                picture: this.picturePath || this.restaurant.picture,
                street: this.addRestaurantForm.value.street,
                city: this.addRestaurantForm.value.city,
                zipcode: this.addRestaurantForm.value.zipcode,
                country: this.addRestaurantForm.value.country,
            };

            if (!this.restaurant) {
                this.restaurantService
                    .createRestaurant(restaurantData)
                    .subscribe({
                        next: (restaurantResponse) => {
                            console.log(restaurantResponse);
                        },
                        error: (error) => {
                            this.errorMessage = error;
                        },
                    });
                return;
            }

            this.restaurantService
                .updateRestaurant(this.restaurant.id!, restaurantData)
                .subscribe({
                    next: (restaurantResponse) => {
                        console.log(restaurantResponse);
                    },
                    error: (error) => {
                        this.errorMessage = error;
                    },
                });
        }
    }
}
