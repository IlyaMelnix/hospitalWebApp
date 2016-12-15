<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- Загрузка html, head, и начала body --%>
<jsp:include page="header.jsp"/>

<div class="container">
    <div class="row">
        <div>

            <h2>Панель администратора</h2>
            <h3>${currentUser.name}, добрый день!</h3>
            <a href="controller?command=Logout">Выйти из системы</a>
            <br/>
            <a href="/create-new-user">Зарегистрировать нового пользователя</a>

            <h3>${usersListMessage}</h3>
            <ul class="collapsible popout" data-collapsible="accordion">

                <c:forEach var="user" items="${users}">
                <li>
                    <div class="collapsible-header"><i class="material-icons">account_circle</i>${user.name} ${user.surname}</div>
                    <div class="collapsible-body">

                        <p>Lorem ipsum dolor sit amet.</p>
                        <p>
                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="deleteUser">
                            <input type="hidden" name="id" value="${user.iduser}">
                            <input type="submit" value="Удалить" class="waves-effect waves-light btn-large">
                        </form>
                        </p>

                        <ul class="collapsible red darken-3" data-collapsible="expandable">

                            <c:forEach var="drug" items="${user.userDrugsList}">
                            <li>
                                <div class="collapsible-header"><i class="material-icons">filter_drama</i>
                                    ${drug.drugName}
                                </div>
                                <div class="collapsible-body"><p>Lorem ipsum dolor sit amet.</p></div>
                            </li>
                            </c:forEach>
                        </ul>


                    </div>
                </li>
                </c:forEach>

            </ul>
        </div>
    </div>
</div>
<%-- Загрузка конца body, импорт js, html --%>
<jsp:include page="footer.jsp"/>