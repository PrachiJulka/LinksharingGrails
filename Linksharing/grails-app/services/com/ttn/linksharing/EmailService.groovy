package com.ttn.linksharing

import com.ttn.linksharing.dto.EmailDTO
import com.ttn.linksharing.util.Utility
import grails.gorm.transactions.Transactional

@Transactional
class EmailService {
    def mailService
    def messageSource
    def validateUserEmail(String email){
        User user = User.findByEmail(email)
        String message = "Email id does not exists"
        if (user && user.active) {
            String newPassword = Utility.getRandomPassword()
            user.password = newPassword
            EmailDTO emailDTO = new EmailDTO(to: [email], subject: "Your LinkSharing New Password",
                    view: '/email/_password', model: [newPassword: newPassword])
            sendMail(emailDTO)
            if (User.updatePassword(newPassword, email)) {
                message = "password updated successfully..Please check your mail"
            } else {
                message = "password update unsuccessfully"
            }
        }
      return message
    }

    def sendMail(EmailDTO emailDTO) {
        mailService.sendMail {
            to(emailDTO.to.toArray())
            subject(emailDTO.subject)
            emailDTO.content ? html(emailDTO.content) :
                    html(view: emailDTO.view, model: emailDTO.model)
        }
    }

def sendUnreadResourcesEmail(User user, List<Resource> unreadResource) {
    EmailDTO emailDTO = new EmailDTO(to: [user.email],
            subject: messageSource.getMessage("com.ttn.dto.EmailDTO.unread.resource", [].toArray(), Locale.default),
            view: "/email/unreadResources", model: [user: user, unreadResource: unreadResource])
    sendMail(emailDTO)
}

}

