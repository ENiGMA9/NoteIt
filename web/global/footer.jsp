<%--
  Created by IntelliJ IDEA.
  User: Sebastian Axinte
  Date: 27-Aug-17
  Time: 10:19 PM

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${not empty alert}">
<script>
    window.onload = function() {
        setTimeout(function(){ $.notify({
            // options
            icon: 'glyphicon glyphicon-warning-sign',
            message: '${alert}'
        }, {
            // settings
            element: 'body',
            position: null,
            type: "success",
            allow_dismiss: true,
            newest_on_top: true,
            showProgressbar: true,
            placement: {
                from: "top",
                align: "right"
            },
            offset: 20,
            spacing: 10,
            z_index: 1031,
            delay: 5000,
            timer: 1,
            animate: {
                enter: 'animated fadeInDown',
                exit: 'animated fadeOutUp'
            },
            icon_type: 'class'
        });},500);
    }





</script></c:if>