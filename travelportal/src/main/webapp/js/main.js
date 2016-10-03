/**
 * 
 */

function submitForm(objForm) {
	alert("submitting form");
	objForm.submit();
}

function printMessage(msg, color) {
	var elem = document.getElementById("message");
	elem.innerHTML = msg
	elem.style.color = color;

}

function logout() {
	
}
function echo() {
	alert("test");
}

function validation() {

	if (document.getElementById("reportname").value.length < 1
			|| document.getElementById("tourPurpose").value.length < 1
			|| document.getElementById("travelMode").value.length < 1
			|| document.getElementById("cost").value.length < 1) {
		alert("Missing mandator fields");
		return false;
	}
	if (!isValidDate(document.getElementById("startDate").value)) {
		alert("Invalid Start Date format");
		return false;
	}

	if (!isValidDate(document.getElementById("endDate").value)) {
		alert("Invalid End Date format");
		return false;
	}

	if (Date.parse(document.getElementById("startDate").value) > Date
			.parse(document.getElementById("endDate").value)) {
		alert("Start Date should be earlier than End Date");
		return false;
	}

	if (! isNumber(document.getElementById("cost").value)
			|| ! isNumber(document.getElementById("cach").value)
			|| ! isNumber(document.getElementById("cadh").value)
			|| ! isNumber(document.getElementById("hcost").value)) {
		alert("Some fields have invalid content. Please enter number");
		return false;
	}

	return true;
}

function isNumber(n) {
	return !isNaN(parseFloat(n)) && isFinite(n);
}

function isValidDate(dateString) {
	// First check for the pattern
	if (!/^\d{4}-\d{2}-\d{2}$/.test(dateString))
		return false;
	// Parse the date parts to integers
	var parts = dateString.split("-");
	var year = parseInt(parts[0], 10);
	var month = parseInt(parts[1], 10);
	var day = parseInt(parts[2], 10);

	// Check the ranges of month and year
	if (year < 1000 || year > 3000 || month == 0 || month > 12)
		return false;

	var monthLength = [ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 ];

	// Adjust for leap years
	if (year % 400 == 0 || (year % 100 != 0 && year % 4 == 0))
		monthLength[1] = 29;

	// Check the range of the day
	return day > 0 && day <= monthLength[month - 1];
};