<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tn"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>
<tn:html page1="active">

<!-- banner -->
<div class="banner bus-banner">
	<!-- container -->
	<br>
	<div class="container">
		<c:if test="${not empty err}">
			<div class="alert alert-danger">${err}</div>
		</c:if>
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
					<h2>Укажите ваш электронный ящик</h2>
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
													<form action="/study/recoverypass.html" method="POST">
														<div class="form-group" hidden="true"></div>
														<div class="form-group">
															<label for="login" style="color: white">Вам на
																почту придет новый пароль.</label> <input type="text"
																class="form-control" id="log" name="email" value=""
																placeholder="Email">
														</div>
														<div class="date_btn">
															<input type="submit" value="Отправить...">
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
</tn:html>