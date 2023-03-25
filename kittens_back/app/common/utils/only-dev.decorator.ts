export function OnlyDev(decorator: (...args: any[]) => PropertyDecorator, ...args: any[]): PropertyDecorator {
  return function (target, propertyKey) {
    if (process.env.DEV) {
      // Need general config wrapper
      decorator(args)(target, propertyKey);
    }
  };
}

export function OnlyNotDev(decorator: (...args: any[]) => PropertyDecorator, ...args: any[]): PropertyDecorator {
  return function (target, propertyKey) {
    if (!process.env.DEV) {
      // Need general config wrapper
      decorator(args)(target, propertyKey);
    }
  };
}
