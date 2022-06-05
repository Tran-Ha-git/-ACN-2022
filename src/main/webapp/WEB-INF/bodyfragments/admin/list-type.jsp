<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
.error {
	color: red
}
</style>
<div>
	<div id="form--popup" class="form__popup">
		<form action="/admin/book/category/edit" id="form-edit">
			<div class="row">
				<h3>Cập nhật thể loại</h3>
			</div>
			<div class="row">
				<span class="error" id="error-pop-up"></span>
			</div>
			<div class="row">
				<input class="input" name="id"  type="hidden" id="id-pop-up" />
			</div>
			<div class="row">
				<label class="label" for="name">Tên</label>
				<input class="input" name="name" id="name-pop-up" />
			</div>
			<div class="row">
				<label class="label" for="status">Trạng thái</label>
				<select name="status" class="input" id="status-pop-up">
					<option value="1">active</option>
					<option value="0">inactive</option>
				</select>
			</div>
			<div class="row">
				<label class="label" for="slug">Slug</label>
				<input class="input" path="slug" id="slug-pop-up" />
			</div>
			<div class="row">
				<label class="label" for="title">Meta title</label>
				<input class="input" name="metaTitle" id="title-pop-up" />
			</div>
			<div class="row">
				<label class="label" for="description">Meta description</label>
				<input class="input" name="metaDescription" id="description-pop-up" />
			</div>
			<div class="row button__row">
				<input class="button button__cancel button__cancel--popup" type="reset" value="Hủy" />
				<button type="submit" class="button">Save</button>
			</div>
		</form>
	</div>
	<a class="title" href="">Danh sách thể loại</a>
	<div class="wrapper">
		<div class="list__wrapper">
			<div class="search">
				<form method="get">
					<span class="text text1">Tìm kiếm:</span> <span class="text">Tên
						thể loại</span> <input name="q" type="text">
					<button class="button">
						<i class="fa fa-search"></i> Tìm
					</button>
				</form>
			</div>
			<p class="total">Tổng số: ${results.totalElements}</p>
			<div class="table__wrapper">
				<table class="table">
					<thead>
						<th>id</th>
						<th>Name</th>
						<th>view</th>
						<th>slug</th>
						<th>status</th>
						<th>meta title</th>
						<th>action</th>
					</thead>
					<tbody>
						<c:forEach items="${results.content}" var="item">
							<tr>
								<td class="table__name">${item.id }</td>
								<td class="table__name">${item.name }</td>
								<td class="table__name">${item.view }</td>
								<td class="table__name">${item.slug }</td>
								<td class="table__name">${(item.status!=null && item.status == 1) ? 'active' : 'inactive' }</td>
								<td class="table__name">${item.metaTitle!=null ? item.metaTitle : "" }</td>
								<td>
									<div style="display: flex">
										<button class="button table__button button__edit"
											style="margin: 0 2px" onclick="showInfo(${item.id})">Sửa</button>
										<a href="/admin/book/category/delete/${item.id }" class="button table__button">Xóa</a>
									</div>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
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
											aria-current="page" class="pagination-link"
											style="color: red">${page }</a></li>
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
		</div>
		<div class="form__wrapper">
			<form:form action="/admin/book/category/create"
				modelAttribute="categoryDto">
				<div class="row">
					<label class="label" for="name">Tên</label>
					<form:input class="input" path="name" id="name" />
					<form:errors path="name" cssClass="error" />
				</div>
				<div class="row">
					<label class="label" for="status">Trạng thái</label>
					<form:select path="status" class="input" id="status">
						<option value="1">active</option>
						<option value="0">inactive</option>
					</form:select>
					<form:errors path="status" cssClass="error" />
				</div>
				<div class="row">
					<label class="label" for="slug">Slug</label>
					<form:input class="input" path="slug" id="slug" />
					<form:errors path="slug" cssClass="error" />
				</div>
				<div class="row">
					<label class="label" for="title">Meta title</label>
					<form:input class="input" path="metaTitle" id="title" />
					<form:errors path="metaTitle" cssClass="error" />
				</div>
				<div class="row">
					<label class="label" for="description">Meta description</label>
					<form:input class="input" path="metaDescription" id="description" />
					<form:errors path="metaDescription" cssClass="error" />
				</div>
				<div class="row button__row">
					<input class="button button__cancel" type="reset" value="Hủy" />
					<button class="button">Thêm mới</button>
				</div>
			</form:form>
		</div>
	</div>
</div>

<script>
   const a = document.querySelectorAll(".button__edit");
   const b = document.querySelector(".form__popup");
   const showInfo = (id) => {
	   $.ajax({
		   method: "GET",
		   url: "/api/v1/book/category/"+id,
		   success: function(data){
			   $("#id-pop-up").val(data.id);
			   $("#name-pop-up").val(data.name);
			   $("#status-pop-up").val(data.status);
			   $("#slug-pop-up").val(data.slug);
			   $("#title-pop-up").val(data.metaTitle);
			   $("#description-pop-up").val(data.metaDescription);
			   $(".form__popup").addClass("flex")
		   },
	   })
   }
   $(".button__cancel--popup").on("click", ()=>{
	   $(".form__popup").removeClass("flex")
   })
   
   $("#form-edit").on("submit", function(event){
	   event.preventDefault();
	   const data = {
			id: $("#id-pop-up").val(),
			name: $("#name-pop-up").val(),
			status: $("#status-pop-up").val(),
			slug:  $("#slug-pop-up").val(),
			metaTitle: $("#title-pop-up").val(),
			metaDescription: $("#description-pop-up").val(),
	   }
	   
	   $.ajax({
		   method: "POST",
		   url: "/api/v1/book/category/edit",
		   data: data,
		   success: function(data){
			   console.log(data);
			   $("#id-pop-up").val(data.id);
			   $("#name-pop-up").val(data.name);
			   $("#status-pop-up").val(data.status);
			   $("#slug-pop-up").val(data.slug);
			   $("#title-pop-up").val(data.metaTitle);
			   $("#description-pop-up").val(data.metaDescription);
			   window.location.reload();
		   },
		   error: function(error) {
			   $("#error-pop-up").text(error.responseJSON.message);
		   }
	   })
   })
</script>
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
                    item.setAttribute("href", '/admin/book/category'+ "?" + href);
                })
            }
</script>
