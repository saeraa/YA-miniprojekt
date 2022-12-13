import React from "react";
import settings from "../properties/settings.json";

function Currency(props) {
	const [formData, setFormData] = React.useState({});
	const [calculatedPrice, setCalculatedPrice] = React.useState({
		price: null,
		resultString: null
	});

	async function callAPI() {
		const baseURL = settings.base_url + "/convertCurrency/";
		const apiResponse = await fetch(baseURL + formData.currency, {
			headers: {
				"Content-Type": "application/json"
			},
			method: "POST",
			body: JSON.stringify(formData)
			// sends an object { euroPrice: x.yy, currency: xyz }
		});
		const result = await apiResponse.text();
		// if you receive json data as a response, change .text() in the above line to .json()
		return result;
	}

	async function submitForm(e) {
		e.preventDefault();

		let result = await callAPI();
		result = Number(result).toFixed(2);
		setCalculatedPrice({
			price: result,
			resultString: `€${
				formData.euroPrice
			} is equal to ${result} in ${formData.currency.toUpperCase()}.`
		});
		e.target.reset();
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
		<div>
			<form onSubmit={submitForm}>
				<label htmlFor="amount">Enter amount to calculate</label>
				<input
					className="currency-amount"
					name="euroPrice"
					onChange={onInputChange}
					type="number"
					id="amount"
				/>

				<label htmlFor="currency">Enter your currency</label>
				<input
					className="currency-currency"
					name="currency"
					onChange={onInputChange}
					type="text"
					id="currency"
				/>

				<input type="submit" value="Calculate" />
			</form>

			{calculatedPrice.price && (
				<div className="currency-output">{calculatedPrice.resultString}</div>
			)}
		</div>
	);
}

export default Currency;
