
        <!-- Modal content-->
%{--        <div class="modal-content topic">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">x</button>
                <h4 class="modal-title">Create Topic(Pop up)</h4>
            </div>

            <div class="modal-body">--}%
        <div class="modal-dialog">

               <!-- Modal content-->
               <div class="modal-content">
                   <div class="modal-header">
                       <button type="button" class="close" data-dismiss="modal">x</button>
                       <h4 class="modal-title"> Topic(Pop up)</h4>
                   </div>

                   <div class="modal-body">
        <form class="form-horizontal" id="getContact">

                    <div class="form-group">
                        <label class="col-sm-2 control-label">Name</label>
                        <div class="col-sm-10">

                            <g:textField class="form-control" type="text" placeholder="Name" id="name" name="name" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Visibility</label>
                        <div class="col-sm-10">

                            <select name="visibility" id="visibility">
                                <option value="PUBLIC">Public</option>
                                <option value="PRIVATE">Private</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">

                            <input type="button" value="Save" id ="saveTopic" class="btn btn-primary"  />

                            <input type="button"  value="Cancel" data-dismiss="modal" class="btn btn-primary"/>
                        </div>
                    </div>
        </form>
            </div>
        </div>
        </div>

