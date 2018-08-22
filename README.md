# BBEmbedded
BebasBayar embeddable service.

# Include This Library to Your Project

*Note: Always use the latest version of this library if possible to get recent update.*

```gradle
implementation 'com.bm.bebasbayar:embedded:0.1.1'
```

# Usage

For Java:

```java
EmbedParameters embedParameters = new EmbedParameters();
embedParameters.setApiKey("APIKEY"); //API Key from BebasBayar
embedParameters.setPartnerUserId("1"); // Partner internal user id
embedParameters.setDevelopment(true); // if set to true then does not require both valid APIKEY and PARTNERSECURITYKEY.

PartnerSecurityParameters partnerSecurityParameters = new PartnerSecurityParameters();
partnerSecurityParameters.setSignature("PARTNERSECURITYKEY");

BBEmbedded.show(context, embedParameters, partnerSecurityParameters);
```

For Kotlin:

```kotlin
val embedParameters = EmbedParameters();
embedParameters.apiKey = "APIKEY" //API Key from BebasBayar
embedParameters.partnerUserId = "1" // Partner internal user id
embedParameters.isDevelopment = true // if set true then does not require both valid APIKEY and PARTNERSECURITYKEY.

val partnerSecurityParameters = PartnerSecurityParameters()
partnerSecurityParameters.signature = "PARTNERSECURITYKEY"

BBEmbedded.show(context, embedParameters, partnerSecurityParameters)
```

*Or if one line method call is your style/preference, then use it like this:*

For Java:

```java
BBEmbedded.show(context, new EmbedParameters("APIKEY", "1", true), new PartnerSecurityParameters("PARTNERSECURITYKEY"));
```

For Kotlin:

```kotlin
BBEmbedded.show(context, EmbedParameters("APIKEY", "1", true), PartnerSecurityParameters("PARTNERSECURITYKEY"))
```
*Be careful though, one line mehod call is indeed more compact and simpler, but sometimes make your code less readable for your fellow programmer*

# About Parameters
