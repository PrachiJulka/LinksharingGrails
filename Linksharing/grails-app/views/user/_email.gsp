<div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">&times;</button>
            <h4 class="modal-title">Send Invitation</h4>
        </div>

        <div class="modal-body">
            <g:form class="form-horizontal" controller="topic" action="invite" >
                <div class="form-group">
                    <label class="col-sm-2 control-label">Email</label>
                    <div class="col-sm-10">

                        <input class="form-control" type="email" placeholder="Email" id="email" name="email" />    </div>

                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2">Topics</label>
                    <div class="col-sm-4">
                        <select name='topicId' id="inviteEmails">

                            <g:each in="${subscriptionsList}" var="subscribedTopics">

                                <option value="${subscribedTopics?.topic?.id}">${subscribedTopics?.topic?.name}</option>
                            </g:each>
                        </select>


                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">

                        <input type="submit" value="Send Invite" name="Send Invite" id="invite" class="btn btn-primary"/>
                        <input type="button" name="Cancel" value="Cancel" id="cancel" data-dismiss="modal" class="btn btn-primary"/>
                    </div>
                </div>
            </g:form>
        </div>
    </div>
</div>