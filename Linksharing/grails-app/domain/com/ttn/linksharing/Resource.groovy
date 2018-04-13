package com.ttn.linksharing

import com.ttn.linksharing.co.ResourceSearchCO
import com.ttn.linksharing.domain.BaseDomain
import com.ttn.linksharing.vo.PostVO
import com.ttn.linksharing.vo.RatingInfoVO

abstract class Resource implements BaseDomain {
    String description

    static belongsTo = [user: User, topic: Topic]
    static hasMany = [resourceRating: ResourceRating, readingItems: ReadingItem]
    static constraints = {
        description(type: 'text')
    }
     RatingInfoVO ratingInfoVO

    static transients = ['ratingInfoVO']

    static namedQueries = {
        search {
            ResourceSearchCO resourceSearchCO ->
            if(resourceSearchCO.topicId)
                    eq('topic.id', resourceSearchCO.topicId)
            if(resourceSearchCO.visibility)
                eq('topic.visibility',resourceSearchCO.visibility)

        }
     }

    Integer totalVotes(Resource resource) {
        Integer votes = ResourceRating.createCriteria().count {
            eq("resource", resource)
        }

        return votes
    }

    def avgScore(Resource resource){
        def average= ResourceRating.createCriteria().get {
            projections {
                avg('score')
            }
            eq("resource",resource)
        }

        return average

    }
    def totalScore(Resource resource){
        def sum1 = ResourceRating.createCriteria().get{

            projections {
                sum('score')
            }
            eq("resource",resource)
        }

        return sum1
    }

    RatingInfoVO getRatingInfoVo(Resource resource){
        RatingInfoVO ratingInfoVO1=new RatingInfoVO()
        ratingInfoVO1.averagescore=avgScore(resource)
        ratingInfoVO1.totalScore=totalScore(resource)
        ratingInfoVO1.totalVotes=totalVotes(resource)
        return ratingInfoVO1
    }

    static List<Resource> topPost(){

        List resourceIds = ResourceRating.createCriteria().list {
            projections {
                property('resource.id')
            }
            groupProperty('resource.id')
            count('resource.id', 'resourceCount')
            order('resourceCount', 'desc')
            maxResults(5)
        }

        List<Resource> resources = Resource.getAll(resourceIds)
        return resources



    }

    static List<Resource> recentShares(){

        List results = Resource.createCriteria().list {
          order("dateCreated", "desc")

            maxResults(2)

        }
        return results
    }

    boolean canViewBy(User user){
        this.topic.canViewedBy(user)
    }

    String checkResourceType(Long id) {
        Resource resource = Resource.get(id)
        String type
        if (resource instanceof LinkResource) {
            type = "LinkResource"
        } else if (resource instanceof DocumentResource) {
            type = "DocumentResource"
        }
        return type
    }
    Boolean deleteFile() {
        log.info("Implemented in linkresource")
        return false
    }
    static PostVO getPost(Long resourceId) {

        List post = Resource.createCriteria().get {
            projections {
                property('id')
                property('description')
                property('url')
                property('filePath')
                'topic' {
                    property('id')
                    property('name')
                }
                'user' {
                    property('id')
                    property('userName')
                    property('firstName')
                    property('lastName')
                }
                property('lastUpdated')
            }
            eq('id', resourceId)
        }

        return new PostVO(resourceID: post[0], score: 0, description: post[1], url: post[2], filePath: post[3],
                topicId: post[4], topicName: post[5], userId: post[6], userUserName: post[7], userFirstName: post[8],
                userLastName: post[9], lastUpdated: post[10])
    }
    static List usersWithUnreadResources() {
        return ReadingItem.createCriteria().listDistinct {
            projections {
                property('user')
            }
            eq('isRead', false)
        }
    }
    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Resource resource1 = (Resource) o

        return id == resource1.id
    }

    int hashCode() {
        return (id != null ? id.hashCode() : 0)
    }



}





