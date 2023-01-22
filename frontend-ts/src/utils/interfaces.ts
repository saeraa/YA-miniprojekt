export interface CurrencyType {
	euroPrice: number;
	currency: string;
}

export interface Customer {
	address: string;
	city: string;
	companyName: string;
	contactTitle: string;
	customerId: string;
}

export interface Recommendation {
	id: number;
	productId: number;
	comment: string;
	email: string;
	rating: number;
}

export interface SupportIssue {
	id: number;
	customerId: string;
	comment: string;
	priority: string;
	statusType: string;
}

export interface SignIn {
	setLoggedIn: React.Dispatch<React.SetStateAction<boolean>>;
	loggedIn: boolean;
	token: string;
	setToken: React.Dispatch<React.SetStateAction<string>>;
	username: string;
	setUsername: React.Dispatch<React.SetStateAction<string>>;
	reEvaluateToken: (token: string) => void;
	isExpired: boolean;
	decodedToken: MyToken | null;
}

export interface MyToken {
	exp: number;
	iat: number;
	iss: string;
	scope: string;
	sub: string;
	reEvaluateToken: (token: string) => void;
	isExpired: boolean;
}
