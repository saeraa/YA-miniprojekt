import { useState } from "react";
import settings from "../utils/settings.json";
import { CurrencyType } from "../utils/interfaces";

const Currency = () => {
	const currencies = [
		"USD",
		"JPY",
		"BGN",
		"CZK",
		"DKK",
		"GBP",
		"HUF",
		"PLN",
		"RON",
		"SEK",
		"CHF",
		"ISK",
		"NOK",
		"HRK",
		"TRY",
		"AUD",
		"BRL",
		"CAD",
		"CNY",
		"HKD",
		"IDR",
		"ILS",
		"INR",
		"KRW",
		"MXN",
		"MYR",
		"NZD",
		"PHP",
		"SGD",
		"THB",
		"ZAR"
	];
	const [formData, setFormData] = useState<CurrencyType>({
		euroPrice: 0,
		currency: "USD"
	});
	const [calculatedPrice, setCalculatedPrice] = useState({
		price: "",
		resultString: ""
	});

	async function callAPI() {
		const baseURL = settings.base_url + "/convertCurrency/";
		const apiResponse = await fetch(baseURL, {
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

	async function submitForm(e: React.FormEvent<HTMLFormElement>) {
		const target = e.target as HTMLFormElement;
		e.preventDefault();

		let result = await callAPI();
		result = Number(result).toFixed(2);
		setCalculatedPrice({
			price: result,
			resultString: `€${
				formData.euroPrice
			} is equal to ${result} in ${formData.currency.toUpperCase()}.`
		});
		target.reset();
	}

	function onInputChange(
		event:
			| React.ChangeEvent<HTMLInputElement>
			| React.ChangeEvent<HTMLSelectElement>
	) {
		const { name, value } = event.target;
		setFormData((prevData) => {
			return {
				...prevData,
				[name]: value.toUpperCase()
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

				<label htmlFor="currency">Choose currency</label>
				<select
					value={formData.currency}
					onChange={onInputChange}
					className="currency-currency"
					id="currency"
					name="currency"
				>
					{currencies.map((currency) => (
						<option key={currency} value={currency}>
							{currency}
						</option>
					))}
				</select>

				<input type="submit" value="Calculate" />
			</form>

			{calculatedPrice.price && (
				<div className="currency-output">{calculatedPrice.resultString}</div>
			)}
		</div>
	);
};

export default Currency;
