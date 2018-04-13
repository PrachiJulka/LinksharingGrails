package com.ttn.linksharing

import com.ttn.linksharing.co.UserCO
import grails.gorm.transactions.Transactional

@Transactional
class LoginService {

    def register(UserCO userCO,def file){

        User user=new User()
        user.setEmail(userCO.email)
        user.setFirstName(userCO.firstName)
        user.setLastName(userCO.lastName)
        user.setUserName(userCO.userName)
        user.setAdmin(false)
        user.setActive(true)
        user.setPassword(userCO.password)
        user.setConfirmPassword(userCO.confirmPassword)
        user.photo=file.bytes

        String message = "User Creation Failed"
        if(user.validate()) {
            user.save()
            message = "User created Successfully"
        }

        return message
     }
}
