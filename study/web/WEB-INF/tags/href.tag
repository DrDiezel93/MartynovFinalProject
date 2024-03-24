<%@ tag %>
<%@ attribute name="href" required="true" %>
<%@ attribute name="title" required="true" %>
<%@ attribute name="clazz" required="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:url value="${href}" var="tmp" /><a href="${tmp}" class="btn btn-${clazz}"><c:out value="${title}" /></a>