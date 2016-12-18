<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- Загрузка html, head, и начала body --%>
<jsp:include page="header.jsp"/>

<div class="container">
    <div class="row">
        <h3>Добро пожаловать, ${currentUser.name}!</h3>
    </div>

    <div class="row">
        <div class="col l3 s12">
            <h5 class="light">Ваши лекарства</h5>
        </div>
        <div class="col l9 s12">

            <c:choose>
                <c:when test="${currentUser.userDrugsList.size() > 0}">

                    <ul class="collapsible" data-collapsible="expandable">

                        <c:forEach var="drug" items="${currentUser.userDrugsList}">
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
                                                <a class="waves-effect waves-green btn-flat">Выписать пациента</a>
                                                <a class="waves-effect waves-green btn-flat">Выписать пациента</a>
                                                <a class="waves-effect waves-green btn-flat">Выписать пациента</a>
                                            </div>
                                        </div>

                                    </div>

                                </div>
                            </li>
                        </c:forEach>

                    </ul>

                </c:when>
                <c:otherwise>

                    <h6 class="light">Вам не было назначено лекарств.</h6>

                </c:otherwise>
            </c:choose>

        </div>
    </div>

    <div class="row">
        <div class="col l3 s12">
            <h5 class="light">Ваши процедуры</h5>
        </div>
        <div class="col l9 s12">

            <c:choose>
                <c:when test="${currentUser.userProceduresList.size() > 0}">

                    <ul class="collapsible" data-collapsible="expandable">

                        <c:forEach var="procedure" items="${currentUser.userProceduresList}">
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
                                    </div>

                                </div>
                            </li>
                        </c:forEach>

                    </ul>

                </c:when>
                <c:otherwise>

                    <h5 class="light">Вам не было назначено процедур.</h5>

                </c:otherwise>
            </c:choose>

        </div>
    </div>

    <div class="row">
        <div class="col l3 s12">
            <h5 class="light">Ваши операции</h5>
        </div>
        <div class="col l9 s12">

            <c:choose>
                <c:when test="${currentUser.userOperationList.size() > 0}">

                    <ul class="collapsible" data-collapsible="expandable">

                        <c:forEach var="operation" items="${currentUser.userOperationList}">
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

        </div>
    </div>

</div>
<%-- Загрузка конца body, импорт js, html --%>
<jsp:include page="footer.jsp"/>
