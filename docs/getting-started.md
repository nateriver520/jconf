# Getting Started

It's really easy to get started with JConf. This section shows you how.

## Import JConf to you project

### Maven

```xml
    <dependency>
        <groupId>com.github.nateriver520</groupId>
        <artifactId>jconf</artifactId>
        <version>0.2.1</version>
    </dependency>
```

### Gradle

```
    compile 'com.github.nateriver520:jconf:0.2.1'
```

## First application

Once you have a config file ***config.json***

```json
    {
        "domain": "www.example.com",
        "mongodb": {
            "host": "localhost",
            "port": 27017
        },
        "site": [
            "www.google.com",
            "www.facebook.com"
        ],
        "is_debug": true
    }
```

You can read config like this:

### java

```java

    // support yaml, ini
    // also support load from text or stream
    Config conf = new Config("config.json");

    conf.getString("domain"); // "www.example.com"
    conf.getInteger("mongodb.port"); // 27017
    conf.getBoolean("is_debug"); // true

    // default value
    // mongodb.password don't exist so return default value
    conf.getString("mongodb.password", "password5"); // "password5"
```


### groovy

```java

    // support yaml, ini
    // also support load from text or stream
    def conf = new Config('conf.json')

    conf.getString("domain") // "www.example.com"
    conf.getInteger("mongodb.port") // 27017
    conf.getBoolean("is_debug") // true

    // default value
    // don't exist so return default value: "example@example.com"
    conf.getString("mongodb.password", "password5")
```


