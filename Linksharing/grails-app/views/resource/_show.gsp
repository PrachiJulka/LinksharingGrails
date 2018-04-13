<div class="col-lg-6" style="left: 20px;">
    <div class="panel panel-default">
        <div class="panel-heading">Resource Detail</div>
        <div class="panel-body">
<div class="row">
            <div class="col-lg-offset-1 col-lg-2">
               <ls:userImage id="${postVO?.userId}"></ls:userImage>
            </div>

            <div class="col-lg-9">

                <span>${postVO?.userUserName}</span>
                <a href="#">${postVO?.topicName}</a>
                <small class="text-muted"><i class="fa fa-heart-o"></i> <i class="fa fa-heart-o"></i><i class="fa fa-heart-o"></i><i class="fa fa-heart-o"></i><i class="fa fa-heart-o"></i></small>
                <div class="row">
                    <div class="col-sm-12">
                        <p> ${postVO?.description}
                        </p>
                    </div>
                </div>

                <span>
                    <i class="fa fa-facebook-square" aria-hidden="true"></i> <i class="fa fa-tumblr" aria-hidden="true"></i> <i class="fa fa-google-plus" aria-hidden="true"></i>
                </span>
                <div class="row">
                    <g:if test="${session.user!=null}">

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
                            <ls:canDeleteResource resourceId="${postVO?.resourceID}"></ls:canDeleteResource>
                        </small>
                    </div>
                    <div class="col-sm-3">
                        <small>
                       <a href="#">Edit</a>
                            <a data-toggle="modal" href="#updateResource" aria-hidden="true">update</a>
                        </small>
                    </div>
                    </g:if>
                </div>
            </div>
            <hr>
</div>
                </div>
            </div>


        </div>
<div id="updateResource" class="modal fade" role="dialog">


    <g:render template="/resource/updateDescription"/>


</div>