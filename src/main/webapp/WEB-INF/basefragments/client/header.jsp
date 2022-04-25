<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.web.dacn.utils.SecurityUtils" %>

<header>
	<div class="container-header container">
		<div class="nav-btn d-block d-lg-none">
			<span><i class="fa-solid fa-bars"></i></span>
			<ul>
				<li><a href="#">Trang chủ</a></li>
				<li><a href="#">Sách nói</a></li>
				<li><a href="#">Ebook</a></li>
				<li><a href="#">Truyện</a></li>
				<li><a href="#">VIP</a></li>
				<li><a href="#">Danh ngôn</a></li>
			</ul>
		</div>
		<div class="logo">
			<a href="#">BookIT.vn</a>
		</div>
		<div class="menu">
			<nav class="d-none d-lg-block">
				<ul>
					<li><a href="#">Trang chủ</a></li>
					<li><a href="#">Sách nói</a></li>
					<li><a href="#">Ebook</a></li>
					<li><a href="#">Truyện</a></li>
					<li><a href="#">VIP</a></li>
					<li><a href="#">Danh ngôn</a></li>
				</ul>
			</nav>
			<%-- <c:set var="fullnameVar" scope="session"
				value="<%=SecurityUtils.getPrincipal().getFullName()%>." />
			<c:out value="" />
			<c:if test="${fullnameVar} != '.' ">
				<a href="#" class="account">
					<div class="avatar">
						<i class="fa-solid fa-circle-user"></i>
					</div>
					<div class="info">
						<div class="title-info">Tài khoản</div>
						<div class="name-info"></div>
					</div>
				</a>
			</c:if> --%>

		</div>
	</div>
</header>
