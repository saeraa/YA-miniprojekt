export interface OrderItemProps {
	showDetails: (id: number) => Promise<void>;
	deleteItem: (id: number) => void;
	order: Order;
	orderRows: [] | OrderRowType[];
}

export interface OrderRowProps {
	order: OrderRowType;
}

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
