import { AuthGuard, IAuthGuard } from '@nestjs/passport';
import { ExecutionContext } from '@nestjs/common';

export class LocalGuard extends AuthGuard('local') implements IAuthGuard {
  getRequest(context: ExecutionContext) {
    return context.switchToHttp().getRequest();
  }
}
