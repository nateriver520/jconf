package com.nateriver520.jconf.parse

import com.nateriver520.jconf.core.ConfNode


interface Parser {
    ConfNode parse(def confContent)
}