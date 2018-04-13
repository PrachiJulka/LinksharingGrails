package com.ttn.linksharing

import com.ttn.linksharing.domain.BaseDomain
import com.ttn.linksharing.enums.Seriousness

//Use eager fetching for topic and user in subscription
class Subscription implements BaseDomain  {

    Seriousness seriousness


    static belongsTo = [topic:Topic,user:User]

    static mapping = {
        seriousness defaultValue: Seriousness.SERIOUS
        topic fetch:'join'
        user fetch: 'join'
    }
    static constraints = {
        seriousness(nullable:false)
        topic(nullable: false, unique:'user')
        user(nullable: false)
    }
    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Subscription subscription = (Subscription) o

        return id == subscription.id
    }

    int hashCode() {
        return (id != null ? id.hashCode() : 0)
    }

}
