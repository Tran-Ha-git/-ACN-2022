<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url value="http://localhost:8080/books" var="myUrl">
	<c:param name="type" value="${type}" />
	<c:param name="search" value="${search}" />
	<c:param name="category_id" value="${category_id}" />
</c:url>
<div class="container a">
	<div class="row">
		<div class="col-md-9">
			<div class="title">
				<div class="page-title">
					<h1></h1>
				</div>
				<div class="result-title">
					<p class="result-count">Hiển thị
						${pageObj.pageable.offset}–${pageObj.pageable.offset + pageObj.numberOfElements}
						của ${pageObj.totalElements} kết quả</p>
					<form action="#" class="ordering" method="get">
						<select name="orderby" class="orderby" id="sort"
							onchange="sortFunc(this, '<c:out value="${myUrl}"/>')">
							<option selected="selected" value="view__DESC">Lượt xem
								phổ biến</option>
							<option value="mod_time__DESC">Mới nhất</option>
							<option value="mod_time__ASC">Cũ nhất</option>
						</select>
					</form>
				</div>
			</div>

			<div class="list-audio-container">
				<div class="description">
					<h2>
						Kết quả tìm được
					</h2>
				</div>
				<div class="list-audio row">
					<c:forEach items="${pageObj.content}" var="book" varStatus="loop">
						<div class="item-audio col-md-3">
							<div class="col-inner">
								<div class="item-content">
									<div class="box-img">
										<a href="/books/${book.slug}"> <img
											src="${book.thumbnail}" alt="Not found">
										</a>
									</div>
									<div class="box-text">
										<p class="category">
											<c:forEach items="${book.categories}" var="category"
												varStatus="loop">
												<a href="/books?type=${type}&category_id=${category.id}">${category.name}</a>
												<c:if test="${book.categories.size() - 1 != loop.index}">, </c:if>
											</c:forEach>
										</p>
										<p class="name">
											<a href="/books/${book.slug}">${book.name}</a>
										</p>
									</div>
								</div>
							</div>
						</div>

					</c:forEach>
				</div>
			</div>

			<c:if test="${pageObj.totalPages > 0}">
				<nav aria-label="Page navigation example" class="audio-nav my-3">
					<ul class=" pagination justify-content-center">
						<c:if test="${not pageObj.first}">
							<li class="page-item"><a class="page-link"
								href="${myUrl}&page=${pageObj.number-1}" aria-label="Previous">
									<span aria-hidden="true"><i
										class="fa-solid fa-angle-left"></i></span>
							</a></li>
						</c:if>
						<c:if test="${pageObj.number > 0}">
							<li class="page-item"><a class="page-link"
								href="${myUrl}&page=${pageObj.number-1}">${pageObj.number}</a></li>
						</c:if>
						<li class="page-item active"><a class="page-link"
							href="${myUrl}&page=${pageObj.number}">${pageObj.number + 1}</a></li>
						<c:if test="${pageObj.number + 1 < pageObj.totalPages}">
							<li class="page-item"><a class="page-link"
								href="${myUrl}&page=${pageObj.number + 1}">${pageObj.number + 2}</a></li>
						</c:if>
						<c:if test="${not pageObj.last}">
							<li class="page-item"><a class="page-link"
								href="${myUrl}&page=${pageObj.number + 1}" aria-label="Next">
									<span aria-hidden="true"><i
										class="fa-solid fa-angle-right"></i></span>
							</a></li>
						</c:if>
					</ul>
				</nav>
			</c:if>
		</div>

		<div class="col-md-3">

			<div class="search-space-audio">
				<p>Tìm kiếm</p>
				<div action="#" class="search-form">
					<input id="search" type="text"
						class="search-input input-field form-control"
						placeholder="Bạn thích sách gì nhỉ?">
					<button class="btn btn-search"
						onclick="searchFunc(this, 'http://localhost:8080/books?type=${type}&category_id=${category_id}')">
						<i class="fa-solid fa-magnifying-glass"></i>
					</button>
				</div>
			</div>

			<div class="sidebar sidebar-category">
				<div class="mb-4">
					<h3 class="text-center text-2xl font-medium text-blue-600">DANH
						MỤC</h3>
				</div>
				<ul>
					<li><a href="/books"
						class=" ${category_id == null ?'active':'' } divide"> <svg
								class="w-5 h-5" fill="none" stroke="currentColor"
								viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
											<path stroke-linecap="round" stroke-linejoin="round"
									stroke-width="2" d="M9 5l7 7-7 7"></path></svg> <span
							class="ml-5 text-gray-900 font-medium">Tất cả</span>
					</a></li>
					<c:forEach items="${categories}" var="category" varStatus="loop">
						<li><a data="${category.id}"
							href="?category_id=${category.id}"
							class="${category_id == category.id ?'active':'' } ${categories.size() - 1 == loop.index ? '' : 'divide'}"> <svg
									class="w-5 h-5" fill="none" stroke="currentColor"
									viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
											<path stroke-linecap="round" stroke-linejoin="round"
										stroke-width="2" d="M9 5l7 7-7 7"></path></svg><span
								class="ml-5 text-gray-900 font-medium">${category.name}</span>
						</a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
			</div>
		<script>
			const sortInput = document.getElementById("sort");
			const searchInput = document.getElementById("search");

			function sortFunc(ele, url) {
				window.location = url + "&sort=" + sortInput.value;
			}

			function searchFunc(ele, url) {
				window.location = url + "&search=" + searchInput.value;
			}
		</script>

	</div>