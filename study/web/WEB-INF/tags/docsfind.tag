<%@ tag pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:useBean id="date" class="java.util.Date" />
<!-- banner -->
<div class="banner bus-banner">
	<!-- container -->
	<br>
	<div class="container">
		<div class="col-md-4">
			<section class="slider2">
				<div class="flexslider">
					<ul class="slides">
						<li>
							<div class="slider-info">
								<img src="images/1.jpg" alt="" height="265">
							</div>
						</li>
					</ul>
				</div>
			</section>
			<!--FlexSlider-->
		</div>
		<div class="col-md-8">
			<div class="sap_tabs">
				<div class="booking-info about-booking-info">
					<h2>Поиск документа</h2>
				</div>
				<div id="horizontalTab"
					style="display: block; width: 100%; margin: 0px;">
					<!---->
					<div class="facts about-facts">
						<div class="booking-form">
							<link rel="stylesheet" href="css/jquery-ui.css" />
							<!---strat-date-piker---->
							<script>
								$(function() {
									$("#datepicker,#datepicker1").datepicker();
								});
							</script>
							<form method="POST" action="/study/findindex.html">
								<div class="online_reservation">
									<div class="b_room">
										<div class="booking_room">
											<div class="reservation">
												<ul>
													<li class="span1_of_1 desti">
														<h5>Название документа</h5>
														<div class="book_date">
															<span class="glyphicon glyphicon-map-marker"
																aria-hidden="true"></span> <input type="text"
																placeholder="Название документа" class="typeahead1"
																name="name" />
														</div>
													</li>
													<li class="span1_of_1 left desti">
														<h5>Дата защиты</h5>
														<div class="book_date">
															<span class="glyphicon glyphicon-calendar"
																aria-hidden="true"></span> <input type="date"
																name="date">
														</div>
													</li>
													<div class="clearfix"></div>
												</ul>
											</div>
											<div class="reservation">
												<ul>


													<div class="clearfix"></div>
												</ul>
											</div>
											<div class="reservation">
												<ul>
													<li class="span1_of_3">
														<div class="date_btn">
															<input type="submit" value="Найти">
														</div>
													</li>
													<div class="clearfix"></div>
												</ul>
											</div>
										</div>
										<div class="clearfix"></div>
									</div>
								</div>
							</form>
							<!---->
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="clearfix"></div>
		<br>
		<c:if test="${not empty list}">
			<c:forEach items="${list}" var="doc" varStatus="status">
				<div class="sap_tabs">
					<div class="p-table">
						<div class="p-table-grids">
							<div class="col-md-3">
								<div class="p-table-grad-heading">
									<h6>
										<span class="starTextLabel">Выложил</span>
									</h6>
								</div>
								<div class="p-table-grid-info">
									<a href="#"><img src="${doc.user.avatar}" alt=""></a>
									<div class="room-basic-info">
										<a href="#">Имя пользователя: ${doc.user.real_name}</a>
										<a href="#">Специализация: ${doc.user.spec}</a>
										<p></p>
										<div class="btn" style="left: 200px">
											<tn:href href="${doc.src}" title="Скачать" clazz="info"></tn:href>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-3 p-table-grid">
								<div class="p-table-grad-heading">
									<h6>
										<span class="starTextLabel">Отчёт от Антиплагиатора</span>
									</h6>
								</div>
								<c:if test="${fn:length(doc.reports)!=null}">
									<c:if test="${not empty doc.reports[0].date_repotrs}">
										<br>
										<span class="starTextLabel">Дата и время проверки:</span>
										<div class="avg-rate">
											<fmt:formatDate pattern="dd-MM-yyyy"
												value="${doc.reports[0].date_repotrs}" var="currDate" />
											<fmt:formatDate pattern="HH:mm"
												value="${doc.reports[0].time_repotrs}" var="currtime" />
											<h5>${currDate} в ${currtime}</h5>
										</div>
										<c:if test="${not empty doc.reports[0].src_repotrs}">
											<div class="btn">
												<tn:href href="${doc.reports[0].src_repotrs}"
													title="Скачать" clazz="info"></tn:href>
											</div>
										</c:if>
										<c:if test="${not empty doc.reports[0].note_repotrs}">
											<br>
											<span class="starTextLabel">Заметка:
												${doc.reports[0].note_repotrs}</span>
										</c:if>
									</c:if>
								</c:if>
								<c:if test="${empty doc.reports[0].src_repotrs}">
									<div class="p-table-grad-heading">
										<h6>
											<span class="starTextLabel">Ожидает проверку</span>
										</h6>
									</div>
								</c:if>
							</div>
							<div class="col-md-3 ">
								<div class="p-table-grad-heading">
									<h6>
										<span class="starTextLabel">Отчёт от Преподавателя</span>
									</h6>
								</div>

										<c:if test="${not empty doc.reports[1].date_repotrs}">
											<br>
											<span class="starTextLabel">Дата и время проверки:</span>
											<div class="avg-rate">
												<fmt:formatDate pattern="dd-MM-yyyy"
													value="${doc.reports[1].date_repotrs}" var="currDate" />
												<fmt:formatDate pattern="HH:mm"
													value="${doc.reports[1].time_repotrs}" var="currtime" />
												<h5>${currDate} в ${currtime}</h5>
											</div>
											<c:if test="${not empty doc.reports[1].src_repotrs}">
												<div class="btn">
													<tn:href href="${doc.reports[1].src_repotrs}"
														title="Скачать" clazz="info"></tn:href>
												</div>
											</c:if>
											<c:if test="${not empty doc.reports[1].note_repotrs}">
												<br>
												<span class="starTextLabel">Заметка:
													${doc.reports[1].note_repotrs}</span>
											</c:if>
										</c:if>

		
								<c:if test="${empty doc.reports[1].src_repotrs}">
									<div class="p-table-grad-heading">
										<h6>
											<span class="starTextLabel">Ожидает проверку</span>
										</h6>
									</div>
								</c:if>

							</div>
							<div class="col-md-3">
								<div class="p-table-grad-heading">
									<h6>
										<span class="starTextLabel">Дата защиты</span>
									</h6>
								</div>
								<c:if test="${doc.protect!=null}">
									<c:if test="${not empty doc.protect.date_protect}">
									<br>
									<span class="starTextLabel">Дата и время защиты:</span>
										<div class="avg-rate">
												<fmt:formatDate pattern="dd-MM-yyyy"
													value="${doc.protect.date_protect}" var="currDate" />
												<fmt:formatDate pattern="HH:mm"
													value="${doc.protect.time_protect}" var="currtime" />
												<h5>${currDate} в ${currtime}</h5>
											</div>
									</c:if>

									<c:if test="${not empty doc.protect.note_protect}">
										<span class="starTextLabel">Заметка:
											${doc.protect.note_protect}</span>
									</c:if>
								</c:if>
								<c:if test="${doc.protect==null}">
												<div class="p-table-grad-heading">
										<h6>
											<span class="starTextLabel">Ожидает проверку</span>
										</h6>
									</div>
								</c:if>
							</div>

							<div class="clearfix"></div>
						</div>
					</div>
				</div>
				<div class="clearfix"></div>
				<br>
			</c:forEach>

		</c:if>
	</div>
	<!-- //container -->
</div>
<!-- //banner -->
<div class="move-text">
	<div class="marquee">
		Пожалуйста, пройдите аутентификацию!<a href="signup.html">Вперед
			за знаниями!</a>
	</div>
	<script type="text/javascript" src="js/jquery.marquee.min.js"></script>
	<script>
		$('.marquee').marquee({
			pauseOnHover : true
		});
	</script>
</div>
<script type="text/javascript">
	$(function() {
		SyntaxHighlighter.all();
	});
	$(window).load(function() {
		$('.flexslider').flexslider({
			animation : "slide",
			start : function(slider) {
				$('body').removeClass('loading');
			}
		});
	});
</script>

<jsp:doBody />
