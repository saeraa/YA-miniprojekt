import { useState, useEffect } from "react";
import settings from "../utils/settings.json";
import SupportIssueItem from "../components/SupportIssueItem";
import SupportIssueForm from "../components/SupportIssueForm";

const SupportIssues = () => {
	const [supportIssues, setSupportIssues] = useState([]);
	const url = settings.api_url + ":" + settings.api_port;

	useEffect(() => {
		const getData = async () => {
			const results = await fetch(url + "/supportissues");
			const data = await results.json();
			if (data.status === 500) {
				return;
			}
			setSupportIssues(() => {
				return [...data];
			});
		};

		getData().catch((error) => console.log(error));
	}, []);

	const supportIssueItems = supportIssues.map((supportIssue) => {
		return (
			<SupportIssueItem key={supportIssue.id} supportIssue={supportIssue} />
		);
	});

	function onFormSubmit(event) {
		event.preventDefault();
	}

	function onInputChange() {
		console.log("hi");
	}

	return (
		<>
			<details>
				<summary>Add a support issue</summary>
				<SupportIssueForm />
			</details>
			<hr />
			<div className="rTable-recommendations">
				<div className="rTableHead">Customer ID</div>
				<div className="rTableHead">Comment</div>
				<div className="rTableHead">Priority</div>
				<div className="rTableHead">Status Type</div>
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
