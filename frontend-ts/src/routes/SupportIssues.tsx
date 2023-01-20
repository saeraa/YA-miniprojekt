import { useState, useEffect } from "react";
import settings from "../utils/settings.json";
import SupportIssueItem from "../components/SupportIssueItem";
import SupportIssueForm from "../components/SupportIssueForm";
import { SupportIssue } from "../utils/interfaces";

const SupportIssues = () => {
	const [update, setUpdate] = useState(false);
	const [supportIssues, setSupportIssues] = useState<SupportIssue[] | []>([]);
	const url = settings.api_url + ":" + settings.api_port;

	const updateData = () => {
		setUpdate((prevState) => (prevState = !prevState));
	};

	useEffect(() => {
		const getData = async () => {
			const results = await fetch(url + "/supportissues", {
				headers: {}
			});
			const data = await results.json();
			if (data.status === 500) {
				return;
			}
			setSupportIssues(() => {
				return [...data];
			});
		};

		getData().catch((error) => console.log(error));
	}, [update]);

	const supportIssueItems = supportIssues.map((supportIssue) => {
		return (
			<SupportIssueItem key={supportIssue.id} supportIssue={supportIssue} />
		);
	});

	return (
		<>
			<h1>Support Issues</h1>
			<details>
				<summary>Add a support issue</summary>
				<SupportIssueForm update={updateData} />
			</details>

			<div className="table table-supportissues">
				<div className="table-head">Customer ID</div>
				<div className="table-head">Comment</div>
				<div className="table-head">Priority</div>
				<div className="table-head">Status Type</div>
				{supportIssueItems.length > 0 && supportIssueItems}
			</div>
		</>
	);
};

export default SupportIssues;

/* 
public class SupportIssue {
	private Integer id;
	private int customerId;
	private Priority priority;
	private String comment;
	private StatusType statusType;

	enum Priority {
		LOW, MEDIUM, HIGH
	}
	enum StatusType {
		PENDING, INPROGRESS, DONE
	}
	*/
