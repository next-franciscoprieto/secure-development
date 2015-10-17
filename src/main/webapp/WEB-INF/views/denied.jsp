<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Unauthorized: Access is denied</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/unauthorized.css'/>"/>
</head>
<body style="background-color: rgba(208, 223, 226, 0.4)">
    <div class="container">
        <h1 class="message">Unauthorized</h1>
        <img src="<c:url value='/resources/img/body.png'/>" alt="">
        <span class="eye"><img src="<c:url value='/resources/img/eye.gif'/>" alt=""></span>
           <span class="hand">
             <img src="<c:url value='/resources/img/hand.png'/>" alt="">
           </span>
    </div>
</body>