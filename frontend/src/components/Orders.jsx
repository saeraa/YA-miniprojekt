import React, { Component } from "react";
import OrderItems from "./OrderItems";
import settings from "../properties/settings.json";

class Orders extends Component {
	inputElement = React.createRef();
	constructor() {
		super();
		this.state = {
			originalOrders: [],
			orders: [],
			currentOrder: {
				text: "",
				key: ""
			},
			search: ""
		};
		this.url = settings.api_url + ":" + settings.api_port;
	}

	componentDidMount() {
		fetch(this.url + "/orders")
			.then((results) => {
				return results.json();
			})
			.then((data) => {
				this.setState({ orders: data });
				this.setState({ originalOrders: data });
			});
	}

	deleteItem = (key) => {
		console.log(key);
		const filteredItems = this.state.orders.filter((order) => {
			return order.id !== key;
		});
		this.setState({
			orders: filteredItems,
			originalOrders: filteredItems
		});
		fetch(this.url + "/deleteOrder/" + key, {
			method: "DELETE"
		}).catch(function () {
			console.log("error");
		});
	};

	editOrder = (key) => {
		console.log("Edit: " + key);
	};

	handleInput = (e) => {
		const itemText = e.target.value;
		const currentOrder = { text: itemText, key: Date.now() };
		this.setState({
			currentOrder
		});
	};

	addItem = (e) => {
		e.preventDefault();
		const newItem = this.state.currentOrder;
		if (newItem.text !== "") {
			const orders = [this.state.orders, newItem];
			this.setState({
				orders: orders,
				currentOrder: { text: "", key: "" }
			});
		}
	};

	updateSearch(event) {
		this.setState({ search: event.target.value });
		let filteredOrders = this.state.originalOrders.filter((item) => {
			return item.customerID.indexOf(event.target.value.toUpperCase()) !== -1;
		});
		this.setState({ orders: filteredOrders });
	}

	render() {
		return (
			<div className="App">
				<header className="App-header">
					<h2>All orders at Company.com</h2>
					<div id="search-div">
						<input
							placeholder="Search customer"
							id="customer-search"
							type="text"
							value={this.state.search}
							onChange={this.updateSearch.bind(this)}
						/>
					</div>
					<div className="rTable">
						<div className="rTableRow">
							<div className="rTableHead">OrderID</div>
							<div className="rTableHead">CustomerID</div>
							<div className="rTableHead">Order date</div>
							<div className="rTableHead">Shipping date</div>
							<div className="rTableHead"></div>
							<div className="rTableHead"></div>
						</div>
						<OrderItems
							entries={this.state.orders}
							deleteItem={this.deleteItem}
							editOrder={this.editOrder}
						/>
					</div>
				</header>
			</div>
		);
	}
}

export default Orders;
