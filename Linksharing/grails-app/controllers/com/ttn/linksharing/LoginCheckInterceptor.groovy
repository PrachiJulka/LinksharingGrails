package com.ttn.linksharing

//If session.user is not set then redirect user to login index,
// this should be done in interceptor
// - user index action should render session user username
class LoginCheckInterceptor {

    LoginCheckInterceptor(){
        matchAll().excludes(controller: 'login')
                  .excludes(controller: 'resource',action: 'show')
                  .excludes(controller:'topic',action:'show')
                  .excludes(controller:'resource',action: 'search')
                  .excludes(controller: 'user',action: 'image')


    }

    boolean before() {
        if (!session.user) {
           flash.error= "NO ACTIVE SESSION"
          redirect(controller: 'login', action: 'index')

        false
        }

        true
    }

    boolean after() { true }
}
