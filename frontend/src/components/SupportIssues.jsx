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
				<option value="" name=""></option>
				<option value="" name=""></option>
				<option value="" name=""></option>
			</select>
			<input type="text" placeholder="" onChange={onInputChange} />
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
