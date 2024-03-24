<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>
<ui:htmlui page1="active">
	<link href="css/bootstrap.icon-large.min.css" rel="stylesheet">
	<div class="banner bus-ban">
		<br>
		<div class="container">
			<c:if test="${not empty err}"> 
				<div class="alert alert-danger">${err}</div>
			</c:if>
			<form method="POST" action="/study/saveinfo.html"
				enctype="multipart/form-data">
				<div class="col-md-4">
					<section class="slider2">
						<div class="flexslider">
							<ul class="slides">
								<li>
									<div class="slider-info">
										<img src="${user.avatar}" alt="" name="avatar" height="300">
										<div class="fileUpload btn btn-success">
											<span>Выбрать</span> <input type="file" class="load"
												accept="image/*" name="src" />
										</div>
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
							<h2>Личная информация</h2>
						</div>
						<div id="horizontalTab"
							style="display: block; width: 100%; margin: 0px;">
							<!---->
							<div class="facts about-facts">
								<div class="booking-form">
									<link rel="stylesheet" href="css/jquery-ui.css" />
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
																	aria-hidden="true"></span> <input type="text"
																	placeholder="Фамилия Имя"
																	class="typeahead1 input-md form-control tt-input"
																	required="" name="real_name" value="${user.real_name}" />
															</div>
														</li>
														<li class="span1_of_1 left desti">
															<h5>Псевдоним</h5>
															<div class="book_date">
																<span class="glyphicon glyphicon-user"
																	aria-hidden="true"></span> <input type="text"
																	placeholder="Псевдоним"
																	class="typeahead1 input-md form-control tt-input"
																	required="" name="display_name"
																	value="${user.display_name}" />
															</div>
														</li>
														<div class="clearfix"></div>
													</ul>
												</div>
												<div class="reservation">
													<ul>
														<li class="span1_of_1">
															<h5>Дата рождения</h5>
															<div class="book_date">
																<span class="glyphicon glyphicon-calendar"
																	aria-hidden="true"></span> <input type="date"
																	value="${user.birthday}" name="bithday">
															</div>
														</li>
														<li class="span1_of_1 left">
															<h5>Пол</h5> <!----------start section_room----------->
															<div class="section_room">
																<select name="sex" class="frm-field required">
																	<c:if test="${selected==1}">
																		<option selected value="1">Женский</option>
																		<option value="2">Мужской</option>
																	</c:if>
																	<c:if test="${selected==2}">
																		<option value="1">Женский</option>
																		<option selected value="2">Мужской</option>
																	</c:if>
																	<c:if test="${empty selected}">
																		<option value="1">Женский</option>
																		<option value="2">Мужской</option>
																	</c:if>
																</select>
															</div>
														</li>

														<div class="clearfix"></div>
													</ul>
												</div>
												<div class="reservation">
													<ul>
														<li class="span1_of_1">
															<h5>Email</h5>
															<div class="book_date">
																<span class="glyphicon glyphicon-envelope"
																	aria-hidden="true"></span> <input type="text"
																	class="typeahead1 input-md form-control tt-input"
																	required="" value="${user.default_email}"
																	name="default_email">
															</div>
														</li>
														<li class="span1_of_1 left">
															<h5>Телефонный номер</h5>
															<div class="book_date">
																<span class="glyphicon glyphicon-phone-alt"
																	aria-hidden="true"></span> <input type="text"
																	class="typeahead1 input-md form-control tt-input"
																	placeholder="(xx)xxx-xxxx" required=""
																	name="phone_number" value="${user.phone_number}"
																	onkeydown="javascript:backspacerDOWN(this,event);"
																	onkeyup="javascript:backspacerUP(this,event);">
															</div>
														</li>
														<div class="clearfix"></div>
													</ul>
												</div>
												<div class="reservation">
													<sec:authorize access="hasRole('ROLE_USER')">
														<ul>
															<li class="span1_of_1">
																<h5>Моё направление</h5>
																<div class="section_room">

																	<select name="spec" class="frm-field required">
																		<c:if test="${not empty selected_sp}">
																			<c:if test="${selected_sp==3}">
																				<option selected value="3">Специалист</option>
																				<option value="2">Магистрант</option>
																				<option value="1">Аспирант</option>
																				<option value="0">Бакалавр</option>
																			</c:if>
																			<c:if test="${selected_sp==2}">
																				<option value="3">Специалист</option>
																				<option selected value="2">Магистрант</option>
																				<option value="1">Аспирант</option>
																				<option value="0">Бакалавр</option>
																			</c:if>
																			<c:if test="${selected_sp==1}">
																				<option value="3">Специалист</option>
																				<option value="2">Магистрант</option>
																				<option selected value="1">Аспирант</option>
																				<option value="0">Бакалавр</option>
																			</c:if>
																			<c:if test="${selected_sp==0}">
																				<option value="3">Специалист</option>
																				<option value="2">Магистрант</option>
																				<option value="1">Аспирант</option>
																				<option selected value="0">Бакалавр</option>
																			</c:if>
																		</c:if>
																		<c:if test="${empty selected_sp}">
																			<option value="3">Специалист</option>
																			<option value="2">Магистрант</option>
																			<option value="1">Аспирант</option>
																			<option value="0">Бакалавр</option>
																		</c:if>

																	</select>
																</div>

															</li>
															<div class="clearfix"></div>
														</ul>
													</sec:authorize>
												</div>
												<div class="reservation">
													<ul>
														<li class="span1_of_1">
															<h5>Пароль</h5>
															<div class="book_date">
																<span class="glyphicon glyphicon-lock"
																	aria-hidden="true"></span> <input type="text"
																	class="typeahead1 input-md form-control"
																	placeholder="Пароль" name="password">
															</div>
														</li>
													</ul>
													<div class="reservation">
														<ul>
															<li class="span1_of_3">
																<div class="date_btn">
																	<input type="submit" value="Сохранить">
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
				</div>
				<div class="clearfix"></div>
			</form>
			<br>

		</div>
	</div>
	<script type="text/javascript">
		var zChar = new Array(' ', '(', ')', '-', '.');
		var maxphonelength = 12;
		var phonevalue1;
		var phonevalue2;
		var cursorposition;

		function ParseForNumber1(object) {
			phonevalue1 = ParseChar(object.value, zChar);
		}
		function ParseForNumber2(object) {
			phonevalue2 = ParseChar(object.value, zChar);
		}

		function backspacerUP(object, e) {
			if (e) {
				e = e
			} else {
				e = window.event
			}
			if (e.which) {
				var keycode = e.which
			} else {
				var keycode = e.keyCode
			}

			ParseForNumber1(object)

			if (keycode >= 48) {
				ValidatePhone(object)
			}
		}

		function backspacerDOWN(object, e) {
			if (e) {
				e = e
			} else {
				e = window.event
			}
			if (e.which) {
				var keycode = e.which
			} else {
				var keycode = e.keyCode
			}
			ParseForNumber2(object)
		}

		function GetCursorPosition() {

			var t1 = phonevalue1;
			var t2 = phonevalue2;
			var bool = false
			for (i = 0; i < t1.length; i++) {
				if (t1.substring(i, 1) != t2.substring(i, 1)) {
					if (!bool) {
						cursorposition = i
						bool = true
					}
				}
			}
		}

		function ValidatePhone(object) {

			var p = phonevalue1

			p = p.replace(/[^\d]*/gi, "")

			if (p.length < 2) {
				object.value = p
			} else if (p.length == 2) {
				pp = p;
				d4 = p.indexOf('(')
				d5 = p.indexOf(')')
				if (d4 == -1) {
					pp = "(" + pp;
				}
				if (d5 == -2) {
					pp = pp + ")";
				}
				object.value = pp;
			} else if (p.length > 2 && p.length < 7) {
				p = "(" + p;
				l30 = p.length;
				p30 = p.substring(0, 3);
				p30 = p30 + ")"

				p31 = p.substring(3, l30);
				pp = p30 + p31;

				object.value = pp;

			} else if (p.length >= 7) {
				p = "(" + p;
				l30 = p.length;
				p30 = p.substring(0, 3);
				p30 = p30 + ")"

				p31 = p.substring(3, l30);
				pp = p30 + p31;

				l40 = pp.length;
				p40 = pp.substring(0, 7);
				p40 = p40 + "-"

				p41 = pp.substring(7, l40);
				ppp = p40 + p41;

				object.value = ppp.substring(0, maxphonelength);
			}

			GetCursorPosition()

			if (cursorposition >= 0) {
				if (cursorposition == 0) {
					cursorposition = 2
				} else if (cursorposition <= 2) {
					cursorposition = cursorposition + 1
				} else if (cursorposition <= 5) {
					cursorposition = cursorposition + 2
				} else if (cursorposition == 6) {
					cursorposition = cursorposition + 2
				} else if (cursorposition == 7) {
					cursorposition = cursorposition + 4
					e1 = object.value.indexOf(')')
					e2 = object.value.indexOf('-')
					if (e1 > -1 && e2 > -1) {
						if (e2 - e1 == 4) {
							cursorposition = cursorposition - 1
						}
					}
				} else if (cursorposition < 11) {
					cursorposition = cursorposition + 3
				} else if (cursorposition == 11) {
					cursorposition = cursorposition + 1
				} else if (cursorposition >= 12) {
					cursorposition = cursorposition
				}

				var txtRange = object.createTextRange();
				txtRange.moveStart("character", cursorposition);
				txtRange.moveEnd("character", cursorposition
						- object.value.length);
				txtRange.select();
			}

		}

		function ParseChar(sStr, sChar) {
			if (sChar.length == null) {
				zChar = new Array(sChar);
			} else
				zChar = sChar;

			for (i = 0; i < zChar.length; i++) {
				sNewStr = "";

				var iStart = 0;
				var iEnd = sStr.indexOf(sChar[i]);

				while (iEnd != -1) {
					sNewStr += sStr.substring(iStart, iEnd);
					iStart = iEnd + 1;
					iEnd = sStr.indexOf(sChar[i], iStart);
				}
				sNewStr += sStr.substring(sStr.lastIndexOf(sChar[i]) + 1,
						sStr.length);

				sStr = sNewStr;
			}

			return sNewStr;
		}
	</script>
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
</ui:htmlui>