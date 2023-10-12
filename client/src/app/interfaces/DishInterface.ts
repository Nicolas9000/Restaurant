export interface Dish {
    name: string | null | undefined;
    description: string | null | undefined;
    picture?: string;
    type: string | null | undefined;
    price: number | null | undefined;
    allergens: string | string[] | null | undefined;
}
