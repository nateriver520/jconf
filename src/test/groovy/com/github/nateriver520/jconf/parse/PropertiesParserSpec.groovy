package com.github.nateriver520.jconf.parse

import spock.lang.Specification


class PropertiesParserSpec extends Specification {

    Parser propertiesParser = new PropertiesParser()

    def "properties parser"() {
        when:
            def root = propertiesParser.parse(this.getClass().getResource( '/properties/config.properties' ).text)
        then:
            root.children.size() == 1
    }
}
