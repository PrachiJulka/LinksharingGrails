<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
    <meta name="layout" content="main">
</head>%{--
 Display user image on user/_show, resource/_show, topic/_show (all the places where user
 image are showing)--}%
<body>
<div class="container">
<div class="row">

<g:render template="show"></g:render>

<div class="col-lg-6" style="left: 20px;">
    <div class="panel panel-default">
        <div class="panel-heading">Posts "${topic?.name}"</div>
        <div class="panel-body">

            <g:each in="${topicPosts}" var="posts">
                <div class="row">
            <div class="col-lg-3">
            <ls:userImage id="${posts?.userId}"></ls:userImage>
            </div>

            <div class=" col-lg-9">

                <div class="row">
                    <div class="col-sm-12">
                        <p>
                ${posts?.description}
                        </p>
                    </div>
                </div>
                <g:if test="${session.user!=null}">

                    <div class="col-sm-4">
                        <small>
                            <ls:read id="${posts.userId}"></ls:read>
                        </small>
                    </div>
                    <div class="col-sm-3">
                        <small>
                            <a href="/resource/show/${posts.resourceID}">View Post</a>
                        </small>
                    </div>
                </div>
                </g:if>
            </div>
                </div>
            </g:each>
        </div>
    </div>
</div>
</div>
</div>
    <div class="container">
        <div class="row">
            <div class="col-lg-6">
                <div class="panel panel-default">
                    <div class="panel-heading">Users</div>
                    <div class="panel-body">


                     <g:each in="${topic?.subscribedUsers}" var="subscribedUsers">
                            <div class="row">
                        <div class="col-lg-3">
                                <ls:userImage id="${subscribedUsers.id}"></ls:userImage>
                        </div>

                        <div class="col-lg-9">
                            <span>${subscribedUsers.userName}</span> <small class ="text-muted">@${subscribedUsers?.firstName}
                        5min</small>

                                <div class="row">
                                    <div class="col-sm-6">
                                        <span class="">Subscription</span>
                                    </div>
                                    <div class="col-sm-6">
                                        <span>Topic</span>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-6">
                                        <a href="#"><ls:subscriptionCount user="${subscribedUsers}" topicId="${topicCO?.id}"></ls:subscriptionCount></a>

                                    </div>
                                    <div class="col-sm-6">
                                       <ls:topicCount user="${subscribedUsers}"></ls:topicCount>

                                    </div>
                                </div>
                        </div>
                            </div>
                        </g:each>

                    </div>

                </div>
        </div>
    </div>
    </div>
</body>

</html>