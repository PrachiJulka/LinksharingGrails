package com.ttn.linksharing

import com.ttn.linksharing.vo.PostVO

class ResourceController {


    ResourceService resourceService
    def index() { }


    def delete(Long resourceId){

        String message=resourceService.delete(resourceId)
        flash.message=message
        redirect(controller:'user',action:'index')
    }
    def handleObjectNotFoundException() {

        render (view: 'index', "no object found")
    }
    def search(){
/*
        if(searchCo.q==null)
            flash.error="Criteria is not given"
*/

     }

    def show(Long resourceId){
        Map data = resourceService.getDataForPost(resourceId,session.user)

        if(data.get("success")){
            PostVO postVO = Resource?.getPost(resourceId)
            render(view: 'show', model: [postVO: postVO, trendingTopics: Topic?.getTrendingTopics()])
        }
        else{

            flash.error=data.get("errorMessage")
            redirect(controller: "login", action: "index")
        }
    }
    void addToReadingItems(Resource resource,Long userId) {
        resourceService.addReadingItems(resource,userId)

    }

    def updateDescription(){
        Long resourceId=Long.parseLong(params.resourceId)
        String message=resourceService.updateDescription(resourceId,params.description)
        flash.message=message

        redirect(controller: "topic", action: 'show')

    }


    def handleNullPointerException() {

        render ("null found")
    }
}
