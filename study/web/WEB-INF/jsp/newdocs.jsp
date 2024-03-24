<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tn"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>
<ui:htmlui page3="active">
	<sec:authorize access="hasRole('ROLE_ANTIPLAGIATOR')">
		<tn:newdoc></tn:newdoc>
	</sec:authorize>
	<sec:authorize access="hasRole('ROLE_EXPERT')">
		<tn:newdocEX></tn:newdocEX>
	</sec:authorize>
	 
</ui:htmlui>
