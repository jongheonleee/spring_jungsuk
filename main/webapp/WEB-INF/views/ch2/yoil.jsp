<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	${myDate.year} <br>
    ${myDate.month} <br>
    ${myDate.day} <br>
</h1>

<P>  The time on the server is ${serverTime}. </P>
</body>
</html>