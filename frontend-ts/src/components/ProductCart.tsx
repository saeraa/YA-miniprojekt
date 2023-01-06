import { Product } from "../utils/interfaces";

interface ProductCartProps {
	subTotal: number;
	cartProducts: Product[];
}

const ProductCart = (props: ProductCartProps) => {
	const { subTotal, cartProducts: productEntries } = props;

	const listItems = productEntries.map((product) => {
		return (
			<div
				className="product-list-entry"
				key={product.productID + Math.random() * 100}
			>
				<span>{product.productName}</span>
				<span>€{product.unitPrice}</span>
			</div>
		);
	});

	return (
		<div className="product-list">
			<div>{listItems}</div>
			<hr />
			<div className="product-list-total">
				{"Total: €" + subTotal.toFixed(2)}
			</div>
		</div>
	);
};

export default ProductCart;
