package com.ttn.linksharing.co

import com.ttn.linksharing.User
import grails.validation.Validateable



class UpdatePasswordCO implements Validateable{
    String oldPassword
    String password

    static constraints={
        password(importFrom([password:password],User) )

    }

}
