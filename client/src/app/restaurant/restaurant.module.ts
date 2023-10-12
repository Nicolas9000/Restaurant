import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { authGuard } from '../auth.guard';
import { AddRestaurantComponent } from './add-restaurant/add-restaurant.component';
import { ReactiveFormsModule } from '@angular/forms';
import { FormRestaurantComponent } from './form-restaurant/form-restaurant.component';
import { DetailRestaurantComponent } from './detail-restaurant/detail-restaurant.component';
import { EditRestaurantComponent } from './edit-restaurant/edit-restaurant.component';
import { FormMenuComponent } from './menu/form-menu/form-menu.component';
import { ListMenuComponent } from './menu/list-menu/list-menu.component';
import { PageMenuComponent } from './menu/page-menu/page-menu.component';
import { PageDishComponent } from './dish/page-dish/page-dish.component';
import { FormDishComponent } from './dish/form-dish/form-dish.component';

const restaurantRoute: Routes = [
    {
        path: 'create_restaurant',
        component: AddRestaurantComponent,
        canActivate: [authGuard],
    },
    {
        path: 'my_restaurant',
        component: DetailRestaurantComponent,
        canActivate: [authGuard],
    },
];

@NgModule({
    declarations: [
        AddRestaurantComponent,
        FormRestaurantComponent,
        DetailRestaurantComponent,
        EditRestaurantComponent,
        FormMenuComponent,
        ListMenuComponent,
        PageMenuComponent,
        PageDishComponent,
        FormDishComponent,
    ],
    imports: [
        CommonModule,
        ReactiveFormsModule,
        RouterModule.forChild(restaurantRoute),
    ],
})
export class RestaurantModule {}
