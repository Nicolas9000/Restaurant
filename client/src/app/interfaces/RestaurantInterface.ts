export interface Restaurant {
    id?: number;
    name: string | null | undefined;
    email: string | null | undefined;
    phone: number | null | undefined;
    description: string | null | undefined;
    picture?: string;
    street: string | null | undefined;
    city: string | null | undefined;
    zipcode: number | null | undefined;
    country: string | null | undefined;
    createdAt?: Date;
}