import { CanActivate, ExecutionContext, Injectable, UnauthorizedException } from '@nestjs/common';
import { AuthRepository } from '../auth.repository';
import { errors } from 'paseto';
import PasetoVerificationFailed = errors.PasetoVerificationFailed;
import { PrismaService } from '#prisma/prisma.service';

@Injectable()
export class DefaultGuard implements CanActivate {
	constructor(private readonly authRepository: AuthRepository, private readonly prismaService: PrismaService) {}

	async canActivate(context: ExecutionContext): Promise<boolean> {
		const req = context.switchToHttp().getRequest();
		if (req.cookies?.token) {
			let payload = null;
			try {
				payload = await this.authRepository.verify(req.cookies.token);
			} catch (err) {
				if (err instanceof PasetoVerificationFailed) {
					throw new UnauthorizedException(err.message);
				}
			}
			if (payload) {
				const user = await this.prismaService.user.findFirst({ where: { id: payload.id } });
				if (user) {
					req.user = user;
					return true;
				}
			}
		}
		return false;
	}
}
