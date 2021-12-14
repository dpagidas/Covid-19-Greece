// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  staging: false,
  baseApiUrl: 'http://localhost:8080',
  // baseApiUrl: 'http://192.168.209.100:8011',
  // languages: [
  //   {
  //     code: 'en',
  //     name: 'en'
  //   },
  //   {
  //     code: 'el',
  //     name: 'el'
  //   }
  // ],
  // oAuth2: {
  //   serverUrl: 'http://192.168.209.100:8080/openam/oauth2/realms/root/realms/',
  //   issuerUrl: 'http://192.168.209.100:8080/openam/oauth2/realms/root/realms/',
  //   realmName: 'PWC-RTA',
  //   redirectUri: 'http://localhost:4200',
  //   clientId: 'PwcRta',
  //   // Scope for the permissions the client should request
  //   scope: 'openid profile email auth',
  //   // Set this to "true" if you want automatic redirection to OAuth2 login to happen when user visits any page as anonymous,
  //   // without manually clicking 'login'.
  //   autoLogin: false
  // },
  // globalRightsClientID: 'PWC',
  // dashboardSelectedIpowerClientsStorageTime: 432000000 //5 day in miliseconds
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
