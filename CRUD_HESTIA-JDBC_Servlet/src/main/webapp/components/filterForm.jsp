<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String fieldNamesParam = request.getParameter("fieldNames");
    String fieldTypesParam = request.getParameter("fieldTypes");
//    String regexIdsParam = request.getParameter("regexIds");
    String tableIdentifier = request.getParameter("table-identifier");

    String[] fieldTypes = fieldTypesParam != null ? fieldTypesParam.split(",") : new String[0];
    String[] fieldNames = fieldNamesParam != null ? fieldNamesParam.split(",") : new String[0];
//    String[] regexIds = regexIdsParam != null ? regexIdsParam.split(",") : new String[0];

//    String[] regex = {"^([A-Z]\\p{L}+|[A-Z]\\p{L}+(\\s[A-Z]\\p{L}+)+)$", "^\\S{8,100}@germinare\\.org\\.br$", "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[^A-Za-z0-9])\\S{4,100}$", "^[1-9][0-9]{1,6},\\d{2}$", "^\\S+$", "^([1-9][0-9]{1,10}|[1-9]).\\d{0,2}$", "^([1-9][0-9]{1,3}|[1-9]).\\d{0,2}$"};
%>
<%
    request.setAttribute("action", "read");
%>
<form action="<%= tableIdentifier %>" method="get" id="filter-form" class="hide-filters" onsubmit="loading.classList.remove('hide-loading')">
    <input type="hidden" name="action" value="<%= request.getAttribute("action") %>">
<%--    <input type="hidden" name="uId" value="">--%>

    <label for="filter-value" id="filter-label">Mostre todos os <%= tableIdentifier %>s onde <select name="predicate" id="predicate">
        <% for (int i = 0; i < fieldTypes.length; i++) {%>
        <option value="<%= fieldTypes[i] %>"><%= fieldNames[i] %>
        </option>
        <%}%>

    </select> = </label>
    <input
            type="text"
            name="uId"
            id="filter-value"
    <%--                    pattern=<%= regex[Integer.parseInt(regexIds[i])] %>--%>
    <%--            <% if (regexIds[i].equals("5")) { %>--%>
    <%--            class="currency"--%>
    <%--            <% } %>--%>
            placeholder=""
            required
    />
    <input type="submit" value="Filtrar"/>
</form>