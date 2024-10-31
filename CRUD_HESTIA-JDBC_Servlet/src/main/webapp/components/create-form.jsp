<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String fieldNamesParam = request.getParameter("fieldNames");
    String fieldTypesParam = request.getParameter("fieldTypes");
    String regexIdsParam = request.getParameter("regexIds");

    String[] fieldNames = fieldNamesParam != null ? fieldNamesParam.split(",") : new String[0];
    String[] fieldTypes = fieldTypesParam != null ? fieldTypesParam.split(",") : new String[0];
    String[] regexIds = regexIdsParam != null ? regexIdsParam.split(",") : new String[0];

    String[] regex = {"^[A-Z][a-z]+(\\s[A-Z][a-z]+)+$", "^\\S{1,8}@germinare\\.org\\.br$", "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W)\\S{1,8}$"};
%>
<div id="form-container">
    <%
        request.setAttribute("action", "create");
    %>
    <form action="login" method="post">
        <input type="hidden" name="action" value="<%= request.getAttribute("action") %>">
        <div class="form-title">
            <h3>Create Admin</h3>
            <i class="material-icons" id="close-form">close</i>
        </div>
        <% for (int i = 0; i < fieldNames.length; i++) { %>
        <div class="input-container">
            <input
                    type=<%= regexIds[i].equals("2") ? "password" : "text"%>
                    name=<%= fieldTypes[i] %>
                    id=<%= fieldTypes[i] %>
                    pattern=<%= regex[Integer.parseInt(regexIds[i])] %>
                    placeholder=""
                    required
            />
            <label for=<%= fieldTypes[i] %>><%= fieldNames[i] %></label>
        </div>
        <% } %>
        <input type="submit" value="Criar"/>
    </form>
</div>