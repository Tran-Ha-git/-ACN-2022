<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<div class="main">
	<div class="title">
		<a href="/admin/authors" class="back"><i class="fa-solid fa-arrow-left"></i></a>
		<h1>Sửa thông tin tác giả</h1>
	</div>
	<form method="PUT" action="/api/authors" id="form">
		<input type="hidden" name="id" value="${author.id}" />
		<table>
			<tbody>
				<tr>
					<td>
						<div class="input">
							<label for=""><span class="required">*</span>Họ tên</label> <input
								type="text" name="fullname" required value="${author.fullname}" />
						</div>
					</td>
					<td>
						<div class="input">
							<label for=""><span class="required">*</span>Mô tả</label> <input
								type="text" name="description" required value="${author.description}" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="input">
							<label for=""><span class="required">*</span>Slug</label> <input
								type="text" name="slug" required value="${author.slug}" />
						</div>
					</td>
					<td>
						<div class="input">
							<label for="">Số điện thoại</label> <input type="text"
								name="phone" value="${author.phone}" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="input">
							<label for="">Ngày sinh</label> <input type="date"
								name="birthday" value="<fmt:formatDate value="${author.birthday}" pattern="yyyy-MM-dd" />" />
						</div>
					</td>
					<td>
						<div class="input">
							<label for="">Địa chỉ</label> <input type="text" name="address" value="${author.address}" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="input">
							<label for="">Tiêu đề SEO</label> <input
								type="text" name="metaTitle" value="${author.metaTitle}" />
						</div>
					</td>
					<td>
						<div class="input">
							<label for="">Mô tả SEO</label> <input type="text"
								name="metaDescription" value="${author.metaDescription}" />
						</div>
					</td>
				</tr>
				
				<tr>
					<td>
						<div class="input checkbox">
							<label for="">Kích hoạt</label> <input type="checkbox"
								name="status" value="${author.status}" ${author.status == 1 ? 'checked' : ''}/>
						</div>					
					</td>
					<td>
						<button class="btn-update" type="submit">Sửa</button>
						<button class="btn-clear" type="reset">Clear</button>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
	<c:if test="${author.books.size() > 0}">
		<div class="section">
			<h1>Sách của tác giả</h1>
			
			<ul class="books">
				<c:forEach items="${author.books}" var="book" varStatus="loop">
					<li>
						<a href="/admin/edit?id=${book.id}">${loop.index + 1}. ${book.name}</a>
					</li>	
				</c:forEach>
			</ul>
		</div>	
	</c:if>
	<c:if test="${author.quotes.size() > 0}">
		<div class="section">
			<h1>Danh ngôn của tác giả</h1>
			
			<ul class="books">
				<c:forEach items="${author.quotes}" var="quote" varStatus="loop">
					<li>
						<a href="/admin/quote/edit/${quote.id}">${loop.index + 1}. ${quote.content}</a>
					</li>	
				</c:forEach>
			</ul>
		</div>	
	</c:if>

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


//Show modal

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


// Submit form 

function getFormData($form){
    var unindexed_array = $form.serializeArray();
    var indexed_array = {};

    $.map(unindexed_array, function(n, i){
        indexed_array[n['name']] = n['value'];
    });

    return indexed_array;
}



$("#form").submit(function(e) {

    e.preventDefault(); // avoid to execute the actual submit of the form.

    var form = $(this);
    var actionUrl = form.attr('action');

    var request = getFormData(form);
    if(!request.status) request.status = 0;
    
    $.ajax({
        type: "PUT",
        url: actionUrl,
        contentType: "application/json; charset=utf-8",
        data : JSON.stringify(request),        
	    success: ( response ) => {
			content.innerHTML = "<div><img src='/assets/images/success.png' /></div><br />"+ "<h3>" + response.message + "</h3>";
			modal.classList.toggle('open');
      	},
      	error: (error) => {
			content.innerHTML = "<div><img src='/assets/images/fail.png' /></div>"+ "<h3>" + error.responseJSON.message + "</h3>";
			modal.classList.toggle('open');
	    }
    });
});
</script>