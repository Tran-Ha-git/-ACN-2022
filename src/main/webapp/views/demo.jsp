<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Demo</title>
        <link rel="stylesheet" href="/web/css/home.css">
</head>
<body>
    <h1>Demo</h1>
    <h3>STT</h3>
    <h2>${stt}</h2>
    <h3>Name</h3>
    <h2>${name}</h2> <br>
    <h2><%= request.getContextPath() %></h2> <br>
    <h2><%= request.getQueryString() %></h2> <br>
    <h2><%= request.getRequestURI() %></h2> <br>
    <h2><%= request.getServletPath() %></h2> <br>
     <h2><%= request.getHeaderNames() %></h2> <br>
    
    <%= request.getPathInfo() %>
    
</body>
</html>