<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="<c:url value='./../../resources/static/web/css/home.css' />">

    <link rel="stylesheet" href="./../../resources/static/web/vendor/bootstrap-5.1.3-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="./../../resources/static/web/vendor/fontawesome-free-6.1.1-web/css/all.css">
    
    <link rel="stylesheet" href="./../../resources/static/web/vendor/owlcarousel/owl.carousel.min.css">
    <link rel="stylesheet" href="./../../resources/static/web/vendor/owlcarousel/owl.theme.default.min.css">

    <title>Trang chủ</title>
</head>

<body>
    <div class="container-fluid">
        <div class="pages-content">
            <div class="row">
                <div class="col-md-12">
                    <div class="heading-container">
                        <div class="bg-search"></div>
                        <div class="heading-block">
                            <h2>Chào mừng bạn đến với BookIT.vn, <br>
                                kho sách chất lượng hàng đầu Việt Nam</h2>
                            <form action="#">
                                <input type="text" class="search-input input-field form-control"
                                    placeholder="Bạn thích sách gì nhỉ?">
                                <button class="btn search-btn input-field">
                                    <i class="fa-solid fa-magnifying-glass"></i>
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
    <div class="container">
        <div class="row">
            <div class="col-md-9">
                <div class="list-product-container ">

                    <div class="header-list">
                        <div class="title-list">
                            Đề xuất cho bạn
                        </div>
                        <div class="owl-nav " id="owl-nav-1"></div>
                    </div>
                    <div class="list-product  owl-carousel owl-theme " id="list-suggest">
                        <div class="item">
                            <div class="item-product" id="item-product-first">
                                <a href="#" class="item-product-link">
                                    <img src="./../../resources/static/images/Sach-noi-Ba-oi-minh-di-dau-Tinh-yeu-cao-thuong-cua-cha.jpg"
                                        alt="" class="item-image">
                                    <div class="item-title">
                                        <p class="category">Sách nói cho trẻ</p>
                                        <p class="name">Sách nói Ba ơi mình đi đâu - Tình yêu cao thượng của cha</p>
                                    </div>
                                </a>
                            </div>
                            <div class="item-product" id="item-product-first">
                                <a href="#" class="item-product-link">
                                    <img src="./../../resources/static/images/cho-toi-xin-mot-ve-di-tuoi-tho.png" alt="" class="item-image">

                                    <img src="./../../resources/static/images/cho-toi-xin-mot-ve-di-tuoi-tho.png" alt="" class="item-image">
                                    <div class="item-title">
                                        <p class="category">Sách nói cho trẻ</p>
                                        <p class="name">Sách nói Cho tôi xin một vé đi tuổi thơ có thực sự đưa bạn trở
                                            về tuổi thơ?</p>
                                    </div>
                                </a>
                            </div>
                        </div>

                        <div class="item">
                            <div class="item-product" id="item-product-first">
                                <a href="#" class="item-product-link">
                                    <img src="./../../resources/static/images/Sach-noi-Den-khong-hat-bong-va-nhung-cau-chuyen-sau-lang-nhat-tai-noi-ranh-gioi-benh-vien.png"
                                        alt="" class="item-image">
                                    <div class="item-title">
                                        <p class="category">
                                            Sách nói hay về tình yêu </p>
                                        <p class="name">Sách nói Đèn không hắt bóng và những câu chuyện sâu lắng nhất
                                        </p>
                                    </div>
                                </a>
                            </div>
                            <div class="item-product" id="item-product-first">
                                <a href="#" class="item-product-link">
                                    <img src="./../../resources/static/images/Chu-de-vat-ly-ly-thuyet-that-moi-me-trong-sach-noi-Vu-tru-trong-vo-hat-de.png"
                                        alt="" class="item-image">
                                    <div class="item-title">
                                        <p class="category">Sách nói cho trẻ</p>
                                        <p class="name">Chủ đề vật lý lý thuyết thật mới mẻ trong sách nói Vũ trụ trong
                                            vỏ hạt dẻ</p>
                                    </div>
                                </a>
                            </div>
                        </div>
                        <div class="item">
                            <div class="item-product" id="item-product-first">
                                <a href="#" class="item-product-link">
                                    <img src="./../../resources/static/images/Mot-hien-tuong-tam-ly-trinh-tham-khong-nen-bo-qua-Sach-noi-Co-gai-tren-tau.png"
                                        alt="" class="item-image">
                                    <div class="item-title">
                                        <p class="category">
                                            Sách nói tiểu thuyết </p>
                                        <p class="name">Một hiện tượng tâm lý trinh thám: Sách nói Cô gái trên tàu</p>
                                    </div>
                                </a>
                            </div>
                            <div class="item-product" id="item-product-first">
                                <a href="#" class="item-product-link">
                                    <img src="./../../resources/static/images/Sach-noi-Ba-oi-minh-di-dau-Tinh-yeu-cao-thuong-cua-cha.jpg"
                                        alt="" class="item-image">
                                    <div class="item-title">
                                        <p class="category">Sách nói cho trẻ5</p>
                                        <p class="name">Sách nói Ba ơi mình đi đâu - Tình yêu cao thượng của cha</p>
                                    </div>
                                </a>
                            </div>
                        </div>
                        <div class="item">
                            <div class="item-product" id="item-product-first">
                                <a href="#" class="item-product-link">
                                    <img src="./../../resources/static/images/Sach-noi-Ba-oi-minh-di-dau-Tinh-yeu-cao-thuong-cua-cha.jpg"
                                        alt="" class="item-image">
                                    <div class="item-title">
                                        <p class="category">Sách nói cho trẻ5</p>
                                        <p class="name">Sách nói Ba ơi mình đi đâu - Tình yêu cao thượng của cha</p>
                                    </div>
                                </a>
                            </div>
                            <div class="item-product" id="item-product-first">
                                <a href="#" class="item-product-link">
                                    <img src="./../../resources/static/images/Gia-tri-noi-dung-cua-sach-noi-Troi-sinh-vung-ve-hay-bu-dap-bang-su-kien-tri.png"
                                        alt="" class="item-image">
                                    <div class="item-title">
                                        <p class="category">
                                            Sách nói hay về giao tiếp </p>
                                        <p class="name">Giá trị của sách nói Trời sinh vụng về, hãy bù đắp bằng sự kiên
                                            trì</p>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="list-product-container ">

                    <div class="header-list">
                        <div class="title-list">
                            Sách mới nhất

                        </div>
                        <div class="owl-nav" id="owl-nav-2">
                        </div>
                    </div>
                    <div class="list-product owl-carousel owl-theme " id="list-new">
                        <div class="item">
                            <div class="item-product" id="item-product-first">
                                <a href="#" class="item-product-link">
                                    <img src="./../../resources/static/images/Sach-noi-Ba-oi-minh-di-dau-Tinh-yeu-cao-thuong-cua-cha.jpg"
                                        alt="" class="item-image">
                                    <div class="item-title">
                                        <p class="category">Sách nói cho trẻ</p>
                                        <p class="name">Sách nói Ba ơi mình đi đâu - Tình yêu cao thượng của cha</p>
                                    </div>
                                </a>
                            </div>
                            <div class="item-product" id="item-product-first">
                                <a href="#" class="item-product-link">
                                    <img src="./../../resources/static/images/cho-toi-xin-mot-ve-di-tuoi-tho.png" alt="" class="item-image">
                                    <div class="item-title">
                                        <p class="category">Sách nói cho trẻ</p>
                                        <p class="name">Sách nói Cho tôi xin một vé đi tuổi thơ có thực sự đưa bạn trở
                                            về tuổi thơ?</p>
                                    </div>
                                </a>
                            </div>
                        </div>

                        <div class="item">
                            <div class="item-product" id="item-product-first">
                                <a href="#" class="item-product-link">
                                    <img src="./../../resources/static/images/Sach-noi-Den-khong-hat-bong-va-nhung-cau-chuyen-sau-lang-nhat-tai-noi-ranh-gioi-benh-vien.png"
                                        alt="" class="item-image">
                                    <div class="item-title">
                                        <p class="category">
                                            Sách nói hay về tình yêu </p>
                                        <p class="name">Sách nói Đèn không hắt bóng và những câu chuyện sâu lắng nhất
                                        </p>
                                    </div>
                                </a>
                            </div>
                            <div class="item-product" id="item-product-first">
                                <a href="#" class="item-product-link">
                                    <img src="./../../resources/static/images/Chu-de-vat-ly-ly-thuyet-that-moi-me-trong-sach-noi-Vu-tru-trong-vo-hat-de.png"
                                        alt="" class="item-image">
                                    <div class="item-title">
                                        <p class="category">Sách nói cho trẻ</p>
                                        <p class="name">Chủ đề vật lý lý thuyết thật mới mẻ trong sách nói Vũ trụ trong
                                            vỏ hạt dẻ</p>
                                    </div>
                                </a>
                            </div>
                        </div>
                        <div class="item">
                            <div class="item-product" id="item-product-first">
                                <a href="#" class="item-product-link">
                                    <img src="./../../resources/static/images/Mot-hien-tuong-tam-ly-trinh-tham-khong-nen-bo-qua-Sach-noi-Co-gai-tren-tau.png"
                                        alt="" class="item-image">
                                    <div class="item-title">
                                        <p class="category">
                                            Sách nói tiểu thuyết </p>
                                        <p class="name">Một hiện tượng tâm lý trinh thám: Sách nói Cô gái trên tàu</p>
                                    </div>
                                </a>
                            </div>
                            <div class="item-product" id="item-product-first">
                                <a href="#" class="item-product-link">
                                    <img src="./../../resources/static/images/Sach-noi-Ba-oi-minh-di-dau-Tinh-yeu-cao-thuong-cua-cha.jpg"
                                        alt="" class="item-image">
                                    <div class="item-title">
                                        <p class="category">Sách nói cho trẻ5</p>
                                        <p class="name">Sách nói Ba ơi mình đi đâu - Tình yêu cao thượng của cha</p>
                                    </div>
                                </a>
                            </div>
                        </div>
                        <div class="item">
                            <div class="item-product" id="item-product-first">
                                <a href="#" class="item-product-link">
                                    <img src="./../../resources/static/images/Sach-noi-Ba-oi-minh-di-dau-Tinh-yeu-cao-thuong-cua-cha.jpg"
                                        alt="" class="item-image">
                                    <div class="item-title">
                                        <p class="category">Sách nói cho trẻ5</p>
                                        <p class="name">Sách nói Ba ơi mình đi đâu - Tình yêu cao thượng của cha</p>
                                    </div>
                                </a>
                            </div>
                            <div class="item-product" id="item-product-first">
                                <a href="#" class="item-product-link">
                                    <img src="./../../resources/static/images/Gia-tri-noi-dung-cua-sach-noi-Troi-sinh-vung-ve-hay-bu-dap-bang-su-kien-tri.png"
                                        alt="" class="item-image">
                                    <div class="item-title">
                                        <p class="category">
                                            Sách nói hay về giao tiếp </p>
                                        <p class="name">Giá trị của sách nói Trời sinh vụng về, hãy bù đắp bằng sự kiên
                                            trì</p>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="list-product-container ">

                    <div class="header-list">
                        <div class="title-list">
                            Truyện hay

                        </div>
                        <div class="owl-nav" id="owl-nav-3">
                        </div>
                    </div>
                    <div class="list-product owl-carousel owl-theme " id="list-story">
                        <div class="item">
                            <div class="item-product" id="item-product-first">
                                <a href="#" class="item-product-link">
                                    <img src="./../../resources/static/images/Sach-noi-Ba-oi-minh-di-dau-Tinh-yeu-cao-thuong-cua-cha.jpg"
                                        alt="" class="item-image">
                                    <div class="item-title">
                                        <p class="category">Sách nói cho trẻ</p>
                                        <p class="name">Sách nói Ba ơi mình đi đâu - Tình yêu cao thượng của cha</p>
                                    </div>
                                </a>
                            </div>
                            <div class="item-product" id="item-product-first">
                                <a href="#" class="item-product-link">
                                    <img src="./../../resources/static/images/cho-toi-xin-mot-ve-di-tuoi-tho.png" alt="" class="item-image">
                                    <div class="item-title">
                                        <p class="category">Sách nói cho trẻ</p>
                                        <p class="name">Sách nói Cho tôi xin một vé đi tuổi thơ có thực sự đưa bạn trở
                                            về tuổi thơ?</p>
                                    </div>
                                </a>
                            </div>
                        </div>

                        <div class="item">
                            <div class="item-product" id="item-product-first">
                                <a href="#" class="item-product-link">
                                    <img src="./../../resources/static/images/Sach-noi-Den-khong-hat-bong-va-nhung-cau-chuyen-sau-lang-nhat-tai-noi-ranh-gioi-benh-vien.png"
                                        alt="" class="item-image">
                                    <div class="item-title">
                                        <p class="category">
                                            Sách nói hay về tình yêu </p>
                                        <p class="name">Sách nói Đèn không hắt bóng và những câu chuyện sâu lắng nhất
                                        </p>
                                    </div>
                                </a>
                            </div>
                            <div class="item-product" id="item-product-first">
                                <a href="#" class="item-product-link">
                                    <img src="./../../resources/static/images/Chu-de-vat-ly-ly-thuyet-that-moi-me-trong-sach-noi-Vu-tru-trong-vo-hat-de.png"
                                        alt="" class="item-image">
                                    <div class="item-title">
                                        <p class="category">Sách nói cho trẻ</p>
                                        <p class="name">Chủ đề vật lý lý thuyết thật mới mẻ trong sách nói Vũ trụ trong
                                            vỏ hạt dẻ</p>
                                    </div>
                                </a>
                            </div>
                        </div>
                        <div class="item">
                            <div class="item-product" id="item-product-first">
                                <a href="#" class="item-product-link">
                                    <img src="./../../resources/static/images/Mot-hien-tuong-tam-ly-trinh-tham-khong-nen-bo-qua-Sach-noi-Co-gai-tren-tau.png"
                                        alt="" class="item-image">
                                    <div class="item-title">
                                        <p class="category">
                                            Sách nói tiểu thuyết </p>
                                        <p class="name">Một hiện tượng tâm lý trinh thám: Sách nói Cô gái trên tàu</p>
                                    </div>
                                </a>
                            </div>
                            <div class="item-product" id="item-product-first">
                                <a href="#" class="item-product-link">
                                    <img src="./../../resources/static/images/Sach-noi-Ba-oi-minh-di-dau-Tinh-yeu-cao-thuong-cua-cha.jpg"
                                        alt="" class="item-image">
                                    <div class="item-title">
                                        <p class="category">Sách nói cho trẻ5</p>
                                        <p class="name">Sách nói Ba ơi mình đi đâu - Tình yêu cao thượng của cha</p>
                                    </div>
                                </a>
                            </div>
                        </div>
                        <div class="item">
                            <div class="item-product" id="item-product-first">
                                <a href="#" class="item-product-link">
                                    <img src="./../../resources/static/images/Sach-noi-Ba-oi-minh-di-dau-Tinh-yeu-cao-thuong-cua-cha.jpg"
                                        alt="" class="item-image">
                                    <div class="item-title">
                                        <p class="category">Sách nói cho trẻ5</p>
                                        <p class="name">Sách nói Ba ơi mình đi đâu - Tình yêu cao thượng của cha</p>
                                    </div>
                                </a>
                            </div>
                            <div class="item-product" id="item-product-first">
                                <a href="#" class="item-product-link">
                                    <img src="./../../resources/static/images/Gia-tri-noi-dung-cua-sach-noi-Troi-sinh-vung-ve-hay-bu-dap-bang-su-kien-tri.png"
                                        alt="" class="item-image">
                                    <div class="item-title">
                                        <p class="category">
                                            Sách nói hay về giao tiếp </p>
                                        <p class="name">Giá trị của sách nói Trời sinh vụng về, hãy bù đắp bằng sự kiên
                                            trì</p>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-md-3">
                <div class="sidebar">
                    <div class="top-range">
                        <h3>Bảng xếp hạng</h3>
                        <ul class="tab-list">
                            <li class="tab-item active">Sách nói</li>
                            <li class="tab-item">Ebook</li>
                        </ul>
                        <div class="tab-content">
                            <div class="top-range-content">
                                <a href="#">
                                    <div class="top-range-item">
                                        <div class="item-left">
                                            <img src="./../../resources/static/images/product02.png" alt="">
                                        </div>
                                        <div class="item-right">
                                            <h3 class="item-position">
                                                01
                                            </h3>
                                            <h4 class="item-title">
                                                Tuổi trẻ dùng để làm gì?
                                            </h4>
                                            <p class="item-author">
                                                <i class="fa-solid fa-circle-user"></i>
                                                <span>Huỳnh Chí Viễn</span>
                                            </p>
                                            <p class="item-category">
                                                <i class="fa-solid fa-folder-closed"></i>
                                                <span>Kỹ năng sống</span>
                                            </p>
                                        </div>

                                    </div>
                                </a>
                                <a href="#">
                                    <div class="top-range-item">
                                        <div class="item-left">
                                            <img src="./../../resources/static/images/product02.png" alt="">
                                        </div>
                                        <div class="item-right">
                                            <h3 class="item-position">
                                                01
                                            </h3>
                                            <h4 class="item-title">
                                                Tuổi trẻ dùng để làm gì?
                                            </h4>
                                            <p class="item-author">
                                                <i class="fa-solid fa-circle-user"></i>
                                                <span>Huỳnh Chí Viễn</span>
                                            </p>
                                            <p class="item-category">
                                                <i class="fa-solid fa-folder-closed"></i>
                                                <span>Kỹ năng sống</span>
                                            </p>
                                        </div>

                                    </div>
                                </a>
                                <a href="#">
                                    <div class="top-range-item">
                                        <div class="item-left">
                                            <img src="./../../resources/static/images/product02.png" alt="">
                                        </div>
                                        <div class="item-right">
                                            <h3 class="item-position">
                                                01
                                            </h3>
                                            <h4 class="item-title">
                                                Tuổi trẻ dùng để làm gì?
                                            </h4>
                                            <p class="item-author">
                                                <i class="fa-solid fa-circle-user"></i>
                                                <span>Huỳnh Chí Viễn</span>
                                            </p>
                                            <p class="item-category">
                                                <i class="fa-solid fa-folder-closed"></i>
                                                <span>Kỹ năng sống</span>
                                            </p>
                                        </div>

                                    </div>
                                </a>
                            </div>

                        </div>
                    </div>
                </div>

                <div class="quote">
                    <div class="title-quote">
                        Danh ngôn hay
                    </div>
                    <ul class="list-quote">
                        <li class="item-quote">
                            <a href="">
                                <p>Thời gian và hoàn cảnh mang lại sự khôn ngoan</p>
                                <div class="infor-quote">
                                    <p class="view">
                                        <i class="fa-solid fa-eye"></i>
                                        <span class="quantity-view">22</span>
                                        <i class="fa-solid fa-thumbs-up"></i>
                                        <span class="quantity-like">10</span>
                                    </p>
                                    <p class="category">
                                        Ngạn ngữ Trung Quốc
                                    </p>
                                </div>
                            </a>
                        </li>
                        <li class="item-quote">
                            <a href="">
                                <p>Thời gian và hoàn cảnh mang lại sự khôn ngoan</p>
                                <div class="infor-quote">
                                    <p class="view">
                                        <i class="fa-solid fa-eye"></i>
                                        <span class="quantity-view">22</span>
                                        <i class="fa-solid fa-thumbs-up"></i>
                                        <span class="quantity-like">10</span>
                                    </p>
                                    <p class="category">
                                        Ngạn ngữ Trung Quốc
                                    </p>
                                </div>
                            </a>
                        </li>
                        <li class="item-quote">
                            <a href="">
                                <p>Thời gian và hoàn cảnh mang lại sự khôn ngoan</p>
                                <div class="infor-quote">
                                    <p class="view">
                                        <i class="fa-solid fa-eye"></i>
                                        <span class="quantity-view">22</span>
                                        <i class="fa-solid fa-thumbs-up"></i>
                                        <span class="quantity-like">10</span>
                                    </p>
                                    <p class="category">
                                        Ngạn ngữ Trung Quốc
                                    </p>
                                </div>
                            </a>
                        </li>
                        <li class="item-quote">
                            <a href="">
                                <p>Thời gian và hoàn cảnh mang lại sự khôn ngoan</p>
                                <div class="infor-quote">
                                    <p class="view">
                                        <i class="fa-solid fa-eye"></i>
                                        <span class="quantity-view">22</span>
                                        <i class="fa-solid fa-thumbs-up"></i>
                                        <span class="quantity-like">10</span>
                                    </p>
                                    <p class="category">
                                        Ngạn ngữ Trung Quốc
                                    </p>
                                </div>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>

        <script>
            $('#list-suggest').owlCarousel({
                loop: true,
                margin: 10,
                nav: true,
                dots: false,
                navContainer: ".list-product-container .header-list #owl-nav-1",
                //navText: [$('#am-next'),$('#am-prev')],
                responsive: {
                    0: {
                        items: 1
                    },
                    600: {
                        items: 2
                    },
                    1000: {
                        items: 4
                    }
                }
            });

            $('#list-new').owlCarousel({
                loop: true,
                margin: 10,
                nav: true,
                dots: false,
                navContainer: ".list-product-container .header-list #owl-nav-2",
                //navText: [$('#am-next'),$('#am-prev')],
                responsive: {
                    0: {
                        items: 1
                    },
                    600: {
                        items: 2
                    },
                    1000: {
                        items: 4
                    }
                }
            });

            $('#list-story').owlCarousel({
                loop: true,
                margin: 10,
                nav: true,
                dots: false,
                navContainer: ".list-product-container .header-list #owl-nav-3",
                //navText: [$('#am-next'),$('#am-prev')],
                responsive: {
                    0: {
                        items: 1
                    },
                    600: {
                        items: 2
                    },
                    1000: {
                        items: 4
                    }
                }
            });

        </script>
        <script src="./../../resources/static/web/vendor/owlcarousel/owl.carousel.min.js"></script>
        <script src="./../../resources/static/web/vendor/jquery3.6.0.min.js"></script>
        <script src="./../../resources/static/web/vendor/bootstrap-5.1.3-dist/js/bootstrap.min.js"></script>
        <script src="./../../resources/static/web/vendor/fontawesome-free-6.1.1-web/js/all.js"></script>
</body>

</html>