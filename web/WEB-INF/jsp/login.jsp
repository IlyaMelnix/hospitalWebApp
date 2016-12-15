<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- Загрузка html, head, и начала body --%>
<jsp:include page="header.jsp"/>

    <form  name="LoginForm" method="POST" action="controller">
        <input type="hidden" name="command" value="Login"/>
        Логин:<br/>
        <input type="text" name="login" value=""/>
        <br/>Пароль:<br/>
        <input type="password" name= "password" value=""/>
            <br/>
        ${errorLoginOrPassMessage}
            <br/>
        ${wrongAction}
            <br/>
        ${nullPage}
            <br/>

        <button class="btn waves-effect waves-light" type="submit" name="action">Войти
            <i class="material-icons right">send</i>
        </button>
    </form>
    <hr/>

<%-- Загрузка конца body, импорт js, html --%>
<jsp:include page="footer.jsp"/>