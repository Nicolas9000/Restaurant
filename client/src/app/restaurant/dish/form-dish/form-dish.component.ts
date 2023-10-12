import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Dish } from 'src/app/interfaces/DishInterface';
import { DishService } from '../dish.service';
import { FileService } from 'src/app/services/file/file.service';

@Component({
    selector: 'app-form-dish',
    templateUrl: './form-dish.component.html',
    styleUrls: ['./form-dish.component.css'],
})
export class FormDishComponent implements OnInit {
    constructor(
        private fb: FormBuilder,
        private dishService: DishService,
        private fileService: FileService
    ) {}

    allergens: string[] = [
        'Céréales contenant du gluten',
        'Crustacés',
        'Oeufs',
        'Poissons',
        'Arachides',
        'Soja',
        'Lait',
        'Fruits à coques',
        'Céleri',
        'Moutarde',
        'Graines de sésame',
        'Anhydride sulfureux et sulfites en concentration de plus de 10mg/kg ou 10 mg/l',
        'Lupin',
        'Mollusques',
    ];

    dishForm: FormGroup;
    isPictureValid: boolean;
    picture: File;
    picturePath: string;

    errorMessage: string;

    ngOnInit(): void {
        this.dishForm = this.fb.group({
            name: [null, Validators.required],
            description: [null, Validators.required],
            picture: [null],
            price: [null, Validators.required],
            type: [null, Validators.required],
            allergens: [null, Validators.required],
        });
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
        if (!this.picturePath) {
            this.isPictureValid = false;
            return;
        }

        this.isPictureValid = true;

        if (this.dishForm.valid) {
            const dishData: Dish = {
                name: this.dishForm.value.name,
                description: this.dishForm.value.description,
                picture: this.picturePath,
                price: this.dishForm.value.price,
                type: this.dishForm.value.type,
                allergens: this.dishForm.value.allergens.join(','),
            };

            this.dishService.createDish(dishData).subscribe({
                next: (dishReponse) => {},
                error: (err) => {},
            });
        }
    }
}
