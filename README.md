JSONy
=======================

_JSONy_ is a JSON parser for Java, a wrapper of [org.json](https://github.com/douglascrockford/JSON-java), that has __similar APIs to [SwiftyJSON](https://github.com/SwiftyJSON/SwiftyJSON)__

```java
JSON json = new JSON(jsonString);

JSON node = json.get("foo").get("bar").get(0); // `get` never fails
Integer value = node.getInt(); // null when the node does not exists or is not an integer
try {
    int value2 = node.getIntValue(); // fails when the node does not exist or is not an integer
} catch (FormatException e) {
    // Error handling
}

for (JSON child : json.get("baz").getList()) {
    // Use child
}
```

Dependencies
-----------------------

- [org.json](https://github.com/douglascrockford/JSON-java)

License
-----------------------
[The MIT License](LICENSE)
