<div class="col-lg-6" >
    <div class="panel panel-default">
        <div class="panel-heading">

            <div class="row">
                <div class="col-lg-4 subform">Topics "${topicCO?.name}"</div>

            </div>
        </div>
        <div class="panel-body">

                <div class="row">

                    <div class="col-lg-offset-1 col-lg-2">
                        <ls:userImage id="${topicCO?.userId}"></ls:userImage>
                     </div>
                    <div class="col-lg-9">
                        <div class="row">
                            <div class=col-sm-12>
                                <a href="#" class="anchor">${topicCO?.name} </a>
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
                            <div class="col-sm-4"><ls:showSubscribe topicId="${topicCO?.id}"></ls:showSubscribe></div>

                            <div class="col-sm-4"><ls:subscriptionCount topicId="${topicCO?.id}"></ls:subscriptionCount></div>
                            <div class="col-sm-4"><ls:resourceCount topicId="${topicCO?.id}"></ls:resourceCount> </div>
                        </div>
                            <g:if test="${session.user!=null}">
                        <div class="row">
                            <div class="col-sm-4">
                                <select>
                                    <option selected="true">Serious</option>
                                    <option>VerySerious</option>
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
                                <a data-toggle="modal" href="#updateTopic" aria-hidden="true">update</a>

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
<div id="updateTopic" class="modal fade" role="dialog">


    <g:render template="/topic/update"/>


</div>