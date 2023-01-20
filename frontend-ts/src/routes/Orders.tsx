import { useState, useEffect, SyntheticEvent, useContext } from "react";
import OrderItems from "../components/OrderItem";
import { Order, OrderRowType } from "../utils/interfaces";
import settings from "../utils/settings.json";
import { SignInContext } from "../utils/signInContext";
import { Buffer } from "buffer";
import { useAxiosFetch } from "../utils/useAxiosFetch";

const Orders = () => {
	const { token } = useContext(SignInContext);
	const [search, setSearch] = useState<string>("");
	const [originalOrders, setOriginalOrders] = useState<Order[] | []>([]);
	const [orders, setOrders] = useState<Order[] | []>([]);
	const [orderRows, setOrderRows] = useState<OrderRowType[] | []>([]);

	const url = settings.api_url + ":" + settings.api_port;

	const getDataParams = {
		method: "GET",
		url: url + "/orders",
		headers: {
			Authorization: "Bearer " + token
		}
	};

	const [data, error, loading, fetchData] = useAxiosFetch(getDataParams);

	useEffect(() => {
		const getData = async () => {
			await fetchData();
			setOriginalOrders(data);
			setOrders(data);
		};

		getData().catch((error) => console.log(error));
	}, []);

	const deleteItem = (id: number) => {
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

	const showDetails = async (id: number) => {
		let exists = false;
		orderRows.forEach((row) => {
			if (row.orderID === id) {
				exists = true;
			}
		});
		if (exists) {
			return;
		}
		const result = await fetch(url + `/order/${id}`, {
			headers: {}
		});
		const data = await result.json();
		setOrderRows((prevData) => {
			return [...prevData, ...data];
		});
	};

	const updateSearch = (event: SyntheticEvent) => {
		const target = event.target as HTMLInputElement;
		setSearch(target.value);
		let filteredOrders = originalOrders.filter((item) => {
			return item.customerID.indexOf(target.value.toUpperCase()) !== -1;
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
				orderRows={orderRows.filter(
					(rows: OrderRowType) => rows.orderID === item.id
				)}
			/>
		);
	});

	return (
		<div className="App">
			<h1>Orders</h1>
			<header className="App-header">
				<div id="search-div">
					<input
						placeholder="Search customer"
						id="customer-search"
						type="text"
						value={search}
						onChange={updateSearch}
					/>
				</div>
				<div className="table table-orders">
					<div className="table-head">OrderID</div>
					<div className="table-head">CustomerID</div>
					<div className="table-head">Order date</div>
					<div className="table-head">Shipping date</div>
					<div className="table-head"></div>
					<div className="table-head"></div>

					{orderDisplay}
				</div>
			</header>
		</div>
	);
};

export default Orders;

/* 
fetch('https://localhost:8888/orders', {method:'GET', 
headers: {'Authorization': 'Basic ' + btoa('user:password')}})
.then(response => response.json())
.then(json => console.log(json));

*/
