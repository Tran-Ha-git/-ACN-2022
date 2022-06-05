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
<div class="main">
	<div class="title">
		<a href="/admin/quote" class="back"><i class="fa-solid fa-arrow-left"></i></a>
		<h1>Create danh ngôn</h1>
	</div>
	<form:form method="POST"  id="form" modelAttribute="quoteDto" enctype="multipart/form-data">
		<table>
			<tbody>
				<tr>
					<td>
						<div class="input">
							<label for="">Content</label> 
							<form:textarea rows="5" path="content" cssStyle="width: 100%"></form:textarea>
							<form:errors path="content" cssClass="error" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="input">
							<label for="">Title</label>
							<form:input rows="5" path="metaTitle" cssStyle="width: 100%"/>
							<form:errors path="metaTitle" cssClass="error" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="input">
							<label for="">Description</label>
							<form:textarea rows="5" path="metaDescription" cssStyle="width: 100%"></form:textarea>
							<form:errors path="metaDescription" cssClass="error" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="input">
							<label for="">Slug</label>
							<form:input path="slug" cssStyle="width: 100%"/>
							<form:errors path="slug" cssClass="error" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="input">
							<label for="">Thumbnail</label>
							<form:input type="file" path="imageFile" cssStyle="width: 100%"/>
							<form:errors path="thumbnail" cssClass="error" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="input">
							<label for="">Category</label>
							<select name="category" 
								style="width: 100%; height: 200px"  multiple>
								<c:forEach items="${categoryList }" var="category">
									<option value="${category.id }">${category.name }</option>
								</c:forEach>
							</select>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="input">
							<label for="">Author</label>
							<select name="author" >
								<c:forEach items="${authorList }" var="author">
									<option value="${author.id }" >${author.fullname }</option>
								</c:forEach>
							</select>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<button class="btn-update" type="submit">Sửa</button>
						<button class="btn-clear" type="reset">Clear</button>
					</td>
				</tr>
			</tbody>
		</table>
	</form:form>
</div>


