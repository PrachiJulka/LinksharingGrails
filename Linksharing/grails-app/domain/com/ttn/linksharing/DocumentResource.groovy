package com.ttn.linksharing

import com.ttn.linksharing.constants.DefaultPassword


class DocumentResource extends Resource {

    String filePath
    String contentType
    static transients = ['contentType', 'fileName']
    static constraints = {
        filePath(nullable: false,blank: false)
        contentType(bindable: true, blank: false, validator: { val, obj ->
            return( val==(DefaultPassword.DOCUMENT_CONTENT_TYPE))
        })
    }

    @Override
     String toString() {
        return "DocumentResource{" +
                "filePath='" + filePath + '\'' +
                '}'
    }
    String getFileName() {
        String fileName = filePath.substring(filePath.lastIndexOf('/') + 1, filePath.length())
        return fileName ?: ""
    }
/*
    Override deleteFile method in DocumentResource.groovy to delete the
     file and call this method in ResourceController delete action after delete is called*/

    @Override
    Boolean deleteFile() {
        String filePath = createCriteria().get {
            projections {
                property('filePath')
            }
            eq('id', this.id)
        }
        boolean fileSuccessfullyDeleted = new File(filePath).delete()
        if (fileSuccessfullyDeleted) {
            this.delete()
        }
        return fileSuccessfullyDeleted
    }
    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        DocumentResource documentResource= (DocumentResource) o

        return id == documentResource.id
    }

    int hashCode() {
        return (id != null ? id.hashCode() : 0)
    }


}
