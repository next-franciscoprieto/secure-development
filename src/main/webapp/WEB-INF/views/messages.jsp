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
	<title>Messages</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="Secure development" />
	<meta name="keywords" content="html5, css3, form, switch, animation, :target, pseudo-class" />
	<meta name="author" content="Codrops" />

	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/bootstrap.min.css'/>"/>
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/font-awesome.min.css'/>"/>
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/animate.css'/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/structure.css'/>"/>

    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/style.css'/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/google-fonts.css'/>"/>

    <!--<link type="text/css" rel="stylesheet" href="http://fonts.googleapis.com/css?family=Varela">-->
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body style="overflow: visible;background-color: rgba(208, 223, 226, 0.4)">

<nav class="navbar navbar-default navbar-fixed-top navbar-shrink message_header">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header page-scroll">
            <span class="message_title">Secure Development</span>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-10">
            <ul class="nav navbar-nav navbar-right">
                <li class="hidden active">
                    <a href="#page-top"></a>
                </li>
                <li class="page-scroll">
                    <a aria-label="Left Align" href="<c:url value='/logout' />"><span class="glyphicon glyphicon-log-out"
                                                                       aria-hidden="true"></span></a>
                </li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container-fluid -->
</nav>

<!-- Preloader -->
<div id="preloader" style="display: none;">
    <div id="status" style="display: none;">&nbsp;</div>
</div>

<section id="contentbody">
    <div class="container">
        <div class="row">
            <!-- start left bar content -->
            <div class="col-sm-12 col-md-12 col-lg-12">
                <div class="row">
                    <div class="leftbar_content">

                        <div class="col-sm-2 col-sm-offset-10 col-md-offset-10 col-md-2 col-lg-offset-10 col-lg-2" style="visibility: visible; animation-name: fadeInDown;">
                            <input type="button" value="+" class="message_button" onclick="location='<c:url
                                    value='/message' />'">
                        </div>

                        <c:forEach var="message"  items="${messages}"  varStatus="status">
                            <div style="visibility: visible; animation-name: fadeInDown;" class="single_stuff wow fadeInDown animated animated">
                                <div class="single_sarticle_inner message_text">
                                    <span class="stuff_date">${message.month} <strong>${message.day}</strong></span>
                                    <a class="stuff_category" href="#">Technology</a>
                                    <div class="stuff_article_inner">
                                        <span class="stuff_image"><img width="75" height="75" class="img-circle"
                                                                      src="<c:url
                                                                      value='/images/'/>${message.image}"></span>

                                        <h2><a href="#">${message.title}</a></h2>
                                        <p>${message.body}</p>

                                    </div>
                                 </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<footer id="footer">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="footer_inner">
                    <p class="pull-right">Developed by Secure Development Group</p>
                </div>
            </div>
        </div>
    </div>
</footer>
<!-- ==================End content body section=============== -->

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="<c:url value='/resources/js/jquery-1.10.2.min.js'/>"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>
<!-- For content animatin  -->
<script src="<c:url value='/resources/js/wow.min.js'/>"></script>

<script src="<c:url value='/resources/js/custom.js'/>"></script>
</body>
</html>