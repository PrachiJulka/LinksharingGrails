package com.ttn.linksharing

import com.ttn.linksharing.domain.BaseDomain

class ResourceRating implements BaseDomain{

    Integer score

    static belongsTo = [resource:Resource,user:User]
    static constraints = {
        score( validator: {
            return it >= 1 &&  it<= 5
        })
        user(nullable: false,blank: false)
        resource(nullable: false,blank:false,unique: 'user')

    }
    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        ResourceRating resourceRating= (ResourceRating) o

        return id == resourceRating.id
    }

    int hashCode() {
        return (id != null ? id.hashCode() : 0)
    }
}
