import React, { Component } from "react";

class OrderItems extends Component {
	createTasks = (order) => {
		const shippedDate = new Date(order.shippedDate).toLocaleDateString();
		const orderDate = new Date(order.orderDate).toLocaleDateString();
		return (
			<div className="rTableRow" key={order.id}>
				<div className="rTableCell">{order.id}</div>
				<div className="rTableCell">{order.customerID}</div>
				<div className="rTableCell">
					<p>{orderDate}</p>
				</div>
				<div className="rTableCell">
					<p>{shippedDate}</p>
				</div>
				<div
					className="rTableCell clickable"
					onClick={() => this.props.editOrder(order.id)}
				>
					Edit
				</div>
				<div
					className="rTableCell clickable"
					onClick={() => this.props.deleteItem(order.id)}
				>
					Delete
				</div>
			</div>
		);
	};
	render() {
		const todoEntries = this.props.entries;
		const listItems = todoEntries.map(this.createTasks);

		return listItems;
	}
}

export default OrderItems;

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
