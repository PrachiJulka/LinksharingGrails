// This is a manifest file that'll be compiled into application.js.
//
// Any JavaScript file within this directory can be referenced here using a relative path.
//
// You're free to add application-wide JavaScript to this file, but it's generally better
// to create separate JavaScript files as needed.
//
//= require jquery-2.2.0.min
//= require bootstrap
//= require_tree .
//= require_self

if (typeof jQuery !== 'undefined') {
    (function($) {
        $(document).ajaxStart(function() {
            $('#spinner').fadeIn();
        }).ajaxStop(function() {
            $('#spinner').fadeOut();
        });
    })(jQuery);
}

$(document).ready(function(){

    $("#saveTopic").click(function(e){
        var form = $("#getContact").serialize();

        $.ajax({
            type: "POST",
            url: "/topic/save",
            data: form,
            success:function (response) {
                // var a = response
                $('#CreateTopic').modal('hide');
                alert(response.sucess);

            }
            ,   error: function (e) {
                $('#CreateTopic').modal('hide');
                alert("Error");

            }
        });

    });



    $("#updateProfile").click(function(e){
         var form = $("#UpdateProfileForm").serialize();

        $.ajax({
            type: "POST",
            url: "/user/edit",
             data: form,
            success:function (response) {
                // var a = response
                alert(response.message);

            }
            ,   error: function (e) {
                alert("Error");
                // $('#CreateTopic').modal('hide');
            }
        });

    });
    //updatePassword

    $("#updatePassword").click(function(e){
      // var id= $(this).closest("form").attr('id');
          var form = $("#updatePasswordForm").serialize();

        $.ajax({
            type: "POST",
            url: "/user/updatePassword",
            data: form,
            success:function (response) {
                // var a = response
                alert(response.success);

            }
            ,   error: function (e) {
                alert("Error");
                // $('#CreateTopic').modal('hide');
            }
        });

    });

    $("#saveDocument").click(function(e){
        var formData = new FormData();
        formData.append("file",$("#document")[0].files[0])
        formData.append("description",$("#description").val());
        formData.append("topicDocument",$("#topicId").val());

        $.ajax({
            type: "POST",
            url: "/document",
            data: formData,
            contentType: false,
            processData: false,

            success: function (response) {
                // we have the response
                alert('Document Shared Successfully');
                $('#shareDocument').modal('hide');
            },
            error: function (e) {
                alert("Error");
                $('#shareDocument').modal('hide');
            }
        });

    });

    /*
    var formData = new FormData();
    formData.append("file",$("#document")[0].files[0])
    formData.append("description",$("#description").val());
    formData.append("topicDocument",$("#topicDocument").val());*/

});
