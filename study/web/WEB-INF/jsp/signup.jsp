<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tn"%>
<tn:html>
<div class="banner-bottom">
	<div class="container">
		<div class="faqs-top-grids">
			<div class="book-grids">
				<div class="col-md-6 book-left">
					<div class="book-left-info">
						<h3>Создайте свой аккаунт:</h3>
					</div>
					<div class="book-left-form">
					<p>После регистрации используйте свой псевдоним и пароль для успешного входа на сайт.</p>
					<p></p>
					<c:if test="${not empty email}">
						<div class="alert alert-danger">${email}</div>
						</c:if>
						<form:form modelAttribute="user" method="POST" action="/study/reg.html" >
							<p>Псевдоним</p>
							<form:input path="display_name"/>
							<p>Фамилия, Имя</p>
							<form:input path="real_name"/>
							<p>Электронный ящик</p>
							<form:input path="default_email"/>
							<p>Пароль</p>
							<form:password path="password"/>
							<div class="checkbox">
								<label> <input type="checkbox" name="j_remember_me" />Запомнить
									меня
								</label>
							</div>
							<input type="submit" id="login" value="Регистрация">
						</form:form>
					</div>
				</div>
				<div class="col-md-6 book-left book-right">
					<div class="book-left-info">
						<h3>Рекомендуется:</h3>
					</div>
					<ul>
						<li>При регистрации рекомендуется </li>
						<li>запомнить свой пароль, либо</li>
						<li>установить галочку </li>
						<li>"Запомнить меня".</li>
					</ul>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
</div>
</tn:html>