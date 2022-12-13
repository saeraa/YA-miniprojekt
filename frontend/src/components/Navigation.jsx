import React from "react";
import { NavLink, Outlet } from "react-router-dom";

const Navigation = () => {
	return (
		<div className="app">
			<nav>
				<NavLink
					to={`/currency`}
					className={({ isActive, isPending }) =>
						isActive ? "active" : isPending ? "pending" : ""
					}
				>
					💰 Currency
				</NavLink>
				<NavLink
					to={`/products`}
					className={({ isActive, isPending }) =>
						isActive ? "active" : isPending ? "pending" : ""
					}
				>
					🧻 Products
				</NavLink>
				<NavLink
					to={`/orders`}
					className={({ isActive, isPending }) =>
						isActive ? "active" : isPending ? "pending" : ""
					}
				>
					📦 Orders
				</NavLink>
				<NavLink
					to={`/recommendations`}
					className={({ isActive, isPending }) =>
						isActive ? "active" : isPending ? "pending" : ""
					}
				>
					🗨️ Recommendations
				</NavLink>
				<NavLink
					to={`/supportissues`}
					className={({ isActive, isPending }) =>
						isActive ? "active" : isPending ? "pending" : ""
					}
				>
					📝 Support Issues
				</NavLink>
			</nav>
			<main>
				<Outlet />
			</main>
		</div>
	);
};

export default Navigation;
