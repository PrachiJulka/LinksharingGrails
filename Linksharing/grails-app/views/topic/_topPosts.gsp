
        <div class="col-lg-6" >
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-sm-4">
                            Top posts
                        </div>
                        <div class="col-sm-offset-4 col-sm-4">
                            <button class="btn-xs drop_button dropdown-toggle " data-placement="left" data-toggle="tooltip"  id="menu1">Tutorials
                                <span class="caret" ></span>    </button>
                        </div>
                    </div>
                </div>
                <div class="panel-body">

                    <div class="row heading">
<g:each in="${com.ttn.linksharing.Resource.topPost()}" var="post">
    <div class="row">
        <div class=" col-lg-offset-1 col-lg-2">
           <ls:userImage id="${post?.user?.id}"></ls:userImage>
        </div>
        <div class=" col-lg-9">
            <div class="row">
                <div class="col-sm-7">
                    <span>${post.user.userName}</span> <small class="text-muted">@uday
                5min</small>
                </div>
                <div class="cil-sm-offset-4 col-sm-1">
    <g:link controller="topic" action="show" params="[topicId:post.topic.id]" class="anchor">${post.topic.name}</g:link>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-11" style="padding-right: 23px">
                    ${post.description}</div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-6">
                <span>
                    <i class="fa fa-facebook-square" aria-hidden="true"></i> <i class="fa fa-tumblr" aria-hidden="true"></i> <i class="fa fa-google-plus" aria-hidden="true"></i>
                </span>
            </div>
            <div class="col-sm-offset-2 col-sm-4">
               <g:link controller="resource" action="show" params="[resourceId:post.id]">View Post</g:link>
            </div>
        </div>
    </div>
</g:each>
</div>
</div>
</div>
</div>
