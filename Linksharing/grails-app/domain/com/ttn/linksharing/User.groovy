package com.ttn.linksharing

import com.ttn.linksharing.co.SearchCO
import com.ttn.linksharing.co.UserCO
import com.ttn.linksharing.domain.BaseDomain

//
/*
_create.gsp transient method in user domain getSubscribedTopic to
get only subscribed topics of user, this method will be used in user
dashboard and dropdown of linkresource create and email invite of topic*/

class User implements BaseDomain {

    String email
    String userName
    String password
    String firstName
    String lastName
    byte[] photo
    boolean admin
    boolean active
    String confirmPassword
    String getName() {
        this.firstName + this.lastName
    }

    static hasMany = [topics: Topic, subscriptions: Subscription, resources: Resource, resourceRating: ResourceRating, readingItems: ReadingItem]
    static mapping = {
        sort("id": "desc")
        subscriptions lazy: false
    }


    List getSubscribedTopic() {
        this.subscriptions.toList()
    }

    static transients = ['confirmPassword', 'subscribedTopic', 'name']

    static constraints = {
        email(unique: true, email: true, blank: false, nullable: false)
        userName(unique: true, blank: false, nullable: false)
        password(blank: false, nullable: false, minSize: 5,validator:{ password, obj ->
            if(obj.id) {
                return true
            }
            def password2 = obj.confirmPassword
            password == password2 ? true : ['invalid.matchingpasswords']
        })
        firstName(blank: false, nullable: false)
        lastName(blank: false, nullable: false)
        photo(nullable: true, sqlType: 'longBlob')
        admin(nullable: true)
        confirmPassword(nullable: true,blank: true)
    }

    @Override
    String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                '}'
    }



    List getUnreadResource(SearchCO searchCO) {

        if (searchCO.q) {
            List<ReadingItem> unReadItems = ReadingItem.createCriteria().list(max: 10, offset: 0) {
                ilike('resource.description', this.resources.description)
                eq('isRead', false)
            }
            return unReadItems
        }
    }


    Boolean canDeleteResource(Long resourceId) {
        Resource resource = Resource.get(resourceId)
        return this.admin || this.id==resource.user.id

    }
   boolean isSubscribed(Long topicId) {
            Topic topic = Topic.get(topicId)
            return topic && Subscription.findByUserAndTopic(this, topic)
        }

     Integer getScore(Long resourceId) {
        Resource resource = Resource.get(resourceId)
        Integer score = 0
        if (resource) {
            score = ResourceRating.createCriteria().get {
                projections {
                    property('score')
                }
                eq('user', this)
                eq('resource', resource)
            }
        }
        return score
    }
    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        User user = (User) o

        return id != user.id
    }

    int hashCode() {
        return (id != null ? id.hashCode() : 0)
    }
    static Integer updatePassword(String newPassword, String email) {
        return executeUpdate("update User set password=:newPassword where email=:email",
                [newPassword: newPassword, email: email])
    }
    List<Resource> unreadResources() {
        return ReadingItem.createCriteria().list {
            projections {
                property('resource')
            }
            eq('user', this)
            eq('isRead', false)
        }
    }

    UserCO toUserCO() {
        // TODO: add user
    }

}
