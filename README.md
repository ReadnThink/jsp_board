﻿# jsp_board

# 톰캣과 서블릿 그리고 Request, Response

## 톰캣
JSP를 실행하기 위해서는 서블릿 컨테이너가 필요합니다. <br/>
**톰캣**은 서블릿 컨테이너중 하나입니다.

서블릿 컨테이너는 웹 애플리케이션을 실행하기 위한 **런타임 환경**을 제공합니다.<br/>
클라이언트의 요청이 들어오면 서블릿 컨테이너가 필요한 서블릿 또는 JSP를 실행하여 웹 애플리케이션의 동적인 컨텐츠를 생성합니다.

## 서블릿
여기서 말하는 서블릿이란 HttpServlet을 상속받은 클래스입니다. <br/>
오버라이드한 doGet, doPost 메서드에서 Request, Response 객체를  사용하여 클라이언트의 요청과 응답에 대한 정보를 처리합니다!<br/>

### 단점
1. HTML 구조를 파악하기 쉽지 않습니다. <br/>
2. HTML, css를 append를 하여 하나하나 적용하는것이 너무 어렵습니다.

아래와 같은 구조를 쉽게 파악하는것이 어렵습니다.
```java
@WebServlet("/gugudan")
public class GugudanServlet extends HttpServlet {
    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        ServletResponseDto servletResponseDto = new ServletResponseDto(req, resp);

        int dan = servletResponseDto.getIntParam(("dan"), 9);
        int limit = servletResponseDto.getIntParam(("limit"), 9);

        servletResponseDto.appendBody("<h1>%d단</h1>\n".formatted(dan));
        for (int i = 1; i <= limit; i++) {
            servletResponseDto.appendBody(("<div>%d * %d = %d <div>\n".formatted(dan, i, dan * i)));
        }
    }
}
```

따라서 Jsp를 사용해 HTML을 구조화하여 UI를 관리할 것입니다. <br/>
동적인 데이터는 Servlet을 통해서 받아오고 HTML을 java코드에서 분리할여 관리하는 것입니다.

# Provided란?

"provided" 스코프는 개발 및 빌드 시에만 필요한 라이브러리로서, 배포된 애플리케이션에서는 포함되지 않고, 독립적으로 실행되는 서버나 컨테이너에서 해당 라이브러리를 제공할 것으로 가정합니다.

예를 들어, 웹 애플리케이션을 개발하는 경우, 서블릿 API를 사용하는 라이브러리를 "provided" 스코프로 선언할 수 있습니다. 이렇게 하면 개발 및 빌드 시에는 해당 라이브러리가 필요하지만, 웹 애플리케이션을 실행하는 서블릿 컨테이너(예: 톰캣)가 해당 라이브러리를 이미 제공하므로, 배포된 WAR 파일에는 해당 라이브러리가 포함되지 않습니다.

Maven의 의존성 스코프를 설정함으로써 프로젝트의 빌드와 배포에 필요한 라이브러리를 효율적으로 관리할 수 있습니다.

# ServletResponseDto 클래스
Http Servlet 의 Request 와 Response 관련 로직을 별도의 Class 인ServletResponseDto 로 분리하여
반복되는 코드를 재사용 할 수 있게 하였습니다.

# Jsp 처리 과정
![image](https://github.com/ReadnThink/JSP_community_2023_07_25-/assets/103480627/50af651f-7718-4318-9fa7-fbdb26fce045)

WAS는 JSP 페이지에 대한 요청이 들어오면 다음과 같은 처리를 합니다.
* JSP에 해당하는 서블릿이 존재하지 않을 경우 (1.1)
    * JSP 페이지로부터 자바 코드를 생성
    * 자바 코드를 컴파일해서 서블릿 클래스 생성
    * 서블릿에 클라이언트 요청 전달
    * 서블릿이 요청을 처리한 결과를 응답으로 생성
    * 응답을 웹 브라우저에 전송
* JSP에 해당하는 서블릿이 존재하는 경우 (즉, 이미 과정 1.1~1.3을 거친 경우)
    * 서블릿에 클라이언트 요청을 전달
    * 서블릿이 요청을 처리한 결과를 응답으로 생성
    * 응답을 웹 브라우저에 전송

> JSP 페이지를 요청할 때에는 JSP를 직접 실행하는 것이 아니라 <br/> 
> JSP를 자바 소스로 변환한 뒤 컴파일해서 생성한 서블릿을 실행하는 것입니다.
> <br/>
> <br/>
> 즉, JSP를 실행한다는 말은 JSP 페이지를 컴파일한 결과인 서블릿 클래스를 실행한다는 의미입니다.

# 트러블 슈팅

## 한글 설정

한글을 사용한다고 알려주어야 합니다.

```java
req.setCharacterEncoding("UTF-8"); //들어오는 데이터를 UTF-8로 해석하겠다.
resp.setCharacterEncoding("UTF-8"); // 반환하는 HTML의 인코딩의 UTF-8로 하겠다.
resp.setContentType("text/html; charset-utf-8"); // 브라우저에게 우리가 만든 결과물이 UTF-8이라고 알려줌
```
