package com.ttn.linksharing

import com.ttn.linksharing.domain.BaseDomain
import com.ttn.linksharing.enums.Seriousness
import com.ttn.linksharing.enums.Visibility
import com.ttn.linksharing.vo.PostVO
import com.ttn.linksharing.vo.TopicVO

/*_create.gsp transient method getSubscribedUsers in topic domain to get all the subscribed users*/

class Topic implements BaseDomain {

    String name
    Visibility visibility

    //List<Resource> resourceList
    List getSubscribedUsers(){

        return this.subscriptions.user.toList()

    }

    static transients = ['subscribedUsers']

    static belongsTo = [ createdBy : User]
    static hasMany = [subscriptions:Subscription, resources:Resource]
    static constraints = {
        name(blank: false, nullable: false, unique: 'createdBy' )
        visibility(nullable: false)
    }
    static mapping = {
        sort("name": "asc")
        subscriptions lazy: false
    }

    def afterInsert() {
        log.info "----------Into After Insert------"
        Topic.withNewSession {
            Subscription subscription= new Subscription(topic: this,seriousness: Seriousness.VERY_SERIOUS,user: this.createdBy)
            subscription.validate()
            log.error("Topic ${subscription.errors.getFieldErrors()}")

            subscription.save()
        }


    }
   static List<TopicVO> getTrendingTopics(){
         List topicList = Resource.createCriteria().list {
            projections {
                createAlias('topic', 't')
                groupProperty('t.id')
                property('t.name')
                property('t.visibility')
                count('t.id', 'topicCount')
                property('t.createdBy')
            }
            eq('t.visibility',Visibility.PUBLIC)
            order('topicCount', 'desc')
            order('t.name', 'asc')
            maxResults(5)
        }



        List topicVOList = []
        topicList.each {
            topicVOList.add(new TopicVO(id: it[0],
                    name: it[1], visibility: it[2], count: it[3], createdBy: it[4]))


        }
        return topicVOList



    }
    List<PostVO> getTopicPosts() {
        List<PostVO> topicPosts = []
        Resource.createCriteria().list {
            projections {
                property('id')
                property('description')
                property('url')
                property('filePath')
                topic {
                    property('id')
                    property('name')
                }
                user {
                    property('id')
                    property('userName')
                    property('firstName')
                    property('lastName')
                }
                property('lastUpdated')
            }
            eq('topic.id', this.id)
            order('lastUpdated', 'desc')
        }.each {
            topicPosts.add(new PostVO(resourceID: it[0], description: it[1], url: it[2], filePath: it[3], topicId:
                    it[4], topicName: it[5], userId: it[6], userUserName: it[7], userFirstName: it[8], userLastName: it[9],
                    lastUpdated: it[10], isRead: ReadingItem.getIsRead(it[0], it[6])))
        }

        return topicPosts
    }


    boolean isPublic(){
        return this.visibility==Visibility.PUBLIC
    }


    boolean canViewedBy(User user){
        return (isPublic() || user?.admin || user?.isSubscribed(this.id))

    }

    @Override
    String toString() {
        return "Topic{" +
                "name='" + name + '\'' +
                '}'
    }
    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Topic topic= (Topic) o

        return id == topic.id
    }

    int hashCode() {
        return (id != null ? id.hashCode() : 0)
    }
}




