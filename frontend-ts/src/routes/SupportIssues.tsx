import { useState, useEffect, useContext } from "react";
import settings from "../utils/settings.json";
import SupportIssueItem from "../components/SupportIssueItem";
import SupportIssueForm from "../components/SupportIssueForm";
import { SupportIssue } from "../utils/interfaces";
import { SignInContext } from "../utils/signInContext";
import { useAxiosFetch } from "../utils/useAxiosFetch";

const SupportIssues = () => {
	const { token, loggedIn } = useContext(SignInContext);
	const [update, setUpdate] = useState(false);
	const [supportIssues, setSupportIssues] = useState<SupportIssue[] | []>([]);
	const url = settings.api_url + ":" + settings.api_port;
	const updateData = () => {
		setUpdate((prevState) => (prevState = !prevState));
	};

	const getDataParams = {
		method: "GET",
		url: url + "/supportissues",
		headers: {
			Authorization: "Bearer " + token
		}
	};

	const [data, error, loading, fetchData] = useAxiosFetch(getDataParams);

	const getSupportIssuesFromAPI = async () => {
		await fetchData();
		setSupportIssues((prevdata) => data);
	};

	useEffect(() => {
		if (data) {
			setSupportIssues(data);
		}
	}, [data]);

	useEffect(() => {
		if (!loggedIn) return;
		if (error) {
			console.log(error);
		}
		const getData = async () => {
			await fetchData();
		};

		getData().catch((error) => console.log(error));
	}, []);

	const supportIssueItems =
		supportIssues !== null
			? supportIssues.map((supportIssue) => {
					return (
						<SupportIssueItem
							key={supportIssue.id}
							supportIssue={supportIssue}
						/>
					);
			  })
			: "No results";

	return (
		<div className="App">
			{!loggedIn ? (
				<h1>You need to log in.</h1>
			) : (
				<>
					<h1>
						Support Issues <span onClick={getSupportIssuesFromAPI}>🔄️</span>
					</h1>
					<details>
						<summary>Add a support issue</summary>
						<SupportIssueForm update={updateData} />
					</details>

					<div className="table table-supportissues">
						<div className="table-head">Customer ID</div>
						<div className="table-head">Comment</div>
						<div className="table-head">Priority</div>
						<div className="table-head">Status Type</div>
						{supportIssueItems.length > 0 && supportIssueItems}
					</div>
				</>
			)}
		</div>
	);
};

export default SupportIssues;
