import { Body, Controller, Get, Post, Req, Res, UseGuards } from '@nestjs/common';
import { LocalGuard } from '#core/auth/guards/local.guard';
import { DefaultGuard } from '#core/auth/guards/default.guard';
import { AuthRepository } from '../auth.repository';
import { AuthDto } from './dto/auth.dto';
import { ApiConflictResponse, ApiOkResponse, ApiUnauthorizedResponse } from '@nestjs/swagger';
import { RegistrationDto } from './dto/registration.dto';
import { UserEntity } from '#common/entities/user.entity';

@Controller('/')
export class AuthController {
	constructor(private readonly authRepository: AuthRepository) {}
	private readonly tokenCookieName = 'token';
	@Get('/')
	@ApiOkResponse({ status: 201 })
	defaultRoute() {
		return 'meow!';
	}

	@Post('auth')
	@UseGuards(LocalGuard)
	@ApiOkResponse({ status: 201 })
	@ApiUnauthorizedResponse({ status: 401 })
	async authenticate(@Body() data: AuthDto, @Req() req, @Res({ passthrough: true }) res) {
		res.cookie(this.tokenCookieName, await this.authRepository.sign(req.user));
		return { success: true };
	}

	@Get('who-am-i')
	@UseGuards(DefaultGuard)
	@ApiOkResponse({ status: 201 })
	@ApiUnauthorizedResponse({ status: 401 })
	async whoAmI(@Req() req) {
		return new UserEntity(req.user);
	}

	@Post('register')
	@ApiOkResponse({ status: 201 })
	@ApiConflictResponse({ status: 409 })
	async registration(@Body() data: RegistrationDto, @Res({ passthrough: true }) res) {
		const auth = await this.authRepository.registration(data);
		res.cookie(this.tokenCookieName, auth.accessToken);
		return { success: true };
	}

	@Get('auth/log-out')
	@ApiOkResponse({ status: 201 })
	async logOut(@Res({ passthrough: true }) res) {
		res.clearCookie(this.tokenCookieName);
		return { success: true };
	}
}
