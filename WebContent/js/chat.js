$(document).ready(function() {
	$(".send_msg").click(function() {
		var content = $("#textarea").val();
		if(null == content && "" == content) {
			alert("请输入聊天内容!");
			return ;
		}
		var now = new Date();
		var month = now.getMonth() + 1; //月份先加上1
		var dateTimeStr = now.getFullYear() + "-" + month + "-" + now.getDate() + " " + now.getHours() + ":" + now.getMinutes() + ":" + now.getSeconds();
		var params = {'courseId':1, 'fromUserId':$('#userId').val(), 'fromUserName': $("#userName").val(), 'content':content, 'dateTime':dateTimeStr};
		$.ajax({
			cache : false,
			timeout : 30000, //30s超时
			url : "sendMessage.json",
			data: params,
			method: "POST",
			dataType : "JSON"
		})
		.done(function(rs){
			if(rs.result == "Y") {
				showChatMsgs(rs.data);
				$(".chat01_content").scrollTop($(".mes").height());
				$("#textarea").val("");
			} else {
				alert(rs.message);
			}
		})
		.fail(function(){
			alert("服务器正在休息，请稍后再尝试");
		});
	});
	
	setInterval(function () { //
		getChatMsgs();
		reloadUserListPage();
	}, 1000);
	
	/**
	 * 请求后台获取聊天信息
	 */
	function getChatMsgs() {
		$.ajax({
			cache : false,
			timeout : 30000, //30s超时
			url : "getMessage.json",
			data: {'courseId':1},
			dataType : "JSON"
		})
		.done(function(rs){
			if(rs.result == "Y") {
				showChatMsgs(rs.data);
			} else {
				alert(rs.message);
			}
		}).fail(function(){
			alert("服务器正在休息，请稍后再尝试");
		});
	};
	
	/**
	 * 重新加载用户列表
	 */
	function reloadUserListPage() {
		$("#chatUserPage").load("getCourseUsers.do", {'courseId':1}, function() {});
	}
	
	/**
	 * 遍历List的json聊天数据，在聊天框上展示聊天信息
	 */
	function showChatMsgs(chatMsgList) {
		var talkHtml = "";
		for(var i=0; i<chatMsgList.length; i++) {
			var name = chatMsgList[i].fromUserName;
			var content = chatMsgList[i].content;
			var date = new Date(chatMsgList[i].dateTime);
			var month = date.getMonth() + 1; //月份先加上1
			var dateTimeStr = date.getFullYear() + "-" + month + "-" + date.getDate() + "  " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
			content = changeFace(content);
			talkHtml += "<div class='message clearfix'>" + "<div class='wrap-text'>" + "<h5 class='clearfix'>" + name + "</h5>" + "<div>" + content + "</div>" + "</div>" + "<div class='wrap-ri'>" + "<div clsss='clearfix'><span>" + dateTimeStr + "</span></div>" + "</div>" + "<div style='clear:both;'></div></div>";
		}
		
		$(".mes").html("").append(talkHtml);
		
		/**
		 * 替换表情
		 */
		function changeFace(content) { //替换所有表情
			while (content.indexOf("*#emo_") >= 0){
				content = content.replace("*#", "<img src='/Online_Learning/img/").replace("#*", ".gif'/>");
             }
			return content;
		}
	}
	
	$(document).on('click', '.user_gag_op', function () { //设置学生发言或禁言
		var status;
		if($(this).hasClass("chat03_gag")) {
			status = false;
		}
		if($(this).hasClass("chat03_talk_ok")) {
			status = true;
		}

		$.ajax({
			cache : false,
			timeout : 30000, //30s超时
			url : "setCourseStuGagStatus.json",
			data: {'courseId':1, 'gag':status},
			dataType : "JSON"
		})
		.done(function(rs){
			if(rs.result == "Y") {
				if(status) {
					$(this).removeClass("chat03_gag");
					$(this).addClass("chat03_talk_ok");
				} else {
					$(this).removeClass("chat03_talk_ok");
					$(this).addClass("chat03_gag");
				}
			} else {
				alert(rs.message);
			}
		}).fail(function(){
			alert("服务器正在休息，请稍后再尝试");
		});
	})
	
	$(document).on('click', '.user_hand_op', function () { //设置学生举手或禁止
		var status;
		if($(this).hasClass("chat03_hand_up")) {
			status = false;
		}
		if($(this).hasClass("chat03_hand_stop")) {
			status = true;
		}

		$.ajax({
			cache : false,
			timeout : 30000, //30s超时
			url : "setCourseStuHandUpStatus.json",
			data: {'courseId':1, 'noHandUp':status},
			dataType : "JSON"
		})
		.done(function(rs){
			if(rs.result == "Y") {
				if(status) {
					$(this).removeClass("chat03_hand_stop");
					$(this).addClass("chat03_hand_up");
				} else {
					$(this).removeClass("chat03_hand_up");
					$(this).addClass("chat03_hand_stop");
				}
			} else {
				alert(rs.message);
			}
		}).fail(function(){
			alert("服务器正在休息，请稍后再尝试");
		});
	
	}) 
	
	$(".close_btn").click(function() {
		$(".chatBox").hide()
	}), $(".chat03_content li").mouseover(function() {
		$(this).addClass("hover").siblings().removeClass("hover")
	}).mouseout(function() {
		$(this).removeClass("hover").siblings().removeClass("hover")
	}), $(".ctb01").mouseover(function() {
		$(".wl_faces_box").show()
	}).mouseout(function() {
		$(".wl_faces_box").hide()
	}), $(".wl_faces_box").mouseover(function() {
		$(".wl_faces_box").show()
	}).mouseout(function() {
		$(".wl_faces_box").hide()
	}), $(".wl_faces_close").click(function() {
		$(".wl_faces_box").hide()
	}), $(".wl_faces_main img").click(function() {
		var a = $(this).attr("src");
		$("#textarea").val($("#textarea").val() + "*#" + a.substr(a.indexOf("img/") + 4, 6) + "#*"), $("#textarea").focusEnd(), $(".wl_faces_box").hide()
	}), document.onkeydown = function(a) {
		var b = document.all ? window.event : a;
		return 13 == b.keyCode ? ($(".send_msg").click(), !1) : void 0
	}, $.fn.setCursorPosition = function(a) {
		return 0 == this.lengh ? this : $(this).setSelection(a, a)
	}, $.fn.setSelection = function(a, b) {
		if (0 == this.lengh) return this;
		if (input = this[0], input.createTextRange) {
			var c = input.createTextRange();
			c.collapse(!0), c.moveEnd("character", b), c.moveStart("character", a), c.select()
		} else input.setSelectionRange && (input.focus(), input.setSelectionRange(a, b));
		return this
	}, $.fn.focusEnd = function() {
		this.setCursorPosition(this.val().length)
	}
});
