package com.ttn.linksharing.util

import com.ttn.linksharing.constants.DefaultPassword
import org.apache.commons.lang.RandomStringUtils


class Utility {
    static String getRandomPassword() {
        RandomStringUtils randomPassword = new RandomStringUtils()
        return randomPassword.randomAlphanumeric(DefaultPassword.PASSWORD_LENGTH)
    }
}
