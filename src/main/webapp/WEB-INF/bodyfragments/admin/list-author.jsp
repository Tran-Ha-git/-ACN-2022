<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<div class="main-content item">
	<div class="title">
		<h2 class="title-content">

			<a class="title-content" href="/admin/authors">Danh sách tác giả</a>

		</h2>
	</div>

	<!-- Start Search -->
	<div class="search">


		<label class="search-title"><b>Tìm kiếm:</b></label>
		<form method="GET" action="/admin/authors">
			<div class="search-by-authorname">

				<label class="search-authorname-label" for="search-authorname-input">Tên
					tác giả</label> <input type="text" class="search-authorname-input"
					id="search-authorname-input" name="search" value="${search}" />
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
			<a href="/admin/authors/new" class="add-author-btn"><i class="fas fa-plus-circle"></i>Thêm
				tác giả</a> <a href="#" class="delete-author-btn" id="delete-author-btn"><i
				class="far fa-times-circle"></i>Xóa</a>
		</div>

		<div class="authors-total">

			<p class="authors-amount">Tổng số tác giả là
				${pageObj.totalElements} quyển</p>

		</div>
	</div>
	<!-- Manage author button-->

	<!--Start list authors -->
	<div class="list-author-container">
		<h3>${message }</h3>
		<table class="list-author-table">

			<!-- table header -->
			<tr class="header-row">
				<th class="header-row-content"><a href="#"><input
						type="checkbox" name="checkbox-delete-btn" id="select-all-row" /></a></th>
				<c:choose>
					<c:when test="${sortName == 'id' && sortType == 'DESC'}">
						<th class="header-row-content"><a
							href="/admin/authors?search=${search}&sort=id__ASC">ID <i
								class="fa-solid fa-arrow-down"></i></a></th>
					</c:when>
					<c:when test="${sortName == 'id' && sortType == 'ASC'}">
						<th class="header-row-content"><a
							href="/admin/authors?search=${search}&sort=id__DESC">ID <i
								class="fa-solid fa-arrow-up"></i></a></th>
					</c:when>
					<c:otherwise>
						<th class="header-row-content"><a
							href="/admin/authors?search=${search}&sort=id__DESC">ID </a></th>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${sortName == 'fullname' && sortType == 'DESC'}">
						<th class="header-row-content"><a
							href="/admin/authors?search=${search}&sort=fullname__ASC">Họ
								tên <i class="fa-solid fa-arrow-down"></i>
						</a></th>
					</c:when>
					<c:when test="${sortName == 'fullname' && sortType == 'ASC'}">
						<th class="header-row-content"><a
							href="/admin/authors?search=${search}&sort=fullname__DESC">Họ
								tên <i class="fa-solid fa-arrow-up"></i>
						</a></th>
					</c:when>
					<c:otherwise>
						<th class="header-row-content"><a
							href="/admin/authors?search=${search}&sort=fullname__DESC">Họ
								tên </a></th>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${sortName == 'birthday' && sortType == 'DESC'}">
						<th class="header-row-content"><a
							href="/admin/authors?search=${search}&sort=birthday__ASC">Ngày
								sinh <i class="fa-solid fa-arrow-down"></i>
						</a></th>
					</c:when>
					<c:when test="${sortName == 'birthday' && sortType == 'ASC'}">
						<th class="header-row-content"><a
							href="/admin/authors?search=${search}&sort=birthday__DESC">Ngày
								sinh <i class="fa-solid fa-arrow-up"></i>
						</a></th>
					</c:when>
					<c:otherwise>
						<th class="header-row-content"><a
							href="/admin/authors?search=${search}&sort=birthday__DESC">Ngày
								sinh </a></th>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${sortName == 'address' && sortType == 'DESC'}">
						<th class="header-row-content"><a
							href="/admin/authors?search=${search}&sort=address__ASC">Địa
								chỉ <i class="fa-solid fa-arrow-down"></i>
						</a></th>
					</c:when>
					<c:when test="${sortName == 'address' && sortType == 'ASC'}">
						<th class="header-row-content"><a
							href="/admin/authors?search=${search}&sort=address__DESC">Địa
								chỉ <i class="fa-solid fa-arrow-up"></i>
						</a></th>
					</c:when>
					<c:otherwise>
						<th class="header-row-content"><a
							href="/admin/authors?search=${search}&sort=address__DESC">Địa
								chỉ </a></th>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${sortName == 'phone' && sortType == 'DESC'}">
						<th class="header-row-content"><a
							href="/admin/authors?search=${search}&sort=phone__ASC">SĐT <i
								class="fa-solid fa-arrow-down"></i></a></th>
					</c:when>
					<c:when test="${sortName == 'phone' && sortType == 'ASC'}">
						<th class="header-row-content"><a
							href="/admin/authors?search=${search}&sort=phone__DESC">SĐT <i
								class="fa-solid fa-arrow-up"></i></a></th>
					</c:when>
					<c:otherwise>
						<th class="header-row-content"><a
							href="/admin/authors?search=${search}&sort=phone__DESC">SĐT </a></th>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${sortName == 'status' && sortType == 'DESC'}">
						<th class="header-row-content"><a
							href="/admin/authors?search=${search}&sort=status__ASC">Trạng
								thái <i class="fa-solid fa-arrow-down"></i>
						</a></th>
					</c:when>
					<c:when test="${sortName == 'status' && sortType == 'ASC'}">
						<th class="header-row-content"><a
							href="/admin/authors?search=${search}&sort=status__DESC">Trạng
								thái <i class="fa-solid fa-arrow-up"></i>
						</a></th>
					</c:when>
					<c:otherwise>
						<th class="header-row-content"><a
							href="/admin/authors?search=${search}&sort=status__DESC">Trạng
								thái </a></th>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${sortName == 'modTime' && sortType == 'DESC'}">
						<th class="header-row-content"><a
							href="/admin/authors?search=${search}&sort=modTime__ASC">Ngày
								cập nhật <i class="fa-solid fa-arrow-down"></i>
						</a></th>
					</c:when>
					<c:when test="${sortName == 'modTime' && sortType == 'ASC'}">
						<th class="header-row-content"><a
							href="/admin/authors?search=${search}&sort=modTime__DESC">Ngày
								cập nhật <i class="fa-solid fa-arrow-up"></i>
						</a></th>
					</c:when>
					<c:otherwise>
						<th class="header-row-content"><a
							href="/admin/authors?search=${search}&sort=modTime__DESC">Ngày
								cập nhật </a></th>
					</c:otherwise>
				</c:choose>
				<th class="header-row-content">Tùy chỉnh</th>
			</tr>
			<!--End table header -->
			<c:forEach items="${pageObj.content}" var="author">
				<!--Start table data -->
				<tr class="data-row">
					<td class="author-data"><input class="select-row"
						type="checkbox" name="checkbok-delete" value="${author.id}" /></td>
					<td class="author-data">${author.id}</td>
					<td class="author-data tr-width-12">${author.fullname}</td>
					<td class="author-data tr-width-12"><fmt:formatDate
							value="${author.birthday}" pattern="dd/MM/yyyy" /></td>
					<td class="author-data tr-width-12">${author.address}</td>
					<td class="author-data tr-width-12">${author.phone}</td>
					<td class="author-data tr-width-12">${author.status == 1 ? "Kích hoạt" : "Không kích hoạt"}</td>
					<td class="author-data tr-width-12"><fmt:formatDate
							value="${author.modTime}" pattern="dd/MM/yyyy" /></td>
					<td class="book-data ">
						<div class="custom-btn">
							<a href="/admin/authors/update/${author.id}" class="edit-custom-btn">Sửa</a> <a href="#"
								class="delete-custom-btn" onclick="deleteSelected([${author.id}])">Xóa</a>
						</div>
					</td>
				</tr>
			</c:forEach>

			<!-- End table data -->
		</table>
		<!-- Start paging -->

		<c:if test="${pageObj.totalPages > 0}">
			<nav aria-label="Page navigation example" class="paging my-3">
				<ul class="list-paging-numbers justify-content-center">
					<c:if test="${not pageObj.first}">
						<li class="page-item"><a class="page-link"
							href="/admin/authors?search=${search}&&page=${pageObj.number}"
							aria-label="Previous"> <span aria-hidden="true">&lt</span>
						</a></li>
					</c:if>
					<c:if test="${pageObj.number > 0}">
						<li class="page-item"><a class="page-link"
							href="/admin/authors?search=${search}&page=${pageObj.number}">${pageObj.number}</a></li>
					</c:if>
					<li class="page-item"><a class="page-link active"
						href="/admin/authors?search=${search}&page=${pageObj.number + 1}">${pageObj.number + 1}</a></li>
					<c:if test="${pageObj.number + 1 < pageObj.totalPages}">
						<li class="page-item"><a class="page-link"
							href="/admin/authors?search=${search}&page=${pageObj.number + 2}">${pageObj.number + 2}</a></li>
					</c:if>
					<c:if test="${not pageObj.last}">
						<li class="page-item"><a class="page-link"
							href="/admin/authors?search=${search}&page=${pageObj.number + 2}"
							aria-label="Next"> <span aria-hidden="true">&gt</span>
						</a></li>
					</c:if>
				</ul>
			</nav>
		</c:if>



		<!-- End paging -->
	</div>
	<!--End list authors -->
