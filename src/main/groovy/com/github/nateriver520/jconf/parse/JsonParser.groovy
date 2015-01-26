package com.github.nateriver520.jconf.parse

import com.github.nateriver520.jconf.core.ConfNode
import com.github.nateriver520.jconf.core.NodeType
import groovy.json.JsonSlurper
import groovy.json.internal.LazyMap


class JsonParser implements Parser {
    def static slurper = new JsonSlurper()

    @Override
    ConfNode parse(def confText) {
        def jsonNode = slurper.parseText(confText)
        def rootNode = new ConfNode(type: NodeType.ROOT)
        fillNode(rootNode, jsonNode)
        return rootNode
    }

    def fillNode(ConfNode root, def jsonNode) {
        if (jsonNode.getClass() != LazyMap) {
            root.value = jsonNode
            return
        }
        jsonNode.each { k, v ->
            ConfNode child = new ConfNode(type: NodeType.getType(v.class))
            root.children.put(k, child)
            fillNode(child, v)
        }
    }

}
