import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App";
import "./scss/main.scss";
import { BrowserRouter } from "react-router-dom";
import Keycloak from "keycloak-js";
import { initKeycloak } from "./utils/keycloak";

initKeycloak();

// function initKeycloak() {
// 	const keycloak = new Keycloak({
// 		url: "http://localhost:8080",
// 		realm: "react",
// 		clientId: "react"
// 	});
// 	keycloak
// 		.init({
// 			checkLoginIframe: false,
// 			onLoad: "login-required"
// 		})
// 		.then(function (authenticated) {
// 			alert(authenticated ? "authenticated" : "not authenticated");
// 		})
// 		.catch(function (err) {
// 			console.log(err);
// 		});
// }

// initKeycloak();

ReactDOM.createRoot(document.getElementById("root") as HTMLElement).render(
	<React.StrictMode>
		<BrowserRouter>
			<App />
		</BrowserRouter>
	</React.StrictMode>
);
