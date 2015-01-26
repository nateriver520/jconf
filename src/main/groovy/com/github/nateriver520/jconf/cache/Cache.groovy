package com.github.nateriver520.jconf.cache


interface Cache <K,V>{
    def set(K k,V v)
    def get(K k)
    def del(K k)
}