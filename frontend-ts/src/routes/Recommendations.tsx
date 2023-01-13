import settings from "../utils/settings.json";
import { useState, useEffect } from "react";
import RecommendationsForm from "../components/RecommendationForm";
import RecommendationItem from "../components/RecommendationItem";
//import recommendationsDummy from "../utils/recommendations";
import { Recommendation } from "../utils/interfaces";
import { keycloak } from "../utils/keycloak";

const Recommendations = () => {
	const [update, setUpdate] = useState(false);
	const [recommendations, setRecommendations] = useState<Recommendation[] | []>(
		[]
	);
	const url = settings.api_url + ":" + settings.api_port;

	const updateData = () => {
		setUpdate((prevState) => (prevState = !prevState));
	};

	useEffect(() => {
		// dummydata example
		//setRecommendations(recommendationsDummy);

		const getData = async () => {
			const results = await fetch(url + "/recommendations", {
				headers: {
					Authorization: `Bearer ${keycloak.token}`
				}
			});
			const data = await results.json();
			console.log(data);
			setRecommendations(() => {
				return [...data];
			});
		};

		getData().catch((error) => console.log(error));
	}, [update]);

	const recommendationItems = recommendations.map((recommendation) => {
		return (
			<RecommendationItem
				key={recommendation.id}
				recommendation={recommendation}
			/>
		);
	});
	return (
		<>
			<h1>Recommendations</h1>
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
	);
};

export default Recommendations;
