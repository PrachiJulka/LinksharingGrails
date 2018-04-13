package com.ttn.linksharing

import com.ttn.linksharing.co.UpdatePasswordCO
import com.ttn.linksharing.co.UpdateProfileCO
import grails.gorm.transactions.Transactional

@Transactional
class UserService {
    EmailService emailService


    User getUser(Long userId) {
        User user = User.get(userId)
        return user
    }

    def sendUnreadItemsEmail() {
        getUserWithUnreadItems().each { user ->
            emailService.sendUnreadResourcesEmail(user, getUnreadResources(user))
        }
    }
    List<User> getUserWithUnreadItems() {
        return Resource.usersWithUnreadResources()
    }
    List<Resource> getUnreadResources(User user) {
        return user.unreadResources()
    }

    def updateProfile(Long id,UpdateProfileCO updateProfileCO){
        User user=getUser(id)
        Integer result
        if(user){
            if(updateProfileCO.photo && updateProfileCO.validate())
          result= User.executeUpdate("update User set firstName=:firstName,lastName=:lastName,userName=:userName,photo=:photo where id=:id",[firstName:updateProfileCO.firstName,lastName:updateProfileCO.lastName, userName:updateProfileCO.userName,photo:updateProfileCO.photo,id:id])
            else
          result= User.executeUpdate("update User set firstName=:firstName,lastName=:lastName,userName=:userName where id=:id",[firstName:updateProfileCO.firstName,lastName:updateProfileCO.lastName, userName:updateProfileCO.userName,id:id])

        }
        result

    }

    def updatePassword(Long id,UpdatePasswordCO updatePasswordCO){
        User user=getUser(id)
        Integer result
        if(user && user.password==updatePasswordCO.oldPassword){
                 result= User.executeUpdate("update User set password=:password where id=:id",[password:updatePasswordCO.password,id:id])

        }
        result
    }

    void updateStatus(Long userId,boolean status){
        User user=User.get(userId)
        user.active=status

         }


}
