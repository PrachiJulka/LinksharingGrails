package com.ttn.linksharing.enums
//_create.gsp a method in visibility enum to convert string into enum and write test case for the same
enum Visibility {
    PUBLIC,PRIVATE

   static Visibility stringToEnum(String visibility){
       return Visibility.valueOf(visibility)

    }

}

