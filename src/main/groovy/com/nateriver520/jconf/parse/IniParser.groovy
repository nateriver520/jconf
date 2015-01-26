package com.nateriver520.jconf.parse

import com.nateriver520.jconf.core.ConfNode
import org.ini4j.Ini
import org.ini4j.Profile


class IniParser implements Parser {

    @Override
    ConfNode parse(def confText) {
        def ini = new Ini()
        ini.load(new ByteArrayInputStream(confText.getBytes()))
        def rootNode = new ConfNode()

        fillNode(rootNode, ini)
        return rootNode
    }

    def fillNode(ConfNode root, Ini ini) {
        ini.keySet().each { sectionKey ->
            // section
            ConfNode sectionNode = new ConfNode()
            Profile.Section section = ini.get(sectionKey)
            section.each { k, v ->
                ConfNode child = new ConfNode(value: v, type: v.class)
                sectionNode.children.put(k, child)
            }

            root.children.put(sectionKey, sectionNode)
        }
    }
}
