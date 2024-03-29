import { useState, FormEvent, ChangeEvent, useContext } from "react";
import settings from "../utils/settings.json";
import { Recommendation } from "../utils/interfaces";
import { SignInContext } from "../utils/signInContext";
import axios from "axios";

const RecommendationsForm = (props: { update: () => void }) => {
	const { token, loggedIn } = useContext(SignInContext);
	const { update } = props;
	const [recommendationSent, setRecommendationSent] = useState(false);
	const [formData, setFormData] = useState<Recommendation>({
		id: 0,
		productId: 0,
		comment: "",
		email: "",
		rating: 10
	});
	const [sending, setSending] = useState(false);

	async function callAPI() {
		const baseURL = settings.base_url + "/recommendation";
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
				setRecommendationSent(true);
				setTimeout(() => {
					setRecommendationSent(false);
				}, 3000);
			}, 2000);
		update();
	}

	function onInputChange(
		e: ChangeEvent<HTMLInputElement> | ChangeEvent<HTMLTextAreaElement>
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
		<>
			{!loggedIn ? (
				<h1>You need to log in.</h1>
			) : (
				<>
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
							required
							onChange={onInputChange}
							type="number"
							name="productId"
							id="productId"
							placeholder="Product ID"
						/>
						<label htmlFor="email">Email</label>
						<input
							required
							onChange={onInputChange}
							type="email"
							name="email"
							id="email"
							placeholder="example@email.now"
						/>
						<label htmlFor="comment">Comment (max 1000 characters)</label>
						<textarea
							required
							onChange={onInputChange}
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
				</>
			)}
		</>
	);
};

export default RecommendationsForm;
