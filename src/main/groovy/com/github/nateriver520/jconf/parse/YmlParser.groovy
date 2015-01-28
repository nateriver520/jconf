package com.github.nateriver520.jconf.parse

import com.github.nateriver520.jconf.core.NodeType
import org.ho.yaml.Yaml
import com.github.nateriver520.jconf.core.ConfNode


class YmlParser implements Parser {

    @Override
    ConfNode parse(def confText) {
        def ymlNode = Yaml.load(confText)
        def rootNode = new ConfNode(type: NodeType.OBJECT)
        fillNode(rootNode, ymlNode)
        return rootNode
    }

    def fillNode(ConfNode root, def ymlNode) {
        if (ymlNode.getClass() != HashMap) {
            root.value = ymlNode
            return
        }
        ymlNode.each { k, v ->
            ConfNode child = new ConfNode(
                    type: NodeType.getType(v.class),
                    parent: root
            )
            root.children.put(k, child)
            fillNode(child, v)
        }
    }
}