</div>

<div class="modal" data-modal="trigger-1" id="modal">
	<article class="content-wrapper" id="content-wrapper">
		<button class="close" id="close"></button>
		<header class="modal-header">
			<h2>Thông báo</h2>
		</header>
		<div class="content" id="content"></div>
	</article>
</div>

<script>
	const selectAll = document.getElementById("select-all-row");
	const selectRows = document.getElementsByClassName("select-row");
	const deleteBtn = document.getElementById("delete-author-btn");
	
	const modal = document.getElementById("modal");
	const contentWrapper = document.getElementById('content-wrapper');
	const close = document.getElementById('close');
	const content = document.getElementById("content");
	close.addEventListener('click', () => {
		modal.classList.remove('open')
		location.reload();
	});
	modal.addEventListener('click', () => {
		modal.classList.remove('open')
		location.reload();
	});
	contentWrapper.addEventListener('click', (e) => e.stopPropagation());

	function deleteSelected(selected) {
		$.ajax({
			  method: "DELETE",
			  url: "/api/authors",
			  contentType: 'application/json',
			  data: JSON.stringify(selected),
			    success: ( response ) => {
			    	console.log(response)
					content.innerHTML = "<div><img src='/assets/images/success.png' /></div><br />"+ "<h3>" + response.message + "</h3>";
					modal.classList.toggle('open');
		      	},
		      	error: (error) => {
		      		console.log(error);
					content.innerHTML = "<div><img src='/assets/images/fail.png' /></div>"+ "<h3>" + error.responseJSON.message + "</h3>";
					modal.classList.toggle('open');
			    }
		});		
	}

	
	
	deleteBtn.onclick = (e) => {
		e.preventDefault();
		const selected = [];
		for(var i = 0; i < selectRows.length; i ++) {
			if(selectRows[i].checked) selected.push(selectRows[i].value);
		}
		
		deleteSelected(selected);
	}
	
	
	selectAll.onclick = (e) => {
		const isSelectAll = selectAll.checked;
		for(var i = 0; i < selectRows.length; i ++) {
			selectRows[i].checked = isSelectAll;
		}
	}	
	
</script>