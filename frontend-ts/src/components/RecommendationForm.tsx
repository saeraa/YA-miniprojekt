import { useState } from "react";
import settings from "../utils/settings.json";

const RecommendationsForm = () => {
	const [recommendationSent, setRecommendationSent] = useState(false);
	const [formData, setFormData] = useState({
		rating: 10
	});
	const [sending, setSending] = useState(false);

	async function callAPI() {
		const baseURL = settings.base_url + "/recommendation/";
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
				setRecommendationSent(true);
				setTimeout(() => {
					setRecommendationSent(false);
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
		<form className="recommendations-form" onSubmit={submitForm}>
			<label htmlFor="rating">Rating</label>
			<input
				value={formData.rating}
				onChange={onInputChange}
				list="tickmarks"
				type="range"
				min="1"
				max="10"
				step="1"
				name="rating"
				id="rating"
			/>
			<datalist id="tickmarks">
				<option value="1" label="1"></option>
				<option value="2" label="2"></option>
				<option value="3" label="3"></option>
				<option value="4" label="4"></option>
				<option value="5" label="5"></option>
				<option value="6" label="6"></option>
				<option value="7" label="7"></option>
				<option value="8" label="8"></option>
				<option value="9" label="9"></option>
				<option value="10" label="10"></option>
			</datalist>
			<label htmlFor="productId">Product ID</label>
			<input
				onChange={onInputChange}
				type="number"
				name="productId"
				id="productId"
				placeholder="Product ID"
			/>
			<label htmlFor="email">Email</label>
			<input
				onChange={onInputChange}
				type="email"
				name="email"
				id="email"
				placeholder="example@email.now"
			/>
			<label htmlFor="comment">Comment (max 1000 characters)</label>
			<textarea
				onChange={onInputChange}
				type="text"
				name="comment"
				id="comment"
				rows={5}
				placeholder="Your comment here."
			/>
			<input
				type="submit"
				name="submit"
				value={sending ? "Submitting.." : "Send"}
			/>
			{recommendationSent && (
				<p className="recommendation-thanks">
					Thank you for your recommendation!
				</p>
			)}
		</form>
	);
};

export default RecommendationsForm;
