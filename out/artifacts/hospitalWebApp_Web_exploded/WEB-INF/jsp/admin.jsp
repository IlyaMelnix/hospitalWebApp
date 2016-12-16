<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- Загрузка html, head, и начала body --%>
<jsp:include page="header.jsp"/>

<div class="container">
    <div class="fixed-action-btn toolbar">
        <a class="btn-floating btn-large red">
            <i class="large material-icons">mode_edit</i>
        </a>
        <ul>
            <li class="waves-effect waves-light"><a href="#!"><i class="material-icons">insert_chart</i></a></li>
            <li class="waves-effect waves-light"><a href="#!"><i class="material-icons">format_quote</i></a></li>
            <li class="waves-effect waves-light"><a href="#!"><i class="material-icons">publish</i></a></li>
            <li class="waves-effect waves-light"><a href="#!"><i class="material-icons">attach_file</i></a></li>
        </ul>
    </div>
    <div class="row">
        <div>

            <h2>Панель администратора</h2>
            <h3>${currentUser.name}, добрый день!</h3>

            <%--<div class="row center">--%>
                <%--<button data-target="modal1" class="btn modal-trigger">Modal</button>--%>
            <%--</div>--%>
            <%--<!-- Modal Structure -->--%>
            <%--<div id="modal1" class="modal modal-fixed-footer">--%>
                <%--<div class="modal-content">--%>
                    <%--<h4>Modal Header</h4>--%>
                    <%--<p>A bunch of text</p>--%>
                <%--</div>--%>
                <%--<div class="modal-footer">--%>
                    <%--<a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat ">Agree</a>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<br><br>--%>


            <a href="controller?command=Logout">Выйти из системы</a>
            <br/>
            <a href="controller?command=createNewUser">Зарегистрировать нового пользователя</a>


            <h3>${usersListMessage}</h3>
            <ul class="collapsible popout" data-collapsible="accordion">

                <c:forEach var="user" items="${users}">
                <li>
                    <div class="collapsible-header">
                        <i class="material-icons">account_circle</i>
                        ${user.name} ${user.surname}
                    </div>
                    <div class="collapsible-body white">
                        <div class="table-output">
                            <div class="col l3">
                                <p><strong>ID</strong></p>
                                <p><strong>Логин</strong></p>
                                <p><strong>Пароль</strong></p>
                                <p><strong>Имя</strong></p>
                                <p><strong>Фамилия</strong></p>
                                <p><strong>Отчество</strong></p>
                                <p><strong>Диагноз</strong></p>
                                <p><strong>Статус</strong></p>
                            </div>
                            <div class="col l9">
                                <p>${user.iduser}</p>
                                <p>${user.username}</p>
                                <p>${user.password}</p>
                                <p>${user.name}</p>
                                <p>${user.surname}</p>
                                <p>${user.patronymic}</p>
                                <p>${user.diagnosis}</p>
                                <p>${user.status}</p>
                            </div>
                        </div>

                        <%--Удаление--%>



                        <div class="user-info">

                            <!-- Modal Trigger -->
                            <a class="btn white grey-text darken-4 waves-effect waves-red" href="#deleteUser${user.iduser}">Удалить пользователя</a>

                            <!-- Modal Structure -->
                            <div id="deleteUser${user.iduser}" class="modal">
                                <div class="modal-content">
                                    <h4>Вы уверены, что хотите удалить пользователя ${user.name}?</h4>
                                    <p>Операция не может быть отменена. Пользователь будет удалён из базы данных.</p>
                                </div>
                                <div class="modal-footer">
                                    <a class="modal-action modal-close waves-effect waves-red btn-flat" href="controller?command=deleteUser&id=<c:out value="${user.iduser}"/>">Удалить пользователя</a>
                                    <a class="modal-action modal-close waves-effect waves-green btn-flat" href="#">Отмена</a>
                                </div>
                            </div>

                            <p><strong>Лекарства</strong></p>
                            <ul class="collapsible" data-collapsible="expandable">

                                <c:forEach var="drug" items="${user.userDrugsList}">
                                    <li>
                                        <div class="collapsible-header"><i class="material-icons">filter_drama</i>
                                                ${drug.drugName}
                                        </div>
                                        <div class="collapsible-body"><p>Lorem ipsum dolor sit amet.</p></div>
                                    </li>
                                </c:forEach>
                            </ul>

                            <a class="waves-effect waves-light btn" href="controller?command=addDrug&id=<c:out value="${user.iduser}"/>">Выписать лекарство</a>
                            <%--<!-- Modal Trigger -->--%>
                            <%--<a class="waves-effect waves-light btn" href="#addDrug${user.iduser}">Выписать лекарство</a>--%>

                            <%--<!-- Modal Structure -->--%>
                            <%--<div id="addDrug${user.iduser}" class="modal">--%>
                                <%--<div class="modal-content">--%>
                                    <%--<h4>Новое лекарство для: ${user.name} ${user.surname} </h4>--%>
                                    <%--<p>Диагноз: ${user.diagnosis} </p>--%>
                                <%--</div>--%>
                                <%--<div class="modal-footer">--%>
                                    <%--<a href="#" class=" modal-action modal-close waves-effect waves-green btn-flat">Выписать лекарство</a>--%>
                                <%--</div>--%>
                            <%--</div>--%>

                            <p><strong>Процедуры</strong></p>
                            <ul class="collapsible" data-collapsible="expandable">

                                <c:forEach var="procedure" items="${user.userProceduresList}">
                                    <li>
                                        <div class="collapsible-header"><i class="material-icons">filter_drama</i>
                                                ${procedure.procedureName}
                                        </div>
                                        <div class="collapsible-body"><p>Lorem ipsum dolor sit amet.</p></div>
                                    </li>
                                </c:forEach>
                            </ul>

                            <!-- Modal Trigger -->
                            <a class="waves-effect waves-light btn" href="#addProcedure${user.iduser}">Назначить процедуру</a>

                            <!-- Modal Structure -->
                            <div id="addProcedure${user.iduser}" class="modal">
                                <div class="modal-content">
                                    <h4>Новое лекарство для: ${user.name} ${user.surname} </h4>
                                    <p>Диагноз: ${user.diagnosis} </p>
                                </div>
                                <div class="modal-footer">
                                    <a href="#" class=" modal-action modal-close waves-effect waves-green btn-flat">Назначить процедуру</a>
                                </div>
                            </div>

                            <p><strong>Операции</strong></p>
                            <ul class="collapsible" data-collapsible="expandable">

                                <c:forEach var="operation" items="${user.userOperationList}">
                                    <li>
                                        <div class="collapsible-header"><i class="material-icons">filter_drama</i>
                                                ${operation.operationName}
                                        </div>
                                        <div class="collapsible-body"><p>Lorem ipsum dolor sit amet.</p></div>
                                    </li>
                                </c:forEach>
                            </ul>

                            <%--Показывать добавление операции только пользователям "Врач"--%>
                            <c:choose>
                                <c:when test="${currentUser.idstatus==3}">

                                    <!-- Modal Trigger -->
                                    <a class="waves-effect waves-light btn" href="#addOperation${user.iduser}">Назначить операцию</a>

                                    <!-- Modal Structure -->
                                    <div id="addOperation${user.iduser}" class="modal">
                                        <div class="modal-content">
                                            <h4>Новое лекарство для: ${user.name} ${user.surname} </h4>
                                            <p>Диагноз: ${user.diagnosis} </p>
                                        </div>
                                        <div class="modal-footer">
                                            <a href="#" class=" modal-action modal-close waves-effect waves-green btn-flat">Назначить процедуру</a>
                                        </div>
                                    </div>

                                </c:when>
                                <c:otherwise>

                                    <a class="btn disabled">Назначить операцию может только врач</a>

                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </li>
                </c:forEach>

            </ul>
        </div>
    </div>
</div>




<%-- Загрузка конца body, импорт js, html --%>
<jsp:include page="footer.jsp"/>