package com.ttn.linksharing

import com.ttn.linksharing.co.LinkResourceCO
import com.ttn.linksharing.co.ResourceCO

class LinkResourceController extends ResourceController implements grails.async.web.AsyncController {

    LinkResourceService linkResourceService

    def index() { }

    def save(LinkResourceCO linkResourceCO){
        ResourceCO resourceCO=new LinkResourceCO()
        resourceCO=linkResourceCO
        resourceCO.user=session.user
        Long id=Long.parseLong(params.topicId)
        def resource= linkResourceService.saveLinkResource(resourceCO,id)
        if(resource) {
            def ctx = startAsync()
            ctx.start {
                addToReadingItems(resource,session.user.id)
                ctx.complete()

            }

            flash.message="Saved Successfully"
        }
        else{
            flash.message="ERROR OCCURED"
        }
        forward(controller:'user', action: 'index')

    }

}
