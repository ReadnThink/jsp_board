<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="com.jsp.board.util.ServletResponseDto"%>

<%
ServletResponseDto servletResponseDto = new ServletResponseDto(request, response);
%>

<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>로그인</title>
</head>
<body>
<style>
    body, ul, li {
        margin: 0;
    }

    .section {
        display: flex;
        justify-content: center;
    }
</style>


<script>
    function LoginForm_submit(form) {

        let LoginForm_submitDone = false;
        if (LoginForm_submitDone) {
            alert('처리 중입니다.');
            return false;
        }

        let loginId = form.loginId.value.trim();
        let loginPw = form.loginPw.value.trim();



        if (loginId.length == 0) {
            alert('아이디를 입력해 주세요.');
            return false;
        }
        if (loginPw.length == 0) {
            alert('비밀번호를 입력해 주세요.');
            return false;
        }

        LoginForm_submitDone = true;
        return true;
    }
</script>

<section class="login_section">
    <div class="con">
        <h1>로그인</h1>
        <form action="../user/doLogin" method="POST" onsubmit="return LoginForm_submit(this);">
            <div>아이디 : <input name="loginId" placeholder="아이디 입력해 주세요" type="text"></div>
            <div>패스워드 : <input name="loginPw" placeholder="비밀번호를 입력해 주세요" type="password"></div>
            <div class="btn-group">
                <button type="submit">로그인</button>
                <button type="button">
                    <a href="/article/list">취소</a>
                </button>
            </div>
        </form>
    </div>
</section>

</body>
</html>