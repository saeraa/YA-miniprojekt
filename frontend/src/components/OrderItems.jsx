import React from "react";
import OrderRow from "./OrderRow.jsx";

const OrderItem = (props) => {
	const { showDetails, deleteItem } = props;
	const { orderDate, shippedDate, customerID, id } = props.order;
	const shippedDateString = new Date(shippedDate).toLocaleDateString();
	const orderDateString = new Date(orderDate).toLocaleDateString();

	const orderRows = props.orderRows.map((row, index) => {
		return <OrderRow key={row + index} order={row} />;
	});

	return (
		<>
			<div className="rTableCell">{id}</div>
			<div className="rTableCell">{customerID}</div>
			<div className="rTableCell">{orderDateString}</div>
			<div className="rTableCell">{shippedDateString}</div>
			<div className="rTableCell clickable" onClick={() => showDetails(id)}>
				Details
			</div>
			<div className="rTableCell clickable" onClick={() => deleteItem(id)}>
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
