import { Order, OrderRowType } from "../utils/interfaces.js";
import OrderRow from "./OrderRow.jsx";

interface OrderItemProps {
	showDetails: (id: number) => Promise<void>;
	deleteItem: (id: number) => void;
	order: Order;
	orderRows: [] | OrderRowType[];
}

const OrderItem = (props: OrderItemProps) => {
	const { showDetails, deleteItem } = props;
	const { orderDate, shippedDate, customerID, id } = props.order;
	const shippedDateString = new Date(shippedDate).toLocaleDateString();
	const orderDateString = new Date(orderDate).toLocaleDateString();

	const orderRows = props.orderRows.map((row, index) => {
		return <OrderRow key={String(row) + index} order={row} />;
	});

	return (
		<>
			<div className="table-cell">{id}</div>
			<div className="table-cell">{customerID}</div>
			<div className="table-cell">{orderDateString}</div>
			<div className="table-cell">{shippedDateString}</div>
			<div className="table-cell clickable" onClick={() => showDetails(id)}>
				Details
			</div>
			<div className="table-cell clickable" onClick={() => deleteItem(id)}>
				🗑️
			</div>

			{props.orderRows.length > 0 && orderRows}
		</>
	);
};

export default OrderItem;

/* 

click on order row to get details
make fetch call ->
GET http://localhost:8888/order/{id}

result into table:

[
    {
        "orderID": 10265,
        "productID": 17,
        "unitPrice": 31.2,
        "quantity": 30,
        "discount": 0.0,
        "productName": "Alice Mutton"
    },
    {
        "orderID": 10265,
        "productID": 70,
        "unitPrice": 12.0,
        "quantity": 20,
        "discount": 0.0,
        "productName": "Outback Lager"
    }
*/
