package com.ttn.linksharing

import com.ttn.linksharing.co.InvitationCO
import com.ttn.linksharing.co.TopicCO
import com.ttn.linksharing.dto.EmailDTO
import com.ttn.linksharing.enums.Seriousness
import com.ttn.linksharing.enums.Visibility
import com.ttn.linksharing.vo.TopicVO
import grails.gorm.transactions.Transactional

@Transactional
class TopicService {
    EmailService emailService

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
        topicCO1.identity {
            name=topic.name
            visibility = topic.visibility
            dateCreated = topic.dateCreated
            userId = topic.createdBy.id
            lastUpdated = topic.lastUpdated
            id = topic.id
            subscribedUsers = topic.subscribedUsers
        }



        // TODO: Need to return userCO
//                .collect { User user ->
//            return user.toUserCO()
//        }
        return topicCO1
    }

    def invite(InvitationCO invitationCO,User user){
        Topic topic = Topic.get(invitationCO.topicId)
        String message="Can't sent invitation"
        if (topic && (!invitationCO.email.matches("\\s"))) {
            TopicVO topicVO = new TopicVO(id: topic.id, name: topic.name, visibility: topic.visibility,
                    createdBy: topic.createdBy)
            EmailDTO emailDTO = new EmailDTO(to: [invitationCO.email], subject: "Invitations for topic from linksharing",
                    view: '/email/_invite', model: [currentUser: user, topic: topicVO])

            emailService.sendMail(emailDTO)
            message = "Successfully invited"
        }
        return message
    }

    Map join(User user,Long topicId){
        Map result=[:]
        if (user) {
            Topic topic = Topic.get(topicId)

            if (topic) {
                Subscription subscription = new Subscription(topic: topic, user: user, seriousness: Seriousness.SERIOUS)
                if (subscription?.save()) {
                    result.put("success",true)
                } else {
                    result.put("success",false)
                    result.put("message","Subscription not save successfully")

                }
            }
            else {
                result.put("success",false)
                result.put("message","Topic does not exists")
            }
        }
       result
    }
    String update(Long topicId,String name){
        Topic topic =Topic.get(topicId)
        topic.name=name
        String message="Cannot update topic name"
        if(topic.save())
            message="Topic name updated successfully"

        message
    }
}
