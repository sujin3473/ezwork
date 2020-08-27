
var clockTarget = document.getElementById("clock");


function clock() {
    var date = new Date();
    var year = date.getYear();
    var month = date.getMonth();
    var clockDate = date.getDate();
    var day = date.getDay();
    var week = ['일', '월', '화', '수', '목', '금', '토'];
    var hours = date.getHours();
    var minutes = date.getMinutes();
    var seconds = date.getSeconds();
    clockTarget .innerText = `${year-100}. ${month+1}. ${clockDate}. (${week[day]}) ` +
    `${hours < 10 ? `0${hours}` : hours}:${minutes < 10 ? `0${minutes }`  : minutes }:${seconds < 10 ? `0${seconds }`  : seconds }`;
}



function init() {
clock();
setInterval(clock, 1000);
}



init();