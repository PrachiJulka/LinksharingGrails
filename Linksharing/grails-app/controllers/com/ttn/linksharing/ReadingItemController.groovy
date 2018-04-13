package com.ttn.linksharing

/*
Add Inbox feature on user/index when user is loggedin

- _create.gsp method getUnReadResources in user domain which takes SearchCO argument
 and returns unreaditems of user from ReadingItem domain
- The search should also work using user/index page, q parameter of SearchCO.
 If searchco.q is found then getUnReadResources method will search the items based
  on ilike of resource.description.
        - The pagination parameter should also be used in getUnReadResources
         criteria query. _create.gsp readingItem/changeIsRead action which takes
          Long id and Boolean isRead
- User executeUpdate to change the isRead of readingItem with given id
- If value returned by executeUpdate is 0 then render error else render success
*/


class ReadingItemController {

    def index() { }

    def changeIsRead(Long id) {
        ReadingItem readingItem = ReadingItem.load(id)
        if (readingItem.isRead) {
            if (ReadingItem.executeUpdate("update ReadingItem set isRead=:isRead where id=:id", [isRead: false, id: id])) {
                flash.message="success"
            } else
               flash.error="ERROR"
        }
        else
        {
            if (ReadingItem.executeUpdate("update ReadingItem set isRead=:isRead where id=:id", [isRead: true, id: id])) {
                flash.message="success"
            } else
                flash.error="ERROR"
        }
       redirect(controller:'user', action: 'index')
    }
}
