package com.ttn.linksharing

import com.ttn.linksharing.co.UpdatePasswordCO
import com.ttn.linksharing.co.UpdateProfileCO
import grails.converters.JSON


class UserController {
    def assetResourceLocator
    UserService userService
    EmailService emailService

    def index() {
        User user = User.get(session.user?.id)
        [user: session.user, resources: user?.resources, subscriptionsList: user?.subscribedTopic]
    }

    def show(Long id) {

        Map result = userService.show(id)

        if (result.get("sucess")) {
            render(result.get("message"))
        } else {
            flash.error = result.get("message")
            redirect(action: "login/index")
        }
    }

    def image(Long id) {
        User user = userService.getUser(id)
        if (user.photo == null) {
            response.outputStream << assetResourceLocator.findAssetForURI('user.png').getInputStream()

        } else {
            response.contentType = 'image/png'
            byte[] imageInByte = user.photo
            response.outputStream << imageInByte
        }
        response.outputStream.flush()
    }

    def editProfile() {
        [user: session.user]
    }

    def updatePassword(UpdatePasswordCO updatePasswordCO) {
        Long id = session.user.id
        def result = userService.updatePassword(id, updatePasswordCO)
        if (result)
            result = [message: "Successfully Updated"]

        result as JSON
    }

    def edit(UpdateProfileCO updateProfileCO) {
        Long id = session.user.id

        if (params.photo) {
            def file = params.photo
            updateProfileCO.photo = file.bytes
        }

        def result = userService.updateProfile(id, updateProfileCO)
        if (result)
            result = [message: "success"]

        result as JSON
    }

    def forgotPassword(String email) {
        def message = emailService.validateUserEmail(email)
        flash.message = message

    }

    def userListing() {
        List<User> userList = User.getAll()

        [userList: userList]
    }

    def changeStatus(boolean status) {
        Long userId = Long.parseLong(params.userId)
        userService.updateStatus(userId, status)
        redirect(controller: "user", action: 'userListing')

    }


}
