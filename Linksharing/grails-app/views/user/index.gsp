<%--
  Created by IntelliJ IDEA.
  User: prachi
  Date: 28/3/18
  Time: 10:04 PM
--%>

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
                <div class="panel-body">



                    <div class="col-lg-offset-1 col-lg-2">
                            <ls:userImage id="${user?.id}"></ls:userImage>
                    </div>

                    <div class="col-lg-9">
                        <p class="name-font">${user?.userName}<sub class="text-muted">@${session.user?.firstName}</sub></p>
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
                                <a href="#"><ls:subscriptionCount user="${user}"></ls:subscriptionCount></a>

                            </div>
                            <div class="col-sm-6">
                                <a href="#">10</a>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <div class="col-lg-6" style="left: 20px;">
            <div class="panel panel-default">
                <div class="panel-heading">Inbox</div>
                <div class="panel-body">



                    <g:each in ="${user?.resources}" var="resources">
                        <div class="row">
                        <div class="col-lg-offset-1 col-lg-2">
                      <ls:userImage id="${resources?.user?.id}"></ls:userImage>
                        </div>
                    <div class="col-lg-9">
                        <span>${resources?.user?.userName}</span> <small class="text-muted">@${resources?.user?.firstName}
                    5min</small> <a class="a-right" href="#" class="anchor">${resources?.topic?.name}</a>
                        <div class="row">
                            <div class="col-sm-12">
                                <p> ${resources?.description}
                                </p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-2">
                                <small><a href="#">Download</a>
                                </small>
                            </div>
                            <div class="col-sm-3">
                                <small>
                                    <a href="#">View full site</a>
                                </small>
                            </div>
                            <div class="col-sm-4">
                                <small>
                                    <ls:read id="${resources?.id}"></ls:read>
                                </small>
                            </div>
                            <div class="col-sm-3">
                                <small>
                                    <a href="#">View Post</a>
                                </small>
                            </div>
                        </div>
                    </div>
                    <hr>
                        </div>
                    </g:each>


                </div>
            </div>
        </div>



        <div class="container">
            <div class="row">
                <div class="col-lg-6" >
                    <div class="panel panel-default">
                        <div class="panel-heading">

                            <div class="row">
                                <div class="col-lg-4 subform">Subscription</div>
                                <div class="col-lg-offset-4 col-lg-4"> <a class="a-right">View All</a></div>
                            </div>
                        </div>
                        <div class="panel-body">
                            <g:each in="${user?.subscribedTopic}" var="subscribedTopics">


                                <div class="row">


                                %{--    <% out.println "${session.user?.subscribedTopic?.topics?.name}"%>
                                --}%

                                  <div class="col-lg-offset-1 col-lg-2">
                              <ls:userImage id="${subscribedTopics.id}"></ls:userImage>
                                </div>
                                <div class="col-lg-9">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <a href="#" class="anchor">${subscribedTopics?.topic?.name} </a>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-4">
                                            <small class="text-muted">@${subscribedTopics?.user?.userName}</small>
                                        </div>
                                        <div class="col-sm-4">
                                            Subscriptions</div>
                                        <div class="col-sm-4">Post</div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-4"><ls:showSubscribe topicId="${subscribedTopics?.topic?.id}"></ls:showSubscribe></div>

                                        <div class="col-sm-4"><a><ls:subscriptionCount user="${session.user}"></ls:subscriptionCount></a></div>
                                        <div class="col-sm-4"><a>30</a></div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-4">
                                            <select name="seriousness">
                                                <option selected="true" value="serious">Serious</option>
                                                <option value="verySerious">VerySerious</option>
                                                <option value="casual">Casual</option>
                                            </select>
                                        </div>
                                        <div class="col-sm-4">
                                            <select>
                                                <option>Private</option>
                                                <option selected="true">Public</option>
                                            </select>

                                        </div>
                                        <div class="col-sm-4">
                                            <a class="fa fa-envelope-o" data-toggle="modal" href="#SendInvite" aria-hidden="true"></a>
                                            <a class="fa fa-file-o" data-toggle="modal" href="#CreateTopic" ariahidden="true"></a>
                                            <a class="fa fa-trash" aria-hidden="true"></a>
                                        </div>

                                    </div>
                                </div>

                                <hr>

                            </div>
                            </g:each>
                        </div>
                    </div>

                </div>
            </div>
        </div>

        <div class="container">
            <div class="row">
         <ls:trendingTopics></ls:trendingTopics>
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
    <g:render template="/documentResource/document"/>
</div>

<!--
                    Send Invitation Modal
             -->
<div id="SendInvite" class="modal fade" role="dialog">
    <g:render template="email"></g:render>
</div>



<div id="CreateTopic" class="modal fade" role="dialog">


<g:render template="/topic/create"/>


</div>



</body>
</html>