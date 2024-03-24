<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tn"%>
<tn:htmlui page5="active">
	<tn:adminui page1="active">
		<div class="find-banner">
			<br>
			<div class="container">
				<form method="POST" action="/study/adminfindusers.html">
					<div class="col-md-4">
						<section class="slider2"> 
							<div class="flexslider">
								<ul class="slides">
									<li>
										<div class="slider-info">
											<img src="images/1.jpg" alt="" height="340">
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
								<h2>Найти пользователя</h2>
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
												$("#datepicker,#datepicker1")
														.datepicker();
											});
										</script>

										<div class="online_reservation">
											<div class="b_room">
												<div class="booking_room">
													<div class="reservation">
														<ul>
															<li class="span1_of_1 desti">
																<h5>Фамилия Имя</h5>
																<div class="book_date">
																	<span class="glyphicon glyphicon-user"
																		aria-hidden="true"></span><input type="text"
																		placeholder="Фамилия Имя"
																		class="typeahead1 input-md form-control"
																		name="real_name" />
																</div>
															</li>
															<li class="span1_of_1 left desti">
																<h5>Псевдоним</h5>
																<div class="book_date">
																	<span class="glyphicon glyphicon-user"
																		aria-hidden="true"></span> <input type="text"
																		placeholder="Псевдоним"
																		class="typeahead1 input-md form-control"
																		name="display_name" />
																</div>
															</li>
															<div class="clearfix"></div>
														</ul>
													</div>
													<div class="reservation">
														<ul>
															<li class="span1_of_1">
																<h5>Направление</h5> <!----------start section_room----------->
																<div class="section_room">
																	<select id="country" name="spec"
																		onchange="change_country(this.value)">
																		<option selected value="0"></option>
																		<option value="1">Специалист</option>
																		<option value="2">Магистрант</option>
																		<option value="3">Аспирант</option>
																		<option value="4">Бакалавр</option>

																	</select>
																</div>
															</li>

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
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="clearfix"></div>
				</form>
				<br>
				<c:if test="${not empty users}">
					<c:forEach items="${users}" var="user" varStatus="status">
						<c:if test="${user.id!=iam.id}">
							<div class="sap_tabs">
								<div class="p-table">
									<div class="p-table-grids">
										<div class="col-md-3">
											<div class="p-table-grad-heading">
												<h6>
													<span class="starTextLabel">Пользователь
														№${status.count}</span>
												</h6>
											</div>
											<div class="p-table-grid-info">
												<a href="#"><img src="${user.avatar}" alt=""></a>
												<div class="room-basic-info">
													<a href="#">${user.real_name} </a>
													<p></p>
												</div>
											</div>
										</div>
										<div class="col-md-3 p-table-grid">
											<div class="p-table-grad-heading">
												<h6>
													<span class="starTextLabel">Данные о пользователе</span>
												</h6>
											</div>
											<div class="rate-features" style="color: white">
												<ul style="color: white">
													<li style="color: white">Псевдоним:
														${user.display_name}</li>
													<li style="color: white">Дата рождения:
														${user.birthday}</li>
													<li style="color: white">Пол: ${user.sex}</li>
													<li style="color: white">Email: ${user.default_email}</li>
													<li style="color: white">Телефон: ${user.phone_number}</li>
													<li style="color: white">Направление: ${user.spec}</li>
													<c:if test="${user.role[0].role == 'ROLE_USER'}">
														<li style="color: white">Должность: Студент</li>
													</c:if>
													<c:if test="${user.role[0].role == 'ROLE_ANTIPLAGIATOR'}">
														<li style="color: white">Должность: Антиплагиатор</li>
													</c:if>
													<c:if test="${user.role[0].role == 'ROLE_EXPERT'}">
														<li style="color: white">Должность: Преподаватель</li>
													</c:if>
													<c:if test="${user.role[0].role == 'ROLE_SECRETARY'}">
														<li style="color: white">Должность: Администратор</li>
													</c:if>
												</ul>
											</div>
										</div>
										<div class="col-md-3">
											<div class="p-table-grad-heading">
												<h6>
													<span class="starTextLabel">Назначить на должность</span>
												</h6>
											</div>
											<form method="POST" action="/study/adminnewrole.html">
												<div class="section_room">
												<input type="hidden" name="user_id" value="${user.id}">
													<select name="add_role" class="frm-field required">
														<option selected value="0"></option>
														<option value="1">Студент</option>
														<option value="2">Антиплагиатор</option>
														<option value="3">Преподаватель</option>
														<option value="4">Администратор</option>

													</select>
												</div>
												<div class="date_btn">
													<input type="submit" value="Назначить">
												</div>
											</form>
										</div>
										<div class="col-md-3">
											<div class="p-table-grad-heading">
												<h6>
													<span class="starTextLabel">Действия</span>
												</h6>
											</div>
											<div class="btn">
												<tn:href href="/deladminuser.html?id=${user.id}"
													title="Удалить" clazz="warning"></tn:href>
											</div>
										</div>
										<div class="clearfix"></div>
									</div>
								</div>
							</div>
							<div class="clearfix"></div>
							<br>
						</c:if>
					</c:forEach>
				</c:if>
			</div>
			<!-- //container -->
		</div>
	</tn:adminui>
</tn:htmlui>
<!-- //banner -->
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

