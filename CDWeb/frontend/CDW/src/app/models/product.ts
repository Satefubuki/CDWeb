import {ProductType} from './product-type';
import {Brand} from './brand';
import {ImageSet} from './image-set';

export interface Product {
    id: number;
    name: string;
    type: ProductType;
    price: number;
    color: string;
    brand: Brand;
    stock: number;
    sale: number;
    mainImg: string;
    subImg: string;
    subImg2: string;
}
