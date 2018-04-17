package com.ttn.linksharing

import com.ttn.linksharing.enums.Seriousness
import grails.gorm.transactions.Transactional

@Transactional
class SubscriptionService {

   String save(Long topicId,User user){
       Topic topic=Topic.load(topicId)
       String message="Error"
       Subscription subscription=new Subscription(user: user,topic:topic,seriousness:Seriousness.stringToEnum('SERIOUS'))
       if(subscription.validate()) {
           topic.addToSubscriptions(subscription)
           user.addToSubscriptions(subscription)
           user.addToSubscriptions(subscription)
           subscription.save()
         message="SUCCESS"
       }
     message
   }
    String delete(Long topicId,User user){
        Topic topic=Topic.load(topicId)
        String message="ERROR"
        Subscription subscription=Subscription.findByUserAndTopic(user,topic)
        if(subscription!=null && topic.createdBy.id != subscription.user.id)
        {
            user.removeFromSubscriptions(subscription)
            topic.removeFromSubscriptions(subscription)
            subscription.delete(flush:true)

            message="Successfully unsubscribed"
        }
       message
    }

    Map update(Long topicId,String seriousness){

       Map result=[:]
        Subscription subscription=Subscription.findByIdAndSeriousness(topicId,Seriousness.valueOf(seriousness))
        if(subscription!=null) {
            if (subscription.save(flush: true))
                result.put("success", true)
            else {
                result.put("success", false)
                result.put("message", "Error in updating subscription")
            }
        }
        else{
            result.put("success", false)
            result.put("message","Topic not found")

        }
    }
}
