package com.ttn.linksharing

import com.ttn.linksharing.co.ResourceCO
import grails.gorm.transactions.Transactional

@Transactional
class LinkResourceService {

    def saveLinkResource(ResourceCO resourceCO,Long topicId){
        Resource resource=new LinkResource()
        Topic topic=Topic.get(topicId)
        resource.setTopic(topic)
        resource.setUser(resourceCO.user)
        resource.setUrl(resourceCO.url)
        resource.setDescription(resourceCO.description)
        if(resource.validate()) {
            resource.save(failOnError:true)
        }

        return resource


    }
}
