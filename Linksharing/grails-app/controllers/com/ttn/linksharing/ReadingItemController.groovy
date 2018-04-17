package com.ttn.linksharing



class ReadingItemController {

    ReadingItemService readingItemService
    def index() { }

    def changeIsRead(Long id) {
       Map result= readingItemService.changeIsRead(id)
        if(result)
            flash.message="Updated successfully"
        else
            flash.error=result.get("message")

       redirect(controller:'user', action: 'index')
    }
}
