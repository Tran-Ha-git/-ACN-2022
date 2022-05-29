<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<main>
	<div class="container bookinfo">
		<div class="row my-4">
			<div class="col-lg-6 col-sm-12 px-4">
				<div class="bookinfo-thumbnail">
					<img src="${bookDTO.thumbnail}" alt="${bookDTO.name}" />
				</div>
				<h2 class="my-4">${bookDTO.name}</h2>
				<div class="d-flex justify-content-between bookinfo-row">
					<span>Tác giả: </span> 
					<span>
					<c:forEach items="${bookDTO.authors}" var="author" varStatus="loop">
						<a href="/books?author=${author.slug}">
							<span class="info-item">${author.fullname}</span>
							<c:if test="${loop.index != bookDTO.authors.size() - 1}"> <span class="mr-3">,</span> </c:if>
						</a>
					</c:forEach>
					</span> 
				</div>
				<div class="d-flex justify-content-between bookinfo-row">
					<span>Thể loại: </span> 
					<span>
					<c:forEach items="${bookDTO.categories}" var="category" varStatus="loop">
					<a href="/books?category_id=${category.id}">
						<span class="info-item">${category.name}</span>
						<c:if test="${loop.index != bookDTO.categories.size() - 1}"> <span class="mr-3">,</span> </c:if>
					</a> 
					</c:forEach>
					</span>
				</div>
				<div class="d-flex justify-content-between bookinfo-row">
					<span>Thời lượng: </span> 
					<c:if test="${bookDTO.audios.size() > 0}">
					<span>${bookDTO.audios.size()*18} phút</span>
					</c:if>
				</div>
				<div class="d-flex justify-content-between bookinfo-row">
					<span>Số chương: </span> 
					<c:if test="${bookDTO.onlines.size() > 0}">
					<span>${bookDTO.onlines.size()} chương</span>
					</c:if>
				</div>
				<div class="d-flex justify-content-between bookinfo-row">
					<span>Số trang: </span> 
					<c:if test="${bookDTO.onlines.size() > 0}">
					<span>${bookDTO.onlines.size()*48} trang</span>
					</c:if>
				</div>
				<div class="d-flex justify-content-between bookinfo-row">
					<span>Lượt xem: </span> <span>${bookDTO.view}</span>
				</div>
			</div>
			<div class="col-lg-6 col-sm-12 px-4">
				<h3>Giới thiệu sách</h3>
				<p>${bookDTO.description }</p>
				<div class="socials">
					<div class="social">
						<i class="fa-brands fa-facebook-f"></i>
					</div>
					<div class="social">
						<i class="fa-brands fa-twitter"></i>
					</div>
					<div class="social">
						<i class="fa-brands fa-pinterest-p"></i>
					</div>
					<div class="social">
						<i class="fa-brands fa-linkedin"></i>
					</div>
				</div>
				<div class="read-types">
					<c:if test="${bookDTO.audios.size() > 0}">
						<button onclick="window.location.href = '/audio/${bookDTO.slug}';">
							<span><i class="fa-solid fa-headphones-simple"></i></span> <span>Nghe Audio</span>
						</button>
					</c:if>
					<c:if test="${bookDTO.pdfs.size() > 0}">
					<button onclick="window.location.href = '/pdf-read/${book.slug}';">
						<span><i class="fa-solid fa-file-pdf"></i></span> <span>Đọc PDF</span>
					</button>
					</c:if>
					<c:if test="${bookDTO.onlines.size() > 0}">
						<button onclick="window.location.href = '/doc-online/${bookDTO.slug}?chapter=1';">
							<span><i class="fa-solid fa-book-open-reader"></i></span> <span>Đọc Online</span>
						</button>
					</c:if>
				</div>
			</div>
		</div>
	</div>
	<div class="container related_books">
		<div class="d-flex justify-content-between align-items-center">
			<h3>Sách tương tự</h3>
			<div class="controls">
				<span class="button-prev"><i class="fa-solid fa-angle-left"></i></span>
				<span class="button-next"><i class="fa-solid fa-angle-right"></i></span>
			</div>
		</div>
		<hr />
		<div class="books">
			<div class="swiper mySwiper">
				<div class="swiper-wrapper">
					<c:forEach items="${similarBooks}" var="similarBook" varStatus="loop">						
						<div class="swiper-slide">
							<a href="/books/${similarBook.slug}" class="book"> 
								<img src="${similarBook.thumbnail}" alt="Not found" />
								<div class="category">
									<c:forEach items="${similarBook.categories}" var="category" varStatus="loop">						
										<a href="/books?author=${category.slug}">
											<span class="info-item">${category.name}</span>
											<c:if test="${loop.index != similarBook.categories.size() - 1}">
												<span class="mr-3">,</span>
								    		</c:if>
										</a>
									</c:forEach>
								</div>
								<div class="title">${similarBook.name}</div>
							</a>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	<div class="container wrap-comment">
		<div
			class="comment-header d-flex justify-content-between align-items-center">
			<h3>Đánh giá sản phẩm</h3>
			<button id="comment-btn">Viết nhận xét</button>
		</div>
		<hr>
		<div
			class="rating d-lg-flex d-sm-block justify-content-between align-items-center">
			<div class="average d-flex align-items-center">
				<div class="score">${score}</div>
				<div class="star-rating" title="70%">
					<div class="back-stars">
						<i class="fa fa-star" aria-hidden="true"></i> 
						<i class="fa fa-star" aria-hidden="true"></i> 
						<i class="fa fa-star" aria-hidden="true"></i> 
						<i class="fa fa-star" aria-hidden="true"></i>
						<i class="fa fa-star" aria-hidden="true"></i>
						<div class="front-stars" style="width: ${score/5*100}%">
							<i class="fa fa-star" aria-hidden="true"></i> 
							<i class="fa fa-star" aria-hidden="true"></i> 
							<i class="fa fa-star" aria-hidden="true"></i> 
							<i class="fa fa-star" aria-hidden="true"></i>
							<i class="fa fa-star" aria-hidden="true"></i>
						</div>
					</div>
				</div>
				<div class="user">
					<span><i class="fa-solid fa-user"></i>&nbsp;</span>
					<span>
					<c:choose>
					  <c:when test="${totalReviews > 0}">${totalReviews}</c:when>
					  <c:otherwise>Chưa có đánh giá</c:otherwise>
					</c:choose>
					</span>
				</div>
			</div>
			<div class="filter d-flex align-items-center">
				<span>Lọc theo:</span>
				<c:forEach var="i" begin="1" end="5">
					<span onclick="filterStar(${6-i })" class="filter-star" id="filter-star-${6-i }"> 
						${6-i}  
						<i class="fa fa-star" aria-hidden="true"></i>
					</span>
				</c:forEach>
			</div>
		</div>
		
		<div class="comments">
		<input type="hidden" value="0" id ="page-comment">
		<input type="hidden" value="${comments.totalPages}" id ="total-page-comment">
		<input type="hidden" value="${bookDTO.slug}" id="slug">
		
		<c:forEach items="${comments.content}" var="comment" varStatus="loop">
			<div class="comment" id="comment-${comment.id}">
				<input type="hidden" value="${comment.star}" class ="star">
				<div class="review">
					<div class="avatar">
						<i class="fa-regular fa-user"></i>
					</div>
					<div class="content-comment">
						<div class="name">${comment.user.fullname}</div>
						<div class="join-date">Đã đăng vào ${comment.modTime}</div>
						<div class="evaluate d-flex"></div>
						<p>${comment.content}</p>
						<input type="hidden" value="${comment.id}" class ="commentId">
						<button>Trả lời</button>
					</div>
				</div>
				<div class="responses ">
				<c:forEach var = "response" items= "${comment.commentBooks}">
					<div class = "response">
						<div class="avatar">
							<i class="fa-regular fa-user"></i>
						</div>
						<div  class="content">
							<div class="name">${response.user.fullname}</div>
							<div class="join-date">Đã đăng vào ${response.modTime}</div>
							<p>${response.content}</p>
							<button hidden>Trả lời</button>
						</div>
					</div>
					<div class = "sub-response" hidden >
						<div class="avatar">
							<i class="fa-regular fa-user"></i>
						</div>
						<div  class="content">
							<div class="name">Hồ Cẩm</div>
							<div class="join-date">Đã đăng vào 22/03/2020</div>
							<p>không có gì</p>
						</div>
					</div>
				</c:forEach>
				</div>
			</div>
		</c:forEach>
			<c:if test="${comments.totalPages > 1}">
			<div class="d-flex justify-content-center show-more-div">
				<button id="show-more-btn">Hiển thị thêm bình luận</button>
			</div>
			</c:if>
		</div>
	</div>
	<div class="modal fade" id="star" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1" aria-labelledby="starLabel"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="starLabel">Đánh giá sách</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div class="rating">
						<input type="radio" name="rating" id="rating-5" value="5">
	                    <label for="rating-5"></label>
	                    <input type="radio" name="rating" id="rating-4" value="4">
	                    <label for="rating-4"></label>
	                    <input type="radio" name="rating" id="rating-3" value="3">
	                    <label for="rating-3"></label>
	                    <input type="radio" name="rating" id="rating-2" value="2">
	                    <label for="rating-2"></label>
	                    <input type="radio" name="rating" id="rating-1" value="1">
	                    <label for="rating-1"></label>
						<div class="emoji-wrapper">
							<div class="emoji">
								<svg class="rating-0" xmlns="http://www.w3.org/2000/svg"
									viewBox="0 0 512 512">
                  <circle cx="256" cy="256" r="256" fill="#ffd93b" />
                  <path
										d="M512 256c0 141.44-114.64 256-256 256-80.48 0-152.32-37.12-199.28-95.28 43.92 35.52 99.84 56.72 160.72 56.72 141.36 0 256-114.56 256-256 0-60.88-21.2-116.8-56.72-160.72C474.8 103.68 512 175.52 512 256z"
										fill="#f4c534" />
                  <ellipse
										transform="scale(-1) rotate(31.21 715.433 -595.455)"
										cx="166.318" cy="199.829" rx="56.146" ry="56.13" fill="#fff" />
                  <ellipse transform="rotate(-148.804 180.87 175.82)"
										cx="180.871" cy="175.822" rx="28.048" ry="28.08"
										fill="#3e4347" />
                  <ellipse transform="rotate(-113.778 194.434 165.995)"
										cx="194.433" cy="165.993" rx="8.016" ry="5.296" fill="#5a5f63" />
                  <ellipse
										transform="scale(-1) rotate(31.21 715.397 -1237.664)"
										cx="345.695" cy="199.819" rx="56.146" ry="56.13" fill="#fff" />
                  <ellipse transform="rotate(-148.804 360.25 175.837)"
										cx="360.252" cy="175.84" rx="28.048" ry="28.08" fill="#3e4347" />
                  <ellipse
										transform="scale(-1) rotate(66.227 254.508 -573.138)"
										cx="373.794" cy="165.987" rx="8.016" ry="5.296" fill="#5a5f63" />
                  <path
										d="M370.56 344.4c0 7.696-6.224 13.92-13.92 13.92H155.36c-7.616 0-13.92-6.224-13.92-13.92s6.304-13.92 13.92-13.92h201.296c7.696.016 13.904 6.224 13.904 13.92z"
										fill="#3e4347" />
                </svg>
								<svg class="rating-1" xmlns="http://www.w3.org/2000/svg"
									viewBox="0 0 512 512">
                  <circle cx="256" cy="256" r="256" fill="#ffd93b" />
                  <path
										d="M512 256A256 256 0 0 1 56.7 416.7a256 256 0 0 0 360-360c58.1 47 95.3 118.8 95.3 199.3z"
										fill="#f4c534" />
                  <path
										d="M328.4 428a92.8 92.8 0 0 0-145-.1 6.8 6.8 0 0 1-12-5.8 86.6 86.6 0 0 1 84.5-69 86.6 86.6 0 0 1 84.7 69.8c1.3 6.9-7.7 10.6-12.2 5.1z"
										fill="#3e4347" />
                  <path
										d="M269.2 222.3c5.3 62.8 52 113.9 104.8 113.9 52.3 0 90.8-51.1 85.6-113.9-2-25-10.8-47.9-23.7-66.7-4.1-6.1-12.2-8-18.5-4.2a111.8 111.8 0 0 1-60.1 16.2c-22.8 0-42.1-5.6-57.8-14.8-6.8-4-15.4-1.5-18.9 5.4-9 18.2-13.2 40.3-11.4 64.1z"
										fill="#f4c534" />
                  <path
										d="M357 189.5c25.8 0 47-7.1 63.7-18.7 10 14.6 17 32.1 18.7 51.6 4 49.6-26.1 89.7-67.5 89.7-41.6 0-78.4-40.1-82.5-89.7A95 95 0 0 1 298 174c16 9.7 35.6 15.5 59 15.5z"
										fill="#fff" />
                  <path
										d="M396.2 246.1a38.5 38.5 0 0 1-38.7 38.6 38.5 38.5 0 0 1-38.6-38.6 38.6 38.6 0 1 1 77.3 0z"
										fill="#3e4347" />
                  <path
										d="M380.4 241.1c-3.2 3.2-9.9 1.7-14.9-3.2-4.8-4.8-6.2-11.5-3-14.7 3.3-3.4 10-2 14.9 2.9 4.9 5 6.4 11.7 3 15z"
										fill="#fff" />
                  <path
										d="M242.8 222.3c-5.3 62.8-52 113.9-104.8 113.9-52.3 0-90.8-51.1-85.6-113.9 2-25 10.8-47.9 23.7-66.7 4.1-6.1 12.2-8 18.5-4.2 16.2 10.1 36.2 16.2 60.1 16.2 22.8 0 42.1-5.6 57.8-14.8 6.8-4 15.4-1.5 18.9 5.4 9 18.2 13.2 40.3 11.4 64.1z"
										fill="#f4c534" />
                  <path
										d="M155 189.5c-25.8 0-47-7.1-63.7-18.7-10 14.6-17 32.1-18.7 51.6-4 49.6 26.1 89.7 67.5 89.7 41.6 0 78.4-40.1 82.5-89.7A95 95 0 0 0 214 174c-16 9.7-35.6 15.5-59 15.5z"
										fill="#fff" />
                  <path
										d="M115.8 246.1a38.5 38.5 0 0 0 38.7 38.6 38.5 38.5 0 0 0 38.6-38.6 38.6 38.6 0 1 0-77.3 0z"
										fill="#3e4347" />
                  <path
										d="M131.6 241.1c3.2 3.2 9.9 1.7 14.9-3.2 4.8-4.8 6.2-11.5 3-14.7-3.3-3.4-10-2-14.9 2.9-4.9 5-6.4 11.7-3 15z"
										fill="#fff" />
                </svg>
								<svg class="rating-2" xmlns="http://www.w3.org/2000/svg"
									viewBox="0 0 512 512">
                  <circle cx="256" cy="256" r="256" fill="#ffd93b" />
                  <path
										d="M512 256A256 256 0 0 1 56.7 416.7a256 256 0 0 0 360-360c58.1 47 95.3 118.8 95.3 199.3z"
										fill="#f4c534" />
                  <path
										d="M336.6 403.2c-6.5 8-16 10-25.5 5.2a117.6 117.6 0 0 0-110.2 0c-9.4 4.9-19 3.3-25.6-4.6-6.5-7.7-4.7-21.1 8.4-28 45.1-24 99.5-24 144.6 0 13 7 14.8 19.7 8.3 27.4z"
										fill="#3e4347" />
                  <path
										d="M276.6 244.3a79.3 79.3 0 1 1 158.8 0 79.5 79.5 0 1 1-158.8 0z"
										fill="#fff" />
                  <circle cx="340" cy="260.4" r="36.2" fill="#3e4347" />
                  <g fill="#fff">
                    <ellipse transform="rotate(-135 326.4 246.6)"
										cx="326.4" cy="246.6" rx="6.5" ry="10" />
                    <path
										d="M231.9 244.3a79.3 79.3 0 1 0-158.8 0 79.5 79.5 0 1 0 158.8 0z" />
                  </g>
                  <circle cx="168.5" cy="260.4" r="36.2" fill="#3e4347" />
                  <ellipse transform="rotate(-135 182.1 246.7)"
										cx="182.1" cy="246.7" rx="10" ry="6.5" fill="#fff" />
                </svg>
								<svg class="rating-3" xmlns="http://www.w3.org/2000/svg"
									viewBox="0 0 512 512">
            <circle cx="256" cy="256" r="256" fill="#ffd93b" />
            <path
										d="M407.7 352.8a163.9 163.9 0 0 1-303.5 0c-2.3-5.5 1.5-12 7.5-13.2a780.8 780.8 0 0 1 288.4 0c6 1.2 9.9 7.7 7.6 13.2z"
										fill="#3e4347" />
            <path
										d="M512 256A256 256 0 0 1 56.7 416.7a256 256 0 0 0 360-360c58.1 47 95.3 118.8 95.3 199.3z"
										fill="#f4c534" />
            <g fill="#fff">
              <path
										d="M115.3 339c18.2 29.6 75.1 32.8 143.1 32.8 67.1 0 124.2-3.2 143.2-31.6l-1.5-.6a780.6 780.6 0 0 0-284.8-.6z" />
              <ellipse cx="356.4" cy="205.3" rx="81.1" ry="81" />
            </g>
            <ellipse cx="356.4" cy="205.3" rx="44.2" ry="44.2"
										fill="#3e4347" />
            <g fill="#fff">
              <ellipse transform="scale(-1) rotate(45 454 -906)"
										cx="375.3" cy="188.1" rx="12" ry="8.1" />
              <ellipse cx="155.6" cy="205.3" rx="81.1" ry="81" />
            </g>
            <ellipse cx="155.6" cy="205.3" rx="44.2" ry="44.2"
										fill="#3e4347" />
            <ellipse transform="scale(-1) rotate(45 454 -421.3)"
										cx="174.5" cy="188" rx="12" ry="8.1" fill="#fff" />
          </svg>
								<svg class="rating-4" xmlns="http://www.w3.org/2000/svg"
									viewBox="0 0 512 512">
                  <circle cx="256" cy="256" r="256" fill="#ffd93b" />
                  <path
										d="M512 256A256 256 0 0 1 56.7 416.7a256 256 0 0 0 360-360c58.1 47 95.3 118.8 95.3 199.3z"
										fill="#f4c534" />
                  <path
										d="M232.3 201.3c0 49.2-74.3 94.2-74.3 94.2s-74.4-45-74.4-94.2a38 38 0 0 1 74.4-11.1 38 38 0 0 1 74.3 11.1z"
										fill="#e24b4b" />
                  <path
										d="M96.1 173.3a37.7 37.7 0 0 0-12.4 28c0 49.2 74.3 94.2 74.3 94.2C80.2 229.8 95.6 175.2 96 173.3z"
										fill="#d03f3f" />
                  <path
										d="M215.2 200c-3.6 3-9.8 1-13.8-4.1-4.2-5.2-4.6-11.5-1.2-14.1 3.6-2.8 9.7-.7 13.9 4.4 4 5.2 4.6 11.4 1.1 13.8z"
										fill="#fff" />
                  <path
										d="M428.4 201.3c0 49.2-74.4 94.2-74.4 94.2s-74.3-45-74.3-94.2a38 38 0 0 1 74.4-11.1 38 38 0 0 1 74.3 11.1z"
										fill="#e24b4b" />
                  <path
										d="M292.2 173.3a37.7 37.7 0 0 0-12.4 28c0 49.2 74.3 94.2 74.3 94.2-77.8-65.7-62.4-120.3-61.9-122.2z"
										fill="#d03f3f" />
                  <path
										d="M411.3 200c-3.6 3-9.8 1-13.8-4.1-4.2-5.2-4.6-11.5-1.2-14.1 3.6-2.8 9.7-.7 13.9 4.4 4 5.2 4.6 11.4 1.1 13.8z"
										fill="#fff" />
                  <path
										d="M381.7 374.1c-30.2 35.9-75.3 64.4-125.7 64.4s-95.4-28.5-125.8-64.2a17.6 17.6 0 0 1 16.5-28.7 627.7 627.7 0 0 0 218.7-.1c16.2-2.7 27 16.1 16.3 28.6z"
										fill="#3e4347" />
                  <path
										d="M256 438.5c25.7 0 50-7.5 71.7-19.5-9-33.7-40.7-43.3-62.6-31.7-29.7 15.8-62.8-4.7-75.6 34.3 20.3 10.4 42.8 17 66.5 17z"
										fill="#e24b4b" />
                </svg>
								<svg class="rating-5" xmlns="http://www.w3.org/2000/svg"
									viewBox="0 0 512 512">
                  <g fill="#ffd93b">
                    <circle cx="256" cy="256" r="256" />
                    <path
										d="M512 256A256 256 0 0 1 56.8 416.7a256 256 0 0 0 360-360c58 47 95.2 118.8 95.2 199.3z" />
                  </g>
                  <path
										d="M512 99.4v165.1c0 11-8.9 19.9-19.7 19.9h-187c-13 0-23.5-10.5-23.5-23.5v-21.3c0-12.9-8.9-24.8-21.6-26.7-16.2-2.5-30 10-30 25.5V261c0 13-10.5 23.5-23.5 23.5h-187A19.7 19.7 0 0 1 0 264.7V99.4c0-10.9 8.8-19.7 19.7-19.7h472.6c10.8 0 19.7 8.7 19.7 19.7z"
										fill="#e9eff4" />
                  <path
										d="M204.6 138v88.2a23 23 0 0 1-23 23H58.2a23 23 0 0 1-23-23v-88.3a23 23 0 0 1 23-23h123.4a23 23 0 0 1 23 23z"
										fill="#45cbea" />
                  <path
										d="M476.9 138v88.2a23 23 0 0 1-23 23H330.3a23 23 0 0 1-23-23v-88.3a23 23 0 0 1 23-23h123.4a23 23 0 0 1 23 23z"
										fill="#e84d88" />
                  <g fill="#38c0dc">
                    <path
										d="M95.2 114.9l-60 60v15.2l75.2-75.2zM123.3 114.9L35.1 203v23.2c0 1.8.3 3.7.7 5.4l116.8-116.7h-29.3z" />
                  </g>
                  <g fill="#d23f77">
                    <path
										d="M373.3 114.9l-66 66V196l81.3-81.2zM401.5 114.9l-94.1 94v17.3c0 3.5.8 6.8 2.2 9.8l121.1-121.1h-29.2z" />
                  </g>
                  <path
										d="M329.5 395.2c0 44.7-33 81-73.4 81-40.7 0-73.5-36.3-73.5-81s32.8-81 73.5-81c40.5 0 73.4 36.3 73.4 81z"
										fill="#3e4347" />
                  <path
										d="M256 476.2a70 70 0 0 0 53.3-25.5 34.6 34.6 0 0 0-58-25 34.4 34.4 0 0 0-47.8 26 69.9 69.9 0 0 0 52.6 24.5z"
										fill="#e24b4b" />
                  <path
										d="M290.3 434.8c-1 3.4-5.8 5.2-11 3.9s-8.4-5.1-7.4-8.7c.8-3.3 5.7-5 10.7-3.8 5.1 1.4 8.5 5.3 7.7 8.6z"
										fill="#fff" opacity=".2" />
                </svg>
							</div>
						</div>
					</div>
				<br>
                <p class="text-center text-success fw-bold fs-5 ms-1" id="label-rating">Hãy chọn đánh giá của bạn</p>
				</div>
				<div class="modal-footer rating-btn">
					<button type="button" data-bs-toggle="modal"
						data-bs-target="#comment" disabled id="review-btn">OK</button>
					<button type="button" data-bs-toggle="modal"
						data-bs-target="#comment">Bỏ qua</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="comment" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="staticBackdropLabel">Bình luận sách</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<textarea placeholder="Bạn nghĩ sao về cuốn sách này?" required="required" id="content-comment-txt" ></textarea>
				</div>
				<div class="modal-footer comment-btn">
					<button data-bs-toggle="modal" data-bs-target="#star" id="show-review-btn"">Đánh giá</button>
					<button id="save-comment-btn">OK</button>
					<button data-bs-dismiss="modal">Hủy</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="response" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 class="modal-title" id="staticBackdropLabel">Gửi phản hồi</h5>
	                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	            </div>
	            <div class="modal-body">
	            	<input type="hidden" value="0" id="review-id">
	                <textarea placeholder="Phản hồi của bạn?" required="required" id="content-response"></textarea>
	            </div>
	            <div class="modal-footer comment-btn">
	            	<input type="hidden" value="0" class ="commentId" id="response-comment-id">
	            	<button type="button" id="response-btn">Gửi</button>
					<button type="button" data-bs-dismiss="modal">Hủy</button>
				</div>
			</div>
		</div>
	</div>
