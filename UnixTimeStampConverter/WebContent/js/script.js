const GET_DATE = "getDate";
const TIMEZONE_ID = "timeZoneId";
const GET_TIMEZONES = "getTimeZones";
const TIMEZONE_OFFSET = "timeZoneOffset";

var timeZone = function() {
	var body = {};
	body.mode = GET_TIMEZONES;
	
	request('POST', '/UnixTimeStampConverter/unixConverter', JSON.stringify(body));
}

var getDate = function() {
	var body = {};
	body.mode = GET_DATE;
	body.timeZone = document.getElementById('timeZone').value;
	body.timeStamp = document.getElementById('timeStamp').value;
	body.dateFormat = document.getElementById('dateFormat').value;
	
	request('POST', '/UnixTimeStampConverter/unixConverter', JSON.stringify(body));
}

var reset = function() {
	document.getElementById('timeStamp').value = '';
	document.getElementById('resultDiv').style.display = 'none';
}

var request = function(method, url, body) {
	try {
		var xhr = new XMLHttpRequest();

		xhr.open(method, url);
		xhr.setRequestHeader('Content-Type', 'application/json');
		xhr.send(body);

		xhr.onload = function() {
			if (xhr.status == 200) {
				parseResponse(xhr.response);
			} else {
				alert("Status : " + xhr.status + "\n" +
						"Response : " + xhr.response);
			}
		}

		xhr.onerror = function() {
			alert("Server down (or) Invalid URL")
		}
	} catch (exception) {
		alert("Request Failed!");
	}
}

var parseResponse = function(response) {
	var res = JSON.parse(response);
	var data = res.data;

	switch(data.mode) {

	case GET_TIMEZONES :
		populateTimeZones(data.timeZones);
		break;
		
	case GET_DATE :
		showDate(data.date);
		break;
	default :
		alert("Invalid Request Mode!")
		break;
	}

}

var populateTimeZones = function(timeZones) {
	var select = document.getElementById('timeZone');
	select.innerHTML = '';

	for (let i=0; i<timeZones.length; i++) {
		let tz = timeZones[i];

		var option = document.createElement('option');
		option.setAttribute('id', tz[TIMEZONE_ID]);
		option.setAttribute('value', tz[TIMEZONE_ID]);
		option.innerHTML = tz[TIMEZONE_OFFSET] + " - " + tz[TIMEZONE_ID];

		select.add(option);
	}
}

var showDate = function(date) {
	var result = document.getElementById('result');
	result.innerHTML = "Date Format : " + date;
	
	var resultDiv = document.getElementById('resultDiv');
	resultDiv.style.display = 'block';
}

document.addEventListener("load", timeZone());
