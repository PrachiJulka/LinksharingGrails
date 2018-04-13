package com.ttn.linksharing

import grails.testing.gorm.DomainUnitTest
import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification
class SubscriptionControllerSpec extends Specification implements ControllerUnitTest<SubscriptionController>,DomainUnitTest<Subscription> {

    def setup() {
    mockDomain(User)
        mockDomain(Topic)

    }
  def "data should be saved on save action" (){
        given:
        User user = new User(email: "prachijulka@gmail.com", userName: "PrachiJulka", password: "ROOT@123",confirmPassword: "ROOT@123",
                firstName: "Prachi", lastName: "Julka", admin: false, active: true, photo: 101)
        Topic topic=new Topic(createdBy: user, name: "topic",visibility: Visibility.PUBLIC)

        session.user=user
        topic.save(flush:true ,failOnError:true)

        when:
        controller.save(topic.id)

        then:
        response.contentAsString=="error"


    }
}
