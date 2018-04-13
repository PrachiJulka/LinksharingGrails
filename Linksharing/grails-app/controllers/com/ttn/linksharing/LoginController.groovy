package com.ttn.linksharing

import com.ttn.linksharing.co.UserCO

/*_create.gsp templates for resource/show topic/show user/login user/register*/

class LoginController {

    UserService userService
  LoginService loginService

    def index() {




      }

    def logout() {
        session.invalidate()
        redirect(action:'/login/index')
    }

    def loginHandler(String emailOrUserName, String password) {

        User user = User.findByUserNameAndPassword(emailOrUserName, password)
        if(user==null){
            user=User.findByEmailAndPassword(emailOrUserName,password)
        }

        if(user!=null) {

            if(user.active) {
             session.user=user

                forward(controller: 'user', action: 'index')

            }
            else {
               flash.error = "Your account is not active"

            }
        }
        else
        {
            flash.error="User not found!! Please enter correct email or Username to login "
        }

        redirect(action: 'index')
    }

    def register(UserCO userCO){
        def file=params.image
        String message=loginService.register(userCO,file)
        flash.message=message
        redirect(controller:'login',action: 'index')
     }




}
