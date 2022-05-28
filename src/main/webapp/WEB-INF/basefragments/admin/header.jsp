<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="header-manage-list">
	<div class="logo">
		<img src="/assets/images/webdocsach.png" alt="Logo"
			class="logo-image" />
	</div>

	<div class="account-menu">
		<div class="dropdown ">
			<div class="menu">
				<button class="dropbtn">
					<img src="/assets/images/dacnhantam.png" alt="Account's image"
						class="account-image-circle" /> 
						${user.fullname} <i class="fas fa-caret-down"></i>
				</button>
			</div>

			<div class="dropdown-content">
				<a href="#">Trang cá nhân</a>
				 <a href="#">Thông báo</a>
				  <a href="#">Cài đặt</a>
				  <a href="/auth/logout" >Đăng xuất</a>
			</div>
		</div>

	</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
$(document).ready(function(){
	$.ajax({       
	       type : "GET",
	       data : "username="+ $("#username").val(),
	       url : "/api/auth/get-id",       
	       success : function(result){
	    	   console.log(result);
	    	   $("#custId").val(result);
	       },
	       error : function(result){
	    	   console.log(result)
	       }
	    });
});
</script>
