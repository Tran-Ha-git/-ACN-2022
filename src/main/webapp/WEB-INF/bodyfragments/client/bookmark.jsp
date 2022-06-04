<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<main>

<div class="container mt-5">
      <h2 class="title">Đánh dấu - bookmarks </h2>
      <div class="row mt-5 operations">
        <div class="col-md-3 text-start row" >
          <!-- <button class="btn btn-outline-secondary btn-block">
            <span class="btn-label"
              ><i class="fa-solid fa-trash-can"></i>
            </span>
            Xóa
          </button> -->
          <span class="col-5 mt-1"><strong>Định dạng:</strong>  </span>
          <select class="form-select col-7 w-50" id="typeMark">
			  <option selected value="all" >Tất cả</option>
			  <option value="online">Online</option>
			  <option value="audio">Audio</option>
			  <option value="pdf">Pdf</option>
			</select>
        </div>
        <div class="col-md-9">
          <div class="row d-flex justify-content-end g-3">
            <div class="col-md-7">
              <input
                type="text"
                class="form-control"
                placeholder="Tên sách ..."
                id="search"
              />
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

      <div class="table-responsive mt-5">
        <table
          class="table table-responsive table-borderless text-center align-middle"
        >
          <thead>
            <tr class="bg-light">
             <!--  <th scope="col" width="5%">
                <input type="checkbox" id="checkedAll" />
              </th> -->
              <th scope="col" width="6%"></th>
              <th scope="col" width="20%">Sách</th>
              <th scope="col" width="13%">Tác giả</th>
              <th scope="col" width="13%">Thể loại</th>
              <th scope="col" width="9%">Định dạng</th>
              <th scope="col" width="18%">Chương</th>
              <th scope="col" width="16%">Thao tác</th>
            </tr>
          </thead>
          <tbody>
          <c:choose>
			<c:when test="${marks.totalElements > 0}">
				<c:forEach items="${marks.content}" var="mark" varStatus="loop">
				<c:if test="${mark.audio != null}">
					 <tr id="mark-audio-${mark.audio.id}">
		              <!-- <th scope="row">
		                <input type="checkbox" class="checkSingle" />
		              </th> -->
		              <td>
		                <img
		                  src="${mark.book.thumbnail}"
		                  width="55"
		                />
		              </td>
		              <td>
		                <a href="/books/${mark.book.slug}">${mark.book.name}</a>
		              </td>
		              <td>
		                <c:forEach items="${mark.book.authors}" var="author" varStatus="loop">
								<a href="/books?author=${author.slug}">
									<span class="info-item">${author.fullname}</span>
									<c:if test="${loop.index != mark.book.authors.size() - 1}"> <span class="mr-3">,</span> </c:if>
								</a>
						</c:forEach>
		              </td>
		              <td>
		                <c:forEach items="${mark.book.categories}" var="category" varStatus="loop">
							<a href="/books?category_id=${category.id}">
								<span class="info-item">${category.name}</span>
								<c:if test="${loop.index != mark.book.categories.size() - 1}"> <span class="mr-3">,</span> </c:if>
							</a> 
						</c:forEach>
		              </td>
		              <td>Audio</td>
		              <td>
		                <a href="/audio/${mark.book.slug}?chapter=${mark.audio.id}">
		                  Chương ${mark.audio.priority}: ${mark.audio.name}</a
		                >
		              </td>
		              <td class="text-end">
		                <button type="button" class="btn btn-primary btn-sm" onclick="window.location.href='/audio/${mark.book.slug}?chapter=${mark.audio.id}'; ">
		                  <i class="fa-solid fa-arrow-up-right-from-square"></i> Mở
		                </button>
		                <button class="btn btn-secondary btn-block btn-sm " onclick="showDeleteAudio('${mark.book.slug}',${mark.audio.id})">
		                  <i class="fa-solid fa-trash-can"></i>
		                  Xóa
		                </button>
		              </td>
		            </tr>
				</c:if>
				<c:if test="${mark.online != null}">
					<tr id="mark-online-${mark.online.id}">
		              <!-- <th scope="row">
		                <input type="checkbox" class="checkSingle" />
		              </th> -->
		              <td>
		                <img
		                  src="${mark.book.thumbnail}"
		                  width="55"
		                />
		              </td>
		              <td>
		                <a href="/books/${mark.book.slug}">${mark.book.name}</a>
		              </td>
		              <td>
		                <c:forEach items="${mark.book.authors}" var="author" varStatus="loop">
								<a href="/books?author=${author.slug}">
									<span class="info-item">${author.fullname}</span>
									<c:if test="${loop.index != mark.book.authors.size() - 1}"> <span class="mr-3">,</span> </c:if>
								</a>
						</c:forEach>
		              </td>
		              <td>
		                <c:forEach items="${mark.book.categories}" var="category" varStatus="loop">
							<a href="/books?category_id=${category.id}">
								<span class="info-item">${category.name}</span>
								<c:if test="${loop.index != mark.book.categories.size() - 1}"> <span class="mr-3">,</span> </c:if>
							</a> 
						</c:forEach>
		              </td>
		              <td>Online</td>
		              <td>
		                <a href="/doc-online/${mark.book.slug}?id=${mark.online.id}">
		                  Chương : ${mark.online.name}</a
		                >
		              </td>
		              <td class="text-end">
		                <button type="button" class="btn btn-primary btn-sm" onclick="window.location.href='/doc-online/${mark.book.slug}?id=${mark.online.id}'; ">
		                  <i class="fa-solid fa-arrow-up-right-from-square"></i> Mở
		                </button>
		                <button class="btn btn-secondary btn-block btn-sm " onclick="showDeleteOnline('${mark.book.slug}',${mark.online.id})">
		                  <i class="fa-solid fa-trash-can"></i>
		                  Xóa
		                </button>
		              </td>
		            </tr>
				</c:if>
				<c:if test="${mark.pdf != null}">
				<tr id="mark-pdf-${mark.pdf.id}">
		              <!-- <th scope="row">
		                <input type="checkbox" class="checkSingle" />
		              </th> -->
		              <td>
		                <img
		                  src="${mark.book.thumbnail}"
		                  width="55"
		                />
		              </td>
		              <td>
		                <a href="/books/${mark.book.slug}">${mark.book.name}</a>
		              </td>
		              <td>
		                <c:forEach items="${mark.book.authors}" var="author" varStatus="loop">
								<a href="/books?author=${author.slug}">
									<span class="info-item">${author.fullname}</span>
									<c:if test="${loop.index != mark.book.authors.size() - 1}"> <span class="mr-3">,</span> </c:if>
								</a>
						</c:forEach>
		              </td>
		              <td>
		                <c:forEach items="${mark.book.categories}" var="category" varStatus="loop">
							<a href="/books?category_id=${category.id}">
								<span class="info-item">${category.name}</span>
								<c:if test="${loop.index != mark.book.categories.size() - 1}"> <span class="mr-3">,</span> </c:if>
							</a> 
						</c:forEach>
		              </td>
		              <td>PDF</td>
		              <td>
		                <a href="/pdf-read/${mark.book.slug}">
		                  ${mark.pdf.name}</a
		                >
		              </td>
		              <td class="text-end">
		                <button type="button" class="btn btn-primary btn-sm" onclick="window.location.href='/pdf-read/${mark.book.slug}'; ">
		                  <i class="fa-solid fa-arrow-up-right-from-square"></i> Mở
		                </button>
		                <button class="btn btn-secondary btn-block btn-sm " onclick="showDeletePdf('${mark.book.slug}',${mark.pdf.id})">
		                  <i class="fa-solid fa-trash-can"></i>
		                  Xóa
		                </button>
		              </td>
		            </tr>
				</c:if>
            </c:forEach>
			
			</c:when>
			<c:otherwise><tr> <td colspan="7">Danh sách còn đang rỗng</td> </tr></c:otherwise>
		  </c:choose>
          </tbody>
        </table>
      </div>

     <c:if test="${marks.totalPages > 0}">
      <div class="row">
        <div class="col-4 mt-4">Hiện thị ${marks.numberOfElements } trên ${marks.totalElements } cuốn sách</div>
        <div class="col-8">
          <ul class="pagination justify-content-end" style="margin: 20px 0">
          	<c:if test="${not marks.first}">
	            <li class="page-item">
	              <a class="page-link" name="${marks.number-1}"><i class="fa-solid fa-angle-left"></i></a>
	            </li>
            </c:if>
            <c:if test="${ marks.number > 0}">
            	<li class="page-item"><a class="page-link"  name="${marks.number-1}">${marks.number}</a></li>
            </c:if>
            <li class="page-item active"><a class="page-link" name="${marks.number}">${marks.number+1}</a></li>
            <c:if test="${ marks.number +1 < marks.totalPages}">
            	<li class="page-item"><a class="page-link" name="${marks.number+1}">${marks.number+2}</a></li>
            </c:if>
            <c:if test="${not marks.last}">
	            <li class="page-item">
	              <a class="page-link" name="${marks.number+1}"><i class="fa-solid fa-angle-right"></i></a>
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
	        <h5 class="modal-title"><i class="fa-solid fa-trash-can"></i> Xóa sách đánh dấu</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
	        <p>Bạn có muốn xóa đánh dấu đã chọn?</p>
	        <input type="hidden" value="" id="idMark">
	         <input type="hidden" value="" id="typeMark">
	         <input type="hidden" value="" id="bookMark">
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
	        <button type="button" class="btn btn-primary" id="deleteMark">Xác nhận</button>
	      </div>
	    </div>
	</div>
