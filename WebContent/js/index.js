	
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

function enrol(pid){
	var param = {pid: pid};
	$.ajax({
		type: "POST",
		async: false,
		url : 'save.do',
		data : param,
		dataType: "json"
	}).done(function(data){
		if(data.result == '00'){
			alert("你已成功报名！");
		}
		if(data.result == '01'){
			setTimeout(function(){
				window.location.href = 'login.do';
			},1000);
		}else if(data.result == '02'){
			alert("你已报名，请勿重复报名！");
		}
	});
}