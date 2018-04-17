package com.ttn.linksharing

import com.ttn.linksharing.co.UserCO
import grails.gorm.transactions.Transactional

@Transactional
class LoginService {

    def register(UserCO userCO,def file){

        User user=new User()
        user.identity {
            setEmail(userCO.email)
            setFirstName(userCO.firstName)
            setLastName(userCO.lastName)
            setUserName(userCO.userName)
            setAdmin(false)
            setActive(true)
            setPassword(userCO.password)
            setConfirmPassword(userCO.confirmPassword)
            photo=file.bytes
        }
        String message = "User Creation Failed"
        if(user.validate()) {
            user.save()
            message = "User created Successfully"
        }

        return message
     }

    def login(String emailOrUserName,String password){
        User user = User.findByUserNameAndPassword(emailOrUserName, password)
        Map data = [:]

        if(user==null){
            user=User.findByEmailAndPassword(emailOrUserName,password)
        }

        if(user!=null) {

            if(user.active) {

               data.put("sucess",true)
                data.put("message",user)

            }
            else {
               data.put("sucess",false)
               data.put("errorMessage","Your account is not active")

            }
        }
        else
        {
            data.put("sucess",false)
            data.put("errorMessage","Wrong Username or password")
        }
        return data
    }
}
