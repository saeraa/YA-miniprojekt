import { createContext, SetStateAction } from "react";
import { SignIn } from "./interfaces";

export const SignInContext = createContext<SignIn>({
	setLoggedIn: function (value: SetStateAction<boolean>): void {
		throw new Error("Function not implemented.");
	},
	loggedIn: false,
	token: "",
	setToken: function (value: SetStateAction<string>): void {
		throw new Error("Function not implemented.");
	},
	username: "",
	setUsername: function (value: SetStateAction<string>): void {
		throw new Error("Function not implemented.");
	},
	reEvaluateToken: function (value: SetStateAction<string>): void {
		throw new Error("Function not implemented.");
	},
	isExpired: false,
	decodedToken: {
		exp: 1,
		iat: 1,
		iss: "",
		scope: "",
		sub: "",
		reEvaluateToken: function (value: SetStateAction<string>): void {
			throw new Error("Function not implemented.");
		},
		isExpired: true
	}
});
