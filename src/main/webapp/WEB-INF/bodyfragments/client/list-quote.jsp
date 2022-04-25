<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
							<a href="#"> 
								<img class="mx-auto rounded-full h-[150px] w-[150px] mt-3" src="${quote.thumbnail}" alt="" />
							</a>
							<div class="py-2 px-2">
								<p class="mb-3 font-normal text-center text-gray-700">${quote.content}</p>
							</div>
							<div class="py-2 px-2 bg-gray-50 font-medium">
								<p class="font-normal text-center text-gray-700">Author:<span class="text-blue-500"> ${quote.author.fullname}</span></p>
								<p class="font-normal text-center text-gray-700">Keyword: 
									<c:forEach items="${quote.quoteCategories}" var="category">
										<span  class="text-blue-500">${category.name }</span>
									</c:forEach>
								</p>
							</div>
							<div class="flex justify-center items-center mt-2">
								<button
									class="m-2 inline-flex items-center py-2 px-3 text-sm font-medium text-center text-white bg-blue-700 rounded-lg hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">Danh gia
								</button>
								<button
									class="mx-2 inline-flex items-center py-2 px-3 text-sm font-medium text-center text-white bg-blue-700 rounded-lg hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">Binh luan
								</button>
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