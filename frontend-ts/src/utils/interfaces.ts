export interface Order {
	orderDate: string;
	shippedDate: string;
	customerID: string;
	id: number;
}

export interface OrderRowType {
	productName: string;
	quantity: number;
	unitPrice: number;
	orderID: number;
  discount: number;
  productID: number;
}

export interface Product {
  productID: number;
  productName: string;
  categoryID: number;
  unitPrice: number;
  quantityPerUnit: string;
  unitsInStock: number;
}