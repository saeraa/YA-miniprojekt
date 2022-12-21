import React from "react";

const RecommendationItem = (props) => {
	const { buyItem } = props;
	const { comment, id, rating, email } = props.recommendation;

	return (
		<>
			<div className="rTableCell">{id}</div>
			<div className="rTableCell">{email}</div>
			<div className="rTableCell">{comment}</div>
			<div className="rTableCell">{rating}</div>
		</>
	);
};

export default RecommendationItem;
