<%--
  Created by IntelliJ IDEA.
  User: prachi
  Date: 28/3/18
  Time: 8:40 PM
--%>
%{--
Login should work from login form on home page and show top posts and recent post on home page--}%
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
    <meta name="layout" content="main">
</head>

<body>

<div class="container">
    <div class="row">
        <div class="col-lg-6" >
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-sm-12">
                            Recent Shares
                        </div>

                    </div>
                </div>

                <div class="panel-body">

                    <div class="row heading">

                        <g:each in="${com.ttn.linksharing.Resource?.recentShares()}"
                                var="recentShares">
                            <div class="row">
                                <div class="row">
                                    <div class=" col-lg-offset-1 col-lg-2">
                                        <ls:userImage id="${recentShares?.user?.id}"></ls:userImage>
                                    </div>
                                    <div class=" col-lg-9">
                                        <div class="row">
                                            <div class="col-sm-7">
                                                    <span>${recentShares?.user?.userName}</span> <small
                                                    class="text-muted">@${recentShares?.user?.firstName}
                                                5min</small>
                                            </div>
                                            <div class="cil-sm-offset-4 col-sm-1">
                                             <a href='/topic/show?topicId=${recentShares?.topic?.id}' class="anchor">${recentShares?.topic?.name}</a>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-11" style="padding-right: 23px">
                                                ${recentShares?.description}</div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-6">
                                                <span> <i class="fa fa-facebook-square"
                                                          aria-hidden="true"></i> <i class="fa fa-tumblr"
                                                                                     aria-hidden="true"></i> <i class="fa fa-google-plus"
                                                                                                                aria-hidden="true"></i>
                                                </span>
                                            </div>
                                            <div class="col-sm-offset-2 col-sm-4">
                                                <a href='/resource/show?resourceId=${recentShares?.id}' class="anchor">View Post</a>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </g:each>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-lg-6" >
            <div class="panel panel-default">
                <div class="panel-heading">Login</div>
                <div class="panel-body">

                    <g:form class="form-horizontal" controller="login"
                            action="loginHandler">
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="userName">Email:</label>
                            <div class="col-sm-10">
                                <g:textField type="text" class="form-control"
                                             placeholder="Enter email" name="emailOrUserName" required="true" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="password">Password:</label>
                            <div class="col-sm-10">
                                <g:passwordField type="password" class="form-control"
                                                 placeholder="Enter password" name="password" required="true"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-5">
                                <a href="#forgotPassword" data-toggle="modal"  aria-hidden="true" >forgot Password</a>
                            </div>

                            <div class="col-sm-5">
                                <g:submitButton name="Save" value="Save"
                                                class="btn btn-default">LogIn</g:submitButton>
                            </div>
                        </div>
                    </g:form>
                </div>
            </div>
        </div>

    </div>
</div>




<!-- second row-->
<div class="container">
<div class="row">
    <ls:topPosts/>

        <div class="col-lg-6" >
            <div class="panel panel-default">
                <div class="panel-heading">Register</div>
                <div class="panel-body">

<g:form controller="login" action="register" class="form-horizontal" enctype="multipart/form-data" >
                        <div class="form-group">

                            <label class="control-label col-sm-4" for="firstName"> First Name*</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="firstName" placeholder="Enter FirstName" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-4" for="lastName">Last Name*:</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control"  name="lastName" placeholder="Enter Last Name" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-4" for="email">Email*:</label>
                            <div class="col-sm-8">
                                <input type="email" class="form-control"  name="email" placeholder="Enter Email" required>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-4" for="userName">Username*:</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="userName" placeholder="Enter Username" required>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-4" for="password">Password*:</label>
                            <div class="col-sm-8">
                                <input type="password" class="form-control" name="password" placeholder="Enter Password" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-4" for="confirmPassword"> Confirm Password*:</label>
                            <div class="col-sm-8">
                                <input type="password" class="form-control"  name="confirmPassword" placeholder="Enter Confirm Password" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-4">Photo:</label>
                            <div class="col-sm-8">
                                <input type="file" name="image"/><br/><br/>
                            </div>
                        </div>
                        <div class="form-group">

                            <div class="col-sm-offset-7 col-sm-4">
                                <button type="submit" class="btn btn-default">Register</button>
                            </div>
                        </div>
                    </g:form>


                </div>
            </div>
        </div>
    </div>
</div>


<!--Share Link-->
<!--Share Link Modal-->
<div id="shareLink" class="modal fade" role="dialog">
   <g:render template="/linkResource/link"></g:render>
</div>

<!--Share Document Modal-->
<div id="shareDocument" class="modal fade" role="dialog">
 <g:render template="/documentResource/document"></g:render>
</div>

<!--
        Send Invitation Modal
 -->
<div id="SendInvite" class="modal fade" role="dialog">
<g:render template="/user/email"></g:render>
</div>


<!-- _create.gsp Topic Modal -->
<div id="CreateTopic" class="modal fade" role="dialog">
   <g:render template="/topic/create"></g:render>
</div>



<!-- forgotPassword -->
<div id="forgotPassword" class="modal fade" role="dialog">
   <g:render template="/user/forgotPassword"></g:render>
</div>



</body>
</html>