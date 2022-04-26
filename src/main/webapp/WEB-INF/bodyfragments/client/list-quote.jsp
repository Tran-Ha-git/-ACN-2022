<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://unpkg.com/flowbite@1.4.3/dist/flowbite.min.css" />
<script src="https://cdn.tailwindcss.com"></script>
</head>
<body>
	<div class="container py-4">
		<div class="grid grid-cols-12 gap-4 mt-2">
			<div class="col-span-9">
				<div class="grid grid-cols-3 gap-4">
					<c:forEach items="${results.content }" var="quote">
						<div
							class="py-4 px-2 bg-white rounded-lg border border-gray-200 shadow-md flex flex-col items-center justify-between">
							<a href="#"> <img
								class="mx-auto rounded-full h-[150px] w-[150px] mt-3"
								src="${quote.thumbnail}" alt="" />
							</a>
							<div class="px-3 py-3 text-center">
								<p
									style="display: -webkit-box; max-width: 100%; -webkit-line-clamp: 3; -webkit-box-orient: vertical; overflow: hidden;">${quote.content }</p>
							</div>
							<div class="flex justify-center items-center mb-2">
								<div class="flex items-center text-grey-500 mr-3">
									<span class="mr-2 font-medium">${quote.view}</span>
									<svg class="w-5 h-5" fill="none" stroke="currentColor"
										viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
										<path stroke-linecap="round" stroke-linejoin="round"
											stroke-width="2" d="M15 12a3 3 0 11-6 0 3 3 0 016 0z"></path>
										<path stroke-linecap="round" stroke-linejoin="round"
											stroke-width="2"
											d="M2.458 12C3.732 7.943 7.523 5 12 5c4.478 0 8.268 2.943 9.542 7-1.274 4.057-5.064 7-9.542 7-4.477 0-8.268-2.943-9.542-7z"></path></svg>
								</div>
								<div class="flex items-center">
									<c:forEach begin="1" end="${quote.getAverageStar()}"
										varStatus="loop">
										<svg class="w-5 h-5 text-yellow-300" fill="currentColor"
											viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg">
										<path
												d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"></path>
										</svg>
									</c:forEach>
									<fmt:parseNumber var="intValue" value="${quote.getAverageStar()}" integerOnly="true"></fmt:parseNumber>
									<c:if
										test="${quote.getAverageStar()!=0 && quote.getAverageStar()!=intValue}">
										<div class="relative">
											<span class="absolute" style="clip-path: polygon(0 0, 50% 0, 50% 100%, 0% 100%);"> <svg class="w-5 h-5 text-yellow-300"
													fill="currentColor" viewBox="0 0 20 20"
													xmlns="http://www.w3.org/2000/svg">
											<path
														d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"></path>
											</svg>
											</span class="absolute"> <span> <svg class="w-5 h-5 text-gray-300"
													fill="currentColor" viewBox="0 0 20 20"
													xmlns="http://www.w3.org/2000/svg">
											<path
														d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"></path>
											</svg>
											</span>
										</div>
									</c:if>
									<c:forEach begin="1" end="${5-quote.getAverageStar()}"
										varStatus="loop">
										<svg class="w-5 h-5 text-gray-300" fill="currentColor"
											viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg">
										<path
												d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"></path>
										</svg>
									</c:forEach>
									<span
										class="bg-blue-100 text-blue-800 text-xs font-semibold mr-2 px-2.5 py-0.5 rounded dark:bg-blue-200 dark:text-blue-800 ml-3">${quote.getAverageStar() }</span>
								</div>
								<div class="flex items-center text-grey-500 ml-2">
									<svg class="w-5 h-5" fill="none" stroke="currentColor"
										viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
										<path stroke-linecap="round" stroke-linejoin="round"
											stroke-width="2"
											d="M12 4.354a4 4 0 110 5.292M15 21H3v-1a6 6 0 0112 0v1zm0 0h6v-1a6 6 0 00-9-5.197M13 7a4 4 0 11-8 0 4 4 0 018 0z"></path></svg>
									<span class="ml-2 font-medium">${quote.reviewQuotes.size()}</span>
								</div>
							</div>
							<div class="py-2 px-2 bg-gray-50 font-medium">
								<p class="font-normal text-center text-gray-700">
									Author:<span class="text-blue-500">
										${quote.author.fullname}</span>
								</p>
								<p class="font-normal text-center text-gray-700">
									Keyword:
									<c:forEach items="${quote.quoteCategories}" var="category">
										<span class="text-blue-500">${category.name }</span>
									</c:forEach>
								</p>
							</div>
							<div class="flex justify-center items-center mt-2">
								<button
									class="m-2 inline-flex items-center py-2 px-3 text-sm font-medium text-center text-white bg-blue-700 rounded-lg hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">Danh
									gia</button>
								<button
									class="mx-2 inline-flex items-center py-2 px-3 text-sm font-medium text-center text-white bg-blue-700 rounded-lg hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">Binh
									luan</button>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
			<div class="col-span-3">
				<aside class="w-full" aria-label="Sidebar">
					<div class="mb-4 w-full">
						<form class="w-full" method="get">
							<div class="relative mt-1">
								<div
									class="absolute inset-y-0 left-0 flex items-center pl-3 pointer-events-none">
									<svg class="w-5 h-5 text-gray-500" fill="currentColor"
										viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg">
										<path fill-rule="evenodd"
											d="M8 4a4 4 0 100 8 4 4 0 000-8zM2 8a6 6 0 1110.89 3.476l4.817 4.817a1 1 0 01-1.414 1.414l-4.816-4.816A6 6 0 012 8z"
											clip-rule="evenodd"></path></svg>
								</div>
								<input type="text" id="table-search"
									class="border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full pl-10 p-2.5 "
									placeholder="Search for items">
							</div>
						</form>
					</div>
					<div class="border overflow-y-auto py-4 px-3 rounded w-full">
						<div class="mb-4">
							<h3 class="text-center text-2xl font-medium text-blue-600">DANH
								MUC</h3>
						</div>
						<ul class="space-y-2">
							<c:forEach items="${categories}" var="category">
								<li><a href="?category=${category.id }"
									class="flex items-center text-blue-600 p-3 text-base font-normal border-b-2 border-blue-500 text-gray-900 rounded-lg hover:bg-blue-100">
										<svg class="w-5 h-5" fill="none" stroke="currentColor"
											viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
											<path stroke-linecap="round" stroke-linejoin="round"
												stroke-width="2" d="M9 5l7 7-7 7"></path></svg> <span
										class="ml-5 text-gray-900 font-medium">${category.name}</span>
								</a></li>
							</c:forEach>
						</ul>
					</div>
				</aside>
			</div>
		</div>
	</div>
	<script src="https://unpkg.com/flowbite@1.4.3/dist/flowbite.js"></script>
</body>
</html>