<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<div class="main-content item">
	<div class="title">
		<h2 class="title-content">Danh sách bình luận</h2>
	</div>

	<!-- Start Search -->
	<div class="search">
		<form method="POST" action="/admin/searchComment">
			<div class="search-by-bookname">
				<input type="text" class="search-bookname-input"
					id="search-bookname-input" name="username" />
				<b>Filter by user</b>
			</div>

			<div class="search-by-authorname">
				<input type="text" class="search-authorname-input"
					id="search-authorname-input" name="bookname"/> <b>Filter by book</b>
			</div>
			<button class="search-btn" type="submit">
				<i class="fas fa-search"></i>Tìm

			</button>
		</form>
	</div>
	<!-- End Search -->

	<!-- Manage book button -->

	<form method="post" action="/admin/DeleteAllComment">
		<div class="manage-book">
			<div class="manage-book-btn">
				<button class="delete-book-btn" type="submit">
					<i class="far fa-times-circle"></i>Xóa
				</button>

			</div>

			<div class="books-total">
				<p class="books-amount">Tổng số bình luận sách ${total}</p>
			</div>
		</div>
		<!-- Manage book button-->

		<!--Start list books -->
		<div class="list-book-container">
			<table class="list-book-table">
				<!-- table header -->
				<tr class="header-row">
					<th class="header-row-content"><input type="checkbox" /></th>
					<th class="header-row-content">ID</th>
					<th class="header-row-content">User</th>
					<th class="header-row-content">Comment</th>
					<th class="header-row-content">Ngày đăng</th>
					<th class="header-row-content">Tên sách</th>
					<th class="header-row-content">Trạng thái</th>
					<th class="header-row-content">Tùy chỉnh</th>
				</tr>
				<!--End table header -->

				<!--Start table data -->
				<c:forEach items="${comments}" var="comment">

					<tr class="data-row">
						<td class="book-data"><input type="checkbox"
							name="delete-item" class="delete-item" value="${comment.id}" /></td>

						<td class="book-data">${comment.id }</td>

						<td class="book-data tr-width-12">
							<div class="commnet-space">
								<div class="comment-img">
									<i class="fas fa-user comment-width"></i>
								</div>
								<div class="comment-item">
									<p>${comment.user.username }</p>

									<p class="comment-email">${comment.user.email }</p>
								</div>
							</div>
						</td>

						<td class="book-data tr-width-12">${comment.content}</td>
						<td class="book-data tr-book-image">${comment.modTime}</td>
						<td class="book-data tr-width-12" >${comment.book.name }</td>

						<c:choose>
							<c:when test="${comment.status==1}">
								<td class="book-data">Hiện</td>

							</c:when>
							<c:otherwise>

								<td class="book-data tr-width-12">Ẩn</td>
							</c:otherwise>
						</c:choose>

						<td class="book-data tr-width-12">
							<div class="custom-btn custom-btn-3" style="display:flex;">
								<c:url value="/admin/DeleteCommentBook" var="url">
									<c:param name="id" value="${comment.id}" />

								</c:url>
								<a href="${url }" class="delete-custom-btn">Xóa</a>
								<c:url value="/admin/spamComment" var="url">
									<c:param name="id" value="${comment.id}" />

								</c:url>
								<a href="${url }" class="spam-custom-btn">Spam</a>
								<c:url value="/admin/approvedComment" var="url">
									<c:param name="id" value="${comment.id}" />

								</c:url>
								<a href="${url }" class="approved-custom-btn">Approved</a>
							</div>
						</td>
					</tr>
				</c:forEach>

				<!-- End table data -->
			</table>
			<!-- Start paging -->
			<div class="paging">
				<ul class="list-paging-numbers">

					<c:if test="${currentpage > 0 }">
						<li><a href="/admin/comments/${currentpage-1}"
							class="previous-page">&lt</a>
						<li>
					</c:if>

					<c:forEach items="${totalPages}" var="page">
						<li><a href="/admin/comments/${page}" class="page-number ">${page+1}</a></li>
					</c:forEach>


					<c:forEach items="${searchPages}" var="page">
						<li><a href="/admin/searchComment/${page}" class="page-number ">${page+1}</a></li>
					</c:forEach>


					<c:if test="${currentpage < totalPages.size()-1 }">
						<li><a href="/admin/comments/${currentpage+1}"
							class="next-page">&gt</a></li>
					</c:if>
				</ul>
			</div>
			<!-- End paging -->
		</div>
		<!--End list books -->
	</form>
</div>

</body>
</html>