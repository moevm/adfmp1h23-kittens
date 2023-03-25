import { ConflictException, Injectable } from '@nestjs/common';
import { RegistrationDto } from './controllers/dto/registration.dto';
import { ConfigService } from '@nestjs/config';
import { PasetoService } from '../../common/paseto/paseto.service';
import { hashWithSalt } from '../../common/utils/hash-with-salt';
import { EnvVariables } from '../../common/env/env-variables';
import { PrismaService } from '../../prisma/prisma.service';

@Injectable()
export class AuthRepository {
	constructor(
		private readonly pasetoService: PasetoService,
		private readonly configService: ConfigService,
		private readonly prismaService: PrismaService,
	) {}

	async sign(payload: any): Promise<string> {
		return this.pasetoService.sign(payload);
	}

	async verify(token: string): Promise<any> {
		return this.pasetoService.verify(token);
	}

	async authenticate(data: { login: string; password: string }): Promise<any> {
		const { login, password } = data;
		const user = await this.prismaService.user.findFirst({
			where: { login, auth: { passwordHash: password } },
		});
		if (user) {
			return user;
		} else {
			return null;
		}
	}

	async registration(data: RegistrationDto) {
		const { login, password, firstName, lastName, birthDate } = data;
		let userData = await this.prismaService.user.findFirst({ where: { login } });
		if (userData) {
			throw new ConflictException();
		}
		userData = await this.prismaService.user.create({ data: { login, lastName, firstName, birthDate } });
		const authData = await this.prismaService.auth.create({
			data: {
				passwordHash: hashWithSalt(password, this.configService.get(EnvVariables.PASSWORD_SALT)),
				userId: String(userData.id),
				accessToken: await this.sign({ login, password }),
				user: {
					connect: {
						id: userData.id,
					},
				},
			},
		});
		await this.prismaService.user.update({ data: { authId: authData.id }, where: { id: userData.id } });
		return authData;
	}
}
