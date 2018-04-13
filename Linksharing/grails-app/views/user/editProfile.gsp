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
                        <img class="img-responsive" alt="/images/index.jpeg" src=""/>
                    </div>

                    <div class="col-lg-9">
                        <p class="name-font">${user?.userName}<sub class="text-muted">@${user?.firstName}</sub></p>
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

        <div class="col-lg-6">

            <div class="panel panel-default">
                <div class="panel-heading">Profile</div>
                <div class="panel-body">

                    <form  enctype="multipart/form-data"  id="UpdateProfileForm" class="form-horizontal"  >
                        <div class="form-group">

                            <label class="control-label col-sm-4" > First Name*</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="firstName" value="${user.firstName}" placeholder="Enter FirstName" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-4" >Last Name*:</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control"  name="lastName" value="${user.lastName}" placeholder="Enter Last Name" required>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-4">Username*:</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" name="userName" value="${user.userName}" placeholder="Enter Username" required>
                            </div>
                        </div>


                        <div class="form-group">
                            <label class="control-label col-sm-4">Photo:</label>
                            <div class="col-sm-8">
                                <input type="file" name="photo"/><br/><br/>
                            </div>
                        </div>
                        <div class="form-group">

                            <div class="col-sm-offset-7 col-sm-4">
                                <button type="button" id="updateProfile" class="btn btn-default">update</button>
                            </div>
                        </div>
                    </form>


                </div>
            </div>

    </div>
    </div>

    <div class="row">
        <div class="col-lg-6">
            <div class="panel panel-default">
                <div class="panel-heading">

                    <div class="row">
                        <div class="col-lg-4 subform">Topics "${topic?.name}"</div>

                    </div>
                </div>
                <div class="panel-body">

                    <div class="row">

                        <div class="col-lg-offset-1 col-lg-2">
                            <ls:userImage id="${topic?.createdBy?.id}"></ls:userImage>
                        </div>
                        <div class="col-lg-9">
                            <div class="row">
                                <div class=col-sm-12>
                                    <a href="#" class="anchor">${topic?.createdBy?.name} </a>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-4">
                                    <small class="text-muted"></small>
                                </div>
                                <div class="col-sm-4">
                                    Subscriptions</div>
                                <div class="col-sm-4">Post</div>
                            </div>
                            <div class="row">
                                <div class="col-sm-4"><ls:showSubscribe topicId="${topic?.id}"></ls:showSubscribe></div>

                                <div class="col-sm-4"><ls:subscriptionCount topicId="${topic?.id}"></ls:subscriptionCount></div>
                                <div class="col-sm-4"><ls:resourceCount topicId="${topic?.id}"></ls:resourceCount> </div>
                            </div>
                            <g:if test="${session.user!=null}">
                                <div class="row">
                                    <div class="col-sm-4">
                                        <select>
                                            <option selected="true">Serious</option>
                                            <option>Very_Serious</option>
                                            <option>Casual</option>
                                        </select>
                                    </div>
                                    <div class="col-sm-4">

                                        <select>
                                            <option>Private</option>
                                            <option selected="true">Public</option>
                                        </select>

                                    </div>
                                    <div class="col-sm-4">
                                        <i class="fa fa-envelope-o" aria-hidden="true"></i>
                                        <i class="fa fa-file-o" aria-hidden="true"></i>
                                        <i class="fa fa-trash" aria-hidden="true"></i>
                                    </div>

                                </div>
                            </g:if>
                        </div>

                        <hr/>
                    </div>

                </div>
            </div>
        </div>
        <div class="col-lg-6">
               <div class="panel panel-default">
                <div class="panel-heading">Change Password</div>
                <div class="panel-body">

<form class="form-horizontal" id="updatePasswordForm" >
   <div class="form-group">

           <label class="control-label col-sm-4" >Old Password*</label>
           <div class="col-sm-8">
               <input type="password" class="form-control" name="oldPassword" placeholder="Enter Password" required>
           </div>
       </div>

    <div class="form-group">

        <label class="control-label col-sm-4" > Password*</label>
        <div class="col-sm-8">
            <input type="password" class="form-control" name="password" placeholder="Enter Password" required>
        </div>
    </div>


</form>
 <div class="form-group">

                            <div class="col-sm-offset-7 col-sm-4">
                                <button type="button" class="btn btn-default" id="updatePassword">update</button>
                            </div>
                        </div>
                </div>
               </div>

        </div>
    </div>
</div>
</body>
</html>
