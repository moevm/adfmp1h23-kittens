import { ApiProperty } from '@nestjs/swagger';
import { IsString, MinLength } from 'class-validator';

export class AuthDto {
	@ApiProperty()
	@IsString()
	login: string;

	@ApiProperty()
	@IsString()
	@MinLength(8)
	password: string;
}
