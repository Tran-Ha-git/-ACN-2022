<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<div class="main-content item">
	<div class="title">
		<h2 class="title-content">Danh sách quản lý</h2>
	</div>
<
	<!-- Start Search -->
	<div class="search">

		<label class="search-title"><b>Tìm kiếm:</b></label>

		<div class="search-by-bookname">
			<label class="search-bookname-label" for="search-bookname-input">Tên
				sách</label> <input type="text" class="search-bookname-input"
				id="search-bookname-input" name="bookname" />
		</div>

		<div class="search-by-authorname">
			<label class="search-authorname-label" for="search-authorname-input">Tên
				tác giả</label> <input type="text" class="search-authorname-input"
				id="search-authorname-input" name=authorname />
		</div>

		<a href="#" class="search-btn"> <i class="fas fa-search"></i>Tìm
		</a> </span>

	</div>
	<!-- End Search -->

     <form method="post" action="/admin/DeleteAll">
	<!-- Manage book button -->
	<div class="manage-book">
		<div class="manage-book-btn">
		<c:url value="/admin/books/addbook" var="url">
								

							</c:url>
			<a href="${url}" class="add-book-btn"><i class="fas fa-plus-circle"></i>Thêm
				sách</a> 
				<c:url value="/admin/DeleteAll" var="url">
							

							</c:url>
				<button class="delete-book-btn" type="submit" ><i
				class="far fa-times-circle" ></i>Xóa</button>
		</div>

		<div class="books-total">
			<p class="books-amount">Tổng số sách là ${books.size() } quyển</p>
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
				<th class="header-row-content">Hình ảnh</th>
				<th class="header-row-content">Tên sách</th>
				<th class="header-row-content">Tác giả</th>
				<th class="header-row-content">Thể loại</th>
				<th class="header-row-content">Trạng thái</th>
				<th class="header-row-content">Ngày đăng</th>
				<th class="header-row-content">Định dạng đọc</th>
				<th class="header-row-content">Tùy chỉnh</th>
			</tr>
			<!--End table header -->
			<c:forEach items="${books}" var="book">
				<!--Start table data -->
				<tr class="data-row">
				
					<td class="book-data"><input type="checkbox" name="delete" value="${book.id}"/></td>
					<td class="book-data">${book.id }</td>
					<td class="book-data"><img src="${book.thumbnail }"
						alt="Book's image" class="book-image" width="36px" height="30px" /></td>
					<td class="book-data">${book.name}</td>
					<td class="book-data tr-width-12"><c:forEach
							items="${book.categories}" var="category">
	                       ${category}
	                      </c:forEach></td>
					<td class="book-data  tr-width-12"><c:forEach
							items="${book.authors}" var="author">
	                     ${author}
	                    </c:forEach></td>
					<td class="book-data">${book.status}</td>
					<td class="book-data">${book.mod_time }</td>
					<td class="book-data">Đọc online</td>
					<td class="book-data ">
						<div class="custom-btn">
						<c:url value="/admin/edit" var="url">
								<c:param name="id" value="${book.id}" />

							</c:url>
							<a href="${url}" class="edit-custom-btn">Sửa</a>
							<c:url value="/admin/DeleteBook" var="url">
								<c:param name="id" value="${book.id}" />

							</c:url>
							<a href="${url }" class="delete-custom-btn">Xóa</a>
						</div>
					</td>
				</tr>
			</c:forEach>

			<!--<tr class="data-row">
				<td class="book-data"><input type="checkbox" /></td>
				<td class="book-data">2</td>
				<td class="book-data">
					<div class="add-image">
						<a href="#" class="add-image-btn"><b>+</b></a> <span>Hình
							ảnh</span>
					</div>
				</td>
				<td class="book-data">Đắc nhân tâm</td>
				<td class="book-data">Dale Carnegie</td>
				<td class="book-data">Tâm lý</td>
				<td class="book-data">Đã đăng</td>
				<td class="book-data">12/04/2022</td>
				<td class="book-data">Đọc online</td>
				<td class="book-data">
					<div class="custom-btn">
						<a href="#" class="edit-custom-btn">Sửa</a> <a href="#"
							class="delete-custom-btn">Xóa</a>
					</div>
				</td>
			</tr>

			<tr class="data-row">
				<td class="book-data"><input type="checkbox" /></td>
				<td class="book-data">3</td>
				<td class="book-data">
					<div class="add-image">
						<a href="#" class="add-image-btn"><b>+</b></a> <span>Hình
							ảnh</span>
					</div>
				</td>
				<td class="book-data">Đắc nhân tâm</td>
				<td class="book-data">Dale Carnegie</td>
				<td class="book-data">Tâm lý</td>
				<td class="book-data">Đã đăng</td>
				<td class="book-data">12/04/2022</td>
				<td class="book-data">Đọc online, nghe audio</td>
				<td class="book-data">
					<div class="custom-btn">
						<a href="#" class="edit-custom-btn">Sửa</a> <a href="#"
							class="delete-custom-btn">Xóa</a>
					</div>
				</td>
			</tr>

			<tr class="data-row">
				<td class="book-data"><input type="checkbox" /></td>
				<td class="book-data">4</td>
				<td class="book-data">
					<div class="add-image">
						<a href="#" class="add-image-btn"><b>+</b></a> <span>Hình
							ảnh</span>
					</div>
				</td>
				<td class="book-data">Đắc nhân tâm</td>
				<td class="book-data">Dale Carnegie</td>
				<td class="book-data">Tâm lý</td>
				<td class="book-data">Đã đăng</td>
				<td class="book-data">12/04/2022</td>
				<td class="book-data">Đọc online</td>
				<td class="book-data">
					<div class="custom-btn">
						<a href="#" class="edit-custom-btn">Sửa</a> <a href="#"
							class="delete-custom-btn">Xóa</a>
					</div>
				</td>
			</tr>

			<tr class="data-row">
				<td class="book-data"><input type="checkbox" /></td>
				<td class="book-data">5</td>
				<td class="book-data">
					<div class="add-image">
						<a href="#" class="add-image-btn"><b>+</b></a> <span>Hình
							ảnh</span>
					</div>
				</td>
				<td class="book-data">Đắc nhân tâm</td>
				<td class="book-data">Dale Carnegie</td>
				<td class="book-data">Tâm lý</td>
				<td class="book-data">Đã đăng</td>
				<td class="book-data">12/04/2022</td>
				<td class="book-data">Đọc online</td>
				<td class="book-data">
					<div class="custom-btn">
						<a href="#" class="edit-custom-btn">Sửa</a> <a href="#"
							class="delete-custom-btn">Xóa</a>
					</div>
				</td>
			</tr>

			<tr class="data-row">
				<td class="book-data"><input type="checkbox" /></td>
				<td class="book-data">6</td>
				<td class="book-data">
					<div class="add-image">
						<a href="#" class="add-image-btn"><b>+</b></a> <span>Hình
							ảnh</span>
					</div>
				</td>
				<td class="book-data">Đắc nhân tâm</td>
				<td class="book-data">Dale Carnegie</td>
				<td class="book-data">Tâm lý</td>
				<td class="book-data">Đã đăng</td>
				<td class="book-data">12/04/2022</td>
				<td class="book-data">Đọc online</td>
				<td class="book-data">
					<div class="custom-btn">
						<a href="#" class="edit-custom-btn">Sửa</a> <a href="#"
							class="delete-custom-btn">Xóa</a>
					</div>
				</td>
			</tr>

			<tr class="data-row">
				<td class="book-data"><input type="checkbox" /></td>
				<td class="book-data">7</td>
				<td class="book-data">
					<div class="add-image">
						<a href="#" class="add-image-btn"><b>+</b></a> <span>Hình
							ảnh</span>
					</div>
				</td>
				<td class="book-data">Đắc nhân tâm</td>
				<td class="book-data">Dale Carnegie</td>
				<td class="book-data">Tâm lý</td>
				<td class="book-data">Đã đăng</td>
				<td class="book-data">12/04/2022</td>
				<td class="book-data">Đọc online</td>
				<td class="book-data">
					<div class="custom-btn">
						<a href="#" class="edit-custom-btn">Sửa</a> <a href="#"
							class="delete-custom-btn">Xóa</a>
					</div>
				</td>
			</tr>

			<tr class="data-row">
				<td class="book-data"><input type="checkbox" /></td>
				<td class="book-data">8</td>
				<td class="book-data">
					<div class="add-image">
						<a href="#" class="add-image-btn"><b>+</b></a> <span>Hình
							ảnh</span>
					</div>
				</td>
				<td class="book-data">Đắc nhân tâm</td>
				<td class="book-data">Dale Carnegie</td>
				<td class="book-data">Tâm lý</td>
				<td class="book-data">Đã đăng</td>
				<td class="book-data">12/04/2022</td>
				<td class="book-data">Đọc online</td>
				<td class="book-data">
					<div class="custom-btn">
						<a href="#" class="edit-custom-btn">Sửa</a> <a href="#"
							class="delete-custom-btn">Xóa</a>
					</div>
				</td>
			</tr>

			<tr class="data-row">
				<td class="book-data"><input type="checkbox" /></td>
				<td class="book-data">9</td>
				<td class="book-data">
					<div class="add-image">
						<a href="#" class="add-image-btn"><b>+</b></a> <span>Hình
							ảnh</span>
					</div>
				</td>
				<td class="book-data">Đắc nhân tâm</td>
				<td class="book-data">Dale Carnegie</td>
				<td class="book-data">Tâm lý</td>
				<td class="book-data">Đã đăng</td>
				<td class="book-data">12/04/2022</td>
				<td class="book-data">Đọc online</td>
				<td class="book-data">
					<div class="custom-btn">
						<a href="#" class="edit-custom-btn">Sửa</a> <a href="#"
							class="delete-custom-btn">Xóa</a>
					</div>
				</td>
			</tr>

			<tr class="data-row">
				<td class="book-data"><input type="checkbox" /></td>
				<td class="book-data">10</td>
				<td class="book-data">
					<div class="add-image">
						<a href="#" class="add-image-btn"><b>+</b></a> <span>Hình
							ảnh</span>
					</div>
				</td>
				<td class="book-data">Đắc nhân tâm</td>
				<td class="book-data">Dale Carnegie</td>
				<td class="book-data">Tâm lý</td>
				<td class="book-data">Đã đăng</td>
				<td class="book-data">12/04/2022</td>
				<td class="book-data">Đọc online</td>
				<td class="book-data">
					<div class="custom-btn">
						<a href="#" class="edit-custom-btn">Sửa</a> <a href="#"
							class="delete-custom-btn">Xóa</a>
					</div>
				</td>
			</tr>  -->
			<!-- End table data -->
		</table>
		<!-- Start paging -->
		<div class="paging">
			<ul class="list-paging-numbers">
				<li><a href="#" class="previous-page">&lt</a>
				<li>
				<li><a href="#" class="page-number active">1</a></li>
				<li><a href="#" class="page-number">2</a></li>
				<li><a href="#" class="page-number">3</a></li>
				<li><a href="#" class="page-number">4</a></li>
				<li><a href="#" class="next-page">&gt</a></li>
			</ul>
		</div>
		<!-- End paging -->
	</div>
	<!--End list books -->
	</form>
</div>
