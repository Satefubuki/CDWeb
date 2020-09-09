export interface Order {
    id: number;
    buyerName: string;
    buyerAddress: string;
    orderAmount: number;
    orderStatus: number;
    createTime: Date;
    products: [];
}