<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<main>

 	<div class="container mt-5">
      <h2 class="title">Sách yêu thích - Love books</h2>
      <div class="row mt-5 operations">
        <div class="col-md-2 text-start">
          <button class="btn btn-outline-secondary btn-block " id="delete-many-btn">
            <span class="btn-label"><i class="fa-solid fa-trash-can"></i></span> Xóa
          </button>
        </div>
        <div class="col-md-10">
          <div class="row d-flex justify-content-end g-3">
            <div class="col-md-6">
              <input type="text" class="form-control" placeholder="Tên sách ..." id="search" />
            </div>
            <div class="col-md-3">
              <button class="btn btn-primary btn-block" onclick="search();">
                <i class="fa-solid fa-magnifying-glass"></i>&nbsp Tìm kiếm
              </button>
            </div>
          </div>
        </div>
      </div>
      
      <div class="alert alert-success mt-4">
	    <strong>Thành công!</strong>  Đã xóa thành công.
	    <a id= "closeAlertSuccess"><strong>x</strong></a>
	  </div>

      <div class="table-responsive mt-5 mb-5">
        <table
          class="table table-responsive table-borderless text-center align-middle"
        >
          <thead>
            <tr class="bg-light">
              <th scope="col" width="5%">
                <input type="checkbox" id="checkedAll" />
              </th>
              <th scope="col" width="6%"></th>
              <th scope="col" width="29%">Sách</th>
              <th scope="col" width="22%">Tác giả</th>
              <th scope="col" width="22%">Thể loại</th>
              <th scope="col" width="16%">Thao tác</th>
            </tr>
          </thead>
          <tbody>
          <c:choose>
			<c:when test="${lovebooks.totalElements > 0}">
				<c:forEach items="${lovebooks.content}" var="lovebook" varStatus="loop">
				
				<tr id="love-${lovebook.slug}"  class="love-id-${lovebook.id}">
	              <th scope="row">
	                <input type="checkbox" class="checkSingle" value="${lovebook.id}"/>
	              </th>
	              <td>
	                <img src="${lovebook.thumbnail}" width="55" />
	              </td>
	              <td>
	                <a href="/books/${lovebook.slug}">${lovebook.name}</a>
	              </td>
	              <td>
	              		<c:forEach items="${lovebook.authors}" var="author" varStatus="loop">
						<a href="/books?author=${author.slug}">
							<span class="info-item">${author.fullname}</span>
							<c:if test="${loop.index != lovebook.authors.size() - 1}"> <span class="mr-3">,</span> </c:if>
						</a>
						</c:forEach>
	              </td>
	              <td>
	                <c:forEach items="${lovebook.categories}" var="category" varStatus="loop">
					<a href="/books?category_id=${category.id}">
						<span class="info-item">${category.name}</span>
						<c:if test="${loop.index != lovebook.categories.size() - 1}"> <span class="mr-3">,</span> </c:if>
					</a> 
					</c:forEach>
	              </td>
	              <td class="text-end">
	                <button type="button" class="btn btn-primary btn-sm" onclick="window.location.href = '/books/${lovebook.slug}'"><i class="fa-solid fa-arrow-up-right-from-square"></i> Xem sách</button>
	                <button class="btn btn-secondary btn-block btn-sm" onclick="showDelete('${lovebook.slug}');"> <i class="fa-solid fa-trash-can"></i> Xóa</button>
	              </td>
	            </tr>
					
				</c:forEach>
			
			</c:when>
			<c:otherwise><tr> <td colspan="5">Danh sách còn đang rỗng</td> </tr></c:otherwise>
		  </c:choose>
            
          </tbody>
        </table>
      </div>
      
      <c:if test="${lovebooks.totalPages > 0}">
      <div class="row">
        <div class="col-4 mt-4">Hiện thị ${lovebooks.numberOfElements } trên ${lovebooks.totalElements } cuốn sách</div>
        <div class="col-8">
          <ul class="pagination justify-content-end" style="margin: 20px 0">
          	<c:if test="${not lovebooks.first}">
	            <li class="page-item">
	              <a class="page-link" name="${lovebooks.number-1}"><i class="fa-solid fa-angle-left"></i></a>
	            </li>
            </c:if>
            <c:if test="${ lovebooks.number > 0}">
            	<li class="page-item"><a class="page-link"  name="${lovebooks.number-1}">${lovebooks.number}</a></li>
            </c:if>
            <li class="page-item active"><a class="page-link" name="${lovebooks.number}">${lovebooks.number+1}</a></li>
            <c:if test="${ lovebooks.number +1 < lovebooks.totalPages}">
            	<li class="page-item"><a class="page-link" name="${lovebooks.number+1}">${lovebooks.number+2}</a></li>
            </c:if>
            <c:if test="${not lovebooks.last}">
	            <li class="page-item">
	              <a class="page-link" name="${lovebooks.number+1}"><i class="fa-solid fa-angle-right"></i></a>
	            </li>
	        </c:if>
          </ul>
        </div>
      </div>
      </c:if>
      
    </div>

