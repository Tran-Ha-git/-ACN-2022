<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
    <div class="main-content item">
        <div class="title">
            <h2 class="title-content">
                Chỉnh sửa tài khoản
            </h2>
        </div>

   

  
    <!--Start list users -->
    <div class="list-user-container">
    <form method="post" action="/admin/edit-user-role/${user.id}">
        <div class="edit-content">
            <div>
                <span class="user-info-title">ID:</span>
                <span class="user-info-value"><b>${user.id }</b></span>
            </div> 
            <div>
                <span class="user-info-title">Username:</span>
                <span class="user-info-value"><b>${user.username }</b></span>

            </div>
            <div>
                <span class="user-info-title">Email:</span>
                <span class="user-info-value"><b>${user.email }</b></span>

            </div>
            <div>
                <span class="user-info-title">Sdt:</span>
                <span class="user-info-value"><b>${user.phone }</b></span>

            </div>
            <div class="edit-user-role">
                <span class="user-info-title">Role:</span>
                <select  class="select-role" name="role">
                   
                    <option value="${role.id }">${role.name }</option>
                   
                	<c:choose>
							<c:when test="${role.id==1}">
								   <option value="2">ADMIN</option>
							</c:when>
							<c:otherwise>

							   <option value="1">CLIENT</option>
							</c:otherwise>
						</c:choose>
                
                </select>
            </div>


        </div>

        <!-- Start paging -->
        <div class="edit-user-btns" >
            <a href="/admin/users" class="cancel-btn">Quay lại</a>
            <button type="submit" class="save-change-btn">Lưu thay đổi</button>
        
        </div>
           <!-- End paging -->
    </form>
    </div>
    <!--End list users -->
    </div> 
   
