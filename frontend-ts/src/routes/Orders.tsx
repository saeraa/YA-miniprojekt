import { useState, useEffect, SyntheticEvent, useContext } from "react";
import OrderItems from "../components/OrderItem";
import { Order, OrderRowType } from "../utils/orderInterfaces";
import settings from "../utils/settings.json";
import { SignInContext } from "../utils/signInContext";
import { useAxiosFetch } from "../utils/useAxiosFetch";
import axios from "axios";

const Orders = () => {
	const { token, loggedIn } = useContext(SignInContext);
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

	const getOrdersFromAPI = async () => {
		await fetchData();
		setOrders(data);
	};

	useEffect(() => {
		// console.log("data");
		if (data) {
			setOriginalOrders(data);
			setOrders(data);
		}
	}, [data]);

	useEffect(() => {
		const getData = async () => {
			await fetchData();
		};

		getData().catch((error) => console.log(error));
	}, []);

	const deleteItem = async (id: number) => {
		const filteredItems = originalOrders.filter((order) => {
			return order.id !== id;
		});
		setOriginalOrders(() => {
			return [...filteredItems];
		});
		setOrders(() => {
			return [...filteredItems];
		});
		const response = await axios.request({
			method: "DELETE",
			url: url + `/deleteOrder/${id}`,
			headers: {
				Authorization: "Bearer " + token
			}
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
		const response = await axios.request({
			method: "GET",
			url: url + `/order/${id}`,
			headers: {
				Authorization: "Bearer " + token
			}
		});
		setOrderRows((prevData) => {
			return [...prevData, ...response.data];
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

	const orderDisplay =
		orders !== null
			? orders.map((item) => {
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
			  })
			: "No results";

	return (
		<div className="App">
			{!loggedIn ? (
				<h1>You need to log in.</h1>
			) : (
				<>
					<h1>
						Orders <span onClick={getOrdersFromAPI}>🔄️</span>
					</h1>

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
				</>
			)}
		</div>
	);
};

export default Orders;
