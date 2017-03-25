$(document).ready(function() {
	$("#handUp").click(function() {
		var params = {'courseId':1, 'fromUserId':$('#userId').val()};
		var stuHandUpLoading = layer.load(2, {shade: [0.8, '#393D49']});
		$.ajax({
			cache : false,
			timeout : 30000, //30s超时
			url : "stuHandUp.json",
			data: params,
			method: "POST",
			dataType : "JSON"
		})
		.done(function(rs){
			layer.close(stuHandUpLoading);
			alert(rs.message);
		})
		.fail(function(){
			alert("服务器正在休息，请稍后再尝试");
		});
	});
	
	
	$(".send_msg").click(function() {
		var content = $("#textarea").val();
		if(null == content || "" == content) {
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
		getStudentHandUpMsg();
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
		var oldChatLength = $(".mes").find("div.message.clearfix").size() || 0;
		var newChatLength = chatMsgList.length;
		for(var i=0; i<newChatLength; i++) {
			var name = chatMsgList[i].fromUserName;
			var content = chatMsgList[i].content;
			var date = new Date(Date.parse(chatMsgList[i].dateTime.replace(/-/g, "/")));
			var month = date.getMonth() + 1; //月份先加上1
			var dateTimeStr = date.getFullYear() + "-" + month + "-" + date.getDate() + "  " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
			content = changeFace(content);
			talkHtml += "<div class='message clearfix'>" + "<div class='wrap-text'>" + "<h5 class='clearfix'>" + name + "</h5>" + "<div>" + content + "</div>" + "</div>" + "<div class='wrap-ri'>" + "<div clsss='clearfix'><span>" + dateTimeStr + "</span></div>" + "</div>" + "<div style='clear:both;'></div></div>";
		}
		
		$(".mes").html(talkHtml);
		if(newChatLength > oldChatLength) {
			$(".chat01_content").scrollTop($(".mes").height());
		}
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
	
	/**
	 * 获取学生举手消息
	 */
	function getStudentHandUpMsg() {
		$.ajax({
			cache : false,
			timeout : 30000, //30s超时
			url : "getStudentHandUpMsg.json",
			data: {'courseId':1},
			dataType : "JSON"
		})
		.done(function(rs){
			if(rs.result == "Y") {
				alertStudentHandUpMsg(rs.data);
			}
		}).fail(function(){
			alert("服务器正在休息，请稍后再尝试");
		});
		
		/**
		 * 弹出学生举手消息
		 */
		function alertStudentHandUpMsg(content) {
			layer.msg(content, {
				title:'系统提示',
				closeBtn :1,
				icon:6,
				shade: 0,
				offset: 'rb',
				anim:2,
				time: 3000
			}); 
		}
	}
	
	$(document).on('click', '.user_gag_op', function () { //设置学生发言或禁言
		var self = this;
		var status;
		if($(self).hasClass("chat03_gag")) {
			status = false;
		}
		if($(self).hasClass("chat03_talk_ok")) {
			status = true;
		}
		var userId = $(self).parents("li").attr("userId");
		var gagLoading = layer.load(2, {shade: [0.8, '#393D49']});
		$.ajax({
			cache : false,
			timeout : 30000, //30s超时
			url : "setCourseStuGagStatus.json",
			data: {'courseId':1, 'gag':status, 'userId':userId},
			dataType : "JSON"
		})
		.done(function(rs){
			layer.close(gagLoading);
			if(rs.result == "Y") {
				if(status) {
					$(self).removeClass("chat03_talk_ok");
					$(self).addClass("chat03_gag");
				} else {
					$(self).removeClass("chat03_gag");
					$(self).addClass("chat03_talk_ok");
				}
			} else {
				alert(rs.message);
			}
		}).fail(function(){
			layer.close(gagLoading);
			alert("服务器正在休息，请稍后再尝试");
		});
	})
	
	$(document).on('click', '.user_hand_op', function () { //设置学生举手或禁止
		var self = this;
		var status;
		if($(self).hasClass("chat03_hand_stop")) {
			status = false;
		}
		if($(self).hasClass("chat03_hand_up")) {
			status = true;
		}

		var userId = $(this).parents("li").attr("userId");
		var noHandUpLoading = layer.load(2, {shade: [0.8, '#393D49']});
		$.ajax({
			cache : false,
			timeout : 30000, //30s超时
			url : "setCourseStuHandUpStatus.json",
			data: {'courseId':1, 'noHandUp':status, 'userId':userId},
			dataType : "JSON"
		})
		.done(function(rs){
			layer.close(noHandUpLoading);
			if(rs.result == "Y") {
				if(status) {
					$(self).removeClass("chat03_hand_up");
					$(self).addClass("chat03_hand_stop");
				} else {
					$(self).removeClass("chat03_hand_stop");
					$(self).addClass("chat03_hand_up");
				}
			} else {
				alert(rs.message);
			}
		}).fail(function(){
			layer.close(noHandUpLoading);
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
