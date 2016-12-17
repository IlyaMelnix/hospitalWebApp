<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- Загрузка html, head, и начала body --%>
<jsp:include page="header.jsp"/>

<div class="container">
    <div class="fixed-action-btn">
        <a class="btn-floating btn-large red" href="controller?command=createNewUser" data-position="bottom" data-delay="50" data-tooltip="I am tooltip">
            <i class="large material-icons">person_add</i>
        </a>
    </div>
    <div class="row">
        <div>

            <h3>Врачебный кабинет</h3>
            <h5>Добро пожаловать, ${currentUser.name}!</h5>

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

                        <div class="user-info">

                            <%--Изменение--%>
                            <a class="waves-effect waves-green btn-flat" href="controller?command=updateUser&id=<c:out value="${user.iduser}"/>">Изменить пользователя</a>

                            <%--Выписать пациента--%>
                            <c:if test="${user.idstatus == 1}">

                                <!-- Modal Trigger -->
                                <a class="waves-effect waves-green btn-flat" href="#dischargeUser${user.iduser}">Выписать пациента</a>

                                <!-- Modal Structure -->
                                <div id="dischargeUser${user.iduser}" class="modal">
                                    <div class="modal-content">
                                        <h4>Вы уверены, что хотите выписать пацинента ${user.name}?</h4>
                                        <p>За пацинентом будет закреплен его окончательный диагноз.</p>
                                    </div>
                                    <div class="modal-footer">
                                        <a class="modal-action modal-close waves-effect waves-red btn-flat" href="controller?command=dischargeUser&id=<c:out value="${user.iduser}"/>">Выписать пациента</a>
                                        <a class="modal-action modal-close waves-effect waves-green btn-flat" href="#">Отмена</a>
                                    </div>
                                </div>


                            </c:if>

                            <%--Удаление--%>
                            <!-- Modal Trigger -->
                            <a class="btn white grey-text darken-4 waves-effect waves-red" href="#deleteUser${user.iduser}">Удалить пользователя</a>

                            <!-- Modal Structure -->
                            <div id="deleteUser${user.iduser}" class="modal">
                                <div class="modal-content">
                                    <h4>Вы уверены, что хотите удалить пользователя ${user.name}?</h4>
                                    <p>Действие не может быть отменено. Пользователь будет удалён из базы данных. Связанные с ним лекарства, процедуры и операции будут удалены.</p>
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
                                        <div class="collapsible-body">

                                            <%--Вывод подробного описания--%>
                                            <div class="table-output">
                                                <div class="col l3">
                                                    <p><strong>ID</strong></p>
                                                    <p><strong>Название</strong></p>
                                                    <p><strong>Описание</strong></p>
                                                    <p><strong>Способ применения</strong></p>
                                                </div>
                                                <div class="col l9">
                                                    <p>${drug.iddrug}</p>
                                                    <p>${drug.drugName}</p>
                                                    <p>${drug.drugDesc}</p>
                                                    <p>${drug.drugDosing}</p>
                                                </div>
                                            </div>

                                            <div class="col 12">

                                                <%--Удаление --%>

                                                <!-- Modal Trigger -->
                                                <a class="btn white grey-text darken-4 waves-effect waves-red" href="#deleteDrug${drug.iddrug}">Удалить лекарство</a>

                                                <!-- Modal Structure -->
                                                <div id="deleteDrug${drug.iddrug}" class="modal">
                                                    <div class="modal-content">
                                                        <h4>Вы уверены, что хотите удалить лекарство ${drug.drugName}?</h4>
                                                        <p>Действие не может быть отменено. Лекарство будет удалено из базы данных.</p>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <a class="modal-action modal-close waves-effect waves-red btn-flat" href="controller?command=deleteDrug&id=<c:out value="${drug.iddrug}"/>">Удалить лекарство</a>
                                                        <a class="modal-action modal-close waves-effect waves-green btn-flat" href="#">Отмена</a>
                                                    </div>
                                                </div>

                                            </div>

                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>

                            <%--<a class="waves-effect waves-light btn" href="controller?command=addDrug&id=<c:out value="${user.iduser}"/>">Выписать лекарство</a>--%>

                            <!-- Modal Trigger -->
                            <a class="waves-effect waves-light btn" href="#addDrug${user.iduser}">Выписать лекарство</a>

                            <!-- Modal Structure -->
                            <div id="addDrug${user.iduser}" class="modal">

                                <form name="AddDrugForm" method="POST" action="controller">

                                    <div class="modal-content">
                                        <h4>Новое лекарство для: ${user.name} ${user.surname} </h4>
                                        <h5>Диагноз: ${user.diagnosis} </h5>

                                        <input type="hidden" name="command" value="AddDrug"/>
                                        <input type="hidden" name="iduser" value="${user.iduser}"/>

                                        <div class="row">
                                            <div class="input-field col s12">
                                                <input id="drugname" type="text" class="validate" name="drugname" length="45">
                                                <label for="drugname">Название лекарства</label>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="input-field col s12">
                                                <textarea id="drugdesc" class="materialize-textarea validate" name="drugdesc"></textarea>
                                                <label for="drugdesc">Описание лекарства</label>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="input-field col s12">
                                                <textarea id="drugdosing" class="materialize-textarea validate" name="drugdosing"></textarea>
                                                <label for="drugdosing">Способ применения</label>
                                            </div>
                                        </div>

                                    </div>
                                    <div class="modal-footer">

                                        <button class="modal-action modal-close waves-effect waves-green btn-flat" type="submit" name="action">Выписать лекарство
                                            <i class="material-icons right">add</i>
                                        </button>

                                        <%--<a href="#" class="modal-action modal-close waves-effect waves-green btn-flat">Выписать лекарство</a>--%>
                                    </div>
                                </form>
                            </div>

                            <p><strong>Процедуры</strong></p>

                            <ul class="collapsible" data-collapsible="expandable">

                                <c:forEach var="procedure" items="${user.userProceduresList}">
                                    <li>
                                        <div class="collapsible-header"><i class="material-icons">filter_drama</i>
                                                ${procedure.procedureName}
                                        </div>
                                        <div class="collapsible-body">


                                            <%--Вывод подробного описания--%>
                                            <div class="table-output">
                                                <div class="col l3">
                                                    <p><strong>ID</strong></p>
                                                    <p><strong>Название</strong></p>
                                                    <p><strong>Описание</strong></p>
                                                    <p><strong>Продолжительность</strong></p>
                                                    <p><strong>Дата начала</strong></p>
                                                    <p><strong>Кол-во раз</strong></p>
                                                </div>
                                                <div class="col l9">
                                                    <p>${procedure.idprocedure}</p>
                                                    <p>${procedure.procedureName}</p>
                                                    <p>${procedure.procedureDesc}</p>
                                                    <p>${procedure.procedureDuration}</p>
                                                    <p>${procedure.procedureStartDate}</p>
                                                    <p>${procedure.procedureHowManyTimes}</p>
                                                </div>
                                            </div>


                                            <%--Удаление --%>

                                            <!-- Modal Trigger -->
                                            <a class="btn white grey-text darken-4 waves-effect waves-red" href="#deleteProcedure${procedure.idprocedure}">Удалить процедуру</a>

                                            <!-- Modal Structure -->
                                            <div id="deleteProcedure${procedure.idprocedure}" class="modal">
                                                <div class="modal-content">
                                                    <h4>Вы уверены, что хотите удалить процедуру ${procedure.procedureName}?</h4>
                                                    <p>Действие не может быть отменено. Процедура будет удалена из базы данных.</p>
                                                </div>
                                                <div class="modal-footer">
                                                    <a class="modal-action modal-close waves-effect waves-red btn-flat" href="controller?command=deleteProcedure&id=<c:out value="${procedure.idprocedure}"/>">Удалить процедуру</a>
                                                    <a class="modal-action modal-close waves-effect waves-green btn-flat" href="#">Отмена</a>
                                                </div>
                                            </div>

                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>

                            <!-- Modal Trigger -->
                            <a class="waves-effect waves-light btn" href="#addProcedure${user.iduser}">Назначить процедуру</a>

                            <!-- Modal Structure -->
                            <div id="addProcedure${user.iduser}" class="modal">

                                <form name="AddProcedureForm" method="POST" action="controller">

                                    <div class="modal-content">

                                        <h4>Новая процедура для: ${user.name} ${user.surname} </h4>
                                        <h5>Диагноз: ${user.diagnosis} </h5>

                                        <input type="hidden" name="command" value="AddProcedure"/>
                                        <input type="hidden" name="iduser" value="${user.iduser}"/>

                                        <div class="row">
                                            <div class="input-field col s12">
                                                <input id="procedureName" type="text" class="validate" name="procedureName" length="45">
                                                <label for="procedureName">Название процедуры</label>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="input-field col s12">
                                                <textarea id="procedureDesc" class="materialize-textarea validate" name="procedureDesc"></textarea>
                                                <label for="procedureDesc">Описание процедуры</label>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="input-field col s12">
                                                <input id="procedureDuration" type="number" class="validate" name="procedureDuration" length="10">
                                                <label for="procedureDuration">Продолжительность процедуры (в минутах)</label>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col s12">
                                                <label for="procedureStartDate">Дата начала</label>
                                                <input id="procedureStartDate" type="date" class="datepicker" name="procedureStartDate">
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="input-field col s12">
                                                <input id="procedureHowManyTimes" type="number" class="validate" name="procedureHowManyTimes" length="10">
                                                <label for="procedureHowManyTimes">Количество раз</label>
                                            </div>
                                        </div>

                                    </div>
                                    <div class="modal-footer">

                                        <button class="modal-action modal-close waves-effect waves-green btn-flat" type="submit" name="action">Назначить процедуру
                                            <i class="material-icons right">add</i>
                                        </button>

                                    </div>
                                </form>
                            </div>

                            <p><strong>Операции</strong></p>
                            <ul class="collapsible" data-collapsible="expandable">

                                <c:forEach var="operation" items="${user.userOperationList}">
                                    <li>
                                        <div class="collapsible-header"><i class="material-icons">filter_drama</i>
                                                ${operation.operationName}
                                        </div>
                                        <div class="collapsible-body">


                                            <%--Вывод подробного описания--%>
                                            <div class="table-output">
                                                <div class="col l3">
                                                    <p><strong>ID</strong></p>
                                                    <p><strong>Название</strong></p>
                                                    <p><strong>Описание</strong></p>
                                                    <p><strong>Дата выполнения</strong></p>
                                                </div>
                                                <div class="col l9">
                                                    <p>${operation.idoperation}</p>
                                                    <p>${operation.operationName}</p>
                                                    <p>${operation.operationDesc}</p>
                                                    <p>${operation.operationDate}</p>
                                                </div>
                                            </div>

                                            <%--Удаление --%>

                                            <!-- Modal Trigger -->
                                            <a class="btn white grey-text darken-4 waves-effect waves-red" href="#deleteOperation${operation.idoperation}">Удалить операцию</a>

                                            <!-- Modal Structure -->
                                            <div id="deleteOperation${operation.idoperation}" class="modal">
                                                <div class="modal-content">
                                                    <h4>Вы уверены, что хотите удалить операцию ${operation.operationName}?</h4>
                                                    <p>Действие не может быть отменено. Операция будет удалена из базы данных.</p>
                                                </div>
                                                <div class="modal-footer">
                                                    <a class="modal-action modal-close waves-effect waves-red btn-flat" href="controller?command=deleteOperation&id=<c:out value="${operation.idoperation}"/>">Удалить операцию</a>
                                                    <a class="modal-action modal-close waves-effect waves-green btn-flat" href="#">Отмена</a>
                                                </div>
                                            </div>

                                        </div>
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

                                        <form name="AddOperationForm" method="POST" action="controller">

                                            <div class="modal-content">

                                                <h4>Новая процедура для: ${user.name} ${user.surname} </h4>
                                                <h5>Диагноз: ${user.diagnosis} </h5>

                                                <input type="hidden" name="command" value="AddOperation"/>
                                                <input type="hidden" name="iduser" value="${user.iduser}"/>

                                                <div class="row">
                                                    <div class="input-field col s12">
                                                        <input id="operationName" type="text" class="validate" name="operationName" length="45">
                                                        <label for="operationName">Название операции</label>
                                                    </div>
                                                </div>

                                                <div class="row">
                                                    <div class="input-field col s12">
                                                        <textarea id="operationDesc" class="materialize-textarea validate" name="operationDesc"></textarea>
                                                        <label for="operationDesc">Описание операции</label>
                                                    </div>
                                                </div>

                                                <div class="row">
                                                    <div class="col s12">
                                                        <label for="operationDate">Дата выполнения</label>
                                                        <input id="operationDate" type="date" class="datepicker" name="operationDate">
                                                    </div>
                                                </div>

                                            </div>
                                            <div class="modal-footer">

                                                <button class="modal-action modal-close waves-effect waves-green btn-flat" type="submit" name="action">Назначить операцию
                                                    <i class="material-icons right">add</i>
                                                </button>

                                            </div>
                                        </form>
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