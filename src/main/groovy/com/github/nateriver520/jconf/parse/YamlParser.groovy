package com.github.nateriver520.jconf.parse

import com.github.nateriver520.jconf.core.NodeType
import org.ho.yaml.Yaml
import com.github.nateriver520.jconf.core.ConfNode


class YamlParser implements Parser {

    @Override
    ConfNode parse(def confText) {
        def yamlNode = Yaml.load(confText)
        def rootNode = new ConfNode(type: NodeType.ROOT)
        fillNode(rootNode, yamlNode)
        return rootNode
    }

    def fillNode(ConfNode root, def yamlNode) {
        if (yamlNode.getClass() != HashMap) {
            root.value = yamlNode
            return
        }
        yamlNode.each { k, v ->
            ConfNode child = new ConfNode(type: NodeType.getType(v.class))
            root.children.put(k, child)
            fillNode(child, v)
        }
    }
}
