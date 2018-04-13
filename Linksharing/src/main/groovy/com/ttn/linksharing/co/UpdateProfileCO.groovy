package com.ttn.linksharing.co

import com.ttn.linksharing.User
import grails.validation.Validateable

/*
    firstName
    lastName
    userName
    photo*/

class UpdateProfileCO implements Validateable {
  //  Long userId
    String firstName
    String lastName
    String userName
    byte[] photo

    static constraints = {
        importFrom([firstName: 'firstName', lastName: 'lastName'], User)
        photo(nullable: true)
    }

}
