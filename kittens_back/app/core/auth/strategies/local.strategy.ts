import { Injectable, UnauthorizedException } from '@nestjs/common';
import { PassportStrategy } from '@nestjs/passport';
import { AuthRepository } from '../auth.repository';
import { Strategy } from 'passport-local';
import { ConfigService } from '@nestjs/config';
import { hashWithSalt } from '#common/utils/hash-with-salt';
import { EnvVariables } from '#common/env/env-variables';

@Injectable()
export class LocalStrategy extends PassportStrategy(Strategy, 'local') {
	constructor(private readonly authRepository: AuthRepository, private readonly configService: ConfigService) {
		super({ usernameField: 'login', passwordField: 'password' });
	}
	async validate(login: string, password: string): Promise<any> {
		const userData = await this.authRepository.authenticate({
			login,
			password: hashWithSalt(password, this.configService.get(EnvVariables.PASSWORD_SALT)),
		});
		if (userData) {
			return userData;
		}
		throw new UnauthorizedException();
	}
}
