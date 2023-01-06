import { useState, useEffect } from "react";
import axios, { AxiosRequestConfig } from "axios";
import settings from "./settings.json";

axios.defaults.baseURL = settings.api_url + ":" + settings.api_port;

export const useAxiosFetch = (params: AxiosRequestConfig<any>) => {
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
