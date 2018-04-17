package com.ttn.linksharing
class SubscriptionController {

    SubscriptionService subscriptionService
    def index() { }


    def save(Long topicId){
        String message=subscriptionService.save(topicId,session.user)
        flash.message=message
        redirect(controller:"user", action:"index")
    }

    def delete(Long id){
      String message= subscriptionService.delete(id,session.user)
        flash.message=message
        redirect(controller:"user", action:"index")
    }

    def update(Long id,String serious){
       Map result=subscriptionService.update(id,serious)
        if(result.get("success")){
            flash.message="Subscription updated successfully"
        }
        else
            flash.error=result.get("message")
    }
}
