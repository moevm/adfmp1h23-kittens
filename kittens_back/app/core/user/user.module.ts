import { Module } from '@nestjs/common';
import { AuthModule } from '../auth/auth.module';
import { UserService } from '#core/user/user.service';
import { UserController } from '#core/user/controllers';

@Module({
	controllers: [UserController],
	providers: [UserService],
	imports: [AuthModule],
})
export class UserModule {}
