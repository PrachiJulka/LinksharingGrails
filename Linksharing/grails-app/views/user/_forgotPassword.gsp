<div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">x</button>
            <h4 class="modal-title">Forgot Password</h4>
        </div>

        <div class="modal-body">
            <g:form class="form-horizontal" controller="user" action="forgotPassword">

                <div class="form-group">
                    <label class="col-sm-2 control-label">Enter Your EmailId</label>
                    <div class="col-sm-10">

                        <input class="form-control" type="email" placeholder="Name" id="email" name="email" />
                    </div>
                </div>



                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">


                        <input type="submit" id="change" value="Send Email" class="btn btn-primary"/>
                        <input type="button" value="Cancel" data-dismiss="modal" class="btn btn-primary"/>
                    </div>
                </div>
            </g:form>
        </div>
    </div>
</div>