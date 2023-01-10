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
			<div className="table-cell">{productID}</div>
			<div className="table-cell">{productName}</div>
			<div className="table-cell">{quantityPerUnit}</div>
			<div className="table-cell">{unitPrice}</div>
			<div
				className="table-cell clickable"
				onClick={() => buyItem(props.product)}
			>
				Buy
			</div>
		</>
	);
};

export default ProductItem;
