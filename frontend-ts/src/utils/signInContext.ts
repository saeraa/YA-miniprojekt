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
	}
});
