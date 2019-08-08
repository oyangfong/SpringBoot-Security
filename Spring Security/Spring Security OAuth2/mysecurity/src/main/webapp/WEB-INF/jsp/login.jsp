<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<body>
<%-- 注意：这里表单的action为 /login 提交方式为POST --%>
<form name='loginForm' action="/login" method='POST'>
    <table>
        <tr>
            <td>User:</td>
            <td><input type='text' name='username' value='admin'></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type='password' name='password' value='123456'/></td>
        </tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="submit" /></td>
        </tr>
    </table>
</form>

</body>

</html>