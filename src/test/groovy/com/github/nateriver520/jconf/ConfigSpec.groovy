package com.github.nateriver520.jconf

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


    def "parser yaml config"() {
        when:
        def conf = new Config(this.getClass().getResource('/yaml/config.yml').path)

        then:
        // can find key
        conf.getInteger("default.redis.port") == 6379
        conf.getString("default.redis.host") == "127.0.0.1"
        conf.getString("production.redis.db") == "0"

        // key miss
        conf.getString("persion.mail", "example@example.com") == "example@example.com"
    }

    def "parser ini config"() {
        when:
        def conf = new Config(this.getClass().getResource('/ini/config.ini').path)

        then:
        // can find key
        conf.getString("dev.JDBC_URL") == "jdbc:h2:mem:mem_test;MODE=Oracle"
        conf.getString("prod.JDBC_URL") == "jdbc:oracle:thin:@prod-oracle:1521:prod"
        conf.getString("dev.JDBC_USERNAME") == ""

        conf.getInteger("dev.JDBC_PORT") == 3006

        // key miss
        conf.getString("persion.mail", "example@example.com") == "example@example.com"
    }

    def "parser xml config"() {
        when:
        def conf = new Config(this.getClass().getResource('/xml/config.xml').path)

        then:
        // can find key
        conf.getString("app.host") == "localhost"
        conf.getInteger("app.port") == 80
        conf.getBoolean("debug")

        // key miss
        conf.getString("persion.mail", "example@example.com") == "example@example.com"
    }

    def "parser config without assign file type"() {
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

    def "parser json config with separator"() {
        when:
        def conf = new Config(this.getClass().getResource('/json/config.json').path, 'json')
        // set separator with ::
        conf.separator = "::"

        then:
        // can find key
        conf.getString("person::name") == 'Guillaume'

        // key miss
        conf.getString("persion::mail", "example@example.com") == "example@example.com"
    }

    def "modify config"() {
        when:
        def conf = new Config(this.getClass().getResource('/yaml/config.yml').path, 'yml')
        conf.set("default.redis.port", 3000)
        conf.set("mock.setting.port", 2000)

        then:
        // conf key default.redis.port should be set to 3000
        conf.getInteger("default.redis.port") == 3000
        conf.getInteger("mock.setting.port") == 2000

        when:
        conf.del("default.redis.port")

        // exist mock.setting.port
        conf.exist("mock.setting.port")
        conf.del("mock.setting.port")

        then:
        conf.getString("default.redis.port") == ""
        !conf.exist("default.redis.port")

        conf.getInteger("mock.setting.port") == 0
        !conf.exist("mock.setting.port")
    }

}
