<%@ tag %>
<%@ attribute name="href" required="true" %>
<%@ attribute name="title" required="false" %>
<%@ attribute name="clazz" required="false" %>
<%@ attribute name="color" required="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:url value="${href}" var="tmp" /><a href="${tmp}" class="${clazz}" style="color: ${color}"><c:out value="${title}"/></a>