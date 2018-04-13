package com.ttn.linksharing

import com.ttn.linksharing.co.TopicCO
import com.ttn.linksharing.enums.Visibility
import grails.gorm.transactions.Transactional

@Transactional
class TopicService {


    def checkVisibility(Long topicId,User currentUser) {
        Topic topic=Topic.get(topicId)
        if(topic){
            if(topic.visibility==Visibility.PUBLIC)
            {
              true
            }
            else{
                if (Subscription.countByUserAndTopic(currentUser, topic)) {
                true
                }
                else
                    return "Private Topic are not allowed"
            }
        }
        else
           return "There is no such topic available"
  }

    def getTopic(Long topicId) {
        Topic topic=Topic.get(topicId)
        TopicCO topicCO1 = new TopicCO()


        topicCO1.name = topic.name
        topicCO1.visibility = topic.visibility
        topicCO1.dateCreated = topic.dateCreated
        topicCO1.userId = topic.createdBy.id
        topicCO1.lastUpdated = topic.lastUpdated
        topicCO1.id = topic.id
        topicCO1.subscribedUsers = topic.subscribedUsers
        // TODO: Need to return userCO
//                .collect { User user ->
//            return user.toUserCO()
//        }
        return topicCO1
    }
}