</main>
<div class="modal fade" id="confirmModal">
	<div class="modal-dialog ">
		<div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title"><i class="fa-solid fa-trash-can"></i> Xóa sách yêu thích</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
	        <p>Bạn có muốn xóa sách đã chọn?</p>
	        <input type="hidden" value="" id="deletingSlugBook">
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
	        <button type="button" class="btn btn-primary" id="deleteLovebook">Xác nhận</button>
	      </div>
	    </div>
	</div>
</div>
<div class="modal fade" id="confirmDeleteManyModal">
	<div class="modal-dialog ">
		<div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title"><i class="fa-solid fa-trash-can"></i> Xóa sách yêu thích</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
	        <p>Bạn có muốn xóa sách đã chọn?</p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
	        <button type="button" class="btn btn-danger" id="deleteManyLovebook">Xác nhận</button>
	      </div>
	    </div>
	</div>
</div>

<script src="./../vendor/jquery/jquery3.6.0.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<script>
/*  delete many */
$(document).ready(function () {
	let deletingBooks = [];
    
	$("#delete-many-btn").click(function () {
		$('input:checkbox.checkSingle:checked').each(function() {
	    	deletingBooks.push($(this).val());
	    });
		if(deletingBooks.length <= 0){
	    	$('#delete-many-btn').prop('disabled', true);
	    	alert("Vui lòng chọn sách trước")
	    }else{
			$("#confirmDeleteManyModal").modal("show");
	    }
	})
	
	$("#deleteManyLovebook").click(function () {
		let param = '';
		$.each(deletingBooks, function( index, value ) {
			param += "&ids="+value;
		});
		$.ajax({type: "DELETE", url: "/api/book/love/books?1=1"+param,
			success: function(result){
				$("#confirmDeleteManyModal").modal("hide");
				$.each(deletingBooks, function( index, value ) {
					$(".love-id-"+value).css("display","none");
				});
				$(".alert-success").css("display","block");
				setTimeout(
					function() {
						$(".alert-success").css("display","none");
						document.location.href= "/lovebook";
				}, 5000);
			}
		});
	})
});

function search(){
	document.location.href= "/lovebook?search="+$("#search").val();
}
/* close alert */
$(document).ready(function () {
	$("#closeAlertSuccess").click(function () {
		$(".alert-success").css("display","none");
	});
});

function showDelete(bookSlug){
	$("#confirmModal").modal("show");
	$("#deletingSlugBook").val(bookSlug);
}

/* delete a lovebook */
$(document).ready(function () {
	$("#deleteLovebook").click(function () {
		let bookSlug = $("#deletingSlugBook").val();
		$.ajax({type: "DELETE", url: "/api/book/"+bookSlug+"/love", success: function(result){
			$("#confirmModal").modal("hide");
			$(".alert-success").css("display","block");
			$("#love-"+bookSlug).css("display","none");
			setTimeout(
				function() {
					$(".alert-success").css("display","none");
			}, 5000);
		}});
	});
});

/*  pagination */
$(document).ready(function () {
	let search = $("#search").val();
	$(".page-link").each(function(){
		let page = $(this).attr("name");
		let url = "/lovebook?search="+search+"&page="+page
		$(this).attr("href",url)
	});
});
	/* check all */
  $(document).ready(function () {
    $("#checkedAll").change(function () {
      if (this.checked) {
        $("input:checkbox.checkSingle").each(function () {
          this.checked = true;
        });
        $("input:checkbox.checkSingle").prop("checked", true);
        $('#delete-many-btn').prop('disabled', false);
      } else {
        $("input:checkbox.checkSingle").each(function () {
          this.checked = false;
        });
        $('#delete-many-btn').prop('disabled', true);
      }
    });

    $(".checkSingle").click(function () {
      if ($(this).is(":checked")) {
        var isAllChecked = 0;
        $('#delete-many-btn').prop('disabled', false);
        
        $(".checkSingle").each(function () {
          if (!this.checked) isAllChecked = 1;
        });

        if (isAllChecked == 0) {
          $("#checkedAll").prop("checked", true);
        }
      } else {
        $("#checkedAll").prop("checked", false);
      }
    });
  });
</script>
