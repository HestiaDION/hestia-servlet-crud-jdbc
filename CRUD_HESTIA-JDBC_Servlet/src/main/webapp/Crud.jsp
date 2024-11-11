<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="icon" type="image/svg+xml" href="images/icons/hestia_icon-CXk1drsB.svg">    <title>Crud</title>
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
        List<String[]> list = (List<String[]>) request.getAttribute("list");

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
                fieldNames = "Id,Ativo,Data Final,Desconto,Total,Plano,E-mail,Tipo de Usuario";
                fieldTypes = "uId,cAtivo,dDtFim,nPctDesconto,nTotal,cNmPlano,cEmailUsuario,cTipoUsuario";
                ignoreField = "true,false,false,false,false,false,false,false";
                regexIds = "null,12,9,6,5,0,8,11";
                break;

            case "plano":
                fieldNames = "Id,Nome,Tipo de Usuário,Valor,Descrição";
                fieldTypes = "uId,cNome,cTipoUsuario,nValor,cDescricao";
                ignoreField = "true,false,false,false,false";
                regexIds = "null,0,11,5,7";
                break;

            case "plano_vantagem":
                fieldNames = "Id,Vantagem,Ativo,Nome do Plano";
                fieldTypes = "uId,cVantagem,cAtivo,cNmPlano";
                ignoreField = "true,false,false,false";
                regexIds = "null,7,12,0";
                break;

            default:
                fieldNames = "Id,Nome,E-mail,Senha";
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
        <div class="account">
            <p id="user-name"><%= request.getAttribute("user-name") %>
            </p>
            <img id="user-photo" src="data:image/png;base64, <%= request.getAttribute("user-photo") %>">
            <a href="login-admin.jsp"><i class="material-icons">logout</i></a>
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
                <h3><%= (tableIdentifier != "plano_vantagem") ? tableIdentifier : "Vantagens do " + request.getParameter("cNmPlano") %>
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
                    if (tableIdentifier == "plano") {
                %>
                <h3>Vantagens</h3>
                <%
                    }
                %>
                <i class="material-symbols-outlined" id="filter">filter_alt</i>
            </div>
            <div class="table-rows">
                <%
                    if (list != null && !list.isEmpty()) {

                %>
                <%

                    for (int i = 0; i < list.size(); i++) {
                %>
                <div class="row">
                    <%
                        for (int j = 0; j < list.get(i).length; j++) {
                            if (regexIds.split(",")[j].equals("5")) {
                    %>
                    <p title="R$<%= String.format("%.2f", Double.parseDouble(list.get(i)[j])).replace('.',',') %>">
                        R$<%= String.format("%.2f", Double.parseDouble(list.get(i)[j])).replace('.', ',') %>
                    </p>
                    <%
                    } else if (regexIds.split(",")[j].equals("6")) {
                    %>
                    <p title="<%= String.format("%.1f", Double.parseDouble(list.get(i)[j])).replace('.',',') %>%"><%= String.format("%.1f", Double.parseDouble(list.get(i)[j])).replace('.', ',') %>
                        %
                    </p>
                    <%
                    } else {
                    %>
                    <p title="<%= list.get(i)[j] %>">
                        <%= list.get(i)[j] %>
                    </p>
                    <%
                            }
                        }

                        if (tableIdentifier == "plano") {
                    %>
                    <p>
                        <span class="material-symbols-outlined" id="open-advantages" data-name="<%=list.get(i)[1]%>">open_in_new</span>
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
                <p>Nenhum registro foi encontrado.</p>
                <%
                    }
                %>
            </div>
        </div>
    </div>
</div>
<script>
    var userName = "<%= request.getAttribute("user-name") != null ? request.getAttribute("user-name") : "" %>";
    var userPhoto = "<%= request.getAttribute("user-photo") != null ? request.getAttribute("user-photo") : "" %>";

    // Verifica se as informações já estão no sessionStorage
    if (userName !== "" && userPhoto !== "") {
        // Pega os valores do request.getAttribute() diretamente do JSP
        var userName = "<%= request.getAttribute("user-name") %>";
        var userPhoto = "<%= request.getAttribute("user-photo") %>";

        // Armazena esses valores no sessionStorage
        sessionStorage.setItem("userName", userName);
        sessionStorage.setItem("userPhoto", userPhoto);
    }

    // Verifica se as informações já estão no sessionStorage
    if (sessionStorage.getItem("userName") != null && sessionStorage.getItem("userPhoto") != null) {
        // Verifica se as informações já estão no sessionStorage
        var storedUserName = sessionStorage.getItem("userName");
        var storedUserPhoto = sessionStorage.getItem("userPhoto");

        // Atualiza o conteúdo da página com essas informações
        document.getElementById("user-name").textContent = storedUserName;
        document.getElementById("user-photo").src = "data:image/png;base64, " + storedUserPhoto;
    }

    document.querySelector('.close-alert').addEventListener('click', function () {
        document.querySelector('.alert').classList.add('hide-alert');
    });

    const creationForm = document.getElementById("creation-form");
    const deleteForm = document.getElementById("confirm-delete");
    const editionForm = document.getElementById("edition-form");
    const filterForm = document.getElementById("filter-form");
    const loading = document.getElementById("loading");
    const currency = document.querySelectorAll('.currency');
    const percentage = document.querySelectorAll('.percentage');

    document.querySelectorAll('#close-form').forEach(close => {
        close.addEventListener('click', () => {
            document.querySelectorAll('.form-container').forEach(element => {
                element.classList.add("closed-form")
            });
        });
    })

    document.querySelectorAll('#create').forEach(element => {
        element.addEventListener('click', () => {
            if (<%= tableIdentifier.equals("plano_vantagem") %>) {
                const predicateExists = document.querySelector('#creation-form form input[name=predicate]');

                if (!predicateExists) {
                    let predicate = document.createElement('input');
                    predicate.type = 'hidden'; // Specify the type of input (e.g., 'text', 'email', 'number')
                    predicate.name = 'predicate'; // Set the name attribute
                    predicate.value = 'cNmPlano'

                    let input = document.createElement('input');
                    input.type = 'hidden'; // Specify the type of input (e.g., 'text', 'email', 'number')
                    input.name = 'cNmPlano'; // Set the name attribute
                    input.value = "<%= request.getParameter("cNmPlano") %>"
                    const cNmPlano = document.querySelector('#creation-form form input[name=cNmPlano]');
                    cNmPlano.value = "<%= request.getParameter("cNmPlano") %>"

                    creationForm.append(predicate)
                    creationForm.append(input)
                }
            }

            creationForm.classList.remove("closed-form")
        });
    });

    document.querySelectorAll('#delete').forEach(element => {
        element.addEventListener('click', () => {
            const uidInput = document.querySelector('#confirm-delete form input[name="uId"]');

            if (<%= tableIdentifier.equals("plano_vantagem") %>) {

                const predicateExists = document.querySelector('#confirm-delete form input[name=predicate]');

                if (!predicateExists) {
                    let input = document.createElement('input');
                    input.type = 'hidden'; // Specify the type of input (e.g., 'text', 'email', 'number')
                    input.name = 'cNmPlano'; // Set the name attribute
                    input.value = "<%= request.getParameter("cNmPlano") %>"
                    deleteForm.append(input)
                }
            }


            uidInput.value = element.dataset.uid;
            deleteForm.classList.remove("closed-form");
        });
    });

    document.querySelectorAll('#edit').forEach(element => {
        element.addEventListener('click', () => {
            const inputs = `<%= fieldTypes %>`.split(',');
            const values = element.dataset.values.split(",");

            inputs.forEach((inputName, index) => {
                if (inputName === "nValor" || inputName === "nPctBoost" || inputName === "nTotal" || inputName === "nPctDesconto") {
                    const input = document.querySelector('#edition-form form input[name=' + inputName + ']');
                    if (input) {
                        input.value = values[index]
                    }
                    inputName += "-edit-masked"
                }

                let input = document.querySelector('#edition-form form input[name=' + inputName + ']');
                if (inputName === "cTipoUsuario" || inputName === "cAtivo") {
                    input = document.querySelector('#edition-form form select[name=' + inputName + ']');
                }
                if (input) {
                    let value = values[index]
                    if (inputName === "nValor-edit-masked" || inputName === "nTotal-edit-masked") {
                        value += 0
                        value = formatAsMoney(value)
                    }
                    if (inputName === "nPctBoost-edit-masked" || inputName === "nPctDesconto-edit-masked") {
                        value += 0
                        value = formatAsPercentage(value)
                    }
                    if (inputName === "cTipoUsuario" && "<%=tableIdentifier%>" === "pagamento") {
                        console.log(value)
                        value = value.toLowerCase()
                        console.log(value)
                    }
                    input.value = value

                    console.log(inputName)
                    if ((inputName === "cNmPlano" && "<%=tableIdentifier%>" === "plano_vantagem") || (inputName === "cTipoUsuario" && "<%=tableIdentifier%>" === "pagamento") || (inputName === "cEmailUsuario" && "<%=tableIdentifier%>" === "pagamento")) {
                        input.readOnly = true;
                        console.log(input)
                    }
                }
            });

            editionForm.classList.remove("closed-form");
        });
    });

    document.querySelectorAll('#open-advantages').forEach(element => {
        element.addEventListener('click', () => {
            let predicate = document.createElement('input');
            predicate.type = 'hidden'; // Specify the type of input (e.g., 'text', 'email', 'number')
            predicate.name = 'predicate'; // Set the name attribute
            predicate.value = 'cNmPlano'

            let input = document.createElement('input');
            input.type = 'hidden'; // Specify the type of input (e.g., 'text', 'email', 'number')
            input.name = 'cNmPlano'; // Set the name attribute
            input.value = element.dataset.name

            const sidebar = document.getElementById("sidebar-form")

            // Set the form action to the desired servlet
            sidebar.action = 'plano_vantagem';
            sidebar.appendChild(predicate);
            sidebar.appendChild(input);

            loading.classList.remove("hide-loading")

            // Submit the form
            sidebar.submit();
        });
    });

    function changeTable(table) {
        // Set the form action to the desired servlet
        document.getElementById("sidebar-form").action = table;

        loading.classList.remove("hide-loading")

        // Submit the form
        document.getElementById("sidebar-form").submit();
    }

    currency.forEach(element => {
        element.addEventListener('input', function (e) {
            let value = e.target.value;

            // Remove all non-digit characters
            value = value.replace(/\D/g, '');

            // Format as currency
            if (value.length > 2) {
                value = (parseFloat(value) / 100).toFixed(2);
            } else if (value.length === 2) {
                value = '0.' + value;
            } else if (value.length === 1) {
                value = '0.0' + value;
            } else {
                value = '0.00';
            }

            // Update the input field with the formatted value
            e.target.value = 'R$' + value.replace('.', ',');

            // Store the raw value in a hidden field
            const input = document.querySelector('input[id=' + e.target.name.slice(0, -7) + ']');
            input.value = value.replace(',', '.');
        });
    });

    function formatAsMoney(text) {
        // Remove all non-digit characters
        let value = text.replace(/\D/g, '');

        // Format as currency
        if (value.length > 2) {
            value = (parseFloat(value) / 100).toFixed(2);
        } else if (value.length === 2) {
            value = '0.' + value;
        } else if (value.length === 1) {
            value = '0.0' + value;
        } else {
            value = '0.00';
        }

        // Update the input field with the formatted value
        return 'R$' + value.replace('.', ',');
    }

    function truncateToDecimals(num, decimals) {
        let factor = Math.pow(10, decimals);
        let truncatedNum = Math.trunc(num * factor) / factor;
        return truncatedNum.toFixed(decimals);
    }

    percentage.forEach(element => {
        element.addEventListener('input', function (e) {
            let value = e.target.value;
            value = value.replace(/%(\d+)$/, '$10');
            // Remove all non-digit characters
            value = value.replace(/\D/g, '');

            if (value.length > 2) {
                if (parseFloat(value) > 9999) {
                    value = '9999'
                }
                value = String(truncateToDecimals((parseFloat(value) / 100.0), 1))
            } else if (value.length === 2) {
                value = '0.' + value; // If the input is two digits, keep it as a fraction
            } else if (value.length === 1) {
                value = '0.0' + value; // If one digit, format to two decimal places
            } else {
                value = '0'; // If empty, just show zero
            }

            // Update the input field with the formatted value, adding "%" at the end
            e.target.value = value.replace('.', ',') + '%';

            // Store the raw value in a hidden field
            const input = document.querySelector('input[id=' + e.target.name.slice(0, -7) + ']');
            input.value = value.replace(',', '.'); // Store the raw percentage value
        });
    });

    function formatAsPercentage(text) {
        let value = text.replace('%', '0');
        value = value.replace(/\D/g, '');

        // Format as percentage without decimals unless explicitly typed
        if (value.length > 2) {
            if (parseFloat(value) > 10000) {
                value = '10000'
            }
            value = (parseFloat(value) / 100).toFixed(1);
        } else if (value.length === 2) {
            value = '0.' + value; // If the input is two digits, keep it as a fraction
        } else if (value.length === 1) {
            value = '0.0' + value; // If one digit, format to two decimal places
        } else {
            value = '0'; // If empty, just show zero
        }

        // Update the input field with the formatted value, adding "%" at the end
        return value.replace('.', ',') + '%';
    }

    document.getElementById("filter").addEventListener('click', () => {
        if(<%= !tableIdentifier.equals("plano_vantagem") %>) {
            filterForm.classList.toggle('hide-filters')
            if (<%= request.getParameter("predicate")%>) {
                changeTable("<%=tableIdentifier%>")
            }
        }
    })

    document.getElementById("predicate").addEventListener('input', (event) => {
        document.getElementById("filter-value").name = event.target.value;
    })

    if (<%= request.getParameter("predicate") != null && !tableIdentifier.equals("plano_vantagem") %>){
        filterForm.classList.remove('hide-filters')
        document.getElementById("predicate").value = "<%=request.getParameter("predicate")%>"
        document.getElementById("filter-value").value = "<%=request.getParameter(request.getParameter("predicate"))%>"
    }
</script>
</body>
</html>