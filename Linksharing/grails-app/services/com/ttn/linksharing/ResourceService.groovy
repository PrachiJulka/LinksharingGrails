package com.ttn.linksharing

import grails.gorm.transactions.Transactional

@Transactional
class ResourceService {

    def addReadingItems(Resource resource,Long userId) {
        Topic topic = Resource.createCriteria().get {
            projections {
                property('topic')
            }
            eq('topic', resource.topic)
        }
        List<User> subscribedUserList = topic.getSubscribedUsers()
        subscribedUserList.each { user ->
            if (user.id == userId)
                resource.addToReadingItems(new ReadingItem(user: user, resource: resource, isRead: true).save())
            else
                resource.addToReadingItems(new ReadingItem(user: user, resource: resource, isRead: false).save())
        }
    }

    def getDataForPost(Long resourceId,User user){
        Map data = [:]
        Resource resource = Resource.findById(resourceId)
        if (resource) {
            if (resource.topic.canViewedBy(user)) {
                    data.put("success", true)
            } else {
                data.put("success", false)
                data.put("errorMessage","You cannot view this topic")

            }
        } else {
            data.put("success", false)
            data.put("errorMessage","Resource not found")
        }
        return data
    }
}
