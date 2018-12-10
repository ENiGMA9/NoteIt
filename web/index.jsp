<%--
  Created by IntelliJ IDEA.
  User: Sebastian Axinte
  Date: 24-Aug-17
  Time: 8:54 PM

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>


    <%@include file="global/header.jsp" %>

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


  </head>
  <body class="jumbotron vertical-center fontOverride">

    <div class="jumbotron-fluid vertical-center table-responsive">
      <div class="container col-sm-10 offset-sm-1 text-center">
        <div class="justify-content-center LOGO">
          NoteIT!
        </div>
        <form class="justify-content-center" action="content/handler" method="POST">
          <div class="form-group row vertical-center">
            <div class="form-group col-sm-6 offset-sm-3">
              <input name="noteTitle" class="form-control" type="text" value="Write the note title here" id="example-text-input">
            </div>
          </div>



          <div class="form-group">
            <textarea name="noteContent" class="form-control" id="actualNote" rows="5" maxlength="10000" >Write your note content here.</textarea>
            <div id="noteLen" class="col-form-legend">Note length: 0/10000</div>
          </div>

          <fieldset class="form-group">
            <legend>Privacy</legend>
            <div class="form-check">
              <label class="form-check-label">
                <input type="radio" class="form-check-input" name="privacyCheck" id="privacyOption1" value="public" checked>
                Public note - this will be available to anyone who has the link.
              </label>
            </div>
            <div class="form-check">
              <label class="form-check-label">
                <input type="radio" class="form-check-input" name="privacyCheck" id="privacyOption2" value="private">
                Private note - this will protect the note with a password. Input a password or leave blank to receive one.
              </label>
            </div>

            <div class="form-group col-sm-2 offset-sm-5">

              <input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
            </div>
          </fieldset>
          <button type="submit" class="btn btn-primary">Send</button>
        </form>
      </div>

    </div>
    <%@include file="/global/footer.jsp" %>
  </body>


</html>
