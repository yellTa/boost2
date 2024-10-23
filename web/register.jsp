<%--
  Created by IntelliJ IDEA.
  User: bbubb
  Date: 10/18/2024
  Time: 10:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="css/Register.css">
    <title>할 일 등록</title>
</head>
<body>
<div class="contents">
    <header>
        <div>
            <h1>할일 등록</h1>
        </div>
    </header>
    <section>
        <form id="myForm">
            <article>
                <div>
                    <span>어떤 일인가요?</span>
                </div>
                <div>
                    <input type="text" class="text" id="title" name="title" maxlength="24" placeholder="우리집 강아지 귀여워(24자까지)" required>
                </div>
            </article>
            <article>
                <div>
                    <span>누가 할일인가요?</span>
                </div>
                <div>
                    <input type="text" class="text" name="owner" style="width:300px;" id="owner" maxlength="10"
                           placeholder="이름을 입력해주세요"
                           required>
                </div>
            </article>
            <article>
                <div>
                    <span>우선순위를 선택하세요</span>
                </div>
                <div class="radioContainer">
                    <input type="radio" id="priority1" name="priority" value="1" checked>
                    <label for="priority1">1순위</label><br>

                    <input type="radio" id="priority2" name="priority" value="2">
                    <label for="priority2">2순위</label><br>

                    <input type="radio" id="priority3" name="priority" value="3">
                    <label for="priority3">3순위</label><br>
                </div>
            </article>

        <article>
            <div class="buttonContainer">
                <button class="left"><a href="http://localhost:8080/" class="left"><이전</a></button>
                <div>
                    <button class="right" type ='submit' id ="submitBtn">제출</button>
                    <button class="right" id="removeBtn">내용지우기</button>
                </div>
            </div>
        </article>
        </form>
    </section>
</div>
</body>
<script src="js/register.js" defer></script>
</html>
