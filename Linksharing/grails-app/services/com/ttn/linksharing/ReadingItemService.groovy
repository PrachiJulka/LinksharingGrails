package com.ttn.linksharing

import grails.gorm.transactions.Transactional

@Transactional
class ReadingItemService {

   Map changeIsRead(Long readingItemId){
       ReadingItem readingItem = ReadingItem.load(readingItemId)
       Map result=[:]
       if (readingItem.isRead) {
           if (ReadingItem.executeUpdate("update ReadingItem set isRead=:isRead where id=:id", [isRead: false, id: id])) {
               result.put("success",true)
           } else{
                result.put("success",false)
                result.put("message","Error in updating")
           }

       }
       else
       {
           if (ReadingItem.executeUpdate("update ReadingItem set isRead=:isRead where id=:id", [isRead: true, id: id])) {
               result.put("success",true)
           } else
           {
               result.put("success",false)
               result.put("message","Error in updating")

           }
       }
       result
   }
}
