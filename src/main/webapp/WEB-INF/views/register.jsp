<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6 lt8"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7 lt8"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8 lt8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<head>
	<meta charset="UTF-8" />
	<!-- <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">  -->
	<title>Register</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="Desarrollo Seguro" />
	<meta name="keywords" content="html5, css3, form, switch, animation, :target, pseudo-class" />
	<meta name="author" content="Codrops" />

    <meta name="_csrf_parameter" content="_csrf" />
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>

	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/demo.css'/>"/>
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/style.css'/>"/>
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/animate-custom.css'/>"/>
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/bootstrap.min.css'/>"/>

	<script src="<c:url value='/resources/js/jquery-1.10.2.min.js'/>"></script>
	<script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>

</head>
<body>
    <div class="container">
        <section>
            <div id="container_demo">
                <div id="wrapper">
                    <div id="register" class="animate form">
                        <%--<form id="registerForm"  method="POST" action="<c:url value='/register'/>?${_csrf.parameterName}=${_csrf.token}" autocomplete="on" enctype="multipart/form-data">--%>
                        <form id="registerForm"  method="POST" action="<c:url value='/register?_csrf=${_csrf.token}'/>" autocomplete="on" enctype="multipart/form-data">
                            <input type="hidden" name="${_csrf.parameterName}"   value="${_csrf.token}"/>

                            <h1> Register </h1>
                            <p>
                                <label for="name" class="uname" data-icon="u">Your name</label>
                                <input id="name" name="name" required="required" type="text" placeholder="John Doe" />
                            </p>
                            <p>
                                <label for="email" class="youmail" data-icon="e" > Your email</label>
                                <input id="email" name="email" required="required" type="email" placeholder="user@beeva.com"/>
                            </p>
                            <p>
                                <label for="pass" class="youpasswd" data-icon="p">Your password </label>
                                <input id="pass" name="pass" required="required" type="password" placeholder="eg. X8df!90EO"/>
                            </p>
                            <p>
                                <label for="pass1" class="youpasswd" data-icon="p">Please confirm your password </label>
                                <input id="pass1" name="pass1" required="required" type="password"  placeholder="eg. X8df!90EO"/>
                            </p>

                            <p>
                                <label class="control-label">Select file</label>
                                <input id="file" name="file" type="file" class="file" data-show-preview="false" >
                            </p>
                            <p class="signin button"><input type="submit" value="Register"/></p>
                            <p class="change_link">
                                Already a member ?
                                <a href="<c:url value='/login' />" class="to_register">log in </a>
                            </p>
                        </form>
                    </div>

                </div>
            </div>
        </section>
    </div>
</body>
</html>