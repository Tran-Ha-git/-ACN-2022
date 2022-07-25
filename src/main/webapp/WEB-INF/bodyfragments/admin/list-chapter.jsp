<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>




<div class="main-content item">
	<div class="title">
	<h2 class="title-content">
	<a href="/admin/edit?id=${book.id }" class="title-content">Cập nhật sách/</a>
    Cập nhật danh sách chương
	</h2>
	</div>

	<div class="list-book-container">
		<table class="list-space">
			<tr>
				<th>Chương</th>
				<th>Thao tác</th>
			</tr>
			<c:forEach items="${book.onlines}" var="onl">
				<tr>
					<td>${onl.name}</td>

					<td class="book-data">
						<div class="custom-btn">
							
							 <button  data-id="${onl.id}" 
						     data-bookid="${book.id}" 
							 class="edit-online-btn">Sửa</button> 
														
							<c:url value="/admin/DeleteChapter" var="url">
								<c:param name="id" value="${onl.id}" />
								<c:param name="bookid" value="${book.id}" />
							</c:url>
							<a href="${url}" class="delete-custom-btn">Xóa</a>
						</div>
					</td>
				</tr>
			</c:forEach>




		</table>
		<!-- Start form  -->
		<div class="form__list-chapter" id="add-online">
			<h3 class="add_chapter">Thêm chương</h3>
			<br />
			<form action="/admin/addChapter" method="post">
				<div class="form__add-chapter">
					<label>Tên chương</label><br /> <input type="text" name="name"
						class="width-input" required/><br /> <br /> 
						<label>Nội dung</label><br />

					<textarea name="content" rows="15" style="width: 100%" required></textarea>
					<label>Priority</label><br /> <input type="text" name="priority"
						class="width-input width-input-prioty width-input isNum"  /><br /> <br />
							<label class="warning"></label><br /> 
				</div>
				<div class="btn spacing">
					<a href="" class="grey">Hủy</a> <input type="submit"
						value="Thêm mới" class="black" />
				</div>
			</form>
		</div>

		<!-- End form -->
		
		<!-- Start edit form  -->
		<div class="form__list-chapter" id="edit-online">
		   <div class="edit-header">
			<h3 class="add_chapter item1">Edit Audio</h3>
		     <button class="add-online-btn item2">Thêm</button> 
			
		
		   </div>
			<br />
			<form onsubmit="return setAction(this)" method="post" id="edit-form">
				<div class="form__add-chapter">
					<label>Tên chương</label><br />
					<input type="text" name="name"
						class="width-input" value="${currentOnline.name } " required/><br /> <br /> 
						
					<label>Nội dung</label><br />
					<input name="content" rows="3" class="width-input height-input" 
					value="${currentOnline.content } " required/>
					
					<label>Priority</label><br /> <input type="text" name="priority"
						class="width-input width-input-prioty width-input isNum" value="${currentOnline.priority}" />
						<label class="warning"></label><br /> 
						<br />
				</div>
				<div class="btn spacing">
					<a href="" class="grey">Hủy</a> <input type="submit"
						value="Lưu thay đổi" class="black"
						 data-id="${currentOnline.id}" id="submit-edit-online"/>
				</div>
			</form>
		</div>

		<!-- End edit form -->
		
	</div>
	<!--End list books -->
</div>
<script type="text/javascript">
var editOnlineBtn= document.querySelectorAll('.edit-online-btn');
var addOnlineBtn= document.querySelector('.add-online-btn');
var submitEdit = document.querySelector('#submit-edit-online');

var editform = document.querySelector('#edit-form');
var addformDiv = document.querySelector('#add-online');
var editformDiv = document.querySelector('#edit-online');

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

editOnlineBtn.forEach(editBtn => {
		 
		 editBtn.onclick=function(e){
			 
		
		 editformDiv.style.display='block';
		 addformDiv.style.display='none';
		
		 document.location='/admin/getEditOnlineChapter/'+editBtn.dataset.id+"/"+editBtn.dataset.bookid; 
}
})


if(window.location.href.includes('/admin/getEditOnlineChapter/')){
	 editformDiv.style.display='block';
	 addformDiv.style.display='none';

}


function setAction(editform){
	
	editform.action='/admin/editChapter/'+submitEdit.dataset.id;
	
	
}
addOnlineBtn.onclick=function(){
	 editformDiv.style.display='none';
	 addformDiv.style.display='block';
}



</script>



