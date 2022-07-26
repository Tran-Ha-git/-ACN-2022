<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@	taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<div class="main-content item">
	<div class="title">
		<h2 class="title-content">

			<a class="title-content" href="/admin/quote">Danh tác danh ngôn</a>

		</h2>
	</div>

	<!-- Start Search -->
	<div class="search">


		<label class="search-title"><b>Tìm kiếm:</b></label>
		<form method="GET">
			<div class="search-by-authorname">
				<input type="text" class="search-authorname-input"
					id="search-authorname-input" name="q" value="${search}" />
			</div>


			<button class="search-btn" type="submit">
				<i class="fas fa-search"></i>Tìm
			</button>
		</form>

	</div>
	<!-- End Search -->


	<!-- Manage author button -->
	<div class="manage-author">
		<div class="manage-author-btn">
			<a href="/admin/quote/new" class="add-author-btn"><i
				class="fas fa-plus-circle"></i>Thêm danh ngôn</a>
		</div>

		<div class="authors-total">

			<p class="authors-amount">Tổng số quotes ${results.totalElements}
				quyển</p>

		</div>
	</div>
	<!-- Manage author button-->

	<!--Start list authors -->
	<div class="list-author-container">
		<h3>${message }</h3>
		<table class="list-author-table">

			<!-- table header -->
			<tr class="header-row">
				<th class="header-row-content">id</th>
				<th class="header-row-content">content</th>
				<th class="header-row-content">thumbnail</th>
				<th class="header-row-content">view</th>
				<th class="header-row-content">author</th>
				<th class="header-row-content">slug</th>
				<th class="header-row-content">title</th>
				<th class="header-row-content">status</th>
				<th class="header-row-content">keyword</th>
				<th class="header-row-content">action</th>
			</tr>
			<!--End table header -->
			<c:forEach items="${results.content}" var="quote">
				<!--Start table data -->
				<tr class="data-row">
					<td class="author-data">${quote.id}</td>
					<td class="author-data">
						<p
							style="max-width: 200px; overflow: hidden; text-overflow: ellipsis; display: -webkit-box; -webkit-box-orient: vertical; -webkit-line-clamp: 2; margin: 0 auto;">
							${quote.content}</p>
					</td>
					<td class="author-data">
						<c:if test = "${!fn:contains(quote.thumbnail, 'dummyimage.com')}">
						    <img src="/admin/quote/images/${quote.thumbnail}"
									style="max-width: 80px; max-height: 80px; object-fit: cover;" />
						</c:if>
						<c:if test = "${fn:contains(quote.thumbnail, 'dummyimage.com')}">
						    <img src="${quote.thumbnail}"
									style="max-width: 100px; max-height: 100px" />
						</c:if>
					</td>
					<td class="author-data">${quote.view}</td>
					<td class="author-data">${quote.author.fullname}</td>
					<td class="author-data">${quote.slug}</td>
					<td class="author-data">${quote.metaTitle}</td>
					<td class="author-data">${quote.status}</td>
					<td class="author-data"><c:forEach
							items="${quote.quoteCategories }" var="item">
							<span>${item.name},</span>
						</c:forEach></td>
					<td class="author-data">
						<div class="custom-btn">
							<a href="/admin/quote/edit/${quote.id}" class="edit-custom-btn">Sửa</a>
							<a href="/admin/quote/delete/${quote.id}"
								class="delete-custom-btn">Xóa</a>
						</div>
					</td>
				</tr>
			</c:forEach>

			<!-- End table data -->
		</table>
		<!-- Start paging -->

		<div style="margin-top: 10px">
			<c:if test="${results.totalPages>0 }">
				<nav>
					<ul style="display: flex; list-style: none">
						<li style="margin: 0 5px;"><a href=""
							data="${results.number > 0 ? results.number:1}"
							class="pagination-link">Previous</a></li>
						<c:forEach items="${pages}" var="page">
							<c:choose>
								<c:when test="${page!=results.number+1}">
									<li style="margin: 0 5px;"><a href="" data="${page}"
										class="pagination-link">${page }</a></li>
								</c:when>
								<c:otherwise>
									<li style="margin: 0 5px"><a href="" data="${page }"
										aria-current="page" class="pagination-link" style="color: red">${page }</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<li style="margin: 0 5px;"><a href=""
							data="${results.number+2>results.totalPages?results.totalPages:results.number+2}"
							class="pagination-link">Next</a></li>
					</ul>
				</nav>
			</c:if>
		</div>



		<!-- End paging -->
	</div>
	<!--End list authors -->
</div>
<script>
            const url_string = window.location.href;
            const url = new URL(url_string);
            const search = url.searchParams.get("q");
            const paginationLinks = document.querySelectorAll(".pagination-link");
            const navItems = document.querySelectorAll(".nav-item");
            if (paginationLinks) {
                paginationLinks.forEach(item => {
                	var search = location.search.substring(1);
                    const params = search ? JSON.parse('{"' + decodeURI(search).replace(/"/g, '\\"')
                            .replace(/&/g, '","').replace(/=/g, '":"') + '"}') : {};
                    const page = item.getAttribute("data");
                    params.page = page;
                    const href = decodeURIComponent(new URLSearchParams(params).toString());
                    item.setAttribute("href", '/admin/quote'+ "?" + href);
                })
            }
</script>
