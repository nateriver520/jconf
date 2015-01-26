package com.nateriver520.jconf

import spock.lang.Specification


class ConfigSpec extends Specification {
    def "parser json config"() {
        when:
        def conf = new Config(this.getClass().getResource('/json/config.json').path, 'json')

        then:
        // can find key
        conf.getString("person.name") == 'Guillaume'
        conf.getInteger("person.age") == 33
        conf.getDouble("person.score") == 93.3.toDouble()
        conf.getFloat("person.score") == 93.3.toFloat()
        conf.getLong("person.id") == 110000012129.toLong()
        conf.getBoolean("person.is_admin")
        conf.getList("person.pets") == ["dog", "cat"]
        conf.getString("work") == "cs"

        // key miss
        conf.getString("persion.mail", "example@example.com") == "example@example.com"
    }

    def "parser json config without assign file type"() {
        when:
        def conf = new Config(this.getClass().getResource('/json/config.json').path)

        then:
        conf.root.children.size() != 0

        when:
        new Config("/tmp/conf")

        then:
        Exception ex = thrown()
        ex.getMessage().contains("don't support type")
    }
}
