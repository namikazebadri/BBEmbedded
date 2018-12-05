# BBEmbedded
BebasBayar embeddable service.

# Include This Library to Your Project

*Note: Always use the latest version of this library if possible to get recent update.*

```gradle
implementation 'com.bm.bebasbayar:embedded:0.3.2'
```

# Usage

For Java:

```java
EmbedParameters embedParameters = new EmbedParameters();
embedParameters.setApiKey(APIKEY); // API Key from BebasBayar
embedParameters.setPartnerUserId(VENDOR_USER_LOGGED_ID); // Partner internal user id
embedParameters.setDevelopment(true); // if set to true then does not require both valid APIKEY and PARTNERSECURITYKEY.
embedParameters.setUserEmail(VENDOR_USER_LOGGED_EMAIL);
embedParameters.setUserPhoneNumber(VENDOR_USER_LOGGED_PHONE);
embedParameters.setUserFullName(VENDOR_USER_LOGGED_FULLNAME);
embedParameters.setClientId(VENDOR_CLIENT_ID);

PartnerSecurityParameters partnerSecurityParameters = new PartnerSecurityParameters();
partnerSecurityParameters.setSignature(PARTNERSECURITYKEY);

BBEmbedded.show(context, embedParameters, partnerSecurityParameters);
```

For Kotlin:

```kotlin
val embedParameters = EmbedParameters();
embedParameters.apiKey = APIKEY // API Key from BebasBayar
embedParameters.partnerUserId = VENDOR_USER_LOGGED_ID // Partner internal user id
embedParameters.isDevelopment = true // if set true then does not require both valid APIKEY and PARTNERSECURITYKEY.
embedParameters.userEmail = VENDOR_USER_LOGGED_EMAIL;
embedParameters.userPhoneNumber = VENDOR_USER_LOGGED_PHONE;
embedParameters.userFullName = VENDOR_USER_LOGGED_FULLNAME;
embedParameters.clientId = VENDOR_CLIENT_ID;

val partnerSecurityParameters = PartnerSecurityParameters()
partnerSecurityParameters.signature = PARTNERSECURITYKEY

BBEmbedded.show(context, embedParameters, partnerSecurityParameters)
```

*Or if one line method call is your style/preference, then use it like this:*

For Java:

```java
BBEmbedded.show(context, new EmbedParameters(APIKEY, VENDOR_USER_LOGGED_ID, true, VENDOR_USER_LOGGED_EMAIL, VENDOR_USER_LOGGED_PHONE, VENDOR_USER_LOGGED_FULLNAME, VENDOR_CLIENT_ID), new PartnerSecurityParameters(PARTNERSECURITYKEY));
```

For Kotlin:

```kotlin
BBEmbedded.show(context, EmbedParameters(APIKEY, VENDOR_USER_LOGGED_ID, true, VENDOR_USER_LOGGED_EMAIL, VENDOR_USER_LOGGED_PHONE, VENDOR_USER_LOGGED_FULLNAME, VENDOR_CLIENT_ID), PartnerSecurityParameters(PARTNERSECURITYKEY))
```
*&ast;Be careful though, one line method call is indeed more compact and simpler, but sometimes make your code less readable for your fellow programmers.*

# About Parameters
