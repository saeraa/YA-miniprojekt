import React from "react";
import settings from "../properties/settings.json";

const RecommendationsForm = () => {
	const [supportIssueSent, setSupportIssueSent] = React.useState(false);
	const [formData, setFormData] = React.useState({
		priority: "LOW",
		statusType: "PENDING"
	});
	const [sending, setSending] = React.useState(false);

	async function callAPI() {
		const baseURL = settings.base_url + "/supportissue/" + formData.customerId;
		console.log(formData);
		const apiResponse = await fetch(baseURL, {
			headers: {
				"Content-Type": "application/json"
			},
			method: "POST",
			body: JSON.stringify(formData)
		});
		const result = await apiResponse.json();

		return result;
	}

	async function submitForm(e) {
		e.preventDefault();
		setSending(true);
		const response = await callAPI();
		if (response)
			setTimeout(() => {
				setSending(false);
				e.target.reset();
				setSupportIssueSent(true);
				setTimeout(() => {
					setSupportIssueSent(false);
				}, 3000);
			}, 2000);
	}

	function onInputChange(e) {
		const { name, value } = e.target;
		setFormData((prevData) => {
			return {
				...prevData,
				[name]: value
			};
		});
	}

	return (
		<form onSubmit={submitForm}>
			<select onChange={onInputChange} value={formData.priority}>
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
			<select onChange={onInputChange} value={formData.statusType}>
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
				id="comment"
				placeholder="Comment"
				onChange={onInputChange}
			/>
			<input
				type="text"
				name="customerId"
				id="customerId"
				placeholder="Customer ID"
				onChange={onInputChange}
			/>
			<input
				type="submit"
				name="submit"
				value={sending ? "Submitting.." : "Send"}
			/>
			{supportIssueSent && (
				<p className="recommendation-thanks">
					Thank you for submitting your issue.
				</p>
			)}
		</form>
	);
};

export default RecommendationsForm;
