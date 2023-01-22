export interface Product {
	productID: number;
	productName: string;
	categoryID: number;
	unitPrice: number;
	quantityPerUnit: string;
	unitsInStock: number;
}

export interface ProductCartProps {
	subTotal: number;
	cartProducts: Product[];
}

export interface ProductItemProps {
	buyItem: (arg0: Product) => void;
	product: Product;
}
