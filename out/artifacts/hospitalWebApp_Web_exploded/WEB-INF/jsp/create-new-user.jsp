<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%-- Загрузка html, head, и начала body --%>
<jsp:include page="header.jsp"/>

<div class="container">

    <div class="row">
        <div class="col l6 offset-l3 s12">
            <h5 class="center-align">Регистрация нового пользователя</h5>
            <h6 class="center-align light">Пожалуйста, заполните указанные поля, чтобы выполнить регистрацию.</h6>
        </div>
    </div>

    <div class="row">
        <div class="col l6 offset-l3 s12 card z-depth-5">

            <form name="CreateNewUserForm" method="POST" action="controller" class="margin30px">

                <input type="hidden" name="command" value="CreateNewUser"/>

                <div class="row">

                    <div class="input-field col s12">
                        <input id="login" type="text" class="validate" length="16" name="username" value="">
                        <label for="login">Логин (используется при входе в систему)</label>
                    </div>

                    <div class="input-field col s12">
                        <input id="password" type="password" class="validate" length="32" name="password" value="">
                        <label for="password">Пароль</label>
                    </div>

                    <div class="input-field col s12">
                        <input id="passwordCheck" type="password" class="validate" length="32" name="passwordCheck" value="">
                        <label for="passwordCheck">Повторите пароль:</label>
                    </div>

                    <div class="input-field col s12">
                        <input id="name" type="text" class="validate" length="45" name="name" value="">
                        <label for="name">Имя</label>
                    </div>

                    <div class="input-field col s12">
                        <input id="surname" type="text" class="validate" length="45" name="surname" value="">
                        <label for="surname">Фамилия</label>
                    </div>

                    <div class="input-field col s12">
                        <input id="patronymic" type="text" class="validate" length="45" name="patronymic" value="">
                        <label for="patronymic">Отчество (не обязательно)</label>
                    </div>

                    <div class="input-field col s12">
                        <input id="diagnosis" type="text" class="validate" length="45" name="diagnosis" value="">
                        <label for="diagnosis">Диагноз (не обязательно)</label>
                    </div>

                    <div class="input-field col s12">
                        <select name="status">
                            <option value="" disabled selected>Выберите статус из списка...</option>
                            <option value="0">Выписанный пациент</option>
                            <option value="1">Пациент</option>
                            <option value="2">Медсестра</option>
                            <option value="3">Врач</option>
                        </select>
                        <label>Статус пользователя</label>
                    </div>

                    <h6 class="light center-align">
                        ${errorLoginOrPassMessage}
                        ${wrongAction}
                        ${nullPage}
                    </h6>

                </div>

                <button class="btn-large waves-effect waves-light width100" type="submit" name="action">
                    Зарегистрировать пользователя
                </button>
            </form>
        </div>
    </div>

</div>
 <%--Загрузка конца body, импорт js, html --%>
<jsp:include page="footer.jsp"/>

