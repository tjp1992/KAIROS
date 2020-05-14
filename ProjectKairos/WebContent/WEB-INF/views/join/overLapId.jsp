<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.js"></script>
</head>
	<style>
		.wrapper{
			text-align: center;
			padding-top : 10px;
		}
		h2{
			padding-top: 50px;
		}
	</style>
	<script>
	$(function(){
			var userId = '${userId}';
			var joinId = '${joinId}';
			$("#joinId").val(joinId);
			var id = $("#joinId").val();
			if(id == userId){
				$("span").html("중복된 아이디 입니다.");
				$("span").css("color","red");
				$("#joinId").css("border","1px solid red");
				$("#overLapChk").click(function(){
					id = $("#joinId").val();
					if(id== userId){
						$("span").html("중복된 아이디 입니다.");
						$("span").css("color","red");
						$("#joinId").css("border","1px solid red");
					}else{
						$("span").html("사용할 수 있는 아이디 입니다.");
						$("#joinId").css("border","1px solid green");
						$("span").css("color","green");
						$("#overLapChk").click(function(){
							
							window.close();
						});
					}
				});
			}else{
				$("span").html("사용할 수 있는 아이디 입니다.");
				$("#joinId").css("border","1px solid green");
				$("span").css("color","green");
				$("#overLapChk").click(function(){
					window.close();
				});
			}
		});

			
	</script>
<body>
	<h2>중복확인</h2>
	<div class="wrapper">
		<input type="text" id="joinId">
		<button id="overLapChk">확인</button><br><span></span>
	</div>
</body>
</html>