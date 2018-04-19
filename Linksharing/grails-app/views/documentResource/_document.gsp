    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Share Document</h4>
            </div>

            <div class="modal-body">
                <g:form class="form-horizontal" controller="documentResource" action="save" enctype="multipart/form-data" >
                    <div class="form-group">
                        <label class="control-label col-sm-2">Document</label>
                        <div class="col-sm-10">

                            <input class="form-control" type="file" name="file" id="document"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-2 font-normal" for="email" style="text-align: left">Description*</label>
                        <div class="col-sm-10">

                            <textarea  class="form-control" rows="4" placeholder="Description" name="description" id="description"></textarea>
                        </div>

                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2">Topic*</label>


                        <div class="col-sm-10">

                             <g:select class="form-control create-topic-modal-dropdown" id="topicId"  name="topicId" from="${subscriptionsList?.topic}"

                                                                optionKey="id"/>

                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <input type="submit" value="save" id="saveDocument" class="btn btn-primary"/>

                            <input type="button" name="Cancel" id="cancel1" value="Cancel" data-dismiss="modal" class="btn btn-primary"/>
                        </div>
                    </div>
                </g:form>
            </div>
        </div>
    </div>