import settings from "../properties/settings.json";
import React from "react";
import RecommendationsForm from "../components/RecommendationsForm";
import RecommendationItem from "../components/RecommendationItem";
// import recommendationsDummy from "../properties/recommendations";

const Recommendations = () => {
	const [recommendations, setRecommendations] = React.useState([]);
	const url = settings.api_url + ":" + settings.api_port;

	React.useEffect(() => {
		// dummydata example
		//		setRecommendations(recommendationsDummy);

		const getData = async () => {
			const results = await fetch(url + "/recommendations");
			const data = await results.json();
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