</main>
<div id="modal-info" class="modal modal-message modal-info fade" style="display: none;" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <i class="fa fa-info-circle"></i>
                </div>
                <div class="modal-title">Thông báo</div>
                <div class="modal-body">Vui lòng đăng nhập!</div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-info" data-bs-dismiss="modal">OK</button>
                </div>
            </div> <!-- / .modal-content -->
        </div> <!-- / .modal-dialog -->
 </div>
<script src="./../vendor/jquery/jquery3.6.0.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
$("#comment-btn").click(function(){
	 $.ajax({url: "/api/book/checkLogin", success: function(result){
		   if(!result){
			   alertLogin();
		   }else{
			   checkReviewed();
			  
		   }
	}});
});

function checkReviewed(){
	let slug = $("#slug").val();
	$.ajax({
		type: 'GET',
		url: '/api/book/'+slug+'/checkReview',
		success: function(response){
			console.log(response)
			if(response){
				$("#star").modal("hide");
				$("#show-review-btn").hide()
				$('.rating input[type="radio"][name="rating"]:checked').prop('checked', false);
				$("#comment").modal("show");
			}else{
				 $('#star').modal('show');
			}
		},
	});
}
function alertLogin(){
	 $('#modal-info').modal('show');
	$('#star').modal('hide');
}

