<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" type="text/css"
	href="/css/admin/header-sidebar.css" />
<link rel="stylesheet" type="text/css"
	href="/<tiles:insertAttribute name="style" />">

<link rel="stylesheet"
	href="/vendor/fontawesome-free-6.1.1-web/css/all.min.css">
<title><tiles:getAsString name="title" /></title>


<title>Book Management</title>
</head>
<body>
	<div>
		<tiles:insertAttribute name="header" />

		<div class="container">
			<tiles:insertAttribute name="sidebar" />
			<tiles:insertAttribute name="body" />
		</div>
	</div>
</body>
</html>