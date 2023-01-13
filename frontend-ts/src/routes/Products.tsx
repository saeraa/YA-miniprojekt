import { useState, useEffect } from "react";
import settings from "../utils/settings.json";
import ProductItems from "../components/ProductItem";
import ProductCart from "../components/ProductCart";
import { Product } from "../utils/interfaces";
import { keycloak } from "../utils/keycloak";
//import ProductsArray from "../utils/products";

const Products = () => {
	const [originalProducts, setOriginalProducts] = useState<Product[] | []>([]);
	const [products, setProducts] = useState<Product[] | []>([]);
	const [search, setSearch] = useState<string>("");
	const [subTotal, setSubTotal] = useState<number>(0.0);
	const [cartProducts, setCartProducts] = useState<Product[] | []>([]);

	const url = settings.api_url + ":" + settings.api_port;

	useEffect(() => {
		// dummydata example
		// setOriginalProducts(ProductsArray);
		// setProducts(ProductsArray);

		const getData = async () => {
			const results = await fetch(url + "/products", {
				headers: {
					Authorization: `Bearer ${keycloak.token}`
				}
			});
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

	const updateSearch = (event: React.ChangeEvent<HTMLInputElement>) => {
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

	const buyItem = (product: Product) => {
		setCartProducts((prevData) => {
			const productIndex = cartProducts.findIndex(
				(product) => product.productID
			);
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
			<h1>Products</h1>
			<header className="App-header">
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
				<div className="table table-products">
					<div className="table-head">ProductID</div>
					<div className="table-head">ProductName</div>
					<div className="table-head">Quantity per unit</div>
					<div className="table-head">Unit price</div>
					<div className="table-head">🧺</div>
					{productItems.length > 0 && productItems}
				</div>
			</header>
		</>
	);
};

export default Products;
