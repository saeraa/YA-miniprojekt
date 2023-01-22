import { NavLink, Outlet } from "react-router-dom";
import Logo from "./Logo";
import currency from "/currency.svg";
import order from "/order.svg";
import recommendation from "/recommendation.svg";
import supportissue from "/supportissue.svg";
import product from "/product.svg";
import { useContext } from "react";
import { SignInContext } from "../utils/signInContext";

const Navigation = () => {
	const { loggedIn, setLoggedIn, username } = useContext(SignInContext);

	const button = loggedIn ? (
		<button className="nav-login" onClick={() => setLoggedIn(false)}>
			Logout ({username})
		</button>
	) : (
		<NavLink to="login">
			<button className="nav-login">Login</button>
		</NavLink>
	);

	return (
		<div className="app">
			<nav>
				<div className="nav-container">
					<div className="nav-logo">
						{button}
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
