package com.nateriver520.jconf.parse

import com.nateriver520.jconf.core.ConfNode
import groovy.json.JsonSlurper
import groovy.json.internal.LazyMap


class JsonParser implements Parser {
    def static slurper = new JsonSlurper()

    ConfNode parse(def confText) {
        def jsonNode = slurper.parseText(confText)
        def rootNode = new ConfNode()
        fillNode(rootNode, jsonNode)
        return rootNode
    }

    def fillNode(ConfNode root, def jsonNode) {
        if (jsonNode.getClass() != LazyMap) {
            root.value = jsonNode
            return
        }
        jsonNode.each { k, v ->
            ConfNode child = ConfNode.newInstance()
            child.setType(v.class)
            root.children.put(k, child)
            fillNode(child, v)
        }
    }

}