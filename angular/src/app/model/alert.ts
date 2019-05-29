export class Alert {
  type: string;
  message: string;

  constructor(type: string, message: string) {
    this.type = type;
    this.message = message;
  }

  public static success(msg: string) {
    return new Alert('success', msg);
  }

  public static warning(msg: string) {
    return new Alert('danger', msg);
  }

}
