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

export interface CurrencyType {
	euroPrice: number;
	currency: string;
}

export interface Customer {
	address: string;
	city: string;
	companyName: string;
	contactTitle: string;
	customerId: string;
}

export interface Recommendation {
	id: number;
	productId: number;
	comment: string;
	email: string;
	rating: number;
}

export interface SupportIssue {
	id: number;
	customerId: string;
	comment: string;
	priority: string;
	statusType: string;
}
