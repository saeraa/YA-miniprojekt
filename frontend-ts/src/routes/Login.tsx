import { useState, useEffect, ChangeEvent, useContext } from "react";
import { SignInContext } from "../utils/signInContext";
import {
	getTokenFromLocalStorage,
	storeTokenInLocalStorage
} from "../utils/auth";
import { useNavigate } from "react-router-dom";
import settings from "../utils/settings.json";
import { Buffer } from "buffer";
import { useAxiosLogin } from "../utils/useAxiosLogin";

const Login = () => {
	const {
		loggedIn,
		setLoggedIn,
		token,
		setToken,
		reEvaluateToken,
		isExpired,
		decodedToken,
		setUsername
	} = useContext(SignInContext);
	const navigate = useNavigate();

	const url = settings.api_url + ":" + settings.api_port;
	const [user, setUser] = useState("");
	const [password, setPassword] = useState("");
	const [isLoading, setIsLoading] = useState(false);

	const [data, error, loading, fetchData] = useAxiosLogin({
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
				if (decodedToken != null) {
					setUsername(decodedToken.sub);
				}
				navigate("/");
			}
		} else {
			return;
		}
	}, []);

	async function submitForm() {
		setIsLoading(true);
		setTimeout(() => {
			fetchData();
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
