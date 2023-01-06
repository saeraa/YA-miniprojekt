import { NavLink, Outlet } from "react-router-dom";
import Logo from "./Logo";
import currency from "/currency.svg";
import order from "/order.svg";
import recommendation from "/recommendation.svg";
import supportissue from "/supportissue.svg";
import product from "/product.svg";
import { keycloak } from "../utils/keycloak";
import { useEffect, useState } from "react";

const Navigation = () => {
	const [loggedIn, setLoggedIn] = useState(false);
	const [username, setUserName] = useState("");

	useEffect(() => {
		if (keycloak.authenticated) {
			setLoggedIn(true);
			setUserName(keycloak.tokenParsed?.preferred_username);
		}
	}, [loggedIn]);

	return (
		<div className="app">
			{loggedIn && (
				<button type="button" className="" onClick={() => keycloak.logout()}>
					Logout ({username})
				</button>
			)}
			{!loggedIn && (
				<button type="button" className="" onClick={() => keycloak.login()}>
					Login
				</button>
			)}

			<nav>
				<div className="nav-container">
					<div className="nav-logo">
						<NavLink to={"/"}>
							<Logo />
						</NavLink>
					</div>
					<div className="nav-links">
						<NavLink
							to={`/currency`}
							className={({ isActive, isPending }) =>
								isActive ? "active" : isPending ? "pending" : ""
							}
						>
							<span>
								<img src={currency} alt="" />
							</span>
							<span>Currency</span>
						</NavLink>
						<NavLink
							to={`/products`}
							className={({ isActive, isPending }) =>
								isActive ? "active" : isPending ? "pending" : ""
							}
						>
							<span>
								<img src={product} alt="" />
							</span>
							<span>Products</span>
						</NavLink>
						<NavLink
							to={`/orders`}
							className={({ isActive, isPending }) =>
								isActive ? "active" : isPending ? "pending" : ""
							}
						>
							<span>
								<img src={order} alt="" />
							</span>
							<span>Orders</span>
						</NavLink>
						<NavLink
							to={`/recommendations`}
							className={({ isActive, isPending }) =>
								isActive ? "active" : isPending ? "pending" : ""
							}
						>
							<span>
								<img src={recommendation} alt="" />
							</span>
							<span>Recommendations</span>
						</NavLink>
						<NavLink
							to={`/supportissues`}
							className={({ isActive, isPending }) =>
								isActive ? "active" : isPending ? "pending" : ""
							}
						>
							<span>
								<img src={supportissue} alt="" />
							</span>
							<span>Support Issues</span>
						</NavLink>
					</div>
				</div>
			</nav>
			<main>
				<Outlet />
			</main>
		</div>
	);
};

export default Navigation;
