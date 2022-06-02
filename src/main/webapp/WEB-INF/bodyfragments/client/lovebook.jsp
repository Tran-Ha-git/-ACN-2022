<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<main>

 	<div class="container mt-5">
      <h2 class="title">Sách yêu thích - Love books</h2>
      <div class="row mt-5 operations">
        <div class="col-md-2 text-start">
          <button class="btn btn-outline-secondary btn-block">
            <span class="btn-label"
              ><i class="fa-solid fa-trash-can"></i>
            </span>
            Xóa
          </button>
        </div>
        <div class="col-md-10">
          <div class="row d-flex justify-content-end g-3">
            <div class="col-md-6">
              <input
                type="text"
                class="form-control"
                placeholder="Tên sách ..."
              />
            </div>
            <div class="col-md-3">
              <button class="btn btn-primary btn-block">
                <i class="fa-solid fa-magnifying-glass"></i>&nbsp Tìm kiếm
              </button>
            </div>
          </div>
        </div>
      </div>

      <div class="table-responsive mt-5">
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
            <tr>
              <th scope="row">
                <input type="checkbox" class="checkSingle" />
              </th>
              <td>
                <img
                  src="https://th.bing.com/th/id/OIP.8XZTWEPTNkyiJZyLe-LRzQHaEK?pid=ImgDet&rs=1"
                  width="55"
                />
              </td>
              <td>
                <a href="#">Nóng giận là bản năng, tĩnh lặng là bản lĩnh</a>
              </td>
              <td>
                <a href="#">Tống Mặc </a>,
                <a href="#">Hà Giang </a>
              </td>
              <td>
                <a href="#">Self Help </a>,
                <a href="#">Nonfiction </a>
              </td>
              <td class="text-end">
                <button type="button" class="btn btn-primary btn-sm">
                  <i class="fa-solid fa-arrow-up-right-from-square"></i> Xem
                  sách
                </button>
                <button class="btn btn-secondary btn-block btn-sm">
                  <i class="fa-solid fa-trash-can"></i>
                  Xóa
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>

      <div class="row">
        <div class="col-4 mt-4">Hiện thị 4 trên 8 cuốn sách</div>
        <div class="col-8">
          <ul class="pagination justify-content-end" style="margin: 20px 0">
            <li class="page-item disabled">
              <a class="page-link" href="#"
                ><i class="fa-solid fa-angle-left"></i
              ></a>
            </li>
            <li class="page-item"><a class="page-link" href="#">1</a></li>
            <li class="page-item"><a class="page-link" href="#">2</a></li>
            <li class="page-item"><a class="page-link" href="#">3</a></li>
            <li class="page-item">
              <a class="page-link" href="#"
                ><i class="fa-solid fa-angle-right"></i
              ></a>
            </li>
          </ul>
        </div>
      </div>
    </div>

</main>


<script src="./../vendor/jquery/jquery3.6.0.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

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
