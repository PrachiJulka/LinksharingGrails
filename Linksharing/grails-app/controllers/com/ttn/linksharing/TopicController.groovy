package com.ttn.linksharing

import com.ttn.linksharing.co.InvitationCO
import com.ttn.linksharing.co.ResourceSearchCO
import com.ttn.linksharing.co.TopicCO
import com.ttn.linksharing.dto.EmailDTO
import com.ttn.linksharing.enums.Seriousness
import com.ttn.linksharing.vo.TopicVO
import grails.converters.JSON

class TopicController {
    TopicService topicService
    EmailService emailService
    def index() { }



    def search(ResourceSearchCO resourceSearchCO){
        def topic = Resource.search(resourceSearchCO).list()
        render("CreatedBy- $topic.createdby.firstname Topicname- $topic.name")
       User user=User.read(session.user.id)
        //println user.topics
        render(user.topics)
    }
    def show(Long topicId){
       def check= topicService.checkVisibility(topicId,session.user)
        if(check==true){
            TopicCO topicCO=topicService.getTopic(topicId)
            render(view: 'show', model: [subscribedUsers: topicCO?.getSubscribedUsers(), topicCO: topicCO,topicPosts: topicCO?.getTopicPosts()])
        }
        else{
            flash.error = check
            redirect(controller: "login", action: "index")
        }

    }

    def delete(Long id){
        Topic topic=Topic.load(id)
        topic.delete(flush:true)
            render("Sucessfully deleted")
     }

    def save(Topic topic){

        topic.createdBy=session.user
        def result=[:]
        if(topic.validate()){

            topic.save()
             result=[sucess:"Topic Saved Successfully"]

        }

        render result as JSON


    }
    def invite(InvitationCO invitationCO) {
        String result=topicService.invite(invitationCO,session.user)
        flash.message=result
        redirect(controller: "user",action: "index")
    }
    def handleObjectNotFoundException() {

        render ("no object found")
    }
    def join(Long topicId) {

        Map result=topicService.join(session.user,topicId)
        if(result.get("success")){
            flash.message="Subscription saved successfully"
        }
        else{
            flash.error=result.get("message")
        }

        redirect(controller: "login", action: 'index')
    }

    def update(){
        Long topicId=Long.parseLong(params.topicId)
        String message=topicService.update(topicId,params.topicName)
        flash.message=message
        redirect(controller: "topic", action: 'show')
     }



}
