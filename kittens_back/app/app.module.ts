import { Module } from '@nestjs/common';
import { AuthModule } from './core/auth/auth.module';
import { ConfigModule } from '@nestjs/config';
import { KittenModule } from './core/kitten/kitten.module';
import { UserModule } from '#core/user/user.module';

@Module({
	imports: [
		KittenModule,
		AuthModule,
		UserModule,
		ConfigModule.forRoot({
			isGlobal: true,
		}),
	],
	exports: [],
	providers: [],
})
export class AppModule {}
