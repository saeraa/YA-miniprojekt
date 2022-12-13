import React, { Component } from "react";

class ProductChart extends Component {
	createTasks = (product) => {
		return (
			<div key={product.productID}>
				{product.productName + " : " + product.unitPrice}
			</div>
		);
	};

	render() {
		const productEntries = this.props.chartProducts;
		const listItems = productEntries.map(this.createTasks);
		const subTotal = this.props.subTotal;
		return (
			<div>
				<div>{listItems}</div>
				<hr></hr>
				<div>{"Total: " + subTotal}</div>
			</div>
		);
	}
}

export default ProductChart;
