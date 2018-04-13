
            <div class="row">
                <div class="col-lg-6" >
                    <div class="panel panel-default">
                        <div class="panel-heading">

                            <div class="row">
                                <div class="col-lg-4 subform">Trending Topics</div>
                                <div class="col-lg-offset-4 col-lg-4"> <a class="a-right">View All</a></div>
                            </div>
                        </div>
                        <div class="panel-body">
<g:each in="${com.ttn.linksharing.Topic?.getTrendingTopics()}" var="trendingTopics">

    <div class="row">

        <div class="col-lg-offset-1 col-lg-2">
        <ls:userImage id="${trendingTopics?.createdBy.id}"></ls:userImage>
        </div>
        <div class="col-lg-9">
            <div class="row">
                <div class=col-sm-12>
                    <a href="#" class="anchor">${trendingTopics.name} </a>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-4">
                    <small class="text-muted">@${trendingTopics.createdBy.userName}</small>
                </div>
                <div class="col-sm-4">
                    Subscriptions</div>
                <div class="col-sm-4">Post</div>
            </div>
            <div class="row">
                <div class="col-sm-4"><ls:showSubscribe topicId="${trendingTopics.id}"/></div>

                <div class="col-sm-4"><ls:subscriptionCount user="${session.user}"></ls:subscriptionCount></div>
                <div class="col-sm-4"><a>${trendingTopics.count}</a></div>
            </div>
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
                    <i class="fa fa-file-o" aria-hidden="true"></i>
                    <i class="fa fa-trash" aria-hidden="true"></i>
                </div>

            </div>
        </div>

        <hr/>
    </div>
</g:each>
                        </div>
                    </div>
                </div>
            </div>