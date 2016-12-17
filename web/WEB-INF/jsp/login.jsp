<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- Загрузка html, head, и начала body --%>
<jsp:include page="header.jsp"/>

<div class="container valign-wrapper">

    <div class="valign width100">
        <div class="row">
            <div class="col s4 offset-s4">
            <h5 class="center-align">Пожалуйста, войдите в систему</h5>
            <h6 class="center-align light">Чтобы войти в систему, воспользуйтесь логином и паролем, выданным Вам Вашим врачом.</h6>
            </div>
        </div>

        <div class="row">
            <div class="col s4 offset-s4 card z-depth-5">

                <form name="LoginForm" method="POST" action="controller" class="margin30px">
                    <input type="hidden" name="command" value="Login"/>

                    <div class="row">
                        <div class="input-field col s12">
                            <input id="login" type="text" class="validate" name="login" length="45" value="">
                            <label for="login">Логин</label>
                        </div>

                        <div class="input-field col s12">
                            <input id="password" type="password" class="validate" name="password" value="">
                            <label for="password">Пароль</label>
                        </div>

                        <h6 class="light center-align">
                        ${errorLoginOrPassMessage}
                        ${wrongAction}
                        ${nullPage}
                        </h6>

                    </div>

                    <button class="btn-large waves-effect waves-light width100" type="submit" name="action">
                        Войти
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>

<%-- Загрузка конца body, импорт js, html --%>
<jsp:include page="footer.jsp"/>