import React from "react";
import settings from "../properties/settings.json";
import ProductItems from "./ProductItems";
import ProductChart from "./ProductChart";
import ProductsArray from "../properties/products.js";

function Products() {
	const [originalProducts, setOriginalProducts] = React.useState([]);
	const [products, setProducts] = React.useState([]);
	const [state, setState] = React.useState({
		currentProduct: {
			text: "",
			key: ""
		},
		customerId: "TORTU"
	});
	const [search, setSearch] = React.useState("");
	const [subTotal, setSubTotal] = React.useState(0.0);
	const [chartProducts, setChartProducts] = React.useState([]);

	const url = settings.api_url + ":" + settings.api_port;

	React.useEffect(() => {
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

	const buyItem = (key) => {
		setChartProducts((prevData) => {
			const productIndex = chartProducts.findIndex;
			if (productIndex == -1) {
				return [...prevData, key];
			} else {
				return [...prevData, key];
			}
		});

		setSubTotal((prevData) => {
			return prevData + key.unitPrice;
		});
	};

	return (
		<>
			<header className="App-header">
				<h2>Buy from Company.com NOW!</h2>
				<div>
					<h3>Your cart:</h3>
					<div className="chart">
						<ProductChart chartProducts={chartProducts} subTotal={subTotal} />
					</div>
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
				<div className="rTable">
					<div className="rTableRow">
						<div className="rTableHead">ProductID</div>
						<div className="rTableHead">ProductName</div>
						<div className="rTableHead">Quantity per unit</div>
						<div className="rTableHead">Unit price</div>
						<div className="rTableHead"></div>
					</div>
					<ProductItems entries={products} buyItem={buyItem} />
				</div>
			</header>
		</>
	);
}

export default Products;
