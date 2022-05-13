<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container-fluid p-0">
	<div class="pages-content">
		<div class="row">
			<div class="col-md-12">
				<div class="heading-container">
					<div class="bg-search"></div>
					<div class="heading-block">
						<h2>
							Chào mừng bạn đến với BookIT.vn, <br> kho sách chất lượng
							hàng đầu Việt Nam
						</h2>
						<form action="/books" method="GET">
							<input type="text" class="search-input input-field form-control"
								placeholder="Bạn thích sách gì nhỉ?" name="search">
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
			<div class="list-product-container">
				<div class="d-flex justify-content-between align-items-center">
					<h3>Đề xuất sách</h3>
					<div class="controls">
						<span class="suggest-button-prev"><i
							class="fa-solid fa-angle-left"></i></span> <span
							class="suggest-button-next"><i
							class="fa-solid fa-angle-right"></i></span>
					</div>
				</div>
				<hr />
				<div class="books">
					<div class="swiper suggest-book">
						<div class="swiper-wrapper">
							<c:forEach items="${suggest_books}" var="book">
								<div class="swiper-slide">
									<a href="/books/${book.slug}" class="book"><img
										src="${book.thumbnail}" alt="Not found" />
										<div class="category">
											<c:forEach items="${book.categories}" var="category"
												varStatus="loop">
												${category.name}<c:if
													test="${book.categories.size() - 1 != loop.index}">, </c:if>
											</c:forEach>
										</div>
										<div class="title">${book.name}</div> </a>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
			<div class="list-product-container">
				<div class="d-flex justify-content-between align-items-center">
					<h3>Sách mới nhất</h3>
					<div class="controls">
						<span class="new-button-prev"><i
							class="fa-solid fa-angle-left"></i></span> <span class="new-button-next"><i
							class="fa-solid fa-angle-right"></i></span>
					</div>
				</div>
				<hr />
				<div class="books">
					<div class="swiper new-book">
						<div class="swiper-wrapper">
							<c:forEach items="${top_newbooks}" var="book">
								<div class="swiper-slide">
									<a href="/books/${book.slug}" class="book"><img
										src="${book.thumbnail}" alt="Not found" />
										<div class="category">
											<c:forEach items="${book.categories}" var="category"
												varStatus="loop">
												${category.name}<c:if
													test="${book.categories.size() - 1 != loop.index}">, </c:if>
											</c:forEach>
										</div>
										<div class="title">${book.name}</div> </a>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
			<div class="list-product-container">
				<div class="d-flex justify-content-between align-items-center">
					<h3>Truyện hay nhất</h3>
					<div class="controls">
						<span class="manga-button-prev"><i
							class="fa-solid fa-angle-left"></i></span> <span
							class="manga-button-next"><i
							class="fa-solid fa-angle-right"></i></span>
					</div>
				</div>
				<hr />
				<div class="books">
					<div class="swiper manga-book">
						<div class="swiper-wrapper">
							<c:forEach items="${top_mangas}" var="book">
								<div class="swiper-slide">
									<a href="/books/${book.slug}" class="book"><img
										src="${book.thumbnail}" alt="Not found" />
										<div class="category">
											<c:forEach items="${book.categories}" var="category"
												varStatus="loop">
												${category.name}<c:if
													test="${book.categories.size() - 1 != loop.index}">, </c:if>
											</c:forEach>
										</div>
										<div class="title">${book.name}</div> </a>
								</div>
							</c:forEach>
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
						<li class="tab-item active" id="audio-tab">Sách nói</li>
						<li class="tab-item"  id="online-tab">Ebook</li>
					</ul>
					<div class="tab-content">
						<div class="top-range-content" id="audio-list">
							<c:forEach items="${top_audios}" var="audio" varStatus="loop">
								<div>
									<div class="top-range-item">
										<div class="item-left">
											<img src="${audio.thumbnail}" alt="Not found">
										</div>
										<div class="item-right">
											<h3 class="item-position">${loop.index + 1}</h3>
											<a href="/books/${audio.slug}"><h4 class="item-title">${audio.name}</h4></a>
											<p class="item-author">
												<i class="fa-solid fa-circle-user"></i> <span> <c:forEach
														items="${audio.authors}" var="author"
														varStatus="loopAuthor">
														<a href="/books?type=${type}">${author.fullname}<c:if
																test="${audio.authors.size() - 1 != loopAuthor.index}">, </c:if></a>
													</c:forEach>
												</span>
											</p>
											<p class="item-category">
												<i class="fa-solid fa-folder-closed"></i> <span> <c:forEach
														items="${audio.categories}" var="category"
														varStatus="loopCategory">
														<a href="/books?type=${type}">${category.name}<c:if
																test="${audio.categories.size() - 1 != loopCategory.index}">, </c:if></a>
													</c:forEach>
												</span>
											</p>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
						<div class="top-range-content non-active" id="online-list">
							<c:forEach items="${top_ebooks}" var="ebook" varStatus="loop">
								<div>
									<div class="top-range-item">
										<div class="item-left">
											<img src="${ebook.thumbnail}" alt="Not found">
										</div>
										<div class="item-right">
											<h3 class="item-position">${loop.index + 1}</h3>
											<a href="/books/${ebook.slug}"><h4 class="item-title">${ebook.name}</h4></a>
											<p class="item-author">
												<i class="fa-solid fa-circle-user"></i> <span> <c:forEach
														items="${ebook.authors}" var="author"
														varStatus="loopAuthor">
														<a href="/books?type=${type}">${author.fullname}<c:if
																test="${ebook.authors.size() - 1 != loopAuthor.index}">, </c:if></a>
													</c:forEach>
												</span>
											</p>
											<p class="item-category">
												<i class="fa-solid fa-folder-closed"></i> <span> <c:forEach
														items="${ebook.categories}" var="category"
														varStatus="loopCategory">
														<a href="/books?type=${type}">${category.name}<c:if
																test="${ebook.categories.size() - 1 != loopCategory.index}">, </c:if></a>
													</c:forEach>
												</span>
											</p>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>

			<div class="quote">
				<div class="title-quote">Danh ngôn hay</div>
				<ul class="list-quote">
					<c:forEach items="${top_quotes}" var="quote">
						<li class="item-quote"><a href="http://localhost:8080/quotes?q=${quote.content}">
								<p>${quote.content}</p>
								<div class="infor-quote">
									<p class="view">
										<i class="fa-solid fa-eye"></i> <span class="quantity-view">${quote.view}</span>
										<i class="fa-solid fa-thumbs-up"></i> <span
											class="quantity-like">${quote.commentQuotes.size()}</span>
									</p>
									<p class="category">
										<c:forEach items="${quote.quoteCategories}" var="category"
											varStatus="loop">
												${category.name}<c:if
												test="${book.categories.size() - 1 != loop.index}">, </c:if>
										</c:forEach>
									</p>
								</div>
						</a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
</div>