import settings from "../utils/settings.json";
import { useState, useEffect, useContext } from "react";
import RecommendationsForm from "../components/RecommendationForm";
import RecommendationItem from "../components/RecommendationItem";
import { Recommendation } from "../utils/interfaces";
import { SignInContext } from "../utils/signInContext";
import { useAxiosFetch } from "../utils/useAxiosFetch";

const Recommendations = () => {
	const { token, decodedToken, loggedIn } = useContext(SignInContext);
	const [update, setUpdate] = useState(false);
	const [recommendations, setRecommendations] = useState<Recommendation[] | []>(
		[]
	);

	const url = settings.api_url + ":" + settings.api_port;

	const updateData = () => {
		setUpdate((prevState) => (prevState = !prevState));
	};

	const getDataParams = {
		method: "GET",
		url: url + "/recommendations",
		headers: {
			Authorization: "Bearer " + token
		}
	};

	const [data, error, loading, fetchData] = useAxiosFetch(getDataParams);

	const getRecommendationsFromAPI = async () => {
		await fetchData();
		setRecommendations((prevdata) => data);
	};

	useEffect(() => {
		if (data) {
			setRecommendations(data);
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

	const recommendationItems =
		recommendations !== null
			? recommendations.map((recommendation) => {
					return (
						<RecommendationItem
							key={recommendation.id}
							recommendation={recommendation}
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
						Recommendations <span onClick={getRecommendationsFromAPI}>🔄️</span>
					</h1>
					<details>
						<summary>Add a recommendation</summary>
						<RecommendationsForm update={updateData} />
					</details>

					<div className="table table-recommendations">
						<div className="table-head">ProductID</div>
						<div className="table-head">Email</div>
						<div className="table-head">Comment</div>
						<div className="table-head">Rating</div>
						{recommendationItems.length > 0 && recommendationItems}
					</div>
				</>
			)}
		</div>
	);
};

export default Recommendations;
