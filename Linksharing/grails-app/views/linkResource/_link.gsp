<div class="container">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Share Link (Pop up)</h4>
            </div>

            <div class="modal-body">
                <g:form class="form-horizontal" controller="linkResource" action="save">
                    <div class="form-group">
                        <div class="col-sm-2">
                            <label class="control-label col-sm-2">Link</label>
                        </div>
                        <div class="col-sm-10">
                            <input class="form-control" type="text" name="url" placeholder="Link" id="link" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-2 font-normal" for="email" style="text-align: left" >Description*</label>
                        <div class="col-sm-10">

                            <textarea  class="form-control" rows="4" name="description" placeholder="Description" id="descriptionLink"></textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-2">Topics</label>
                        <div class="col-sm-4">
                             %{--   <g:each in="${session.user?.subscribedTopic}" var="subscribedTopics">

                                    <option value="${subscribedTopics?.topic?.name}">${subscribedTopics?.topic?.name}</option>
                                </g:each>--}%
                            <g:select
                                    class="form-control create-topic-modal-dropdown "
                                    name="topicId" from="${session.user?.subscribedTopic?.topic}"

                                    optionKey="id"/>


                        </div>

                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <g:actionSubmit value="Save" class="btn btn-primary"/>
                            <input type="button" class="btn btn-primary" id="cancelLinkBtn" value="Close" data-dismiss="modal"/>
                        </div>
                    </div>

                </g:form>
            </div>
        </div>
    </div>
</div>