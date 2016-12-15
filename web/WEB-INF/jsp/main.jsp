<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- Загрузка html, head, и начала body --%>
<jsp:include page="header.jsp"/>

    <h3>Welcome</h3>
    <hr/>
        ${user}, hello!
    <hr/>
    <a href="controller?command=Logout">Выйти из системы</a>

<%-- Загрузка конца body, импорт js, html --%>
<jsp:include page="footer.jsp"/>
