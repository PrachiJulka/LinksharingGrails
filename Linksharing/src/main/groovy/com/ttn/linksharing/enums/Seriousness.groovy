package com.ttn.linksharing.enums
//_create.gsp static method in seriuosness
// which take string as parameter and returns seriousness, it should be case insensitive
enum Seriousness {

    SERIOUS, VERY_SERIOUS, CASUAL

    static Seriousness stringToEnum(String seriousness){
        return Seriousness.valueOf(seriousness)
    }
}