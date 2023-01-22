import { useState, useEffect, useContext } from "react";
import settings from "../utils/settings.json";
import ProductItems from "../components/ProductItem";
import ProductCart from "../components/ProductCart";
import { Product } from "../utils/productInterfaces";
import { SignInContext } from "../utils/signInContext";
import { useAxiosFetch } from "../utils/useAxiosFetch";

const Products = () => {
	const { token, loggedIn } = useContext(SignInContext);
	const [originalProducts, setOriginalProducts] = useState<Product[] | []>([]);
	const [products, setProducts] = useState<Product[] | []>([]);
	const [search, setSearch] = useState<string>("");
	const [subTotal, setSubTotal] = useState<number>(0.0);
	const [cartProducts, setCartProducts] = useState<Product[] | []>([]);

	const url = settings.api_url + ":" + settings.api_port;

	const getDataParams = {
		method: "GET",
		url: url + "/products",
		headers: {
			Authorization: "Bearer " + token
		}
	};

	const [data, error, loading, fetchData] = useAxiosFetch(getDataParams);

	const getProductsFromAPI = async () => {
		await fetchData();
		setProducts((prevdata) => data);
	};

	useEffect(() => {
		if (data) {
			setProducts(data);
			setOriginalProducts(data);
		}
	}, [data]);

	useEffect(() => {
		if (error) {
			console.log(error);
		}
		const getData = async () => {
			await fetchData();
		};
		if (loggedIn) {
			getData().catch((error) => console.log(error));
		} else return;
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

	let productItems =
		products !== null
			? products.map((product) => {
					return (
						<ProductItems
							product={product}
							key={product.productID}
							buyItem={buyItem}
						/>
					);
			  })
			: "No results";

	return (
		<div className="App">
			{!loggedIn ? (
				<h1>You need to log in.</h1>
			) : (
				<>
					<h1>
						Products <span onClick={getProductsFromAPI}>🔄️</span>
					</h1>
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
			)}
		</div>
	);
};

export default Products;
