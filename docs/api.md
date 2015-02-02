# API

### Load config file from different source

```java

    // set config file type
    def conf = new Config('config', 'json')

    // load from text
    def conf = Config.loadFromText('{"username":"example"}', 'json')

    // load from stream
    def conf = Config.loadFromStream(stream, 'json')
```

### Set

```java

    // set config manually
    conf.set("person.name", "jack")
    conf.get("person.name") // "jack"
```


### Delete

```java

    // delete special key
    conf.del("person.name")
    conf.exist("person.name") // false
```

### Exist

```java

    // if some key exist
    conf.exist("person.name") // true or false
```

### Reset

```java

    // reset config to the origin version without change
    conf.reset()
```

### Set Separator

```java

    // set separator with ::
    conf.separator = "::"

    // now you can use :: to read conf
    conf.getString("person::name")
```