</div>

<script src="./../vendor/jquery/jquery3.6.0.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<script>
$(document).ready(function () {
	
	/*  Alert success */
	$("#closeAlertSuccess").click(function () {
		$(".alert-success").css("display","none");
	});
	
	/*  Pagination */
	let search = $("#search").val();
	$(".page-link").each(function(){
		let page = $(this).attr("name");
		let url = "/bookmark?search="+search+"&page="+page
		$(this).attr("href",url)
	});
	
	/*  delete mark */
	$("#deleteMark").click(function () {
		let idMark = $("#idMark").val();
		let typeMark = $("#typeMark").val();
		let bookMark = $("#bookMark").val();
		
		switch(typeMark) {
		  case "audio":
			  deleteAudio(bookMark,idMark)
		    break;
		  case "online":
			  deleteOnline(bookMark,idMark)
		    break;
		  default:
			  deletePdf(bookMark,idMark)
		}

	});
	
	/*  type mark */
	$("#typeMark").on('change', function() {
		document.location.href= "/bookmark?type="+this.value+"&search="+$("#search").val();
	});
	
	/*  search mark */
	let searchParams = new URLSearchParams(window.location.search);
	if(searchParams.has('type')) {
		let type = searchParams.get('type');
		$("select#typeMark").val(type);
	}
	if(searchParams.has('search')) {
		let search = searchParams.get('search');
		$("#search").val(search);
	}
});

