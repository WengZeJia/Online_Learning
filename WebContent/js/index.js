	
function searchCourese(param){
	$("#scList").load("search.do", param, function(data){
	}); 
}

function indexCourese(param){
	$("#scList").load("index.do", param, function(data){
	}); 
}

$(function(){
	searchCourese(null);
  }) 
	 
function jumpTo(page){
    if(parseInt(page)<parseInt(1)){
		alert('已经是第一页！')
		return false;
	}
	var tpn = $("#totalPage").val();
	if(parseInt(page)>parseInt(tpn)){
		alert('已经是最后一页！')
		return false;
	}
	var param = {"keyword":$("#keyword").val(), "pageNo":page};
	searchCourese(param);
}