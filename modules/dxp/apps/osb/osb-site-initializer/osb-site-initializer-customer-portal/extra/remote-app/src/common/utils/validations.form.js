const EMAIL_REGEX = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
const LOWCASE_NUMBERS_REGEX = /^[0-9a-z]+$/;

const required = (value) => {
	if (!value) {
		return 'This field is required.';
	}
};

const maxLength = (value, max) => {
	if (value.length > max) {
		return `This field exceeded ${max} characters.`;
	}
};

const isValidEmail = (value, bannedEmailDomains) => {
	if (!EMAIL_REGEX.test(value)) {
		return 'Please insert a valid email.';
	}

	if (bannedEmailDomains.includes(value.split('@')[1])) {
		return 'Email domain not allowed.';
	}
};

const isLowercaseAndNumbers = (value) => {
	if (!LOWCASE_NUMBERS_REGEX.test(value)) {
		return 'Lowercase letters and numbers only.';
	}
};

const validate = (validations, value) => {
	let error;

	if (validations) {
		validations.forEach((validation) => {
			const callback = validation(value);

			if (callback) {
				error = callback;
			}
		});
	}

	return error;
};

export {
	required,
	maxLength,
	isValidEmail,
	validate,
	isLowercaseAndNumbers,
};
