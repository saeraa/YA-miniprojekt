import React from "react";
import OrderItems from "../components/OrderItems.jsx";
import settings from "../properties/settings.json";

const Orders = () => {
	const [search, setSearch] = React.useState("");
	const [originalOrders, setOriginalOrders] = React.useState([]);
	const [orders, setOrders] = React.useState([]);
	const [orderRows, setOrderRows] = React.useState([]);

	const url = settings.api_url + ":" + settings.api_port;

	React.useEffect(() => {
		const getData = async () => {
			const results = await fetch(url + "/orders");
			const data = await results.json();
			setOriginalOrders(() => {
				return [...data];
			});
			setOrders(() => {
				return [...data];
			});
		};

		getData().catch((error) => console.log(error));
	}, []);

	const deleteItem = (id) => {
		const filteredItems = originalOrders.filter((order) => {
			return order.id !== id;
		});
		setOriginalOrders(() => {
			return [...filteredItems];
		});
		setOrders(() => {
			return [...filteredItems];
		});
		fetch(url + "/deleteOrder/" + id, {
			method: "DELETE"
		}).catch(function () {
			console.log("error");
		});
	};

	const showDetails = async (id) => {
		let exists = false;
		orderRows.forEach((row) => {
			if (row.orderID === id) {
				exists = true;
			}
		});
		if (exists) {
			return;
		}
		const result = await fetch(url + `/order/${id}`);
		const data = await result.json();
		setOrderRows((prevData) => {
			return [...prevData, ...data];
		});
	};

	const updateSearch = (event) => {
		setSearch(event.target.value);
		let filteredOrders = originalOrders.filter((item) => {
			return item.customerID.indexOf(event.target.value.toUpperCase()) !== -1;
		});
		setOrders([...filteredOrders]);
	};

	const orderDisplay = orders.map((item) => {
		return (
			<OrderItems
				key={item.id}
				order={item}
				deleteItem={() => deleteItem(item.id)}
				showDetails={() => showDetails(item.id)}
				orderRows={orderRows.filter((rows) => rows.orderID === item.id)}
			/>
		);
	});

	return (
		<div className="App">
			<header className="App-header">
				<h2>All orders at Company.com</h2>
				<div id="search-div">
					<input
						placeholder="Search customer"
						id="customer-search"
						type="text"
						value={search}
						onChange={updateSearch}
					/>
				</div>
				<div className="rTable">
					<div className="rTableHead">OrderID</div>
					<div className="rTableHead">CustomerID</div>
					<div className="rTableHead">Order date</div>
					<div className="rTableHead">Shipping date</div>
					<div className="rTableHead"></div>
					<div className="rTableHead"></div>

					{orderDisplay}
				</div>
			</header>
		</div>
	);
};

export default Orders;