$(document).ready(function () {
	$(".comment input[type=hidden].star").each(function(){
		let star = parseInt($(this).val());
		showStar($(this), star);
	});
});
function showStar(ele, star){
	if(star>0){
		let evaluateHtml =`<div class="c-green">`;
		let quote ='';
		switch(star) {
			case 1:
				quote ='Không thích';
			break;
			case 2:
				quote ='Tạm được';
			break;
			case 3:
				quote ='Hài lòng';
			break;
			case 4:
				quote ='Rất hay';
			break;
			case 5:
				quote ='Quá tuyệt vời';
			break;	  
			 default:
				 quote ='';
		}  
		evaluateHtml +=`<b>`+quote+`</b> </div> <div>`;
		for(let i=0; i<star; i++){
			evaluateHtml+=`<span class="c-yellow"><i class="fa fa-star" aria-hidden="true"></i></span>`
		}
		for(let i=star; i<5; i++){
			evaluateHtml+=`<span><i class="fa fa-star" aria-hidden="true"></i></span>`
		}
		evaluateHtml +=`</div> </div>`;
		ele.siblings(".review").children(".content-comment").children(".evaluate").html(evaluateHtml);
	}
};
$(document).ready(function () {
	$('.rating input[type="radio"][name="rating"]').on('change', function() {
		let rating =parseInt( $('.rating input[type="radio"][name="rating"]:checked').val());
		let label ="Hãy chọn đánh giá của bạn!"
		
		switch(rating) {
		  case 1:
			  label ="Không thích"
		    break;
		  case 2:
			  label ="Tạm được"
		    break;
		  case 3:
			  label ="Hài lòng"
			    break;
		  case 4:
			  label ="Rất hay"
		  break;
		  case 5:
			  label ="Quá tuyệt vời!"
			break;
		default:
			label ="Hãy chọn đánh giá của bạn!"
		}
		$("#label-rating").html(label);
		$("#review-btn").removeAttr('disabled');
	});
});
let arrFilterStar= [];
function noFilterStar(){
	if(arrFilterStar.length==0){
		$(".comment").each(function () {
	   		$(this).css('display', 'block')
		});
		$(".filter-star").each(function () {
	   		$(this).children().removeClass("c-yellow");
		});
	}
}
function filterStar (star){	
	if($("#filter-star-"+star).children().hasClass( "c-yellow" )){
		arrFilterStar.splice( $.inArray(star, arrFilterStar), 1 );
		if(arrFilterStar.length==0) {
			noFilterStar();
			return;
		}
		$("#filter-star-"+star).children().removeClass("c-yellow");
		$(".comment input.star").each(function () {
		   	if($.inArray(parseInt($(this).val()), arrFilterStar)!=-1){
		   		$(this).parent(".comment").css('display', 'block')
		   	}else{
		   		$(this).parent(".comment").css('display', 'none')
		   	}
		});
	}else{
		arrFilterStar.push(star);
		$("#filter-star-"+star).children().addClass("c-yellow");
		$(".comment input.star").each(function () {
			if($.inArray(parseInt($(this).val()), arrFilterStar)==-1){
		   		$(this).parent(".comment").css('display', 'none')
		   	}else{
		   		$(this).parent(".comment").css('display', 'block')
		   	}
		});
	}
}

