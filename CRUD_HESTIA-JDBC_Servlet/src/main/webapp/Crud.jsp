<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Crud</title>
    <link rel="stylesheet" href="css/style.css"/>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet"/>
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200"/>
</head>
<body style="--white: #fdfdfd; --dark-blue: #00224d; --red: #e20a3d; --light-red: #ff3263; --light-blue: #f4f8ff; --blue: #195198; background-color: #00285a;">
<div class="crud background">
    <%
        boolean success = (boolean) request.getAttribute("success");
        String log = (String) request.getAttribute("log");
        String tableIdentifier = (String) request.getAttribute("table-identifier");
        String fieldNames;
        String ignoreField;
        String regexIds;
        String fieldTypes;

        switch (tableIdentifier)
        {
            case "boost":
                fieldNames = "Id,Nome,Valor,Percentual,Descrição";
                fieldTypes = "uId,cNmBoost,nValor,nPctBoost,cDescricao";
                ignoreField = "true,false,false,false,false";
                regexIds = "null,4,3,3,4";
                break;

            case "filtro":
                fieldNames = "Id,Nome,Categoria";
                fieldTypes = "uId,cNome,cCategoria";
                ignoreField = "true,false,false";
                regexIds = "null,0,0";
                break;

            default:
                fieldNames = "Id,Nome,Email,Senha";
                fieldTypes = "uId,cNome,cEmail,cSenha";
                ignoreField = "true,false,false,false";
                regexIds = "null,0,1,2";
                break;
        }

        if (log != null) {
    %>
    <div class="alert" style="--alert-color: <%= success ? "#37c87b" : "#e20a3d" %>">
        <span class="material-symbols-outlined" style="font-variation-settings: 'FILL' 1">
            <%= success ? "check_circle" : "error" %>
        </span>
        <p><%= log %>
        </p>
        <div class="close-alert">
            <i class="material-icons">close</i>
        </div>
    </div>
    <%
        }
    %>
    <jsp:include page="components/create-form.jsp">
        <jsp:param name="table-identifier" value="<%=tableIdentifier%>"/>
        <jsp:param name="fieldNames" value="<%=fieldNames%>"/>
        <jsp:param name="fieldTypes" value="<%=fieldTypes%>"/>
        <jsp:param name="ignoreField" value="<%=ignoreField%>"/>
        <jsp:param name="regexIds" value="<%=regexIds%>"/>
    </jsp:include>
    <div class="nav" id="crud-nav">
        <div class="header-title">
            <img src="images/icons/hestia.svg" alt="" id="logo"/>
            <div class="vertical-line"></div>
            <h5>ADMIN</h5>
            <label for="check">
                <i class="material-icons" id="open-menu">menu</i>
            </label>
        </div>
        <div class="acount">
            <p>Flopa fofa</p>
            <img src="images/perfil.png" alt=""/>
            <a href="index.html"><i class="material-icons">logout</i></a>
        </div>
    </div>
    <input type="checkbox" id="check"/>
    <div class="content">
        <div class="sidebar">
            <div class="sidebar-title">
                <i class="material-symbols-outlined" id="open-sidebar">data_table</i>
                <h3>Tabelas</h3>
            </div>
            <%
                request.setAttribute("action", "read");
            %>
            <form id="sidebar-form" method="get">
                <input type="hidden" name="action" value="<%= request.getAttribute("action") %>">
                <button style="--position: 1" type="button" onclick="changeTable('admin')">Admin</button>
                <button style="--position: 2" type="button" onclick="changeTable('boost')">Boost</button>
                <button style="--position: 3" type="button" onclick="changeTable('filtro')">Filtro</button>
                <button style="--position: 4" type="button" onclick="changeTable('pagamento')">Pagamento</button>
                <button style="--position: 5" type="button" onclick="changeTable('plano')">Plano</button>
            </form>
        </div>

        <div class="table">
            <div class="table-title">
                <h3><%= tableIdentifier %>
                </h3>
                <div class="blue-button" id="create">Criar</div>
            </div>
            <div class="table-header">
                <%
                    // Dynamically create headers from the field names
                    for (String fieldName : fieldNames.split(",")) {
                %>
                <h3><%= fieldName %>
                </h3>
                <%
                    }
                %>
                <i class="material-symbols-outlined" id="filter">filter_alt</i>
            </div>
            <div class="table-rows">
                <%
                    List<String[]> list = (List<String[]>) request.getAttribute("list");
                    if (list != null && !list.isEmpty()) {

                %>
                <%

                    for (int i = 0; i < list.size(); i++) {
                %>
                <div class="row">
                    <%
                        for (int j = 0; j < list.get(i).length; j++) {
                    %>
                    <p><%= list.get(i)[j] %>
                    </p>
                    <%
                        }
                    %>
                    <form id="edit-delete-form<%=i%>" method="post" action="<%= tableIdentifier %>">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="uId" value="<%= list.get(i)[0] %>">
                        <i class="material-symbols-outlined" id="edit">edit</i>
                        <i class="material-symbols-outlined" id="delete"
                           onclick="editOrDelete('edit-delete-form<%=i%>')">delete</i>
                    </form>
                </div>
                <%
                    }
                } else {
                %>
                <p>No records found.</p>
                <%
                    }
                %>
            </div>
        </div>
    </div>
</div>
<script>
    document.querySelector('.close-alert').addEventListener('click', function () {
        document.querySelector('.alert').classList.add('hide-alert');
    });

    const creationForm = document.querySelector(".creation-form");

    document.querySelectorAll('#close-form').forEach(close => {
        close.addEventListener('click', () => {
            console.log('bbb')
            document.querySelectorAll('#form-container').forEach(element => {
                console.log('aaaaaaa')
                element.classList.add("closed-form")
            });
        });
    })

    document.querySelectorAll('#create').forEach(element => {
        element.addEventListener('click', () => {
            creationForm.classList.remove("closed-form")
        });
    });

    document.querySelectorAll('#delete').forEach(element => {
        element.addEventListener('click', () => {

        });
    });

    function changeTable(table) {
        // Set the form action to the desired servlet
        document.getElementById("sidebar-form").action = table;
        // Submit the form
        document.getElementById("sidebar-form").submit();
    }

    function editOrDelete(formId) {
        document.getElementById(formId).submit();
    }

</script>
</body>
</html>