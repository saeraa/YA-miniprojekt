import { useState, useEffect, useContext } from "react";
import axios, { AxiosRequestConfig } from "axios";
import settings from "./settings.json";
import { SignInContext } from "../utils/signInContext";

axios.defaults.baseURL = settings.api_url + ":" + settings.api_port;

export const useAxiosLogin = (params: AxiosRequestConfig<any>) => {
	const { loggedIn } = useContext(SignInContext);
	const [data, setData] = useState<any>(null);
	const [error, setError] = useState<any>(null);
	const [loading, setLoading] = useState<boolean>(true);

	const fetchData = async (): Promise<void> => {
		try {
			const response = await axios.request(params);
			setData(response.data);
		} catch (error) {
			if (axios.isAxiosError(error)) {
				setError("Axios Error with Message: " + error.message);
			} else {
				setError(error);
			}
			setLoading(false);
		} finally {
			setLoading(false);
		}
	};

	useEffect(() => {
		fetchData();
	}, []);

	return [data, error, loading, fetchData] as const;
};
