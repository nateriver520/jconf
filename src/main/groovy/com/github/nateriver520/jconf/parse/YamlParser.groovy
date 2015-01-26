package com.github.nateriver520.jconf.parse

import org.ho.yaml.Yaml
import com.github.nateriver520.jconf.core.ConfNode
/**
 * Created by yangshuo on 1/26/15.
 */
class YamlParser implements Parser {

    @Override
    ConfNode parse(def confText) {
        def yamlNode = Yaml.load(confText)
        def rootNode = new ConfNode()
        fillNode(rootNode, yamlNode)
        return rootNode
    }

    def fillNode(ConfNode root, def yamlNode) {
        if (yamlNode.getClass() != HashMap) {
            root.value = yamlNode
            return
        }
        yamlNode.each { k, v ->
            ConfNode child = ConfNode.newInstance()
            child.setType(v.class)
            root.children.put(k, child)
            fillNode(child, v)
        }
    }
}
