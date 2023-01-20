import { useState, useEffect, ChangeEvent, useContext } from "react";
import { SignInContext } from "../utils/signInContext";
import {
	getTokenFromLocalStorage,
	storeTokenInLocalStorage
} from "../utils/auth";
import { useNavigate } from "react-router-dom";
import { useAxiosFetch } from "../utils/useAxiosFetch";
import settings from "../utils/settings.json";
import { useJwt } from "react-jwt";
import { Buffer } from "buffer";

const Login = () => {
	const { loggedIn, setLoggedIn, token, setToken } = useContext(SignInContext);
	const navigate = useNavigate();

	const url = settings.api_url + ":" + settings.api_port;
	const [user, setUser] = useState("user");
	const [password, setPassword] = useState("password");
	const [isLoading, setIsLoading] = useState(false);

	const { decodedToken, isExpired, reEvaluateToken } = useJwt(token);

	const [data, error, loading, fetchData] = useAxiosFetch({
		method: "POST",
		url: url + "/token",
		headers: {
			Authorization:
				"Basic " + Buffer.from(user + ":" + password).toString("base64")
		}
	});

	useEffect(() => {
		const tokenFromLocalStorage: string | null = getTokenFromLocalStorage();
		if (tokenFromLocalStorage) {
			reEvaluateToken(tokenFromLocalStorage);
			if (isExpired) {
				storeTokenInLocalStorage("");
			} else {
				setToken(tokenFromLocalStorage);
				setIsLoading(false);
				setLoggedIn(true);
				//navigate("/");
			}
		} else {
			return;
		}
	}, []);

	async function submitForm() {
		setIsLoading(true);
		console.log("submitForm method ", { loggedIn });
		setTimeout(() => {
			fetchData();
			console.log(data);
			reEvaluateToken(data);
			if (!isExpired) {
				storeTokenInLocalStorage(data);
				setLoggedIn(true);
			}
		}, 500);
	}

	function onInputChange(e: ChangeEvent<HTMLInputElement>) {
		const { name, value } = e.target;
		if (name == "user") {
			setUser(value);
		} else if (name == "password") {
			setPassword(value);
		}
	}

	useEffect(() => {
		if (data) {
			//	console.log("data");
		} else {
			//	console.log("meep");
		}
	}, []);

	useEffect(() => {
		if (error) {
			console.log(error);
		}
	}, [error]);

	return (
		<div>
			<form>
				<input
					type="text"
					name="username"
					placeholder="user"
					onChange={onInputChange}
					autoComplete="on"
				/>
				<input
					autoComplete="on"
					type="password"
					name="password"
					placeholder="password"
					onChange={onInputChange}
				/>
				<input
					type="button"
					onClick={submitForm}
					name="login"
					value={isLoading ? "logging in..." : "login"}
				/>
			</form>
			{loggedIn && <div>You are logged in!</div>}
		</div>
	);
};

export default Login;
