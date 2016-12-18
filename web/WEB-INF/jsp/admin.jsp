<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- Загрузка html, head, и начала body --%>
<jsp:include page="header.jsp"/>

<div class="container">

    <c:if test="${currentUser.idstatus==3}">
        <div class="fixed-action-btn" >
            <a class="btn-floating btn-large teal tooltipped" href="controller?command=createNewUser" data-position="left" data-delay="50" data-tooltip="Зарегистрировать нового пользователя">
                <i class="large material-icons">person_add</i>
            </a>
        </div>
    </c:if>
    <div class="row">
        <div>

            <h3>Врачебный кабинет</h3>
            <h5>Добро пожаловать, ${currentUser.name}!</h5>

            <div class="row">
                <div class="col l3 s12">
                    <h5 class="light">Пациенты</h5>
                </div>
                <div class="col l9 s12">
                    <%
                        request.setAttribute("users", request.getSession().getAttribute("patients"));
                    %>
                    <jsp:include page="user-output.jsp">
                        <jsp:param name="users" value="${users}"/>
                    </jsp:include>
                </div>
            </div>

            <div class="row">
                <div class="col l3 s12">
                    <h5 class="light">Медсестры</h5>
                </div>
                <div class="col l9 s12">
                    <%
                        request.setAttribute("users", request.getSession().getAttribute("nurses"));
                    %>
                    <jsp:include page="user-output.jsp">
                        <jsp:param name="users" value="${nurses}"/>
                    </jsp:include>
                </div>
            </div>

            <div class="row">
                <div class="col l3 s12">
                    <h5 class="light">Врачи</h5>
                </div>
                <div class="col l9 s12">
                    <%
                        request.setAttribute("users", request.getSession().getAttribute("doctors"));
                    %>
                    <jsp:include page="user-output.jsp">
                        <jsp:param name="users" value="${doctors}"/>
                    </jsp:include>
                </div>
            </div>

            <div class="row">
                <div class="col l3 s12">
                    <h5 class="light">Выписанные пациенты</h5>
                </div>
                <div class="col l9 s12">
                    <%
                        request.setAttribute("users", request.getSession().getAttribute("dischargedPatients"));
                    %>
                    <jsp:include page="user-output.jsp">
                        <jsp:param name="users" value="${dischargedPatients}"/>
                    </jsp:include>
                </div>
            </div>



        </div>
    </div>
</div>




<%-- Загрузка конца body, импорт js, html --%>
<jsp:include page="footer.jsp"/>