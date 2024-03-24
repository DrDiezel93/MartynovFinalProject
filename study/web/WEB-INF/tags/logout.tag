<%@ tag %>
<%@ attribute name="href" required="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="${href}" var="tmp" /><a href="${tmp}"></a>