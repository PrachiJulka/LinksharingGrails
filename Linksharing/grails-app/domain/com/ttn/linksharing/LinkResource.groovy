package com.ttn.linksharing
//Add toString for linkresource with url
class LinkResource extends Resource {

    String url

    static constraints = {
        url(url: true,nullable:false,blank: false)
    }


    @Override
    String toString() {
        return "LinkResourceCO{" +
                "url='" + url + '\'' +
                '}'
    }
    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        LinkResource linkResource= (LinkResource) o

        return id == linkResource.id
    }

    int hashCode() {
        return (id != null ? id.hashCode() : 0)
    }
}
