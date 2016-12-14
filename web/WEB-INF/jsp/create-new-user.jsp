<%--
  Created by IntelliJ IDEA.
  User: ilyah
  Date: 15.12.2016
  Time: 0:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация нового пользователя</title>
</head>
<body>
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
        ${errorUserIsNotCreated}
        <br/>
        ${wrongAction}
        <br/>
        ${nullPage}
        <br/>
        <input type="submit" value="Зарегистрировать пользователя"/>
    </form>
</body>
</html>
