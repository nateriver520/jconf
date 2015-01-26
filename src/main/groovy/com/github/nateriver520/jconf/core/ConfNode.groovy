package com.github.nateriver520.jconf.core

/**
 * Use to represent conf node
 */
class ConfNode {
    Map children = [:]
    def type
    def value

    def get(String key){
        children.get(key)
    }

    def setType(def className) {

        switch (className) {
            case Integer:
            case Double:
            case Float:
                this.type = NodeType.NUMBER
                break
            case String:
                this.type = NodeType.STRING
                break
            case Boolean:
                this.type = NodeType.BOOLEAN
                break
            case List:
                this.type = NodeType.ARRAY
                break
            default:
                this.type = NodeType.OBJECT
        }
    }
}

enum NodeType {
    NUMBER,
    STRING,
    OBJECT,
    ARRAY,
    BOOLEAN
}
