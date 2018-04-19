package com.ttn.linksharing

class UrlMappings {

    static mappings = {

        "/$controller/$action?/$id?"{
            constraints {

            }
        }


        "/"(view:"/login/index")
         "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
