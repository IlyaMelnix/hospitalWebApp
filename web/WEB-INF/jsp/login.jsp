<%@ page  language="java" contentType= "text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Login</title>
    </head>
    <body>
        <form  name="LoginForm" method="POST" action="controller">
            <input type="hidden" name="command" value="Login"/>
            Login:<br/>
            <input type="text" name="login" value=""/>
            <br/>Password:<br/>
            <input type="password" name= "password" value=""/>
                <br/>
            ${errorLoginOrPassMessage}
                <br/>
            ${wrongAction}
                <br/>
            ${nullPage}
                <br/>
            <input type= "submit" value="Log in" class="waves-effect waves-light btn indigo darken-4"/>
        </form>
        <hr/>
    </body>
</html>
