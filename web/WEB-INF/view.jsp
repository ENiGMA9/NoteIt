<%--
  Created by IntelliJ IDEA.
  User: Sebastian Axinte
  Date: 27-Aug-17
  Time: 4:51 PM

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../global/header.jsp" %>

    <c:choose>
        <c:when test="${sessionScope.token.contentEquals(token)}">
            <nav class="navbar navbar-toggleable-md navbar-light bg-faded">

                <div class="navbar-brand">Owner Token: ${token}</div>

                <div class="row spacer">
                    <div class="span4"></div>
                </div>


                    <c:if test="${not empty password}">
                <div class="navbar-brand">Note Password: ${password}</div>
                    </c:if>
                <div class="row spacer">
                    <div class="span4"></div>
                </div>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <form class="form-inline my-2 my-lg-0" method="post">
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Edit Note</button>
                        <input type="hidden" name="scope" value="editMode">
                    </form>
                </div>
            </nav>
        </c:when>
        <c:otherwise>
            <form class="form-inline"  method="post">
                <label class="sr-only" for="tokenForm">Owner Token:</label>
                <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                    <div class="input-group-addon">Owner Token</div>
                    <input type="password" name="token" class="form-control" id="tokenForm">
                    <input type="hidden" name="scope" value="tokenAuth">
                </div>

                <button type="submit" class="btn btn-primary">Identify</button>
            </form>
        </c:otherwise>
    </c:choose>
</head>




<body class="jumbotron align-content-center">
<c:choose>
    <c:when test="${(empty password || sessionScope.password.contentEquals(password) || sessionScope.token.contentEquals(token)) and empty sessionScope.editMode}">

<h1 class="display-3">${title}</h1>

<hr class="my-4">
<p>
<p class="card-text">${content}</p>

<p class="lead">
</p>

    </c:when>

    <c:when test="${(sessionScope.token.contentEquals(token)) and sessionScope.editMode}">
        <script>
            window.onload = function() {
                var note = document.getElementById("actualNote");
                var maxlen = note.getAttribute("maxlength");
                var noteLenLabel = document.getElementById("noteLen");
                var func = function() {
                    noteLenLabel.innerHTML = "Note length: " + note.value.length + "/" + maxlen;
                }

                note.onkeyup = func;
                note.onblur = func;
            }

        </script>
<div class="jumbotron-fluid vertical-center table-responsive">
    <div class="container col-sm-10 offset-sm-1 text-center">
        <form class="justify-content-center"  method="POST">
            <div class="form-group row vertical-center">
                <div class="form-group col-sm-6 offset-sm-3">
                    <input name="newTitle" class="form-control" type="text" value="${title}" id="example-text-input">
                </div>
            </div>

            <div class="form-group">
                <textarea name="newContent" class="form-control" id="actualNote" rows="5" maxlength="10000" >${content}</textarea>
                <div id="noteLen" class="col-form-legend">Note length: 0/10000</div>
            </div>

            <input type="hidden" name="token" value="${sessionScope.token}">
            <input type="hidden" name="scope" value="update">
            <button type="submit" class="btn btn-primary">Update</button>
        </form>
    </div>
</div>
</c:when>

    <c:otherwise>
        This note requires a password to access. Alternatively you can identify yourself with the edit token above. </br>

        <form class="form-inline" method="post">
            <label class="sr-only" for="passwordForm">Note Password:</label>
            <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                <div class="input-group-addon">Note Password</div>
                <input type="hidden" name="scope" value="passwordAuth">
                <input type="password" name="pass" class="form-control" id="passwordForm">
            </div>

            <button type="submit" class="btn btn-primary">Access</button>
        </form>

    </c:otherwise>
</c:choose></p></p>


<%@include file="/global/footer.jsp" %>
</body>


</html>
