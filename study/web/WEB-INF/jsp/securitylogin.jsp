<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tn"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>
<tn:htmlui page1="active">

	<!-- banner -->
	<div class="banner bus-banner">
	<br>
		<!-- container -->
		<div class="container">
			<div class="col-md-4 banner-left">
				<section class="slider2">
					<div class="flexslider">
						<ul class="slides">
						</ul>
					</div>
				</section>
			</div>
			<div class="col-md-8 banner-right">
				<div class="sap_tabs">
					<div class="booking-info about-booking-info">
						<h2>Вы успешно сменили логин</h2>
					</div>
					<div id="horizontalTab"
						style="display: block; width: 100%; margin: 0px;">
						<!---->
						<div class="facts about-facts">
							<div class="booking-form">
								<link rel="stylesheet" href="css/jquery-ui.css" />
								<!---strat-date-piker---->
								<div class="online_reservation">
									<div class="b_room">
										<div class="booking_room">
											<div class="reservation">
												<ul>
													<div id="locationField">
														<li class="span1_of_1 desti"></li>
														<li class="span1_of_1 left desti"></li>
														<div class="clearfix"></div>
												</ul>
											</div>
											<div class="reservation">
												<ul>
													<li class="span1_of_1">
											</div>
											</li>
											<li class="span1_of_1 left"></li>

											<div class="clearfix"></div>
											</ul>
										</div>
										<div class="reservation">
											<ul>
												<li class="span1_of_3">
													<div class="date_btn">
														<c:url value="/j_security_check" var="href" />
														<form action="${href}" method="POST">
															<div class="form-group" hidden="true">
																<input type="text" class="form-control" id="login"
																	name="j_username" value="${j_username}" hidden="true">
															</div>
															<div class="form-group" hidden="true">
																<input type="password" class="form-control" id="passwd"
																	name="j_password" value="${j_password}" hidden="true">
															</div>
															<div class="date_btn">
																<input type="submit" value="Продолжить...">
															</div>
														</form>
													</div>
												</li>
												<div class="clearfix"></div>
											</ul>
										</div>
									</div>
									<div class="clearfix"></div>
								</div>
							</div>
							<!---->
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="clearfix"></div>
	</div>
	<!-- //container -->
	</div>
</tn:htmlui>