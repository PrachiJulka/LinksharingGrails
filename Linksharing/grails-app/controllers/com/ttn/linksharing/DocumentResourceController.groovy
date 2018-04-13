package com.ttn.linksharing

import com.ttn.linksharing.co.DocumentResourceCO
import com.ttn.linksharing.co.ResourceCO

class DocumentResourceController extends ResourceController implements grails.async.web.AsyncController {

    DocumentResourceService documentResourceService

    def index() { }

    def save(){
        def file=params.file
        if(file.empty){
            flash.error="file not found"
        }
        else{
          Topic topic=documentResourceService.getTopic(params.int('topicId'))
             if(topic) {
                String path = "/home/prachi/Documents${grailsApplication.config.getProperty('linksharing.documents.folderPath')}${UUID.randomUUID()}.pdf"
                ResourceCO documentResource = new DocumentResourceCO(description: params.description, filePath: path,
                        user: session.user, topic: topic, contentType: params.file.contentType)
               def message= documentResourceService.saveDocument(documentResource, params)
                if(message) {
                    def ctx = startAsync()
                    ctx.start {
                        addToReadingItems(message,session.user.id)
                        ctx.complete()
                    }
                }
            }
            else {
                 flash.error = "Topic is not valid"
             }

        }
        forward(controller:'user',action:'index')
     }

}
