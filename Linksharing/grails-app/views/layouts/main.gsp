<!DOCTYPE html>
<html>
<head>

    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js?compile=false"></script>

    <g:layoutHead/>
</head>

<body>
<div class="container">
    <div class="page-header">
        <div class="row">
            <div class="col-sm-2">
                <a class="head-icon" style="float: left; margin-top: 13px;">LINK
                SHARING</a>
            </div>

            <div class="col-sm-offset-1 col-sm-3 search">
                <i class="fa fa-search" aria-hidden="true"></i>
                <input type="text" class="search_border " placeholder="search" name="q"/>
                <span class="glyphicon glyphicon-remove-sign  cross"></span>
            </div>


            <g:if test="${flash.error}">
                <g:message message="${flash.error}"></g:message>

            </g:if>
            <g:if test="${flash.message}">
                <g:message message="${flash.message}"></g:message>

            </g:if>
            <g:if test="${session.user != null}">
                <div class="col-sm-1">
                    <i class="fa fa-comment head-icon" aria-hidden="true"
                       style="font-size: 26px;" data-toggle="modal" data-target="#CreateTopic"></i>
                </div>

                <div class="col-sm-1">
                    <i class="fa fa-envelope-o head-icon" aria-hidden="true"
                       style="font-size: 26px;" data-toggle="modal" data-target="#SendInvite"></i>
                </div>

                <div class="col-sm-1">
                    <i class="fa fa-link head-icon" aria-hidden="true"
                       style="font-size: 26px;" data-toggle="modal" data-target="#shareLink"></i>
                </div>

                <div class="col-sm-1">
                    <i class="fa fa-file-o head-icon" aria-hidden="true"
                       style="font-size: 26px;" data-toggle="modal" data-target="#shareDocument"></i>
                </div>

                <div class="col-lg-1">
                    <div class="col-lg-1">
                        <i class="fa fa-user head-icon" style="font-size: 26px;"
                           aria-hidden="true"></i>
                    </div>
                    <div class="col-lg-2 dropdown">
                        <button class="btn-xs drop_button dropdown-toggle head-btn"
                                data-placement="left" data-toggle="dropdown">
                            <span class="caret"></span>

                        </button>
                        <ul class="dropdown-menu">
                            <li><a href="/user/editProfile">Profile</a></li>
                            <li><a href="#">Users</a></li>
                            <li><a href="#">Topic</a></li>
                            <li><a href="#">Post</a></li>
                            <li><a href="/login/logout">Logout</a></li>
                        </ul>
                    </div>


                </div>
            </g:if>
                </div>
            </div>
            </div>

        <g:layoutBody/>

</body>
<link
        href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
        rel="stylesheet">

<link rel="stylesheet"
      href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>





<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<asset:javascript src="application.js?compile=false"></asset:javascript>
</html>
