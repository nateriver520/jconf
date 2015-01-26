package com.github.nateriver520.jconf.parse

import spock.lang.Specification


class IniParserSpec extends Specification {
    Parser iniParser = new IniParser()

    def "ini parser"() {
        when:
        def root = iniParser.parse(this.getClass().getResource('/ini/config.ini').text)
        then:
        root.children.size() == 3
    }
}
