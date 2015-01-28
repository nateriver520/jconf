package com.github.nateriver520.jconf.cache

import com.github.nateriver520.jconf.core.ConfNode

class ConfCache implements Cache<String, ConfNode> {
    private def map = [:]

    @Override
    def set(String s, ConfNode confNode) {
        map.put(s, confNode)
    }

    @Override
    def get(String s) {
        map.get(s)
    }

    @Override
    def del(String s) {
        map.remove(s)
    }

    def size(){
        map.size()
    }
}