function showDelete(bookSlug){
	$("#confirmModal").modal("show");
	$("#deletingSlugBook").val(bookSlug);
}
function search(){
	document.location.href= "/bookmark?search="+$("#search").val();
}
function showDeleteAudio(bookSlug,id){
	$("#confirmModal").modal("show");
	$("#idMark").val(id);
	$("#typeMark").val("audio");
	$("#bookMark").val(bookSlug);
}
function showDeleteOnline(bookSlug,id){
	$("#confirmModal").modal("show");
	$("#idMark").val(id);
	$("#typeMark").val("online");
	$("#bookMark").val(bookSlug);
}
function showDeletePdf(bookSlug,id){
	$("#confirmModal").modal("show");
	$("#idMark").val(id);
	$("#typeMark").val("pdf");
	$("#bookMark").val(bookSlug);
}

function deleteAudio(bookSlug, id){
	$.ajax({type: "DELETE", url: "/api/mark/" + bookSlug + "/audio/id-" + id, success: function(result){
		$("#confirmModal").modal("hide");
		$(".alert-success").css("display","block");
		$("#mark-audio-"+id).css("display","none");
		setTimeout(
			function() {
				$(".alert-success").css("display","none");
			}, 5000);
		}
	});
}
function deleteOnline(bookSlug, id){
	$.ajax({type: "DELETE", url: "/api/mark/" + bookSlug + "/online/id-" + id, success: function(result){
		$("#confirmModal").modal("hide");
		$(".alert-success").css("display","block");
		$("#mark-online-"+id).css("display","none");
		setTimeout(
			function() {
				$(".alert-success").css("display","none");
			}, 5000);
		}
	});
}
function deletePdf(bookSlug,id){
	$.ajax({type: "DELETE", url: "/api/mark/" + bookSlug + "/pdf/id-" + id, success: function(result){
		$("#confirmModal").modal("hide");
		$(".alert-success").css("display","block");
		$("#mark-pdf-"+id).css("display","none");
		setTimeout(
			function() {
				$(".alert-success").css("display","none");
			}, 5000);
		}
	});
}
</script>
<script>
  $(document).ready(function () {
    $("#checkedAll").change(function () {
      if (this.checked) {
        $("input:checkbox.checkSingle").each(function () {
          this.checked = true;
        });
        $("input:checkbox.checkSingle").prop("checked", true);
      } else {
        $("input:checkbox.checkSingle").each(function () {
          this.checked = false;
        });
      }
    });

    $(".checkSingle").click(function () {
      if ($(this).is(":checked")) {
        var isAllChecked = 0;

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
