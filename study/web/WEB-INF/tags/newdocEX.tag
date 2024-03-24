<%@ tag pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tn"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tn"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>
<div class="banner bus-banner">
		<!-- container -->

		<br>
		<div class="container">
		<c:if test="${not empty error}">
			<div class="alert alert-danger">${error}</div>
		</c:if>
			<div class="clearfix"></div>
			<br>
			<c:if test="${not empty list}">
				<c:forEach items="${list}" var="doc" varStatus="status">
					<c:if test="${fn:length(doc.reports[1].src_repotrs)==0}">
					<c:if test="${fn:length(doc.reports[0].src_repotrs)!=0}">
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
										
									</div>
									<div class="col-md-3 p-table-grid">
										<div class="p-table-grad-heading">
											<h6>
												<span class="starTextLabel">Отчёт от Преподавателя</span>
											</h6>
										</div>

										<form method="POST" action="/study/upload_reports.html"
											enctype="multipart/form-data">

											<p></p>
											<div class="book_date">
												<span class="glyphicon glyphicon-map-marker"
													aria-hidden="true"></span> <span
													class="glyphicon glyphicon-map-marker" aria-hidden="true"></span>
												<input type="text" placeholder="Примечание"
													class="typeahead1 input-md form-control tt-input"
													required="" name="name" />
											</div>
											<input type="text" value="${doc.id}" hidden="true" name="id" />
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
									<div class="col-md-3">
										<div class="p-table-grad-heading">
											<h6>
												<span class="starTextLabel">Дата защиты</span>
											</h6>
										</div>


										<div class="p-table-grad-heading">
											<h6>
												<div class="col-md-3 p-table-grid">
											<div class="p-table-grad-heading">
												<h6>
													<span class="starTextLabel">Дата защиты</span>
												</h6>
											</div>
											<form method="POST" action="/study/protect_afd.html">
												<div class="reservation">
													<ul>
														<li class="span1_of_1">
															<h5>Дата</h5>
															<fmt:formatDate pattern="yyyy-MM-dd"
														value="${doc.protect.date_protect}" var="currDate" />
															<div class="book_date">
																<span class="glyphicon glyphicon-calendar"
																	aria-hidden="true"></span> <input type="date"
																	name="date" value = "${currDate}">
															</div>
														</li>
														<li class="span1_of_1">
															<h5>Время</h5>
															<fmt:formatDate pattern="HH:mm"
														value="${doc.protect.time_protect}" var="currtime" />
															<div class="book_date">
																<span class="glyphicon glyphicon-calendar"
																	aria-hidden="true"></span> <input type="time"
																	name="time" value="${currtime}">
															</div>
														</li>
													</ul>

									<br>
													<input type="text" value="${doc.id}" hidden="true"
														name="id" />
													<div class="book_date">
														<span class="glyphicon glyphicon-map-marker"
															aria-hidden="true"></span> <span
															class="glyphicon glyphicon-map-marker" aria-hidden="true"></span>
														<input type="text" placeholder="Примечание"
															class="typeahead1 input-md form-control tt-input"
															required="" name="note" value="${doc.protect.note_protect}"/>
													</div>
													<div class="sp-bor-btn">
														<div class="fileUpload btn btn-success">
															<span>Назначить</span><input type="submit" class="load">
														</div>
														<div class="btn">
															<tn:href href="/del_sec_afd_doc.html?id=${doc.id}"
																title="Удалить документ" clazz="warning"></tn:href>
														</div>
													</div>
											</h6>
										</div>

									</div>

									<div class="clearfix"></div>
								</div>
							</div>
						</div>
						<div class="clearfix"></div>
						<br>
							</c:if>
						</c:if>
				</c:forEach>

			</c:if>
		</div>
		<!-- //container -->
	</div>
<jsp:doBody />