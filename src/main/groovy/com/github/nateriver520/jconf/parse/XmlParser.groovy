package com.github.nateriver520.jconf.parse

import com.github.nateriver520.jconf.core.ConfNode
import com.github.nateriver520.jconf.core.NodeType


class XmlParser implements Parser{
    def static xmlParser = new groovy.util.XmlParser()

    @Override
    ConfNode parse(def confText) {
        def xmlNode = xmlParser.parseText(confText)
        def rootNode = new ConfNode(type: NodeType.OBJECT)
        fillNode(rootNode, xmlNode)
        return rootNode
    }

    def fillNode(ConfNode root, Node xmlNode) {

        xmlNode.children().each { childNode ->
            ConfNode child = new ConfNode(
                    type: NodeType.getType(childNode.class),
                    parent: root
            )
            // don't exist name method, means reach to the bottom
            if(childNode.getClass() != Node){
                root.value = childNode
                root.type = NodeType.getType(childNode.class)
                return
            }
            root.children.put(childNode.name(), child)
            fillNode(child, childNode)
        }

    }
}
