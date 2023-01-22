import { OrderRowProps } from "../utils/orderInterfaces";

const OrderRow = (props: OrderRowProps) => {
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
