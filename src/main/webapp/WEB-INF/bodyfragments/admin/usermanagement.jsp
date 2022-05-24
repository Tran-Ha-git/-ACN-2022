<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
    <div class="main-content item">
        <div class="title">
            <h2 class="title-content">
                Danh sách tài khoản 
            </h2>
        </div>

    <!-- Start Search -->
        <div class="search">

           
            <label class="search-title"><b>Tìm kiếm:</b></label>
          	<form method="POST" action="/admin/search-users">
            <div class="search-by-username">
               
                <input type="text" 
                    class="search-username-input" 
                    id="search-username-input"
                    name="input"
                    value="${searchInput }"
                />
            
			<button class="search-btn" type="submit">
				<i class="fas fa-search"></i>Tìm

			</button>
            </div>
            </form>
        

        </div>
    <!-- End Search -->

    <!--role-->
    <form method="post" action="/admin/delete-all-users">
    
    <div class="user-role">
      <div class="role-control">
            <a href="/admin/users?role-id=1" class="role-user" id="role-user-link">Client</a>
            <span>/</span>
            <a href="/admin/users?role-id=2" class="role-admin" id="role-admin-link">Admin</a>
       </div>
       <div class="manage-user-btn">
        <button class="delete-user-btn" type="submit">
					<i class="far fa-times-circle"></i>Xóa tất cả
		</button>
       </div>
    </div>
    <!--End role-->

    <!--Start list users -->
    <div class="list-user-container">
    <h3>${message }</h3>
        <table class="list-user-table" >
            <!-- table header -->
            <tr class="header-row">
                <th class="header-row-content">
                    <input type="checkbox" class="checkbox-delete-btn" />
                </th>
                <th class="header-row-content">ID</th>
                <th class="header-row-content">Username</th>
                <th class="header-row-content">Email</th>
                 <th class="header-row-content">Điện thoại</th>
               
                <th class="header-row-content">Role</th>
                <th class="header-row-content">Tùy chỉnh</th>          
            </tr>
            <!--End table header -->

            <!--Start table data -->
            <c:forEach items="${users}" var="user">
            <tr class="data-row">
                <td class="user-data">               
                        <input type="checkbox" name="delete-item"
							class="delete-item" value="${user.id}"/>               
                </td>
                <td class="user-data">${user.id }</td>
              
                <td class="user-data">${user.username }</td>
              
                  <td class="user-data">${user.email}</td>
                  
                <td class="user-data">${user.phone }</td>
                 
                <td class="user-data">
                <c:forEach
								items="${roles}" var="role">
								<c:if test="${role.key==user.id }">
									<c:forEach items="${role.value}" var="userRole">
			                          ${userRole.name} <br />
									</c:forEach>
								</c:if>
							</c:forEach>
                </td>
             
                <td class="user-data">
                  	<div class="custom-btn">

								<c:url value="/admin/edit-user" var="url">
									<c:param name="id" value="${user.id}" />

								</c:url>
								<a href="${url}" class="edit-custom-btn">Sửa</a>
								<c:url value="/admin/delete-user" var="url">
									<c:param name="id" value="${user.id}" />

								</c:url>
								<a href="${url}" class="delete-custom-btn">Xóa</a>

							</div>
                </td>
            </tr>
            </c:forEach>

           
            <!-- End table data -->
        </table>
     

        <!-- Start paging -->
      <div class="paging">
				<ul class="list-paging-numbers">

					<c:if test="${currentpage > 0 }">
						<li><a href="/admin/users/${currentpage-1}"
							class="previous-page">&lt</a>
						<li>
					</c:if>

					<c:forEach items="${totalPages}" var="page">
						<li><a href="/admin/users/${page}" class="page-number ">${page+1}</a></li>
					</c:forEach>


					<c:forEach items="${searchPages}" var="page">
						<li><a href="/admin/search-users/${page}" class="page-number ">${page+1}</a></li>
					</c:forEach>


					<c:if test="${currentpage < totalPages.size()-1 }">
						<li><a href="/admin/users/${currentpage+1}" class="next-page">&gt</a></li>
					</c:if>
				</ul>
			</div>

           <!-- End paging -->
    </div>
    <!--End list users -->
    </form>
    </div> 
   
<script>
var checkboxBtn = document.querySelector('.checkbox-delete-btn');
checkboxBtn.onclick= function(){
	var items = document.querySelectorAll('.delete-item');
	if(checkboxBtn.checked){
	
		items.forEach(item =>{
			item.checked=true;
		})
	}
	else{
		items.forEach(item =>{
			item.checked=false;
		})	
	}
}
var adminRoleLink= document.querySelector('#role-admin-link');
var userRoleLink= document.querySelector('#role-user-link');


if(window.location.href.includes('/admin/users?role-id=2')){
	adminRoleLink.style.textDecoration ='underline';
	userRoleLink.style.textDecoration ='none';

}

</script>
