package com.github.nateriver520.jconf.core

/**
 * Use to represent conf node
 */
class ConfNode {
    Map children = [:]
    ConfNode parent

    def type
    def value

    def get(String key){
        children.get(key)
    }

    def set(String key, ConfNode child){
        children.put(key, child)
    }

    def del(String key){
        children.remove(key)
    }
}

enum NodeType {
    NUMBER,
    STRING,
    OBJECT,
    ARRAY,
    BOOLEAN

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
