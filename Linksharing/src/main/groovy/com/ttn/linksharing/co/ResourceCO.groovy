package com.ttn.linksharing.co

import com.ttn.linksharing.Topic
import com.ttn.linksharing.User
import com.ttn.linksharing.domain.BaseDomain

abstract class ResourceCO implements BaseDomain {
    String description
    User user
    Topic topic

}
