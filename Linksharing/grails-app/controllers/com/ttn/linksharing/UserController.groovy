package com.ttn.linksharing

import com.ttn.linksharing.co.SearchCO
import com.ttn.linksharing.co.UpdatePasswordCO
import com.ttn.linksharing.co.UpdateProfileCO
import com.ttn.linksharing.co.UserCO
import com.ttn.linksharing.enums.Visibility
import grails.converters.JSON

/*
Use read() for /topic/show and load() for /resource/delete & /topic/delete action.
*/
class UserController {
    def assetResourceLocator
    UserService userService
    EmailService emailService
    LoginService loginService

    def index() {
    [user:session.user]

    }

    def show(Integer id){

        Topic topic=Topic.get(id)
        if(topic.visibility==Visibility.PUBLIC) {
           render("success")
        }
        else{
            if(Subscription.findByTopicAndUser(topic,session.user))
                render("Subscription Exists")
            else
            {
                flash.error="Subscription does not exists"
                redirect(action:"login/index")
            }
        }
     }
    def image(Long id){
        User user=userService.getUser(id)
        if(user.photo==null){
            response.outputStream << assetResourceLocator.findAssetForURI('user.png').getInputStream()

            }
        else {
            response.contentType = 'image/png'
            byte[] imageInByte = user.photo
            response.outputStream << imageInByte
        }
        response.outputStream.flush()
    }

    def editProfile(){
      [user:session.user]
    }

    def updatePassword(UpdatePasswordCO updatePasswordCO){
        Long id=session.user.id
        def result=userService.updatePassword(id,updatePasswordCO)
       if(result)
           result=[message:"Successfully Updated"]

        result as JSON
    }

    def edit(UpdateProfileCO updateProfileCO){
        Long id=session.user.id

        if(params.photo) {
            def file=params.photo
            updateProfileCO.photo=file.bytes
         }

      def result=  userService.updateProfile(id, updateProfileCO)
        if(result)
            result= [message:"success"]

        result as JSON
    }

    def forgotPassword(String email) {
        def message=emailService.validateUserEmail(email)
            flash.message=message

    }

    def userListing(){
        List<User> userList=User.getAll()

       [userList:userList]
    }

    def changeStatus(boolean status){
        Long userId=Long.parseLong(params.userId)
        userService.updateStatus(userId,status)
         redirect(controller: "user", action: 'userListing')

    }


}
