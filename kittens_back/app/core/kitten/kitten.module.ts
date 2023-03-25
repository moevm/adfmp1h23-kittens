import { Module } from '@nestjs/common';
import { PrismaModule } from '#prisma/prisma.module';
import { KittenController } from '#core/kitten/controllers/kitten.controller';
import { KittenRepository } from '#core/kitten/repositories/kitten.repository';
import { AuthModule } from '#core/auth/auth.module';

@Module({
	imports: [PrismaModule, AuthModule],
	exports: [KittenRepository],
	controllers: [KittenController],
	providers: [KittenRepository],
})
export class KittenModule {}
