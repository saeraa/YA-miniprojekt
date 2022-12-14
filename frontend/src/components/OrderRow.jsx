import React from "react";

const OrderRow = (props) => {
	const { productName, quantity, unitPrice } = props.order;
	return (
		<div className="order-row">
			<div className="order-row-quantity">{quantity + "pcs"}</div>
			<div className="order-row-product-name">{productName}</div>
			<div className="order-row-price">{"€" + unitPrice}</div>
		</div>
	);
};

export default OrderRow;

/* 

discount
: 
0
orderID
: 
10253
productID
: 
31
productName
: 
"Gorgonzola Telino"
quantity
: 
20
unitPrice
: 
10
*/
