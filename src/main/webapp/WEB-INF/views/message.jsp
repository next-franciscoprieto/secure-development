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
	<title>Message</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="Desarrollo Seguro" />
	<meta name="keywords" content="html5, css3, form, switch, animation, :target, pseudo-class" />
	<meta name="author" content="Codrops" />

	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/demo.css'/>"/>
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/style.css'/>"/>
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/animate-custom.css'/>"/>
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/bootstrap.min.css'/>"/>

	<script src="<c:url value='/resources/js/jquery-1.10.2.min.js'/>"></script>
	<script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>

</head>
<body>
    <div>
        <section>
            <div>
                <div id="wrapper" class="col-sm-offset-3 col-sm-6 col-md-offset-3 col-md-6 col-lg-offset-3 col-lg-6">
                    <div id="register" class="animate form">
                        <form id="messageForm"  method="POST" action="<c:url
                        value='/publish?${_csrf.parameterName}=${_csrf.token}'/>" autocomplete="on">

                            <h1> Publish message </h1>
                            <p>
                                <label for="title" class="title" data-icon="u">Your title</label>
                                <input id="title" name="title" required="required" type="text" placeholder="" />
                            </p>
                            <p>
                                <label for="message" class="body" data-icon="e"> Your message</label>
                                <textarea class="form-control" rows="7" id="message" name="message"></textarea>
                            </p>
                            <p class="signin button"><input type="submit" value="Publish"/></p>

                        </form>
                    </div>

                </div>
            </div>
        </section>
    </div>
</body>
</html>