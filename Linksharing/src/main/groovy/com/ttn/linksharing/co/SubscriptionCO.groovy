package com.ttn.linksharing.co

import com.ttn.linksharing.domain.BaseDomain
import com.ttn.linksharing.enums.Seriousness

class SubscriptionCO implements BaseDomain{
    Seriousness seriousness
    Long userId
    Long topicId
    Long id
}
