package com.ttn.linksharing

class UnreadItemEmailJob {

    def userService
    static triggers = {

        cron name: 'Trigger', cronExpression: "0 0 1 ? * MON *"
    }

    def execute() {
        userService.sendUnreadItemsEmail()
    }
}
