<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%-- Загрузка html, head, и начала body --%>
<jsp:include page="header.jsp"/>

<div class="container">

    <div class="row">
        <div class="col l6 offset-l3 s12">


            <h5 class="center-align">
                ${type}
            </h5>

            <h6 class="center-align light">Пожалуйста, заполните указанные поля, чтобы выполнить регистрацию.</h6>
        </div>
    </div>

    <div class="row">
        <div class="col l6 offset-l3 s12 card z-depth-5">

            <form name="CreateNewUserForm" method="POST" action="controller" class="margin30px">

                <div class="row">

                    <div class="input-field col s12">
                        <input id="username" type="text" class="validate" length="16" name="username" value="${user.username}">
                        <label for="username">Логин (используется при входе в систему)</label>
                    </div>

                    <div class="input-field col s12">
                        <input id="password" type="password" class="validate" length="32" name="password" value="${user.password}">
                        <label for="password" data-error="${wrongpassword}">Пароль</label>
                    </div>

                    <div class="input-field col s12">
                        <input id="passwordCheck" type="password" class="validate" length="32" name="passwordCheck" value="${user.password}">
                        <label for="passwordCheck" data-error="${wrongpassword}">Повторите пароль:</label>
                    </div>

                    <div class="input-field col s12">
                        <input id="name" type="text" class="validate" length="45" name="name" value="${user.name}">
                        <label for="name">Имя</label>
                    </div>

                    <div class="input-field col s12">
                        <input id="surname" type="text" class="validate" length="45" name="surname" value="${user.surname}">
                        <label for="surname">Фамилия</label>
                    </div>

                    <div class="input-field col s12">
                        <input id="patronymic" type="text" class="validate" length="45" name="patronymic" value="${user.patronymic}">
                        <label for="patronymic">Отчество (не обязательно)</label>
                    </div>


                    <%-- Позволять только пользователям "Врач" устанавливать диагноз и изменять статус--%>
                    <c:if test="${currentUser.idstatus==3}">

                        <div class="input-field col s12">
                            <input ${user.idstatus == 0 ? 'disabled' : ''} id="diagnosis" type="text" class="validate" length="45" name="diagnosis" value="${user.diagnosis}">
                            <label for="diagnosis">Диагноз (${user.idstatus == 0 ? 'не может изменяться для выписанного пациента' : 'не обязательно'})</label>
                        </div>

                        <div class="input-field col s12">
                            <select name="status">
                                <option value="" disabled>Выберите статус из списка...</option>
                                <option value="0" ${user.idstatus == 0 ? 'selected' : ''}>Выписанный пациент</option>
                                <option value="1" selected ${user.idstatus == 1 ? 'selected' : ''}>Пациент</option>
                                <option value="2" ${user.idstatus == 2 ? 'selected' : ''}>Медсестра</option>
                                <option value="3" ${user.idstatus == 3 ? 'selected' : ''}>Врач</option>
                            </select>
                            <label>Статус пользователя</label>
                        </div>

                    </c:if>

                    <h6 class="light center-align">
                        ${errorLoginOrPassMessage}
                        ${wrongAction}
                        ${nullPage}
                    </h6>

                </div>

                <c:choose>
                    <c:when test="${type.equals('Регистрация нового пользователя')}">
                        <input type="hidden" name="command" value="CreateNewUser"/>
                        <button class="btn-large waves-effect waves-light width100" type="submit" name="action">
                            Зарегистрировать пользователя
                        </button>
                    </c:when>
                    <c:otherwise>
                        <input type="hidden" name="command" value="UpdateUser"/>
                        <input type="hidden" name="id" value="${user.iduser}">
                        <button class="btn-large waves-effect waves-light width100" type="submit" name="action">
                            Обновить информацию
                        </button>
                    </c:otherwise>
                </c:choose>

            </form>
        </div>
    </div>

</div>
 <%--Загрузка конца body, импорт js, html --%>
<jsp:include page="footer.jsp"/>

