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

    String[] regex = {"^([A-Z]\\p{L}+|[A-Z]\\p{L}+(\\s[A-Z]\\p{L}+)+)$", "^\\S{8,100}@germinare\\.org\\.br$", "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[^A-Za-z0-9])\\S{4,100}$", "^[1-9][0-9]{1,6},\\d{2}$", "^\\S+$", "^R\\$([1-9][0-9]{1,10}|[1-9])(,\\d{0,2})?$", "^([1-9][0-9]{1,3}|[1-9])(,\\d{0,2})?\\%$", "^((\\S[^[A-Za-z]]*|[A-Z]\\S[^[A-Z0-9]]*)|(\\S[^[A-Za-z]]*|[A-Z]\\S[^[A-Z0-9]]*)(\\s(\\S[^[A-Za-z]]*|\\S[^[A-Z0-9]]*))*)$"};
%>
<div id="edition-form" class="form-container closed-form" onsubmit="loading.classList.remove('hide-loading')">
    <%
        request.setAttribute("action", "update");
    %>
    <form action="<%= tableIdentifier %>" method="post"
            <% if (tableIdentifier.equals("admin")){%>
          enctype="multipart/form-data"
            <%}%>
    >
        <input type="hidden" name="action" value="<%= request.getAttribute("action") %>">
        <div class="form-title">
            <h3>Editar <%= tableIdentifier %>
            </h3>
            <i class="material-icons" id="close-form">close</i>
        </div>
        <% if (tableIdentifier.equals("admin")){%>
        <div class="input-container">
            <div class="input-container">
                <label for="cFoto-edit" id="custom-file-upload">Foto
                    <span class="material-symbols-outlined" style="font-variation-settings: 'FILL' 1; color: #37c87b" id="icon-photo-edit">
                     check_circle
                    </span>
                </label>
                <input type="file" id="cFoto-edit" name="cFoto" accept="image/*">
            </div>
        </div>
        <%}%>
        <% for (int i = 0; i < fieldNames.length; i++) { %>
        <div class="input-container <% if (Boolean.parseBoolean(ignoreField[i])) { %>hidden-input<% } %>">
            <% if (!regexIds[i].equals("10") && !regexIds[i].equals("11") && !regexIds[i].equals("12")) { %>
            <input
                    type=<%= Boolean.parseBoolean(ignoreField[i]) ? "hidden" : regexIds[i].equals("2") ? "password" : regexIds[i].equals("9") ? "date" : regexIds[i].equals("8") ? "email" : regexIds[i].equals("5") || regexIds[i].equals("6") ? "hidden" : "text" %>
                            name=<%= fieldTypes[i] %>
                    id=<%= fieldTypes[i] + "-edit" %>
                        <% if (!Boolean.parseBoolean(ignoreField[i]) && !regexIds[i].equals("5") && !regexIds[i].equals("6")  && !regexIds[i].equals("9")  && !regexIds[i].equals("8")) { %>
                            pattern="<%= regex[Integer.parseInt(regexIds[i])] %>"
                    <% } %>
                    placeholder=""
                    <% if (!regexIds[i].equals("9")) { %>required <%}%>
            />
            <% } else if (regexIds[i].equals("10")) { %>
            <select name="<%= fieldTypes[i] %>" id="<%= fieldTypes[i] %>" required>
                <option value="" disabled selected>Select one</option>
                <option value="0">Anunciante</option>
                <option value="1">Universitario</option>
            </select>
            <% } else if (regexIds[i].equals("11")) { %>
            <select name="<%= fieldTypes[i] %>" id="<%= fieldTypes[i] %>" required>
                <option value="" disabled selected>Select one</option>
                <option value="anunciante">Anunciante</option>
                <option value="universitario">Universitario</option>
            </select>
            <% } else { %>
            <select name="<%= fieldTypes[i] %>" id="<%= fieldTypes[i] %>" required>
                <option value="" disabled selected>Select one</option>
                <option value="-1">Inativo</option>
                <option value="0">Em andamento</option>
                <option value="1">Ativo</option>
                '
            </select>
            <% } %>
            <% if (regexIds[i].equals("5") || regexIds[i].equals("6")) { %>
            <input
                    type=<%= "text" %>
                            name=<%= fieldTypes[i] + "-edit-masked" %>
                    id=<%= fieldTypes[i] + "-edit-masked" %>
                            pattern=<%= regex[Integer.parseInt(regexIds[i])] %>
                    placeholder=""
                    class=<%= regexIds[i].equals("5") ? "currency" : "percentage"%>
                            required
            />
            <% } %>
            <% if (!Boolean.parseBoolean(ignoreField[i])) {%>
            <label for=<%= fieldTypes[i] %>><%= fieldNames[i] %>
            </label>
            <%}%>
        </div>
        <%
            }%>
        <input type="submit" value="Editar"/>
    </form>
</div>

<script>
    document.getElementById('cFoto-edit').addEventListener('change', function() {
        var icon = document.getElementById('icon-photo-edit');
        if (this.files && this.files.length > 0) {
            icon.innerHTML = 'check_circle';
            icon.style.color = '#37c87b'
        } else {
            icon.innerHTML = 'error';
            icon.style.color = '#e20a3d'
        }
    });
</script>