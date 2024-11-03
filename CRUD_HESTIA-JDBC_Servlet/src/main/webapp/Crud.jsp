<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.UUID" %>
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
<div id="loading" class="hide-loading"></div>
<div class="crud background">
    <%
        boolean success = (boolean) request.getAttribute("success");
        String log = (String) request.getAttribute("log");
        String tableIdentifier = (String) request.getAttribute("table-identifier");
        String fieldNames;
        String ignoreField;
        String regexIds;
        String fieldTypes;

        switch (tableIdentifier) {
            case "boost":
                fieldNames = "Id,Nome,Valor,Percentual,Descrição";
                fieldTypes = "uId,cNmBoost,nValor,nPctBoost,cDescricao";
                ignoreField = "true,false,false,false,false";
                regexIds = "null,0,5,6,7";
                break;

            case "filtro":
                fieldNames = "Id,Opção,Categoria";
                fieldTypes = "uId,cNome,cCategoria";
                ignoreField = "true,false,false";
                regexIds = "null,7,4";
                break;

            case "pagamento":
                fieldNames = "Id,Ativo,Data Fim,Total";
                fieldTypes = "uId,cAtivo,dDtFim,nTotal";
                ignoreField = "true,false,false,false";
                regexIds = "null,0,4,4,5";
                break;

//            private UUID uId;            // (UUID)
//            private String cNome;        // (VARCHAR(100))
//            private String cTipoUsuario; // ()
//            private double nValor;       // (DECIMAL(10,2))
//            private String cDescricao;   // (VARCHAR(MAX))

            case "plano":
                fieldNames = "Id,Nome,Tipo de Usuário,Valor,Descrição";
                fieldTypes = "uId,cNome,cTipoUsuario,nValor,cDescricao";
                ignoreField = "true,false,false,false,false";
                regexIds = "null,0,0,5,7";
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
    <jsp:include page="components/creationForm.jsp">
        <jsp:param name="table-identifier" value="<%=tableIdentifier%>"/>
        <jsp:param name="fieldNames" value="<%=fieldNames%>"/>
        <jsp:param name="fieldTypes" value="<%=fieldTypes%>"/>
        <jsp:param name="ignoreField" value="<%=ignoreField%>"/>
        <jsp:param name="regexIds" value="<%=regexIds%>"/>
    </jsp:include>
    <jsp:include page="components/editionForm.jsp">
        <jsp:param name="table-identifier" value="<%=tableIdentifier%>"/>
        <jsp:param name="fieldNames" value="<%=fieldNames%>"/>
        <jsp:param name="fieldTypes" value="<%=fieldTypes%>"/>
        <jsp:param name="ignoreField" value="<%=ignoreField%>"/>
        <jsp:param name="regexIds" value="<%=regexIds%>"/>
    </jsp:include>
    <jsp:include page="components/confirmDelete.jsp">
        <jsp:param name="table-identifier" value="<%=tableIdentifier%>"/>
    </jsp:include>
    <form action="login" class="hidden-input" method="post" id="logout">
    </form>
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
            <jsp:include page="components/filterForm.jsp">
                <jsp:param name="table-identifier" value="<%=tableIdentifier%>"/>
                <jsp:param name="fieldNames" value="<%=fieldNames%>"/>
                <jsp:param name="fieldTypes" value="<%=fieldTypes%>"/>
            </jsp:include>
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
                    <i class="material-symbols-outlined" id="edit"
                       data-values="<%=String.join(",",list.get(i))%>">edit</i>
                    <i class="material-symbols-outlined" id="delete" data-uId="<%= list.get(i)[0] %>">delete</i>
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

    const creationForm = document.getElementById("creation-form");
    const deleteForm = document.getElementById("confirm-delete");
    const editionForm = document.getElementById("edition-form");
    const filterForm = document.getElementById("filter-form");
    const loading = document.getElementById("loading");
    const currency = document.querySelectorAll('.currency');

    document.querySelectorAll('#close-form').forEach(close => {
        close.addEventListener('click', () => {
            document.querySelectorAll('.form-container').forEach(element => {
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
            const uidInput = document.querySelector('#confirm-delete form input[name="uId"]');

            uidInput.value = element.dataset.uid;
            deleteForm.classList.remove("closed-form");
        });
    });

    document.querySelectorAll('#edit').forEach(element => {
        element.addEventListener('click', () => {
            const inputs = `<%= fieldTypes %>`.split(',');
            const values = element.dataset.values.split(",");

            inputs.forEach((inputName, index) => {
                const input = document.querySelector('#edition-form form input[name=' + inputName + ']');
                if (input) {
                    input.value = values[index]
                }
            });

            editionForm.classList.remove("closed-form");
        });
    });

    function changeTable(table) {
        // Set the form action to the desired servlet
        document.getElementById("sidebar-form").action = table;

        loading.classList.remove("hide-loading")

        // Submit the form
        document.getElementById("sidebar-form").submit();
    }

    // currency.forEach(element => {
    //     element.addEventListener('input', (event) => {
    //         let inputValue = event.target.value.replace(/\D/g, ''); // Remove all non-digit characters
    //         if (inputValue) {
    //             inputValue = (parseFloat(inputValue) / 100).toFixed(2); // Convert to decimal
    //             event.target.value = 'R$' + inputValue.replace(',', '.'); // Format to R$xx.xx
    //         } else {
    //             event.target.value = ''; // Clear if no input
    //
    //         }
    //     })
    // });

    document.getElementById("filter").addEventListener('click', () => {
        filterForm.classList.toggle('hide-filters')
    })

    document.getElementById("predicate").addEventListener('input', (event) => {
        document.getElementById("filter-value").name = event.target.value;
    })
</script>
</body>
</html>