import { OrderItemProps } from "../utils/orderInterfaces";
import OrderRow from "./OrderRow.jsx";

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
