package com.ttn.linksharing.co

import com.ttn.linksharing.domain.BaseDomain
import com.ttn.linksharing.enums.Visibility

class TopicCO implements BaseDomain {
    Long id
    String name
    Visibility visibility
    Long userId
    List subscribedUsers
    List topicPosts


}
