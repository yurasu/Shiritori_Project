/**
 *
 */

/**
 *
 */
$(function(){
	var check_form = false;
	var check_password = false;

	$(".check_form").on("focusout",function(){
		var youso = $(this);
		var position = $(this).position();
		if($(this).val() == null || $(this).val() == ""){
			youso.parent().after("<div class=\"info_danger\">この項目は必須項目です</div>");
			var position = youso.parent().position();
			var width = youso.innerWidth();
			$(".info_danger").css({'width':width,'left':position.left,'top':position.top+youso.parent().height()});
			check_form = false;
			return;
		}else{
			youso.parent().next(".info_danger").remove();
			youso.parent().next(".info_success").remove();
			youso.parent().after("<div class=\"info_success\">Success</div>");
			var position = youso.parent().position();
			var width = youso.innerWidth();
			youso.parent().next(".info_success").css({'width':width,'left':position.left,'top':position.top+youso.parent().height()});
			check_form = true;
		}
	});
	$(".check_form").on("focusin",function(){
		var youso = $(this);
		youso.parent().next(".info_danger").remove();
		youso.parent().next(".info_success").remove();
		check_form = false;
	});

	$(".check_password").on("focusout",function(){
		var youso = $(this);
		var position = $(this).position();
		if($(this).val() == null || $(this).val() == ""){
			youso.parent().next(".info_danger").remove();
			youso.parent().next(".info_success").remove();
			youso.parent().after("<div class=\"info_danger\">この項目は必須項目です</div>");
			var position = youso.parent().position();
			var width = youso.innerWidth();
			$(".info_danger").css({'width':width,'left':position.left,'top':position.top+youso.parent().height()});
			check_password = false;
			return;
		}else{
			youso.parent().next(".info_danger").remove();
			youso.parent().next(".info_success").remove();
			youso.parent().after("<div class=\"info_success\">Success</div>");
			var position = youso.parent().position();
			var width = youso.innerWidth();
			youso.parent().next(".info_success").css({'width':width,'left':position.left,'top':position.top+youso.parent().height()});
			check_password = true;
		}
	});

	$(".check_password").on("focusin",function(){
		var youso = $(this);
		youso.parent().next(".info_danger").remove();
		youso.parent().next(".info_success").remove();
		check_password = false;
	});

	$("#loginform").submit(function(){
		if(!(check_form&&check_password)){
			alert('正しい値を入力してください');
			return false;
		}
		$("#loginform").off("submit");
		$.ajax({
			type : "POST",
			url : "http://"+location.host +"/Shiritori_Project/Check_Login",
			dataType : "JSON",
			data:{"username":$.toJSON($("#username").val()),
				"password":$.toJSON($("#password").val())
			},
			success : function(json) {
				if(json == "No"){
					alert("ユーザーネームかパスワードが間違っています");
					$("#loginform").off("submit");
					return;
				}else{
					 $("#loginform").submit();
				}

			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("エラーが発生しました：" + textStatus + ":\n" + errorThrown);
				$("#loginform").off("submit");
				return;
			}
		});
		return false;
	});
})