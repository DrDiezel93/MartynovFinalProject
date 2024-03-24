<%@ tag pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tn"%>
<%@ attribute name="page1" required="false"%>
<%@ attribute name="page2" required="false"%>
<%@ attribute name="page3" required="false"%>
<%@ attribute name="page4" required="false"%>
<%@ attribute name="page5" required="false"%>
<%@ attribute name="page6" required="false"%>
<%@ attribute name="page7" required="false"%>
<%@ attribute name="page8" required="false"%>
<%@ attribute name="page9" required="false"%>
<!DOCTYPE html>
<html>
<head>
<title>StuDy.ru</title>
<!-- Custom Theme files -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="application/x-javascript">
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 



















</script>

<link href="css/bootstrap.css" type="text/css" rel="stylesheet"
	media="all">
<link href="css/style.css" type="text/css" rel="stylesheet" media="all">
<link rel="stylesheet" href="css/flexslider.css" type="text/css"
	media="screen" />
<link type="text/css" rel="stylesheet" href="css/JFFormStyle-1.css" />
<!-- js -->
<script src="js/jquery.min.js"></script>
<script src="js/modernizr.custom.js"></script>
<!-- //js -->
<!-- fonts -->
<link
	href='//fonts.googleapis.com/css?family=Roboto:400,100,100italic,300,300italic,400italic,500,700,500italic,700italic,900,900italic'
	rel='stylesheet' type='text/css'>
<link
	href='//fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic'
	rel='stylesheet' type='text/css'>
<!-- //fonts -->
<script type="text/javascript">
	$(document).ready(function() {
		$('#horizontalTab').easyResponsiveTabs({
			type : 'default', //Types: default, vertical, accordion           
			width : 'auto', //auto or any width like 600px
			fit : true
		// 100% fit in a container
		});
	});
</script>
<!--pop-up-->
<script src="js/menu_jquery.js"></script>
<!--//pop-up-->
</head>
<body>
	<div class="header">
		<div class="container">
			<div class="header-grids">
				<div class="logo">
					<h1>
						<a href="index.html"><span>Stu</span>Dy.ru</a>
					</h1>
				</div>
				<!--navbar-header-->
				<div class="header-dropdown">
					<div class="emergency-grid"></div>
					<div class="clearfix"></div>
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="nav-top">
				<div class="top-nav">
					<span class="menu"><img src="images/menu.png" alt="" /></span>
					<ul class="nav1">
						<li class="${page1}"><a href="mypage.html">Моя страница</a></li>
						<sec:authorize access="hasRole('ROLE_USER')">
							<li class="${page2}"><a href="mydocs.html">Мои документы</a></li>
						</sec:authorize>


						<sec:authorize access="hasAnyRole('ROLE_ANTIPLAGIATOR','ROLE_EXPERT')">
							<li class="${page3}"><a href="newdocs.html">Документы на
									проверку</a></li>
						</sec:authorize>
	<sec:authorize access="hasAnyRole('ROLE_USER','ROLE_ANTIPLAGIATOR','ROLE_EXPERT')">
												<li class="${page4}"><a href="finddocs.html">Поиск
								документов</a></li>
								</sec:authorize>
						<sec:authorize access="hasRole('ROLE_SECRETARY')">
							<li class="${page5}"><a href="admin.html">Администратор</a></li>
						</sec:authorize>
					</ul>
					<div class="clearfix"></div>
					<!-- script-for-menu -->
					<script>
						$("span.menu").click(function() {
							$("ul.nav1").slideToggle(300, function() {
								// Animation complete.
							});
						});
					</script>
					<!-- /script-for-menu -->
				</div>
				<div class="dropdown-grids">
					<div id="loginContainer">
						<a href="logout" id="logoutButton"><span>Выйти</span></a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="clearfix"></div>
	</div>

	<jsp:doBody />
	<!--//header-->

	<script defer src="js/jquery.flexslider.js"></script>
	<script src="js/easyResponsiveTabs.js" type="text/javascript"></script>
	<script src="js/jquery-ui.js"></script>
	<script type="text/javascript" src="js/script.js"></script>
</body>
</html>
