package com.github.nateriver520.jconf.parse

import com.github.nateriver520.jconf.core.ConfNode


interface Parser {
    ConfNode parse(def confContent)
}