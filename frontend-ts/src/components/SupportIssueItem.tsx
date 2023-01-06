import { SupportIssue } from "../utils/interfaces";

interface SupportIssueItemProps {
	supportIssue: SupportIssue;
}

const SupportIssueItem = (props: SupportIssueItemProps) => {
	const { customerId, comment, priority, statusType } = props.supportIssue;

	return (
		<>
			<div className="rTableCell">{customerId}</div>
			<div className="rTableCell">{comment}</div>
			<div className="rTableCell">
				{priority == "HIGH"
					? "High priority"
					: priority == "LOW"
					? "Low priority"
					: "Medium priority"}
			</div>
			<div className="rTableCell">
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
