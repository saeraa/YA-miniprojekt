import { Recommendation } from "../utils/interfaces";

const RecommendationItem = (props: { recommendation: Recommendation }) => {
	const { comment, productId, rating, email } = props.recommendation;

	return (
		<>
			<div className="table-cell">{productId}</div>
			<div className="table-cell">{email}</div>
			<div className="table-cell">{comment}</div>
			<div className="table-cell">{rating}</div>
		</>
	);
};

export default RecommendationItem;
