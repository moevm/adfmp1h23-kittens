import { ApiProperty } from '@nestjs/swagger';
import { IsNotEmpty, IsOptional, IsString, IsStrongPassword } from 'class-validator';
import { OnlyNotDev } from '#common/utils/only-dev.decorator';

export class RegistrationDto {
	@ApiProperty()
	@IsString()
	@IsNotEmpty()
	login: string;

	@ApiProperty()
	@IsString()
	@IsNotEmpty()
	password: string;

	@ApiProperty()
	@IsString()
	firstName: string;

	@ApiProperty()
	@IsOptional()
	@IsString()
	middleName?: string;

	@ApiProperty()
	@IsString()
	lastName: string;

	@ApiProperty()
	@IsOptional()
	@IsString()
	about?: string;

	@ApiProperty()
	@IsOptional()
	@IsString()
	birthDate: Date;
}
