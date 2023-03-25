import { NestApplication } from '@nestjs/core';

export const appOptionsFix = (app: NestApplication) => {
  let options: unknown = {};
  Object.defineProperty((app as any).microservicesModule, 'appOptions', {
    get() {
      return options ?? {};
    },
    set(value) {
      options = value;
    },
  });
};
