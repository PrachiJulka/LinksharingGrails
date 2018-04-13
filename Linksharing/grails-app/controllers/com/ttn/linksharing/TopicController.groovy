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
        Topic topic = Topic.get(invitationCO.topicId)
        if (topic && (!invitationCO.email.matches("\\s"))) {
            TopicVO topicVO = new TopicVO(id: topic.id, name: topic.name, visibility: topic.visibility,
                    createdBy: topic.createdBy)
            EmailDTO emailDTO = new EmailDTO(to: [invitationCO.email], subject: "Invitations for topic from linksharing",
                    view: '/email/_invite', model: [currentUser: session.user, topic: topicVO])


                emailService.sendMail(emailDTO)


            flash.message = "Successfully invited"
        } else {
            flash.error = "Can't sent invitation"
        }
        redirect(controller: "user", action: "index")

    }
    def handleObjectNotFoundException() {

        render ("no object found")
    }
    def join(Long topicId) {
        User user = session.user
        if (user) {
            Topic topic = Topic.get(topicId)
            if (topic) {
                Subscription subscription = new Subscription(topic: topic, user: session.user, seriousness: Seriousness.SERIOUS)
                if (subscription?.save(flush: true)) {
                    flash.message = "Subscription save successfully"
                } else {
                    flash.error = "Subscription not save successfully"
                }
            } else {
                flash.error = "Topic not exist"
            }
        }
        redirect(controller: "login", action: 'index')
    }
    def update(){
        Long topicId=Long.parseLong(params.topicId)
    Long result =Topic.executeUpdate("update Topic set name=:name where id=:topicId",[name:params.topicName,topicId:topicId])
    if(result)
        flash.message="Topic name updated successfully"
        else
        flash.error="Cannot update topic name"

        redirect(controller: "topic", action: 'show')

    }



}
