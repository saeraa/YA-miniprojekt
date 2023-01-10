import { SupportIssue } from "../utils/interfaces";

interface SupportIssueItemProps {
	supportIssue: SupportIssue;
}

const SupportIssueItem = (props: SupportIssueItemProps) => {
	const { customerId, comment, priority, statusType } = props.supportIssue;

	return (
		<>
			<div className="table-cell">{customerId}</div>
			<div className="table-cell">{comment}</div>
			<div className="table-cell">
				{priority == "HIGH"
					? "High priority"
					: priority == "LOW"
					? "Low priority"
					: "Medium priority"}
			</div>
			<div className="table-cell">
				{statusType == "INPROGRESS"
					? "In progress"
					: statusType == "DONE"
					? "Done"
					: "Pending"}
			</div>
		</>
	);
};

export default SupportIssueItem;
