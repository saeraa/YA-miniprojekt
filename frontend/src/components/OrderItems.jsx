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
