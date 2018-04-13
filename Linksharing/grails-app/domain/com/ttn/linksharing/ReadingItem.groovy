package com.ttn.linksharing

import com.ttn.linksharing.domain.BaseDomain

class ReadingItem implements BaseDomain {

    Boolean isRead
/*    Date dateCreated*/
   /* Date lastUpdated*/
    static belongsTo = [user:User,resource:Resource]

    static constraints = {
        isRead nullable: false
        user nullable: false
        resource nullable: false,unique: 'user'


    }

    static Boolean getIsRead(Long resourceId, Long userId) {
        createCriteria().get {
            projections {
                property('isRead')
            }
            eq('resource.id', resourceId)
            eq('user.id', userId)
        }
    }
    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        ReadingItem readingItem = (ReadingItem) o

        return id == readingItem.id
    }

    int hashCode() {
        return (id != null ? id.hashCode() : 0)
    }
}
