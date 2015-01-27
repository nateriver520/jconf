package com.github.nateriver520.jconf.parse

import com.github.nateriver520.jconf.core.ConfNode
import com.github.nateriver520.jconf.core.NodeType


class PropertiesParser implements Parser {
    static def confSluper = new ConfigSlurper()

    @Override
    ConfNode parse(def confText) {
        // parse to properties
        Properties props = new Properties()
        props.load(new ByteArrayInputStream(confText.getBytes()))

        def propertiesNode = confSluper.parse(props)
        def rootNode = new ConfNode(type: NodeType.OBJECT)
        fillNode(rootNode, propertiesNode)
        return rootNode
    }

    def fillNode(ConfNode root, def propertiesNode) {
        if (propertiesNode.getClass() != ConfigObject) {
            root.value = propertiesNode
            return
        }

        propertiesNode.each { k, v ->
            // hack way to skip class node
            if(k == 'class') return
            ConfNode child = new ConfNode(
                    type: NodeType.getType(v.class),
                    parent: root
            )

            root.children.put(k, child)
            fillNode(child, v)
        }
    }
}
