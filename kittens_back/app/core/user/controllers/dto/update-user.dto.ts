import { ApiProperty, ApiPropertyOptional } from '@nestjs/swagger';
import { IsDate, IsNumber, IsOptional, IsString } from 'class-validator';

export class UpdateUserDto {
	@ApiProperty()
	@IsNumber()
	id: number;

	@ApiProperty()
	@ApiPropertyOptional()
	@IsOptional()
	@IsString()
	login?: string;

	@ApiProperty()
	@ApiPropertyOptional()
	@IsOptional()
	@IsString()
	firstName?: string;

	@ApiProperty()
	@ApiPropertyOptional()
	@IsOptional()
	@IsString()
	middleName?: string;

	@ApiProperty()
	@ApiPropertyOptional()
	@IsOptional()
	@IsString()
	lastName?: string;

	@ApiProperty()
	@ApiPropertyOptional()
	@IsOptional()
	@IsString()
	birthDate?: Date;

	@ApiProperty()
	@IsOptional()
	@ApiPropertyOptional()
	@IsString()
	picture?: string;
}
