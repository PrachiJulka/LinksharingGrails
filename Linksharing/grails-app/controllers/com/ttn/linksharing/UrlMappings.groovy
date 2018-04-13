package com.ttn.linksharing

class UrlMappings {

    static mappings = {

        "/$controller/$action?/$id?"{
            constraints {
// apply constraints here
            }
        }


        "/"(view:"/login/index")
         "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
