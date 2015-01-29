package com.github.nateriver520.jconf

import com.github.nateriver520.jconf.cache.Cache
import com.github.nateriver520.jconf.cache.ConfCache
import com.github.nateriver520.jconf.core.ConfNode
import spock.lang.Specification


class ConfigCacheSpec  extends Specification{
    def _mockcache = Mock(Cache)
    def _jsonConfPath = this.getClass().getResource('/json/config.json').path


    def "when parse a config file from the same path, then it should read from cache"() {
        setup:
        _mockcache.get(_) >> new ConfNode()

        when:
        Config._cache = _mockcache
        new Config(_jsonConfPath)

        then:
        0 * _mockcache.set(_jsonConfPath, _)
    }

    def "when parse a config file for the first time, then it should set cache"() {
        when:
        Config._cache = _mockcache
        new Config(_jsonConfPath)

        then:
        1 * _mockcache.get(_jsonConfPath)
        1 * _mockcache.set(_jsonConfPath, _)
    }

    def "when parse a config file, then it will also been set to the cache"() {
        setup:
        Config._cache = new ConfCache()

        when:
        new Config(_jsonConfPath)

        then:
        Config._cache.size() != 0
    }
}
