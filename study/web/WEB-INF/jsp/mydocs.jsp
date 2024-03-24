<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<tn:htmlui page2="active">
	<div class="upfoto-banner ">
		<!-- container -->

		<!--single-page-->
		<div class="container">
		<c:if test="${empty user.phone_number}">

					<div class="alert alert-info">Укажите свой мобильный телефон</div>
		</c:if>
<br>
		<c:if test="${empty user.spec}">

					<div class="alert alert-info">Укажите своё напраление
						обучения</div>

		</c:if>



					
							<c:if test="${not empty user.default_email}">
			<c:if test="${not empty user.phone_number}">
				<c:if test="${not empty user.spec}">
					<c:if test="${not empty err}">
						<div class="alert alert-danger">${err}</div>
					</c:if>
						<div class="sap_tabs">
							<div class="single-page">
								<div class="col-md-4">

									<div class="flexslider">
										<ul class="slides">
											<li>
												<div class="slider-info">
													<img src="images/1.jpg" alt="" height="250" weight="245">
												</div>
											</li>
										</ul>
									</div>
									<!-- FlexSlider -->
									<script defer src="js/jquery.flexslider.js"></script>
									<script>
										// Can also be used with $(document).ready()
										$(window).load(function() {
											$('.flexslider').flexslider({
												animation : "slide",
												controlNav : "thumbnails"
											});
										});
									</script>

								</div>

								<c:if test="${not empty user.phone_number}">
									<c:if test="${not empty user.default_email}">
										<c:if test="${not empty user.spec}">
											<div class="col-md-4"></div>
											<div class="col-md-4">
												<div class="spl-btn">
													<div class="spl-btn-bor">
														<span class="glyphicon glyphicon-tag" aria-hidden="true"></span>
														</a>
														<p>Выбрать новый документ</p>
														<script>
															$(document)
																	.ready(
																			function() {
																				$(
																						'[data-toggle="tooltip"]')
																						.tooltip();
																			});
														</script>
													</div>

													<form method="POST" action="/study/upload.html"
														enctype="multipart/form-data">

														<p class="best-pri" style="color: white">Укажите
															отображаемое имя вашего документа.</p>
														<div class="book_date">
															<span class="glyphicon glyphicon-map-marker"
																aria-hidden="true"></span> <span
																class="glyphicon glyphicon-map-marker"
																aria-hidden="true"></span> <input type="text"
																placeholder="Название документа"
																class="typeahead1 input-md form-control tt-input"
																required="" name="name" />
														</div>

														<div class="sp-bor-btn">

															<div class="fileUpload btn btn-success">
																<span>Выбрать</span> <input type="file" class="upload"
																	accept="application/msword, application/vnd.ms-excel, application/vnd.ms-powerpoint,text/plain, application/pdf"
																	name="src" />
															</div>

															<div class="fileUpload btn btn-success">
																<span>Загрузить</span><input type="submit" class="load">
															</div>

														</div>
													</form>

												</div>
											</div>
											<div class="clearfix"></div>
										</c:if>
									</c:if>
								</c:if>
							</div>
						</div>
						<!--//single-page-->
						<br>
						<c:if test="${not empty docs}">
							<c:forEach items="${docs}" var="doc" varStatus="status">
								<form method="POST" action="/study/update.html">
									<div class="sap_tabs">
										<div class="p-table">
											<div class="p-table-grids">
												<div class="col-md-4">
													<div class="p-table-grad-heading">
														<h6>
															<span class="starTextLabel">Ваш документ:</span>
															<div class="book_date">
																<span class="glyphicon glyphicon-map-marker"
																	aria-hidden="true"></span> <span
																	class="glyphicon glyphicon-map-marker"
																	aria-hidden="true"></span> <input type="text"
																	placeholder="Укажите название"
																	class="typeahead1 input-md form-control tt-input"
																	name="name"
																	style="background: transparent; border: null; color: white"
																	value="${doc.name} " />
															</div>
															<br> <span class="starTextLabel">Дата
																публикации:</span>
															<div class="avg-rate">
																<h5>${doc.date}</h5>
															</div>
															<br> <span class="starTextLabel">Действия</span>
														</h6>
													</div>
													<input type="hidden" name="docid" value="${doc.id}">
													<div class="fileUpload btn btn-success">
														<span>Редактировать</span> <input type="submit"
															class="upload" />
													</div>
													<div class="btn">
														<tn:href href="/del.html?id=${doc.id}" title="Удалить"
															clazz="warning"></tn:href>

														<div class="btn">
															<tn:href href="${doc.src}" title="Скачать" clazz="info"></tn:href>
														</div>
													</div>
												</div>
												<div class="col-md-3">
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

												<div class="col-md-3">
													<div class="p-table-grad-heading">
														<h6>
															<span class="starTextLabel">Отчёт от Преподавателя</span>
														</h6>
													</div>
													<c:if test="${fn:length(doc.reports)!=null}">
														<c:if test="${fn:length(doc.reports)!=1}">
															<c:if test="${not empty doc.reports[1].date_repotrs}">
																<br>
																<span class="starTextLabel">Дата и время
																	проверки:</span>
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
												<div class="col-md-2">
													<div class="p-table-grad-heading">
														<h6>
															<span class="starTextLabel">Дата защиты</span>
														</h6>
													</div>
													<c:if test="${doc.protect!=null}">
														<c:if test="${not empty doc.protect.date_protect}">
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
								</form>
							</c:forEach>
						</c:if>
				</c:if>
			</c:if>
		</c:if>
					</div>

	</div>

	<!-- //container -->


</tn:htmlui>
