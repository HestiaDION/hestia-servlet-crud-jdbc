<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String fieldNamesParam = request.getParameter("fieldNames");
    String fieldTypesParam = request.getParameter("fieldTypes");
    String regexIdsParam = request.getParameter("regexIds");
    String ignoreFieldParam = request.getParameter("ignoreField");
    String tableIdentifier = request.getParameter("table-identifier");

    String[] fieldNames = fieldNamesParam != null ? fieldNamesParam.split(",") : new String[0];
    String[] fieldTypes = fieldTypesParam != null ? fieldTypesParam.split(",") : new String[0];
    String[] regexIds = regexIdsParam != null ? regexIdsParam.split(",") : new String[0];
    String[] ignoreField = ignoreFieldParam != null ? ignoreFieldParam.split(",") : new String[0];

    String[] regex = {"^([A-Z]\\p{L}+|[A-Z]\\p{L}+(\\s[A-Z]\\p{L}+)+)$", "^\\S{8,100}@germinare\\.org\\.br$", "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[^A-Za-z0-9])\\S{4,100}$", "^[1-9][0-9]{1,6},\\d{2}$", "^\\S+$"};
%>
<div id="form-container" class="closed-form creation-form">
    <%
        request.setAttribute("action", "create");
    %>
    <form action="<%= tableIdentifier %>" method="post">
        <input type="hidden" name="action" value="<%= request.getAttribute("action") %>">
        <div class="form-title">
            <h3>Create <%= tableIdentifier %></h3>
            <i class="material-icons" id="close-form">close</i>
        </div>
        <% for (int i = 0; i < fieldNames.length; i++) {
            if (!Boolean.parseBoolean(ignoreField[i])) {%>
        <div class="input-container">
            <input
                    type=<%= regexIds[i].equals("2") ? "password" : "text"%>
                    name=<%= fieldTypes[i] %>
                    id=<%= fieldTypes[i] %>
                    pattern=<%= regex[Integer.parseInt(regexIds[i])] %>
                    placeholder=""
                    required
            />
            <label for=<%= fieldTypes[i] %>><%= fieldNames[i] %>
            </label>
        </div>
        <% }
        }%>
        <input type="submit" value="Criar"/>
    </form>
</div>