<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
    <meta name="layout" content="main">
</head>
<body>

<div class="container">
    <h2>User List</h2>
     <div class="table-responsive">
        <table class="table">
            <thead>
            <tr>

                <th>UserName</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <g:if test="${session.user?.admin}">
                <g:each in="${userList}" var="users">

                <tr>
                    <td>${users?.userName}</td>
                    <g:if test="users?.active">
                    <td>Active</td>
                    </g:if>
                    <g:else>
                        <td>deactivate</td>
                    </g:else>
                    <g:if test="${users?.active}">
                        <td style="background-color: green"><a href="/user/changeStatus?status=false&userId=${users?.id}">Deactivate</a> </td>
                    </g:if>
                    <g:else>
                        <td style="background-color: red"><a href="/user/changeStatus?status=true&userId=${users?.id}">Active</a></td>
                    </g:else>


                </tr>
                </g:each>

            </g:if>
            </tbody>
        </table>
    </div>


</div>

</body>
</html>
