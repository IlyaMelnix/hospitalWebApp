<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%-- Загрузка html, head, и начала body --%>
<jsp:include page="header.jsp"/>

<div class="container">

    <div class="col s12">
        <h1>Регистрация нового пользователя</h1>

        <form name="CreateNewUserForm" method="POST" action="controller">
            <input type="hidden" name="command" value="CreateNewUser"/>
            <br/><label>Заполните, пожалуйста, поля регистрации:</label>
            Логин (используется при входе в систему):<br/>
            <input type="text" name="username" value=""/>
            <br/>Пароль:<br/>
            <input type="password" name="password" value=""/>
            <br/>Повторите пароль:<br/>
            <input type="password" name="passwordCheck" value=""/>
            <br/><hr/>
            <br/>Имя:<br/>
            <input type="text" name="name" value=""/>
            <br/>Фамилия:<br/>
            <input type="text" name="surname" value=""/>
            <br/>Отчество (не обязательно):<br/>
            <input type="text" name="patronymic" value=""/>
            <br/><hr/>
            <br/>Диагноз (не обязательно):<br/>
            <input type="text" name="diagnosis" value=""/>
            <br/><hr/>
            <select size="5" name="status">
                <option disabled>Выберите статус из списка...</option>
                <option value="0">Выписанный пациент</option>
                <option value="1">Пациент</option>
                <option value="2">Медсестра</option>
                <option value="3">Врач</option>
            </select>

            <%--<div class="input-field col s12">--%>
                <%--<select size="5" name="status">--%>
                    <%--<optgroup label="Пациенты">--%>
                        <%--<option value="0">Выписанный пациент</option>--%>
                        <%--<option value="1">Пациент</option>--%>
                    <%--</optgroup>--%>
                    <%--<optgroup label="Врачи">--%>
                        <%--<option value="2">Медсестра</option>--%>
                        <%--<option value="3">Врач</option>--%>
                    <%--</optgroup>--%>
                <%--</select>--%>
                <%--<label>Выберите статус из списка...</label>--%>
            <%--</div>--%>
            ${errorUserIsNotCreated}
            <br/>
            ${wrongAction}
            <br/>
            ${nullPage}
            <br/>
            <input type="submit" value="Зарегистрировать пользователя"/>
        </form>
    </div>
</div>
 <%--Загрузка конца body, импорт js, html --%>
<jsp:include page="footer.jsp"/>

