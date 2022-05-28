<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<header>
	<div class="container-header container">
		<div class="nav-btn d-block d-lg-none">
			<span><i class="fa-solid fa-bars"></i></span>
			<ul>
				<li><a href="/home">Trang chủ</a></li>
				<li><a href="/books?type=audio">Sách nói</a></li>
				<li><a href="/books?type=ebook">Ebook</a></li>
				<li><a href="/books?type=pdf">Truyện</a></li>
				<li><a href="#">VIP</a></li>
				<li><a href="/quotes">Danh ngôn</a></li>
			</ul>
		</div>
		<div class="logo">
			<a href="#">BookIT.vn</a>
		</div>
		<div class="menu">
			<nav class="d-none d-lg-block">
				<ul>
				<li><a href="/home">Trang chủ</a></li>
				<li><a href="/books?type=audio">Sách nói</a></li>
				<li><a href="/books?type=ebook">Ebook</a></li>
				<li><a href="/books?type=pdf">Truyện</a></li>
				<li><a href="#">VIP</a></li>
				<li><a href="/quotes">Danh ngôn</a></li>
				<c:if test="${user == null}" > 
				<li><a href="/auth/login">Đăng nhập</a></li>
				</c:if>
				</ul>
			</nav>
			<c:if test="${user != null}" >
				<span class="my-account">
				<a href="#" class="account">
					<div class="avatar">
						<i class="fa-solid fa-circle-user"></i>
					</div>
					<div class="info">
						<div class="title-info">Tài khoản</div>
						<div class="name-info">${user.fullname}</div>
					</div>
				</a>
				<c:if test="${user != null}" > 
				<a  href="/auth/logout" class="logout-btn"  >Đăng xuất</a>
				</c:if>
				</span>
			</c:if>			 
		</div>
	</div>
</header>
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
		</div>
	</div>
</header>
