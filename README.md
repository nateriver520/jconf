JConf
===============

JConf is a easy and light library for config module in Java or Groovy applications.

Currently this library support [JSON](http://en.wikipedia.org/wiki/JSON), [YAML](http://en.wikipedia.org/wiki/YAML)

Notice
---------------

***This package is still in dev status, not ready for prod***

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

    def conf = new Config('conf.json')

    conf.getString("person.name") // 'Guillaume'
    conf.getInteger("person.age") // 33
    conf.getDouble("person.score") // 93.3
    conf.getFloat("person.score") // 93.3
    conf.getLong("person.id") // 110000012129
    conf.getBoolean("person.is_admin") // true
    conf.getList("person.pets") // ["dog", "cat"]
    conf.getString("work") == "cs"

    // key miss
    conf.getString("persion.mail", "example@example.com") // don't exist so return default value: example@example.com
```

LICENSE
-------------
[MIT](LICENSE.md)
