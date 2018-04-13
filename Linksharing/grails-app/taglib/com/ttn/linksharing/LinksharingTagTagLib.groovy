package com.ttn.linksharing
/*
Update changeIsRead action of readingitem controller,
now it will take resourceId as argument and user
 from session update the query accordingly
*/
class LinksharingTagTagLib {

        static namespace = "ls"
    static defaultEncodeAs = [taglib:'text']

    def read={
        attrs,body ->
            def resourceId = attrs?.id

            if(session.user!=null) {

                    Long id=session.user.id
                    User user=User.get(id)
                Resource resource=Resource.get(resourceId)
                ReadingItem readingItem = ReadingItem.findByResourceAndUser(resource, user)

                        if(readingItem?.isRead) {
                            out << "<a href='/readingItem/changeIsRead/${resourceId}'>unread</a>"
                         }
                        else
                            out << "<a href='/readingItem/changeIsRead/${resourceId}'>Mark as Read</a>"


                }
            }



    def trendingTopics={
                attrs->
           out << g.render(template: '/topic/trendingTopics')
            
    }
    def topPosts={
        attrs->
            out<< g.render(template: '/topic/topPosts')
    }




    def canDeleteResource = { attr, body ->

       if(session.user!=null) {
           Boolean canDelete = session.user?.canDeleteResource(attr.resourceId)
           if (canDelete) {
               out << "<a href='/resource/delete/${attr.resourceId}'>delete</a>"
           }
       }
    }
/*
    Create tag showSubscribe which will take topicId and shows the text with delete or
     save link only when topicId is given and user is logged in*/
    def showSubscribe = { attr, body ->
        Long id = attr.topicId
        if (session.user) {
            if (session.user.isSubscribed(id)) {

                out <<"<a href='/subscription/delete/${id}'>Unsubscribe</a>"

            } else {
                out << "<a href='/subscription/save/${id}'>subscribe</a>"

            }
        }
    }

    def subscriptionCount = { attr, body ->
        Long count = 0
        if (attr.topicId) {
            count = Subscription.createCriteria().count {
                eq('topic.id', attr.topicId)
            }
        } else if (attr.user) {
            count = Subscription.createCriteria().count {
                eq('user', attr.user)
            }
        }
        out << count
    }
    //Create tag resourceCount which will take topicId and shows resource count of that topic
    def resourceCount={
        attr,body->
            Long count=Resource.createCriteria().count{
                eq('topic.id',attr.topicId)
            }
            out<<count

    }

    //Create tag topicCount which takes user and shows number of topics created by user
    def topicCount={
        attr,body->
            Long topicCount = Topic.createCriteria().count {
                eq('createdBy', attr.user)
            }
            out << topicCount
    }
    //Create tag userImage which will take userId as a parameter and creates img tag with
    // src of /user/image/id and add all other parameters to img tag like width, height
    def userImage={
        attr,body->
            Long id=attr.id
            out << "<img src='/user/image/${id}' height=\"100\" width=\"100\">"
    }


   /* static defaultEncodeAs = [taglib:'html']*/
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
}
