# Support Config File

### JSON

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

### XML

```xml

    <?xml version="1.0" encoding="UTF-8"?>
    <config>
        <app>
            <host>localhost</host>
            <port>80</port>
            <base>/my/app</base>
        </app>
        <security>
            <secret>s3cr3t-c0d3</secret>
        </security>
        <debug>true</debug>
    </config>
```

### INI

```ini

    ; for dev
    [dev]
    JDBC_URL = jdbc:h2:mem:mem_test;MODE=Oracle
    JDBC_PORT = 3006
    JDBC_USERNAME =
    JDBC_PASSWORD =

    SERVICE_ENDPOINT = http://localhost:8080/Central/api/AppService

    [qa]
    JDBC_URL = jdbc:oracle:thin:@qa-oracle:1521:qa
    JDBC_USERNAME = qauser
    JDBC_PASSWORD = qapass

    SERVICE_ENDPOINT = http://qa-services/Central/api/AppService

    [prod]
    JDBC_URL = jdbc:oracle:thin:@prod-oracle:1521:prod
    JDBC_USERNAME = scott
    JDBC_PASSWORD = tiger

    SERVICE_ENDPOINT = http://prod-services/Central/api/AppService
```

### YAML

```yaml

    default:
      redis:
        port: 6379
        host: '127.0.0.1'
        password: '123456'
        db: 1
        options: {}
    test:
      redis:
        db: 12
    production:
      redis:
        db: 0
      new_prop:
        hello: 'world'
```

### Properties File

```properties

    log4j.rootLogger=INFO,Stdout

    # Config ConsoleAppender
    log4j.appender.Stdout=org.apache.log4j.ConsoleAppender
    log4j.appender.Stdout.layout=org.apache.log4j.PatternLayout
    log4j.appender.Stdout.layout.conversionPattern=%d [%t] %-5p %-30.30c{1} %x - %m%n
```
