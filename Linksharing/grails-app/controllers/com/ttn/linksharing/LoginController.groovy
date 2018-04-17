package com.ttn.linksharing

import com.ttn.linksharing.co.UserCO

class LoginController {

    UserService userService
    LoginService loginService

    def index() {

     }

    def logout() {
        session.invalidate()
        redirect(controller: 'login',action: 'index')
    }

    def loginHandler(String emailOrUserName, String password) {

        Map data = loginService.login(emailOrUserName,password)
        if(data.get("sucess")) {
            session.user=data.get("message")
            redirect(controller: "user", action: "index")
        }
        else
        redirect(action: 'index')
    }

    def register(UserCO userCO){
        def file=params.image
        String message=loginService.register(userCO,file)
        flash.message=message

      loginHandler(userCO.email,userCO.password)

     }




}
