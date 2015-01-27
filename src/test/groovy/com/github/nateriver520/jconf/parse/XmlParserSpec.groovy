package com.github.nateriver520.jconf.parse

import spock.lang.Specification


class XmlParserSpec extends Specification{
    Parser xmlParser = new XmlParser()

    def "xml parser"() {
        when:
        def root = xmlParser.parse(this.getClass().getResource( '/xml/config.xml' ).text)
        then:
        root.children.size() == 3
    }
}
