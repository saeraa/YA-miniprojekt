import { Recommendation } from "../utils/interfaces";

interface RecommendationItemProps {
	recommendation: Recommendation;
}

const RecommendationItem = (props: RecommendationItemProps) => {
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
