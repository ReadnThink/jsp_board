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
    <title>회원가입</title>
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
    function JoinForm_submit(form) {

        let JoinForm_submitDone = false;
        if (JoinForm_submitDone) {
            alert('처리 중입니다.');
            return false;
        }

        let username = form.username.value.trim();
        let email = form.email.value.trim();
        let loginId = form.loginId.value.trim();
        let loginPw = form.loginPw.value.trim();
        let loginPwConfirm = form.loginPwConfirm.value.trim();

        if (username.length == 0) {
            alert('성함을 입력해 주세요.');
            return false;
        }

        if (email.length == 0) {
            alert('이메일을 입력해 주세요.');
            return false;
        }

        if (loginId.length == 0) {
            alert('아이디를 입력해 주세요.');
            return false;
        }
        if (loginPw.length == 0) {
            alert('비밀번호를 입력해 주세요.');
            return false;
        }
        if (loginPwConfirm != loginPw) {
            alert('비밀번호가 일치하지 않습니다.');
            return false;
        }
        alert('회원가입 성공');
        JoinForm_submitDone = true;
        return true;
    }
</script>

<section class="join_section">
    <div class="con">
        <h1>회원가입</h1>
        <form action="../join/doJoin" method="POST" onsubmit="return JoinForm_submit(this);">
            <div>이름 : <input name="username" placeholder="이름을 입력해 주세요" type="text"></div>
            <div>이메일 : <input name="email" placeholder="이메일을 입력해 주세요" type="email"></div>
            <div>아이디 : <input name="loginId" placeholder="아이디 입력해 주세요" type="text"></div>
            <div>패스워드 : <input name="loginPw" placeholder="비밀번호를 입력해 주세요" type="password"></div>
            <div>패스워드 확인 : <input name="loginPwConfirm" placeholder="비밀번호를 입력해 주세요" type="password"></div>
            <div class="btn-group">
                <button type="submit">회원가입</button>
                <button type="button">
                    <a href="/article/list">취소</a>
                </button>
            </div>
        </form>
    </div>
</section>

</body>
</html>