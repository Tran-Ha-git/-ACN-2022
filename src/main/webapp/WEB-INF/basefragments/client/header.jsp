<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<header>
	<div class="container-header container ">
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
				<div class="dropdown-content">
				    <a href="/lovebook">Sách yêu thích</a>
				    <a href="/bookmark">Đánh dấu</a>
				    <a href="/auth/logout">Đăng xuất</a>
				    
				    <c:forEach items="${user.roles}" var="role" varStatus="loop">		
				    <c:if test="${role.name == 'ADMIN'}" >
				    	<a href="/admin/books">
							Admin Page
						</a>
				    </c:if>					
				</c:forEach>
				 </div>
				</span>
			</c:if>			 
		</div>
	</div>
</header>

