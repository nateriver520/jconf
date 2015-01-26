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
}

enum NodeType {
    NUMBER,
    STRING,
    OBJECT,
    ARRAY,
    BOOLEAN,
    ROOT,
    SECTION

    static NodeType getType(def className){
        switch (className) {
            case Integer:
            case Double:
            case Float:
                return NUMBER
            case String:
                return STRING

            case Boolean:
                return BOOLEAN

            case List:
                return ARRAY

            default:
                return OBJECT
        }
    }
}
