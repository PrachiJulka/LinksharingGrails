package com.ttn.linksharing

import com.ttn.linksharing.enums.Visibility
import grails.testing.gorm.DomainUnitTest
import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification
class TopicControllerSpec extends Specification implements ControllerUnitTest<TopicController> ,DomainUnitTest<Topic>{


    def setup () {
       mockDomain(User)
    }



      def "topic should be saved on save action" () {
          given:
          User user = new User(email: "prachijulka@gmail.com", userName: "PrachiJulka", password: "ROOT@123", confirmPassword: "ROOT@123",
                  firstName: "Prachi", lastName: "Julka", admin: false, active: true, photo: 101)

              session.user=user
          Topic topic=new Topic(name: "topic20",visibility:Visibility.PUBLIC,createdBy: session.user)
          user.addToTopics(topic)

          when:
          controller.save(topic)

          then:
          response.contentAsString == "Topic Saved Successfully"


      }

    def "Delete topic" (){
        given:
        User user = new User(email: "prachijulka@gmail.com", userName: "PrachiJulka",confirmPassword: "ROOT@123", password: "ROOT@123",
                firstName: "Prachi", lastName: "Julka", admin: false, active: true, photo: 101)
        session.user=user
        Topic topic=new Topic(name: "topic20",visibility:Visibility.PUBLIC,createdBy: session.user)
        topic.save(flush:true)
        user.addToTopics(topic)
        user.save()
        when:
        controller.delete(topic.id)
        then:
        response.contentAsString=="Sucessfully deleted"

    }

}
