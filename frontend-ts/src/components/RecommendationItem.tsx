import { Recommendation } from "../utils/interfaces";

interface RecommendationItemProps {
	recommendation: Recommendation;
}

const RecommendationItem = (props: RecommendationItemProps) => {
	const { comment, productId, rating, email } = props.recommendation;

	return (
		<>
			<div className="rTableCell">{productId}</div>
			<div className="rTableCell">{email}</div>
			<div className="rTableCell">{comment}</div>
			<div className="rTableCell">{rating}</div>
		</>
	);
};

export default RecommendationItem;
