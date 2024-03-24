<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tn"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>
<tn:htmlui page5="active">
	<tn:adminui page3="active">
		<div class="banner bus-banner">
			<!-- container -->
 
			<div class="container">
				<c:if test="${not empty err}">
					<div class="alert alert-danger">${err}</div>
				</c:if>
				<div class="clearfix"></div>
				<br>
				<c:if test="${not empty list}">
					<c:forEach items="${list}" var="doc" varStatus="status">
					<c:if test="${doc.protect == null}">
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
												<a href="#">Имя пользователя: ${doc.user.real_name}</a> <a
													href="#">Специализация: ${doc.user.spec}</a>
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

										<form method="POST" action="/study/upload_mag_ant.html"
											enctype="multipart/form-data">

											<p></p>

											<input type="text" value="${doc.id}" hidden="true" name="id" />

											<c:if test="${not empty list_1}">
												<c:forEach items="${list_1}" var="rep" varStatus="status">

													<c:if test="${doc.reports[0].src_repotrs==rep.src_repotrs}">
														<c:if test="${doc.id==rep.docs.id}">

															<c:if
																test="${doc.reports[0].note_repotrs==rep.note_repotrs}">

																<fmt:formatDate pattern="HH:mm"
																	value="${rep.time_repotrs}" var="cuDate" />
																<c:if test="${currtime==cuDate}">
																	<input type="text" value="${rep.id}" hidden="true"
																		name="rep_id" />

																</c:if>
															</c:if>
														</c:if>
													</c:if>
												</c:forEach>
											</c:if>
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
									<div class="col-md-3 p-table-grid">
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

										<form method="POST" action="/study/upload_mag_exp.html"
											enctype="multipart/form-data">

											<p></p>

											<input type="text" value="${doc.id}" hidden="true" name="id" />

											<c:if test="${not empty list_1}">
												<c:forEach items="${list_1}" var="repp" varStatus="status">

													<c:if
														test="${doc.reports[1].src_repotrs==repp.src_repotrs}">
														<c:if test="${doc.id==repp.docs.id}">

															<c:if
																test="${doc.reports[1].note_repotrs==repp.note_repotrs}">

																<fmt:formatDate pattern="HH:mm"
																	value="${repp.time_repotrs}" var="cuDate" />
																<c:if test="${currtime==cuDate}">
																	<input type="text" value="${repp.id}" hidden="true"
																		name="rep_id" />
																</c:if>
															</c:if>
														</c:if>
													</c:if>
												</c:forEach>
											</c:if>
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
									<div class="col-md-3 p-table-grid">
										<div class="p-table-grad-heading">
											<h6>
												<span class="starTextLabel">Дата защиты</span>
											</h6>
										</div>
										<form method="POST" action="/study/protect_mag.html">
											<div class="reservation">
												<ul>
													<li class="span1_of_1">
														<h5>Дата</h5>
														<div class="book_date">
															<span class="glyphicon glyphicon-calendar"
																aria-hidden="true"></span> <input type="date"
																name="date">
														</div>
													</li>
													<li class="span1_of_1">
														<h5>Время</h5>
														<div class="book_date">
															<span class="glyphicon glyphicon-calendar"
																aria-hidden="true"></span> <input type="time"
																name="time">
														</div>
													</li>
												</ul>
												<input type="text" value="${doc.id}" hidden="true" name="id" />
												<div class="book_date">
													<span class="glyphicon glyphicon-map-marker"
														aria-hidden="true"></span> <span
														class="glyphicon glyphicon-map-marker" aria-hidden="true"></span>
													<input type="text" placeholder="Примечание"
														class="typeahead1 input-md form-control tt-input"
														required="" name="note" />
												</div>
												<div class="sp-bor-btn">
													<div class="fileUpload btn btn-success">
														<span>Назначить</span><input type="submit" class="load">
													</div>
													<div class="btn">
														<tn:href href="/del_sec_mag_doc.html?id=${doc.id}"
															title="Удалить документ" clazz="warning"></tn:href>
													</div>
												</div>

												<div class="clearfix"></div>

											</div>
										</form>
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
