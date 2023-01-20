import Products from "./routes/Products";
import Currency from "./routes/Currency";
import Navigation from "./components/Navigation";
import Orders from "./routes/Orders";
import Recommendations from "./routes/Recommendations";
import SupportIssues from "./routes/SupportIssues";
import Welcome from "./routes/Welcome";
import Login from "./routes/Login";
import { useJwt } from "react-jwt";
import { Routes, Route, useNavigate } from "react-router-dom";
import { useState, useEffect } from "react";
import { SignInContext } from "./utils/signInContext";
import {
	getTokenFromLocalStorage,
	storeTokenInLocalStorage
} from "./utils/auth";
import { MyToken } from "./utils/interfaces";

function App() {
	const navigate = useNavigate();
	const [loggedIn, setLoggedIn] = useState(false);
	const [token, setToken] = useState("");
	const [username, setUsername] = useState("");
	const { decodedToken, isExpired, reEvaluateToken } = useJwt<MyToken>(token);

	function logOut() {
		setLoggedIn(false);
	}
	function logIn() {
		setLoggedIn(true);
	}

	useEffect(() => {
		const tokenFromLocalStorage: string | null = getTokenFromLocalStorage();
		if (tokenFromLocalStorage) {
			reEvaluateToken(tokenFromLocalStorage);
			if (isExpired) {
				storeTokenInLocalStorage("");
			} else {
				setToken(tokenFromLocalStorage);
				setLoggedIn(true);
				if (decodedToken != null) {
					setUsername(decodedToken.sub);
				}
				navigate("/");
			}
		} else {
			return;
		}
	}, []);

	return (
		<>
			<SignInContext.Provider
				value={{
					loggedIn,
					setLoggedIn,
					token,
					setToken,
					username,
					setUsername
				}}
			>
				<Routes>
					<Route path="/" element={<Navigation />}>
						<Route path="/" element={<Welcome />} />
						<Route path="login" element={<Login />} />
						<Route path="products" element={<Products />} />
						<Route path="currency" element={<Currency />} />
						<Route path="orders" element={<Orders />} />
						<Route path="recommendations" element={<Recommendations />} />
						<Route path="supportissues" element={<SupportIssues />} />
					</Route>
				</Routes>
			</SignInContext.Provider>
		</>
	);
}

export default App;
