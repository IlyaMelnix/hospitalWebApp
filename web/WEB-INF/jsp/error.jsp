<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>

<%-- Загрузка html, head, и начала body --%>
<jsp:include page="header.jsp"/>

Request from ${pageContext.errorData.requestURI} is failed <br/>
Servlet name or type : ${pageContext.errorData.servletName} <br>
Status code: ${pageContext.errorData.statusCode} <br/>
Exception^ ${pageContext.errorData.throwable}

<%-- Загрузка конца body, импорт js, html --%>
<jsp:include page="footer.jsp"/>