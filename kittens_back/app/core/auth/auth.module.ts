import { Module } from '@nestjs/common';
import { AuthController } from '#core/auth/controllers/auth.controller';
import { DefaultGuard } from '#core/auth/guards/default.guard';
import { AuthRepository } from './auth.repository';
import { LocalStrategy } from './strategies/local.strategy';
import { PrismaModule } from '#prisma/prisma.module';
import { PasetoModule } from '#common/paseto';

@Module({
	imports: [PasetoModule, PrismaModule],
	exports: [AuthRepository],
	providers: [DefaultGuard, LocalStrategy, AuthRepository],
	controllers: [AuthController],
})
export class AuthModule {}
