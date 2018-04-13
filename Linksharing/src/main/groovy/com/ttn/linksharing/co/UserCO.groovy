package com.ttn.linksharing.co

import com.ttn.linksharing.domain.BaseDomain

class UserCO implements BaseDomain {
    Long id
    String email
    String userName
    String password
    String firstName
    String lastName
    byte[] photo
    boolean admin
    boolean active

    String confirmPassword
}
