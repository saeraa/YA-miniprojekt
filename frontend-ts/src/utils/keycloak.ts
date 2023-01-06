import Keycloak from "keycloak-js";

// Setup Keycloak instance as needed
// Pass initialization options as required or leave blank to load from 'keycloak.json'
const keycloak = new Keycloak({
	url: "http://localhost:8080",
	realm: "react",
	clientId: "react"
});

function initKeycloak() {
	keycloak
			.init({
				checkLoginIframe: false,
				onLoad: "login-required"
			})
			.then(function (authenticated) {
				console.log(authenticated ? "authenticated" : "not authenticated");
			})
			.catch(function (err) {
				console.log(err);
			});
}


export { keycloak, initKeycloak};