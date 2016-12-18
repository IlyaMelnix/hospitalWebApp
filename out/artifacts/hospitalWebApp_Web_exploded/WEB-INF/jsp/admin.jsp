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

            <ul class="collapsible popout" data-collapsible="accordion">

                <c:forEach var="user" items="${users}">
                <li>
                    <div class="collapsible-header grey lighten-5">
                        <i class="material-icons">account_circle</i>
                        ${user.name} ${user.surname}
                    </div>
                    <div class="collapsible-body grey lighten-4">


                        <div class="table-output padding30px padding-bottom-10">
                            <div class="row">
                                <div class="col l3 m3 s12">
                                    <p><strong>ID</strong></p>
                                </div>
                                <div class="col l9 m9 s12">
                                    <p>${user.iduser}</p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col l3 m3 s12">
                                    <p><strong>Логин</strong></p>
                                </div>
                                <div class="col l9 m9 s12">
                                    <p>${user.username}</p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col l3 m3 s12">
                                    <p><strong>Пароль</strong></p>
                                </div>
                                <div class="col l9 m9 s12">
                                    <p>${user.password}</p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col l3 m3 s12">
                                    <p><strong>Имя</strong></p>
                                </div>
                                <div class="col l9 m9 s12">
                                    <p>${user.name}</p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col l3 m3 s12">
                                    <p><strong>Фамилия</strong></p>
                                </div>
                                <div class="col l9 m9 s12">
                                    <p>${user.surname}</p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col l3 m3 s12">
                                    <p><strong>Отчество</strong></p>
                                </div>
                                <div class="col l9 m9 s12">
                                    <p>${user.patronymic}</p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col l3 m3 s12">
                                    <p><strong>Диагноз</strong></p>
                                </div>
                                <div class="col l9 m9 s12">
                                    <p>${user.diagnosis}</p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col l3 m3 s12">
                                    <p><strong>Статус</strong></p>
                                </div>
                                <div class="col l9 m9 s12">
                                    <p>${user.status}</p>
                                </div>
                            </div>

                            <div class="row">

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
                                <c:if test="${currentUser.idstatus==3}">

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

                                </c:if>
                            </div>

                        </div>

                        <div class="padding30px padding-top-10">

                            <div class="row">

                                <h6><strong>Лекарства</strong></h6>

                                <c:choose>
                                    <c:when test="${user.userDrugsList.size() > 0}">
                                    <ul class="collapsible" data-collapsible="expandable">

                                        <c:forEach var="drug" items="${user.userDrugsList}">
                                            <li>
                                                <div class="collapsible-header grey lighten-5"><i class="material-icons">local_drink</i>
                                                        ${drug.drugName}
                                                </div>
                                                <div class="collapsible-body grey lighten-4">

                                                        <%--Вывод подробного описания--%>

                                                    <div class="table-output padding30px ">
                                                        <div class="row">
                                                            <div class="col l3 m3 s12">
                                                                <p><strong>ID</strong></p>
                                                            </div>
                                                            <div class="col l9 m9 s12">
                                                                <p>${drug.iddrug}</p>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col l3 m3 s12">
                                                                <p><strong>Название</strong></p>
                                                            </div>
                                                            <div class="col l9 m9 s12">
                                                                <p>${drug.drugName}</p>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col l3 m3 s12">
                                                                <p><strong>Описание</strong></p>
                                                            </div>
                                                            <div class="col l9 m9 s12">
                                                                <p>${drug.drugDesc}</p>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col l3 m3 s12">
                                                                <p><strong>Способ применения</strong></p>
                                                            </div>
                                                            <div class="col l9 m9 s12">
                                                                <p>${drug.drugDosing}</p>
                                                            </div>
                                                        </div>

                                                        <div class="row">
                                                            <div class="col s12">
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
                                                    </div>
                                                </div>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                    </c:when>
                                    <c:otherwise>

                                        <h6 class="light">Пользователю ещё не было назначено лекарств.</h6>

                                    </c:otherwise>
                                </c:choose>

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
                            </div>
                            <div class="row">

                                <h6><strong>Процедуры</strong></h6>

                                <c:choose>
                                    <c:when test="${user.userProceduresList.size() > 0}">

                                    <ul class="collapsible" data-collapsible="expandable">

                                        <c:forEach var="procedure" items="${user.userProceduresList}">
                                            <li>
                                                <div class="collapsible-header grey lighten-5"><i class="material-icons">local_pharmacy</i>
                                                        ${procedure.procedureName}
                                                </div>
                                                <div class="collapsible-body grey lighten-4">


                                                        <%--Вывод подробного описания--%>
                                                    <div class="table-output padding30px">
                                                        <div class="row">
                                                            <div class="col l3 m3 s12">
                                                                <p><strong>ID</strong></p>
                                                            </div>
                                                            <div class="col l9 m9 s12">
                                                                <p>${procedure.idprocedure}</p>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col l3 m3 s12">
                                                                <p><strong>Название</strong></p>
                                                            </div>
                                                            <div class="col l9 m9 s12">
                                                                <p>${procedure.procedureName}</p>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col l3 m3 s12">
                                                                <p><strong>Описание</strong></p>
                                                            </div>
                                                            <div class="col l9 m9 s12">
                                                                <p>${procedure.procedureDesc}</p>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col l3 m3 s12">
                                                                <p><strong>Продолжительность</strong></p>
                                                            </div>
                                                            <div class="col l9 m9 s12">
                                                                <p>${procedure.procedureDuration} минут</p>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col l3 m3 s12">
                                                                <p><strong>Дата начала</strong></p>
                                                            </div>
                                                            <div class="col l9 m9 s12">
                                                                <p>${procedure.procedureStartDate}</p>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col l3 m3 s12">
                                                                <p><strong>Кол-во раз</strong></p>
                                                            </div>
                                                            <div class="col l9 m9 s12">
                                                                <p>${procedure.procedureHowManyTimes}</p>
                                                            </div>
                                                        </div>

                                                        <div class="row">
                                                            <div class="col s12">

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
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                    </c:when>
                                    <c:otherwise>

                                        <h6 class="light">Пользователю ещё не были назначены процедуры.</h6>

                                    </c:otherwise>
                                </c:choose>

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
                            </div>
                            <div class="row">

                                <h6><strong>Операции</strong></h6>

                                <c:choose>
                                    <c:when test="${user.userOperationList.size() > 0}">

                                    <ul class="collapsible" data-collapsible="expandable">

                                        <c:forEach var="operation" items="${user.userOperationList}">
                                            <li>
                                                <div class="collapsible-header grey lighten-5"><i class="material-icons">local_hospital</i>
                                                        ${operation.operationName}
                                                </div>
                                                <div class="collapsible-body grey lighten-4">

                                                        <%--Вывод подробного описания--%>
                                                    <div class="table-output padding30px">
                                                        <div class="row">
                                                            <div class="col l3 m3 s12">
                                                                <p><strong>ID</strong></p>
                                                            </div>
                                                            <div class="col l9 m9 s12">
                                                                <p>${operation.idoperation}</p>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col l3 m3 s12">
                                                                <p><strong>Название</strong></p>
                                                            </div>
                                                            <div class="col l9 m9 s12">
                                                                <p>${operation.operationName}</p>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col l3 m3 s12">
                                                                <p><strong>Описание</strong></p>
                                                            </div>
                                                            <div class="col l9 m9 s12">
                                                                <p>${operation.operationDesc}</p>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col l3 m3 s12">
                                                                <p><strong>Дата выполнения</strong></p>
                                                            </div>
                                                            <div class="col l9 m9 s12">
                                                                <p>${operation.operationDate}</p>
                                                            </div>
                                                        </div>

                                                        <div class="row">
                                                            <div class="col s12">

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
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                    </c:when>
                                    <c:otherwise>

                                        <h6 class="light">Вам не было назначено операций.</h6>

                                    </c:otherwise>
                                </c:choose>

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
                    </div>
                </li>
                </c:forEach>

            </ul>
        </div>
    </div>
</div>




<%-- Загрузка конца body, импорт js, html --%>
<jsp:include page="footer.jsp"/>