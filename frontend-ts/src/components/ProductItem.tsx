import { Product } from "../utils/interfaces";

interface ProductItemProps {
	buyItem: (arg0: Product) => void;
	product: Product;
}

const ProductItem = (props: ProductItemProps) => {
	const { buyItem } = props;
	const { productID, productName, quantityPerUnit, unitPrice } = props.product;

	return (
		<>
			<div className="rTableCell">{productID}</div>
			<div className="rTableCell">{productName}</div>
			<div className="rTableCell">{quantityPerUnit}</div>
			<div className="rTableCell">{unitPrice}</div>
			<div
				className="rTableCell clickable"
				onClick={() => buyItem(props.product)}
			>
				Buy
			</div>
		</>
	);
};

export default ProductItem;
