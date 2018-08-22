# BBEmbedded
BebasBayar embeddable service.

# Include This Library into Your Application

*Note: Always use the latest version of this library if possible to get recent update.*

```gradle
implementation 'com.bm.bebasbayar:embedded:0.1.0'
```

# Usage

For Java:

```java
BBEmbedded.show(context, new EmbedParameters("APIKEY", "1", true), new PartnerSecurityParameters("PARTNERSECURITYKEY"));
```

For Kotlin:

```kotlin
BBEmbedded.show(context, EmbedParameters("APIKEY", "1", true), PartnerSecurityParameters("PARTNERSECURITYKEY"))
```

# About Parameters