$(document).ready(function () {
	$("#save-comment-btn").click(function(e) {
		e.preventDefault();
		saveComment();
	});
});

function saveComment(){
	let slug = $("#slug").val();
	let rating =parseInt( $('.rating input[type="radio"][name="rating"]:checked').val());
	if(isNaN(rating)) {
		rating = 0;
	}
	let content=$("#content-comment-txt").val();
	$.ajax({
		type: 'POST',
		url: '/api/book/'+slug+'/comment',
	    dataType: 'json',
	    data: "star="+rating+"&content="+content,
	    complete: function(response){
	    	let commentHtml=``;
	    	let value = response.responseJSON
	    	let star = parseInt(value.star);

	    	commentHtml +=`
	    	<div class="comment" id="comment-`+ value.id+`">
				<input type="hidden" value="`+value.star+`" class ="star">
				<div class="review">
					<div class="avatar">
						<i class="fa-regular fa-user"></i>
					</div>
					<div class="content-comment">
						<div class="name">`+value.user.fullname+`</div>
						<div class="join-date">Đã đăng vào `+value.modTime+`</div>
						<div class="evaluate d-flex"></div>
						<p>`+value.content+`</p>
						<input type="hidden" value="`+value.id+`" class ="commentId">
						<button>Trả lời</button>
					</div>
				</div>
				<div class="responses "></div>
			</div>`;
	    	$(".comments").prepend(commentHtml);
	    	
	    	$(".comment input[type=hidden].star").each(function(){
				let star = parseInt($(this).val());
				showStar($(this), star);
			});
			$("#comment").modal("hide");
			$("#content-comment-txt").val("");
			addEventButtonResponse();
	    },
	});
}
$(document).ready(function () {
	$("#show-more-btn").click(function(e) {
		e.preventDefault();
		
		arrFilterStar=[]
		noFilterStar();
		let slug = $("#slug").val();

		let page = parseInt($("input#page-comment").val());
		$("input#page-comment").val(page +1);
		page = parseInt($( "input#page-comment" ).val());
		
		if($("input#total-page-comment").val() < page + 2 ) {
			 $( "#show-more-btn" ).hide()
		}
		
		$.ajax({
	        type: "GET",
	        url: "/api/book/"+slug+"/more-review/"+page,
	        complete: function(result) {
	        	let data = result.responseJSON;
	        	let commentHtml=``;
	        	$.each(data, function( index, value ) {
			    	let star = parseInt(value.star);
	
			    	commentHtml +=`
			    	<div class="comment" id="comment-`+ value.id+`">
						<input type="hidden" value="`+value.star+`" class ="star">
						<div class="review">
							<div class="avatar">
								<i class="fa-regular fa-user"></i>
							</div>
							<div class="content-comment">
								<div class="name">`+value.user.fullname+`</div>
								<div class="join-date">Đã đăng vào `+value.modTime+`</div>
								<div class="evaluate d-flex"></div>
								<p>`+value.content+`</p>
								<input type="hidden" value="`+value.id+`" class ="commentId">
								<button>Trả lời</button>
							</div>
						</div>
						<div class="responses ">`
						let responses = data[index].commentBooks;
						
						$.each(responses, function( i, v ) {
						  let response = v;
							commentHtml+=`
							<div class = "response">
								<div class="avatar">
									<i class="fa-regular fa-user"></i>
								</div>
								<div  class="content">
									<div class="name">`+response.user.fullname+`</div>
									<div class="join-date">Đã đăng vào `+response.modTime+`</div>
									<p>`+response.content+`</p>
								</div>
							</div>
							`
						});
												
			commentHtml+=`</div>
					</div>`;
	        	});
	        	$(commentHtml).insertBefore( ".show-more-div" );
				$(".comment input[type=hidden].star").each(function(){
					let star = parseInt($(this).val());
					showStar($(this), star);
				});
				addEventButtonResponse();
	        },
		}); 
	});
});
$(document).ready(function () {
	addEventButtonResponse();
});

$(document).ready(function () {
	$("#response-btn").click(function(e) {
		let slug = $("#slug").val();
		let commentId = $("#response-comment-id").val();
		let content = $("#content-response").val();
		$.ajax({
	        type: "POST",
	        url: "/api/book/"+slug+"/response/"+commentId,
	        data: "content="+content,
	        complete: function(result) {
	        	let data = result.responseJSON;
	        	let commentHtml=``;
	        	commentHtml+=`
	        <div class = "response">
				<div class="avatar">
					<i class="fa-regular fa-user"></i>
				</div>
				<div  class="content">
					<div class="name">`+data.user.fullname+`</div>
					<div class="join-date">Đã đăng vào `+data.modTime+`</div>
					<p>`+data.content+`</p>
				</div>
			</div>`;
	        	let item= "#comment-"+commentId;
	        	$(item).children(".responses").append(commentHtml);
	        	$("#content-response").val("");
	        	$("#response").modal("hide");
	        },
		});
	});
});
function addEventButtonResponse(){
	$(".comment .review .content-comment button").click(function(e) {
		let commentId = $(this).siblings("input[type=hidden].commentId").val();
		$("#response-comment-id").val(commentId);
		$("#response").modal("show");
	});
}
</script>