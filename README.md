[![Build Status](https://travis-ci.org/nateriver520/jconf.svg?branch=master)](https://travis-ci.org/nateriver520/jconf)
[![Documentation Status](https://readthedocs.org/projects/jconf/badge/?version=latest)](https://readthedocs.org/projects/jconf/?badge=latest)

JConf
===============

JConf is a easy and light library for config module in Java or Groovy applications.

Currently this library support [JSON](http://en.wikipedia.org/wiki/JSON), [YAML](http://en.wikipedia.org/wiki/YAML), [INI](https://en.wikipedia.org/wiki/INI_file), [XML](https://en.wikipedia.org/wiki/XML), [Properties](http://en.wikipedia.org/wiki/.properties)

Visit [http://jconf.readthedocs.org/en/latest/](http://jconf.readthedocs.org/en/latest/) for more detail info

Usage
----------------
- For maven

add dependency

```xml
    <dependency>
        <groupId>com.github.nateriver520</groupId>
        <artifactId>jconf</artifactId>
        <version>0.2.1</version>
    </dependency>
```


Example
---------------

if I want to load a json config like this:

```json
    {
      "person": {
        "name": "Guillaume",
        "age": 33,
        "score": 93.3,
        "id": 110000012129,
        "pets": [
          "dog",
          "cat"
        ],
        "is_admin": true
      },
      "work": "cs"
    }
```

Here is example

```groovy

    // support yaml, ini
    // also support load from text or stream
    def conf = new Config('conf.json')

    conf.getString("person.name") // "Guillaume"
    conf.getInteger("person.age") // 33
    conf.getDouble("person.score") // 93.3
    conf.getFloat("person.score") // 93.3
    conf.getLong("person.id") // 110000012129
    conf.getBoolean("person.is_admin") // true
    conf.getList("person.pets") // ["dog", "cat"]
    conf.getString("work") //"cs"

    // default value
    // don't exist so return default value: "example@example.com"
    conf.getString("persion.mail", "example@example.com")

    // use other separator
    // set separator with ::
    conf.separator = "::"

    conf.getString("person::name") // "Guillaume"

    // we also can set config manually
    conf.set("person::name", "jack")
    conf.get("person::name") // "jack"

    // delete conf
    conf.del("person::name")
    conf.exist("person::name") // false

    // we can reset config to the origin version without change
    conf.reset()

```


LICENSE
-------------
[MIT](LICENSE)
