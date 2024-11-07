<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String tableIdentifier = request.getParameter("table-identifier");
%>
<div id="confirm-delete" class="form-container closed-form" onsubmit="loading.classList.remove('hide-loading')">
    <%
        request.setAttribute("action", "delete");
    %>
    <form action="<%= tableIdentifier %>" method="post">
        <input type="hidden" name="action" value="<%= request.getAttribute("action") %>">
        <input type="hidden" name="uId">
        <div class="form-title">
            <h3>Gostaria de deletar o registro?</h3>
        </div>
        <div class="confirmation-buttons">
            <input type="submit" value="Sim">
            <button type="button" id="close-form">Não</button>
        </div>
    </form>
</div>
