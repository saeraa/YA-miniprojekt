import { useState, useEffect } from "react";
import settings from "../utils/settings.json";
import ProductItems from "../components/ProductItem";
import ProductCart from "../components/ProductCart";

const Products = () => {
	const [originalProducts, setOriginalProducts] = useState([]);
	const [products, setProducts] = useState([]);
	const [search, setSearch] = useState("");
	const [subTotal, setSubTotal] = useState(0.0);
	const [cartProducts, setCartProducts] = useState([]);

	const url = settings.api_url + ":" + settings.api_port;

	useEffect(() => {
		// dummydata example
		//setOriginalProducts(ProductsArray);

		const getData = async () => {
			const results = await fetch(url + "/products");
			const data = await results.json();
			setOriginalProducts(() => {
				return [...data];
			});
			setProducts(() => {
				return [...data];
			});
		};

		getData().catch((error) => console.log(error));
	}, []);

	const updateSearch = (event) => {
		setSearch(event.target.value);
		let filteredProducts = originalProducts.filter((item) => {
			return (
				item.productName
					.toLowerCase()
					.indexOf(event.target.value.toLowerCase()) !== -1
			);
		});
		setProducts([...filteredProducts]);
	};

	const buyItem = (product) => {
		setCartProducts((prevData) => {
			const productIndex = cartProducts.findIndex;
			if (productIndex == -1) {
				return [...prevData, product];
			} else {
				return [...prevData, product];
			}
		});

		setSubTotal((prevData) => {
			return prevData + product.unitPrice;
		});
	};

	const productItems = products.map((product) => {
		return (
			<ProductItems
				product={product}
				key={product.productID}
				buyItem={buyItem}
			/>
		);
	});

	return (
		<>
			<header className="App-header">
				<h2>Buy from Company.com NOW!</h2>
				<div>
					{cartProducts.length > 0 && (
						<ProductCart cartProducts={cartProducts} subTotal={subTotal} />
					)}
				</div>
				<div id="search-div">
					<input
						placeholder="Search product"
						id="customer-search"
						type="text"
						value={search}
						onChange={updateSearch}
					/>
				</div>
				<div className="rTable-products">
					<div className="rTableHead">ProductID</div>
					<div className="rTableHead">ProductName</div>
					<div className="rTableHead">Quantity per unit</div>
					<div className="rTableHead">Unit price</div>
					<div className="rTableHead">🧺</div>
					{productItems.length > 0 && productItems}
				</div>
			</header>
		</>
	);
};

export default Products;
