package com.github.nateriver520.jconf

import com.github.nateriver520.jconf.cache.ConfCache
import com.github.nateriver520.jconf.core.ConfNode
import com.github.nateriver520.jconf.parse.IniParser
import com.github.nateriver520.jconf.parse.JsonParser
import com.github.nateriver520.jconf.parse.Parser
import com.github.nateriver520.jconf.parse.YamlParser


class Config {
    private static def _cache = new ConfCache()
    ConfNode root
    Parser parser
    def separator = "\\."

    Config(String confPath, String fileType) {
        // get the absolute path for cache
        confPath = new File(confPath).absolutePath

        if (_cache.get(confPath)) {
            // if find in cache, get data from cache
            this.root = _cache.get(confPath)
        }

        switch (fileType.toLowerCase()) {
            case SupportType.JSON.val:
                this.parser = new JsonParser()
                break
            case SupportType.YAML.val:
                this.parser = new YamlParser()
                break

            case SupportType.INI.val:
                this.parser = new IniParser()
                break

            default:
                throw new Exception("don't support type:${fileType} conf file.")
        }

        if (!root) {
            root = parser.parse(new File(confPath).text)
            _cache.set(confPath, root)
        }
    }

    Config(String confPath) {
        this(confPath, confPath.lastIndexOf('.').with {it != -1 ? confPath[it+1..-1] : ''})
    }

    private ConfNode get(String key) {
        ConfNode node = root
        key.split(separator).each { k ->
            if (!node) {
                return
            }

            node = node.get(k)
        }
        node && node.value != null ? node : null
    }

    String getString(String key, String defVal = '') {
        ConfNode node = get(key)
        node ? node.value.toString() : defVal
    }

    boolean getBoolean(String key, boolean defVal = false) {
        ConfNode node = get(key)
        node ? node.value.toBoolean() : defVal
    }

    List getList(String key, List defVal = []) {
        ConfNode node = get(key)
        node ? node.value : defVal
    }

    Double getDouble(String key, Double defVal = 0.0) {
        ConfNode node = get(key)
        node ? node.value.toDouble() : defVal
    }

    Float getFloat(String key, Float defVal = 0.0) {
        ConfNode node = get(key)
        node ? node.value.toFloat() : defVal
    }

    Long getLong(String key, Long defVal = 0L) {
        ConfNode node = get(key)
        node ? node.value.toLong() : defVal
    }

    Integer getInteger(String key, Integer defVal = 0) {
        ConfNode node = get(key)
        node ? node.value.toInteger() : defVal
    }
}

enum SupportType {
    JSON("json"),
    YAML("yml"),
    INI("ini")

    def val

    SupportType(val) {
        this.val = val
    }
}