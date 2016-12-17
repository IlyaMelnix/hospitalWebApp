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
                <c:when test="${currentUser.userDrugsList.size() != 0}">

                    <ul class="collapsible" data-collapsible="expandable">

                        <c:forEach var="drug" items="${currentUser.userDrugsList}">
                            <li>
                                <div class="collapsible-header"><i class="material-icons">filter_drama</i>
                                        ${drug.drugName}
                                </div>
                                <div class="collapsible-body">

                                    <%--<table class="highlight">--%>
                                        <%--<tbody>--%>
                                        <%--<tr>--%>
                                            <%--<td><strong>ID</strong></td>--%>
                                            <%--<td>${drug.iddrug}</td>--%>
                                        <%--</tr>--%>
                                        <%--<tr>--%>
                                            <%--<td><strong>Название</strong></td>--%>
                                            <%--<td>${drug.drugName}</td>--%>
                                        <%--</tr>--%>
                                        <%--<tr>--%>
                                            <%--<td><strong>Описание</strong></td>--%>
                                            <%--<td>${drug.drugDesc}</td>--%>
                                        <%--</tr>--%>
                                        <%--<tr>--%>
                                            <%--<td><strong>Способ применения</strong></td>--%>
                                            <%--<td>${drug.drugDosing}</td>--%>
                                        <%--</tr>--%>
                                        <%--</tbody>--%>
                                    <%--</table>--%>

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
                                    <div class="table-output">
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
                                    </div>

                                </div>
                            </li>
                        </c:forEach>

                    </ul>

                </c:when>
                <c:otherwise>

                    <h5 class="light">Вам не было назначено лекарств.</h5>

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
                <c:when test="${currentUser.userDrugsList.size() != 0}">

                    <ul class="collapsible" data-collapsible="expandable">

                        <c:forEach var="procedure" items="${currentUser.userProceduresList}">
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
                <c:when test="${currentUser.userDrugsList.size() != 0}">

                    <ul class="collapsible" data-collapsible="expandable">

                        <c:forEach var="operation" items="${currentUser.userOperationList}">
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

                                </div>
                            </li>
                        </c:forEach>

                    </ul>

                </c:when>
                <c:otherwise>

                    <h5 class="light">Вам не было назначено операций.</h5>

                </c:otherwise>
            </c:choose>

        </div>
    </div>

</div>
<%-- Загрузка конца body, импорт js, html --%>
<jsp:include page="footer.jsp"/>
