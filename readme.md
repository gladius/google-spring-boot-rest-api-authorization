# Google Spring boot rest api authorization server

This **Spring Boot Starter** can be your ready to use middleware server for your Flutter, React Native, Native Mobile or Web Frontends projects with **Spring Security** to seamlessly create and consume protected rest API's.

Note:-

This server does not use spring security's Oauth2 social library but rather uses google's api client in combination with spring security's http security functionality to provide a simple yet secure authorization solution.

### Configuration

Register your application at https://console.developers.google.com and create creantials.

security.google-props.client-ids accepts an array of client_ids, you configure one for each platform like IOS,WEB,ANDROID.

### UI Demo

- To try the demo ui , edit html under ui folder to replace content of meta tag with name "google-signin-client_id" to your web client_id.

- Run with live-server or http-server

![Image of UI Demo ](https://raw.githubusercontent.com/gladius/google-spring-boot-rest-api-authorization/master/screenshots/ui.png)

### Protected API

![Image of Protected API ](https://raw.githubusercontent.com/gladius/google-spring-boot-rest-api-authorization/master/screenshots/backend_call_unauth.png)

## Author

👤 **Gladius Thayalarajan**

- Website: thepro.io/@/gladius
- Follow me @ Github: [@gladius-thayalarajan](https://github.com/gladius)

## Show your support

Give a ⭐️ if this project helped you!

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
