<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tn"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>
<tn:html page2="active">
<div class="banner about-banner">
	<br>
	<div class="container">
		<div class="col-md-6">
			<div class="sap_tabs">
				<div class="booking-info about-booking-info">
					<h2>Студентам</h2>
				</div>
				<div id="horizontalTab"
					style="display: block; width: 100%; margin: 0px;">
					<!---->
					<div class="facts about-facts">
						<div class="booking-form">
							<link rel="stylesheet" href="css/jquery-ui.css" />
							<script>
								$(function() {
									$("#datepicker,#datepicker1").datepicker();
								});
							</script>

							<div class="online_reservation">
								<div class="b_room">
									<div class="booking_room">
										<div class="reservation">
											<ul>
												<li class="span1_of_1">
													<h5>1. Загрузите требуемую работу</h5>
													<div class="book_date">
														<br> <span class="starTextLabel">Просто
															загрузите нужную работу,соблюдая все </span><span
															class="starTextLabel"> требования и правила к оформлению.</span>
													</div>
												</li>
												<div class="clearfix"></div>
											</ul>
										</div>
										<div class="reservation">
											<ul>
												<li class="span1_of_1">
													<h5>2. Дождитесь результатов проверки</h5>
													<div class="book_date">
														<span class="starTextLabel">Дождитесь момента, когда все преподватели <br> проверят вашу работу и оставят отчёты. </span>
													</div>
												</li>
												<div class="clearfix"></div>
											</ul>
										</div>
										<div class="reservation">
											<ul>
												<li class="span1_of_1">
													<h5>3. Защита</h5>
													<div class="book_date">
														<span class="starTextLabel">В назначенную дату явитесь на защиту работы.</span>
													</div>
												</li>

												<div class="clearfix"></div>
											</ul>
										</div>
									</div>
								</div>
								<!---->
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="sap_tabs">
				<div class="booking-info about-booking-info">
					<h2>Преподователям</h2>
				</div>
				<div id="horizontalTab"
					style="display: block; width: 100%; margin: 0px;">
					<!---->
					<div class="facts about-facts">
						<div class="booking-form">
							<link rel="stylesheet" href="css/jquery-ui.css" />
							<script>
								$(function() {
									$("#datepicker,#datepicker1").datepicker();
								});
							</script>

							<div class="online_reservation">
								<div class="b_room">
									<div class="booking_room">
										<div class="reservation">
											<ul>
												<li class="span1_of_1">
													<h5>Оперативность</h5>
													<div class="book_date">
														<br> <span class="starTextLabel">Оперативно реагируйте на новые загруженные работы, чтобы своевремнно устранить все недочеты студентов.</span>
													</div>
												</li>
												<div class="clearfix"></div>
											</ul>
										</div>
										
									</div>
								</div>
								<!---->
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="clearfix"></div>

	</div>
</div>
</tn:html>