<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="java.lang.reflect.Method" %>
<%@ page import="org.example.crud_hestiajdbc_servlet.model.Admin" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Crud</title>
    <link rel="stylesheet" href="css/style.css"/>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet"/>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200"/>
</head>
<body style="--white: #fdfdfd; --dark-blue: #00224d; --red: #e20a3d; --light-red: #ff3263; --light-blue: #f4f8ff; --blue: #195198; background-color: #00285a;">
<div class="crud background">
    <%
        boolean success = (boolean) request.getAttribute("success");
        String log = (String) request.getAttribute("log");

        if (log != null)
        {
    %>
    <div class="alert" style="--alert-color: <%= success ? "#37c87b" : "#e20a3d" %>">
        <span class="material-symbols-outlined" style="font-variation-settings: 'FILL' 1">
            <%= success ? "check_circle" : "error" %>
        </span>
        <p><%= log %></p>
        <div class="close-alert">
            <i class="material-icons">close</i>
        </div>
    </div>
    <%
        }
    %>
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
            <ul>
                <li style="--position: 1">Admin</li>
                <li style="--position: 2">Boost</li>
                <li style="--position: 3">Filtros</li>
                <li style="--position: 4">Pagamento</li>
                <li style="--position: 5">Plano</li>
            </ul>
        </div>

        <%
            Field[] fields;
            // Using reflection to get the fields of the classes
            fields = Admin.class.getDeclaredFields();
//            fields = Boost.class.getDeclaredFields();
        %>

        <div class="table" style="--field-quantity: <%= fields.length %>">
            <div class="table-title">
                <h3><%= Admin.class.getSimpleName() %></h3>
                <div class="blue-button">Criar</div>
            </div>
            <div class="table-header">
                <%
                    // Dynamically create headers from the field names
                    for (Field field : fields) {
                        String fieldName = field.getName();
                %>
                <h3><%= fieldName.substring(1) %></h3>
                <%
                    }
                %>
                <i class="material-symbols-outlined" id="filter">filter_alt</i>
            </div>
            <div class="table-rows">
                <%
                    List<Admin> list = (List<Admin>) request.getAttribute("ListaAdmins");

                    if (list != null && !list.isEmpty()) {
                        for (Admin item : list) {
                %>
                <div class="row">
                    <%
                        for (Field field : fields) {
                            String getterMethodName = "get" + field.getName();
                            try {
                                Method getterMethod = Admin.class.getMethod(getterMethodName);
                                Object value = getterMethod.invoke(item);
                                if (value != null) {
                    %>
                    <p><%= value.toString() %></p>
                    <%
                    } else {
                    %>
                    <p class="null">null</p>
                    <%
                                }
                            } catch (NoSuchMethodException e) {
                                // Handle the case where the getter method is not found
                                e.printStackTrace();
                            }
                        }
                    %>
                    <i class="material-symbols-outlined" id="edit">edit</i>
                    <i class="material-symbols-outlined" id="delete">delete</i>
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
    document.querySelector('.close-alert').addEventListener('click', function() {
        document.querySelector('.alert').classList.add('hide-alert');
    });
</script>
</body>
</html>