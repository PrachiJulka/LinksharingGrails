package com.ttn.linksharing

import com.ttn.linksharing.vo.PostVO

class ResourceController {


    ResourceService resourceService
    def index() { }


    def delete(Integer id){

        Resource resource=Resource.load(id)
        if(resource)
        {
            resource.delete(flush:true)
            flash.message="Resource Deleted Successfully"
            redirect(controller:'user',action:'index')
        }
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
        Long result =Resource.executeUpdate("update Resource set description=:description where id=:resourceId",[description:params.description,resourceId:resourceId])
        if(result)
            flash.message="Resource description updated successfully"
        else
            flash.error="Cannot update Resource description"

        redirect(controller: "topic", action: 'show')

    }


    def handleNullPointerException() {

        render ("null found")
    }
}
