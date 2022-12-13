import React from "react";
import Products from "./components/Products";
import Currency from "./components/Currency";
import Navigation from "./components/Navigation";
import Orders from "./components/Orders";
import Recommendations from "./components/Recommendations";
import SupportIssues from "./components/SupportIssues";

import { Routes, Route } from "react-router-dom";

function App() {
	return (
		<>
			<Routes>
				<Route path="/" element={<Navigation />}>
					<Route path="products" element={<Products />} />
					<Route path="currency" element={<Currency />} />
					<Route path="orders" element={<Orders />} />
					<Route path="recommendations" element={<Recommendations />} />
					<Route path="supportissues" element={<SupportIssues />} />
				</Route>
			</Routes>
		</>
	);
}

export default App;
