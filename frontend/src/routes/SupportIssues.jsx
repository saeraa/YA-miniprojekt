import React from "react";

const SupportIssues = () => {
	function onFormSubmit(event) {
		event.preventDefault();
	}

	function onInputChange() {
		console.log("hi");
	}

	return (
		<form onSubmit={onFormSubmit}>
			<select onChange={onInputChange}>
				<option value="LOW" name="LOW">
					Low priority
				</option>
				<option value="MEDIUM" name="MEDIUM">
					Medium priority
				</option>
				<option value="HIGH" name="HIGH">
					High priority
				</option>
			</select>
			<select>
				<option value="pending" name="">
					Pending
				</option>
				<option value="inprogress" name="">
					In progress
				</option>
				<option value="done" name="">
					Done
				</option>
			</select>
			<input
				type="text"
				name="comment"
				placeholder="Comment"
				onChange={onInputChange}
			/>
			<input
				type="text"
				name="customerId"
				placeholder="Customer ID"
				onChange={onInputChange}
			/>
		</form>
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
