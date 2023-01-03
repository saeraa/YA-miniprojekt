import Products from "./routes/Products";
import Currency from "./routes/Currency";
import Navigation from "./components/Navigation";
import Orders from "./routes/Orders";
import Recommendations from "./routes/Recommendations";
import SupportIssues from "./routes/SupportIssues";
import Welcome from "./routes/Welcome";

import { Routes, Route } from "react-router-dom";

function App() {
	return (
		<>
			<Routes>
				<Route path="/" element={<Navigation />}>
					<Route path="/" element={<Welcome />} />
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
