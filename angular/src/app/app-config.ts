export class AppConfig {

  public static CAPTCHA_SITE_KEY = '6LeP5HgUAAAAALO7V_TD1IHncVlJ9yGntG2LkhtF';

  public static ENDPOINT_API = 'http://localhost:8080';
  public static ENDPOINT_OAUTH = AppConfig.ENDPOINT_API + '/oauth/token';
  public static ENDPOINT_REGISTRATION = AppConfig.ENDPOINT_API + '/rest/register';
  public static ENDPOINT_RESEND = AppConfig.ENDPOINT_API + '/rest/register/resend';
  public static ENDPOINT_ACTIVATE = AppConfig.ENDPOINT_API + '/rest/register/activate';
  public static ENDPOINT_NEWS = AppConfig.ENDPOINT_API + '/rest/news';

}
