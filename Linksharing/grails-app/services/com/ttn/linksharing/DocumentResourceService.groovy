package com.ttn.linksharing

import com.ttn.linksharing.co.ResourceCO
import grails.gorm.transactions.Transactional

@Transactional
class DocumentResourceService {

    def saveDocument(ResourceCO resourceCO, def params) {
        Resource resource = new DocumentResource()
        resource.topic = resourceCO.topic
        resource.user = resourceCO.user
        resource.description = resourceCO.description
        resource.filePath = resourceCO.filePath
        resource.contentType = resourceCO.contentType
        if (resource.validate()) {
            File fileDest = new File(resource.filePath)
            resource.save()
            params.file.transferTo(fileDest)
            return resource

        }

    }

    def getTopic(Long id) {
        Topic topic = Topic.get(id)
        return topic

    }



}
