<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<div class="main-content item">
	<div class="title">
		<h2 class="title-content">Thêm sách</h2>
	</div>

	<!-- Start Search -->

	<!-- End Search -->

	<!-- Manage book button -->

	<!-- Manage book button-->

	<!--Start list books -->
	<div class="list-book-container">
		<form action="/admin/books/add" method="post" enctype="multipart/form-data">
			<label>Tên sách</label> <input type="text" name="name"
				class="width-input" required /><br />
			<br /> <label>Upload hình</label><br /> <input type="file"
				name="fileUpload" class="width-input" /><br />
			<br />

			<div class="form-lable-tyle">
				<div class="form-lable-3">
					<label>Trạng thái</label><br> <select name="status"
						class="width-input">
						
						<option value="1">Hoàn thành</option>
						<option value="0" >Đã xóa</option>
						<option value="2" >Đang cập
							nhật</option>
					</select><br>
				</div>

				<div class="form-lable-3">
					<label>Loại</label><br> <input type="checkbox"
						class="width-input" value="false" name="vip" checked /> <label>Vip</label>
				</div>

				<div class="form-lable-3">
					<label>Giá</label><br> <input type="text" name="price"
						class="width-input isNum" />
						<label class="warning"></label>
				</div>
				<br>
				<div class="form-lable-3">
					<label>Slug</label><br> <input type="text" name="slug"
						class="width-input" required/>
				</div>
				<br>

			</div>
			<br> <label>Meta title</label><br /> <input type="text"
				name="metaTitle" class="width-input" required /><br />
			<br /> <label>Meta Description</label> <input type="text"
				name="metaDescription" class="width-input" required/><br />
			<br /> <label>Giới thiệu sách</label><br />

			<textarea rows="3" class="width-input height-input"
				name="description" required></textarea>
			<br />
			<br> <label>Tác giả</label><br /> <input type="text"
				name="fullname" class="width-input" required/><br />
			<br /> <label>Thể loại</label><br /> <input type="text"
				name="categoryName" class="width-input" required/><br />
			<br />
			<div class="btn spacing">
				<a href="" class="grey">Hủy</a>
				 <input type="submit"
					value="Thêm mới" class="black">
			</div>

		</form>
	</div>
	</div>
	<script>
	var numInput= document.querySelector('.isNum');
	var warning= document.querySelector('.warning');
	numInput.onblur= function(){
		if (!Number(numInput.value)){
			  warning.innerHTML='Dữ liệu phải là số';
			  numInput.value='';
			}
			else{
				warning.innerHTML='';
			}
		
	}
	console.log(numInput)
	</script>