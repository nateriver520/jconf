package com.github.nateriver520.jconf.parse

import spock.lang.Specification


class YamlParserSpec extends Specification {

    Parser yamlParser = new YmlParser()

    def "yaml parser"() {
        when:
            def root = yamlParser.parse(this.getClass().getResource( '/yaml/config.yml' ).text)
        then:
            root.children.size() == 3
    }
}
