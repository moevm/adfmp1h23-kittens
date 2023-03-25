import { Body, Controller, Post, Req, UploadedFile, UseGuards, UseInterceptors } from '@nestjs/common';
import { UserService } from '../user.service';
import { Express } from 'express';
import { FileInterceptor } from '@nestjs/platform-express';
import { DefaultGuard } from '../../auth/guards/default.guard';
import { UpdateUserDto } from './dto/update-user.dto';
import { ApiBody, ApiConflictResponse, ApiOkResponse } from '@nestjs/swagger';
import { UserEntity } from '../../../common/entities/user.entity';

@Controller('user')
export class UserController {
	constructor(private readonly userService: UserService) {}

	@Post('update')
	@ApiBody({ type: UpdateUserDto })
	@ApiOkResponse({ status: 201 })
	@ApiConflictResponse({ status: 409 })
	@UseGuards(DefaultGuard)
	async updateUser(@Body() data: UpdateUserDto) {
		return new UserEntity(await this.userService.updateUser(data));
	}
}
