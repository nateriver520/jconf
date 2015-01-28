package com.github.nateriver520.jconf

import com.github.nateriver520.jconf.cache.ConfCache
import com.github.nateriver520.jconf.core.ConfNode
import com.github.nateriver520.jconf.core.NodeType
import com.github.nateriver520.jconf.parse.Parser

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

        this.parser = getParser(fileType)
        if (!root) {
            root = parser.parse(new File(confPath).text)
            _cache.set(confPath, root)
        }
    }

    Config(String confPath) {
        this(confPath, confPath.lastIndexOf('.').with { it != -1 ? confPath[it + 1..-1] : '' })
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

    def set(String key, def v) {
        def node = root
        def keyList = key.split(separator)
        keyList.each { k ->
            ConfNode next = node.get(k)
            if (!next || keyList.last() == k) {
                // add new one
                ConfNode child = new ConfNode(parent: node)
                if (keyList.last() == k) {
                    child.type = NodeType.getType(v.class)
                    child.value = v
                } else {
                    child.type = NodeType.OBJECT
                }
                node.children.put(k, child)
                next = child
            }
            node = next
        }
    }

    def del(String key) {
        def node = get(key)
        if (node) node.parent.del(key.split(separator).last())
    }

    def exist(String key) {
        get(key) ? true : false
    }

    private ConfNode get(String key) {
        ConfNode node = root
        key.split(separator).find { k ->
            if (!node) return true
            node = node.get(k)
            return false
        }
        node
    }

    static def getParser(String fileType) {
        try {
            Eval.me("return new ${"com.github.nateriver520.jconf.parse.${fileType[0].toUpperCase() + fileType.substring(1).toLowerCase()}Parser"}()")
        } catch (Exception e) {
            throw new Exception("don't support type:${fileType} conf file.")
        }
    }
}