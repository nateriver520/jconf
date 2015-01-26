package com.nateriver520.jconf.parse

import spock.lang.Specification


class JsonParserSpec extends Specification {

    Parser jsonParser = new JsonParser()

    def "json parser"() {
        when:
            def root = jsonParser.parse(this.getClass().getResource( '/json/config.json' ).text)
        then:
            root.children.size() == 2
    }
}
