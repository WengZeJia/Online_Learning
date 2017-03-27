$(function(){
	loadHotPeriod();
	loadScList();
});

function loadHotPeriod(){
	$("#hotPeriod").load("loadHotPeriod.do");
}

function loadScList(){
	$("#scList").load("loadSolicitContributions.do");
}
