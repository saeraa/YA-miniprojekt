import settings from "../utils/settings.json";
import { useState, useEffect } from "react";
import RecommendationsForm from "../components/RecommendationForm";
import RecommendationItem from "../components/RecommendationItem";
//import recommendationsDummy from "../utils/recommendations";
import { Recommendation } from "../utils/interfaces";

const Recommendations = () => {
	const [recommendations, setRecommendations] = useState<Recommendation[] | []>(
		[]
	);
	const url = settings.api_url + ":" + settings.api_port;

	useEffect(() => {
		// dummydata example
		//setRecommendations(recommendationsDummy);

		const getData = async () => {
			const results = await fetch(url + "/recommendations");
			const data = await results.json();
			console.log(data);
			setRecommendations(() => {
				return [...data];
			});
		};

		getData().catch((error) => console.log(error));
	}, []);

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
			<details>
				<summary>Add a recommendation</summary>
				<RecommendationsForm />
			</details>
			<hr />
			<div className="rTable-recommendations">
				<div className="rTableHead">ProductID</div>
				<div className="rTableHead">Email</div>
				<div className="rTableHead">Comment</div>
				<div className="rTableHead">Rating</div>
				{recommendationItems.length > 0 && recommendationItems}
			</div>
		</>
	);
};

export default Recommendations;
