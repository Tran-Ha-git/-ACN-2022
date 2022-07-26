<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>




<div class="main-content item">
	<div class="title">
	<h2 class="title-content">
		<a href="/admin/edit?id=${book.id }" class="title-content">
		Cập nhật sách/
		</a>
     	Cập nhật danh sách chương
	</h2>
	
	</div>

	<div class="list-book-container">
	
	
		<table class="list-space">
			<tr>
				<th class="list-audio-th">Chương</th>
				<th class="list-audio-th">Link</th>
				<th class="list-audio-th">Thao tác</th>
			</tr>
			<c:forEach items="${book.audios}" var="audi">
			
		
			    <tr class="list-audio-tr">
			 	<td class="list-audio-td">
					<input class="audio-name" name="name" disabled value="${audi.name}" required/>
					
				</td>
				<td class="list-audio-td" >
				
						<input class="audio-url" name="url" disabled value="${audi.url}" required/>
						
				</td>
			  			
					<td class="list-audio-td">
					
						<div class="custom-btn">
					
							 
                             <c:url value="/admin/getAudio" var="url">
								<c:param name="id" value="${audi.id}" />
							</c:url>	
												
							<button  data-id="${audi.id}" 
						    data-bookid="${book.id}" 
							 class="edit-audio-btn">Sửa</button> 
														
 				        	<c:url value="/admin/DeleteChapterAudio" var="url">
								<c:param name="id" value="${audi.id}" />
								<c:param name="bookid" value="${book.id}" />
							</c:url>
							
							<a href="${url }"
								class="delete-custom-btn">Xóa</a>
					
 						</div>
			        </td>
				 </tr>					 
			</c:forEach>

		</table>
		
		<!-- Start form  -->
		<div class="form__list-chapter" id="add-audio">
			<h3 class="add_chapter">Thêm Audio</h3>
			<br />
			<form action="/admin/addChapterAudio" class="border-form"
			 method="post" enctype="multipart/form-data">
				<div class="form__add-chapter">
					<label>Tên Audio</label><br /> <input type="text" name="name"
						class="width-input" required /><br /> <br /> <label>Upload File</label><br />


					<input type="file" name="urlAudio" class="width-input height-input" required>
					<label>Priority</label><br /> <input type="text" name="priority"
						class="width-input width-input-prioty isNum" />
						<label class="warning"></label><br /> <br />
				</div>
				<div class="btn spacing">
					<a href="" class="grey">Hủy</a> 
					<input type="submit" value="Thêm "
						class="black" />
				</div>
			</form>
		</div>
		 <!-- edit -->
		<div class="form__list-chapter" id="edit-audio">
		     <div class="edit-header">
			<h3 class="add_chapter item1">Edit Audio</h3>
		     <button class="add-audio-btn item2">Thêm</button> 
			
		
		   </div>
			<form onsubmit="return setAction(this)" class="border-form"
			 method="post" enctype="multipart/form-data"  id="edit-form">
				<div class="form__add-chapter">
					<label>Tên Audio</label><br /> <input type="text" name="name"
						class="width-input" value="${currentAudio.name }" required/><br /> <br /> 
			
			        <label>Tên url: ${currentAudio.url }</lable><br /><br/>
					<label>***Change Audio File</label>


					<input type="file" name="urlAudio" class="width-input height-input">
					<label>Priority</label><br /> <input type="text" name="priority"
						class="width-input width-input-prioty isNum" value="${currentAudio.priority }"/>
						<label class="warning"></label><br /> <br />
			     </div>
				<div class="btn spacing">
					<a href="" class="grey">Hủy</a> 
					<input type="submit" value="Lưu thay đổi" data-id="${currentAudio.id}" 
					id="submit-edit-audio"
						class="black" />
				</div>
			</form>
		</div>

		<!-- End form -->
	</div>
	<!--End list books -->
</div>
<script type="text/javascript">
var editAudioBtn= document.querySelectorAll('.edit-audio-btn');
var addAudioBtn= document.querySelector('.add-audio-btn');
var submitEdit = document.querySelector('#submit-edit-audio');

var editform = document.querySelector('#edit-form');
var addformDiv = document.querySelector('#add-audio');
var editformDiv = document.querySelector('#edit-audio');

editAudioBtn.forEach(editBtn => {
		 
		 editBtn.onclick=function(e){
			 
		
		 editformDiv.style.display='block';
		 addformDiv.style.display='none';
		
		 document.location='/admin/getEditAudioChapter/'+editBtn.dataset.id+"/"+editBtn.dataset.bookid; 
}
})


if(window.location.href.includes('/admin/getEditAudioChapter/')){
	 editformDiv.style.display='block';
	 addformDiv.style.display='none';

}


function setAction(editform){
	
	editform.action='/admin/editChapterAudio/'+submitEdit.dataset.id;
	
	
}
addAudioBtn.onclick=function(){
	 editformDiv.style.display='none';
	 addformDiv.style.display='block';
}
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

