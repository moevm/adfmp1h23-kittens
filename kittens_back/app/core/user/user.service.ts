import { Injectable } from '@nestjs/common';
import { PrismaService } from '../../prisma/prisma.service';
import { UpdateUserDto } from '#core/user/controllers/dto/update-user.dto';

@Injectable()
export class UserService {
	constructor(private readonly prismaService: PrismaService) {}

	async updateUser(data: UpdateUserDto) {
		return this.prismaService.user.update({ data: { ...data }, where: { id: data.id } });
	}
}
