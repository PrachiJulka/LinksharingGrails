package com.ttn.linksharing

import com.ttn.linksharing.enums.Seriousness

//_create.gsp update action which takes an id and serious
// as a parameter if subscription and seriousness found,
// then save else render not found, if saved then render success else errors
class SubscriptionController {

    def index() { }


    def save(Long id){
        Topic topic=Topic.load(id)
        Subscription subscription=new Subscription(user: session.user,topic:topic,seriousness:Seriousness.stringToEnum('SERIOUS'))
        if(subscription.validate()) {
            topic.addToSubscriptions(subscription)
            session.user.addToSubscriptions(subscription)
            session.user.addToSubscriptions(subscription)
            subscription.save()
            flash.message="SUCCESS"
        }
        else
            flash.error="ERROR"

        redirect(controller:"user", action:"index")
    }

    def delete(Long id){
        Topic topic=Topic.load(id)
        Subscription subscription=Subscription.findByUserAndTopic(session.user,topic)
        if(subscription!=null && topic.createdBy.id != subscription.user.id)
        {
            (User)(session.user).removeFromSubscriptions(subscription)
            topic.removeFromSubscriptions(subscription)
            subscription.delete(flush:true)

            flash.message="Successfully unsubscribed"
        }
        else {
            flash.error = "ERROR"


        }
        redirect(controller:"user", action:"index")
    }

    def update(Integer id,String serious){
        Subscription subscription=Subscription.findByIdAndSeriousness(id,Seriousness.valueOf(serious))
        if(subscription!=null)
        {
            if(subscription.save(flush:true))
            render("success")
            else
                render("failure")
        }
        else
            render("not found")
    }
}
