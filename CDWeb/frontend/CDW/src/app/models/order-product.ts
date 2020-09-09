import {ProductType} from './product-type';
import {Brand} from './brand';

export interface OrderProduct{
    id: number;
    name: string;
    type: ProductType;
    price: number;
    img: string;
    color: string;
    brand: Brand;
    quantity: number;
}