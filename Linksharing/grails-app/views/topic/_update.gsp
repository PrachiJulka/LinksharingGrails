<div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">x</button>
            <h4 class="modal-title"> Update Topic name(Pop up)</h4>
        </div>

        <div class="modal-body">
            <g:form class="form-horizontal" controller="topic" action="update">
    <input type="hidden" value="${topicCO.id}" name="topicId"/>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Name</label>
                    <div class="col-sm-10">

                        <g:textField class="form-control" type="text" placeholder="Name" id="name" name="topicName" />
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">

                        <input type="submit" value="Update" class="btn btn-primary"  />

                        <input type="button"  value="Cancel" data-dismiss="modal" class="btn btn-primary"/>
                    </div>
                </div>
            </g:form>
        </div>
    </div>
</div>
