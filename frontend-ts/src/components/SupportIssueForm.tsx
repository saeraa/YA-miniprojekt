import { useState, useEffect, FormEvent, ChangeEvent, useContext } from "react";
import { Customer } from "../utils/interfaces";
import settings from "../utils/settings.json";
import { useAxiosFetch } from "../utils/useAxiosFetch";
import { SignInContext } from "../utils/signInContext";
import axios from "axios";

const SupportIssueForm = (props: { update: () => void }) => {
	const { token, loggedIn } = useContext(SignInContext);
	const { update } = props;
	const [supportIssueSent, setSupportIssueSent] = useState(false);
	const [formData, setFormData] = useState({
		priority: "LOW",
		statusType: "PENDING",
		comment: "",
		customerId: "ALFKI"
	});
	const [sending, setSending] = useState(false);
	const [customers, setCustomers] = useState<Customer[] | []>([]);

	const params = {
		method: "GET",
		url: "/customers",
		headers: {
			Authorization: "Bearer " + token
		}
	};

	const [data, error, loading, fetchData] = useAxiosFetch(params);

	useEffect(() => {
		if (data) {
			setCustomers(data);
		} else {
			setCustomers([]);
		}
	}, [data]);

	useEffect(() => {
		if (error) {
			console.log(error);
		}
	}, [error]);

	const url = settings.api_url + ":" + settings.api_port;

	async function callAPI() {
		const baseURL = url + "/supportissue/" + formData.customerId;
		const apiResponse = await axios.request({
			method: "POST",
			url: baseURL,
			data: JSON.stringify(formData),
			headers: {
				Authorization: "Bearer " + token,
				"Content-Type": "application/json"
			}
		});
		const result = apiResponse.data;

		return result;
	}

	async function submitForm(e: FormEvent<HTMLFormElement>) {
		const target = e.target as HTMLFormElement;
		e.preventDefault();
		setSending(true);
		const response = await callAPI();
		if (response)
			setTimeout(() => {
				setSending(false);
				target.reset();
				setSupportIssueSent(true);
				setTimeout(() => {
					setSupportIssueSent(false);
				}, 3000);
			}, 2000);
		update();
	}

	function onInputChange(
		e: ChangeEvent<HTMLSelectElement> | ChangeEvent<HTMLInputElement>
	) {
		const { name, value } = e.target;
		setFormData((prevData) => {
			return {
				...prevData,
				[name]: value
			};
		});
	}

	return (
		<form className="recommendations-form" onSubmit={submitForm}>
			<select
				name="priority"
				onChange={onInputChange}
				value={formData.priority}
			>
				<option value="LOW">Low priority</option>
				<option value="MEDIUM">Medium priority</option>
				<option value="HIGH">High priority</option>
			</select>
			<select
				name="statusType"
				onChange={onInputChange}
				value={formData.statusType}
			>
				<option value="PENDING">Pending</option>
				<option value="INPROGRESS">In progress</option>
				<option value="DONE">Done</option>
			</select>
			<input
				type="text"
				name="comment"
				id="comment"
				placeholder="Comment"
				onChange={onInputChange}
			/>
			<select
				name="customerId"
				onChange={onInputChange}
				value={formData.customerId}
			>
				{customers?.map((customer) => {
					return (
						<option
							key={customer.customerId}
							value={customer.customerId.toLowerCase()}
						>
							{customer.customerId}
						</option>
					);
				})}
			</select>
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

export default SupportIssueForm;
