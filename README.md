[![Build Status](https://travis-ci.org/nateriver520/jconf.svg?branch=master)](https://travis-ci.org/nateriver520/jconf)

JConf
===============

JConf is a easy and light library for config module in Java or Groovy applications.

Currently this library support [JSON](http://en.wikipedia.org/wiki/JSON) , [YAML](http://en.wikipedia.org/wiki/YAML) , [INI](https://en.wikipedia.org/wiki/INI_file), [XML](https://en.wikipedia.org/wiki/XML)


Notice
---------------

***This package is still in dev status, not ready for prod***

Usage
----------------
- For maven

allow snapshots

```xml
    <profiles>
        <profile>
            <id>allow-snapshots</id>
            <activation><activeByDefault>true</activeByDefault></activation>
            <repositories>
                <repository>
                    <id>snapshots-repo</id>
                    <url>https://oss.sonatype.org/content/repositories/snapshots</url>
                    <releases><enabled>false</enabled></releases>
                    <snapshots><enabled>true</enabled></snapshots>
                </repository>
            </repositories>
        </profile>
    </profiles>
```

add dependency

```xml
    <dependency>
        <groupId>com.github.nateriver520</groupId>
        <artifactId>jconf</artifactId>
        <version>0.1.3-SNAPSHOT</version>
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
```
Road Map
------------
- ~~Support YAML~~
- ~~Support JSON~~
- ~~Support INI~~
- ~~Support XML~~
- ~~Push to maven repository~~
- Support java .properties


LICENSE
-------------
[MIT](LICENSE)
