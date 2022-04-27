<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>


<div class="main-content item">
	<div class="title">
		<h2 class="title-content">Cập nhật sách</h2>
	</div>

	<!-- Start Search -->

	<!-- End Search -->

	<!-- Manage book button -->

	<!-- Manage book button-->

	<!--Start list books -->
	<div class="list-book-container">
		<form>
			<label>Tên sách</label> <input type="text" name="name"
				class="width-input" value="${book.name }" /><br />
			<br /> <label>Upload hình</label><br /> <input type="file"
				name="name" class="width-input" value="${book.thumbnail}" /><br />
			<br />

			<div class="form-lable-tyle">
				<div class="form-lable-3">
					<label>Trạng thái</label><br /> <select name="status"
						class="width-input">
						<option value="Choose">Choose...</option>
						<option value="update">Đã xóa</option>
						<option value="full">Hoàn thành</option>
						<option value="full">Đang cập nhật</option>
					</select><br />
				</div>

				<div class="form-lable-3">
					<label>Loại</label><br />
					<form>
						<input type="checkbox" class="width-input" /> <label>Vip</label>
					</form>
				</div>

				<div class="form-lable-3">
					<label>Giá</label><br /> <input type="text" name="name"
						class="width-input" value="${book.price}" />
				</div>
				<br />
				<div class="form-lable-3">
					<label>Slug</label><br /> <input type="text" name="name"
						class="width-input" value="${book.slug}" />
				</div>
				<br />
			</div>

			<label>Meta title</label><br /> <input type="text" name="name"
				class="width-input" value="${book.metaTitle}" /><br />
			<br /> <label>Meta Description</label> <input type="text"
				name="name" class="width-input" value="${book.metaDescription}" /><br />
			<br /> <label>Giới thiệu sách</label><br /> <input type="text"
				name="name" class="width-input" value="${book.description}">
			<br />
			<br />
			
			<div class="listCheck_two">
				<div class="list-checkbox">
					<p>Tác giả</p>
					<form action="" class="form-1">
						<c:forEach items="${book.author}" var="author">
							<div class="form_input">
								<input type="checkbox" id="form-1" /> <label> ${author }
								</label><br />
							</div>
						</c:forEach>

					</form>
				</div>
				<div class="list-checkbox">
					<p>Thể loại</p>
					<form action="" class="form-1">
						<c:forEach items="${book.category}" var="category">
							<div class="form_input">
								<input type="checkbox" id="form-1" /> <label>
									${category} </label><br />
							</div>
						</c:forEach>
					</form>
				</div>
			</div>
			<div class="lable-tyle">
				<br />
				<p>Nội dung</p>

				<div class="btn_two">
					<div class="btn_two_content">
						<a href="" class="grey">Cập nhật danh sách chương</a> <a href=""
							class="grey">Cập nhật danh sách Audio</a>
					</div>
					<div class="btn_two-input">
						<label>Link Pdf</label> 
						
					
						<input type="text" name="pdf" size="24" value="${book.pdf}" />
					</div>
				</div>
			</div>

			<div class="btn spacing">
				<a href="" class="grey">Hủy</a> <input type="submit"
					value="Cập nhật" class="black" />
			</div>
		</form>
	</div>
	<!-- table header -->

	<!--End table header -->

	<!--Start table data -->

	<!-- End table data -->

	<!-- Start paging -->

	<!-- End paging -->