import { IsNumber, IsOptional, IsString } from 'class-validator';
import { ApiProperty, ApiPropertyOptional } from '@nestjs/swagger';

export class UpdateKittenDto {
	@ApiProperty()
	@IsNumber()
	id: number;

	@ApiProperty()
	@ApiPropertyOptional()
	@IsOptional()
	@IsString()
	name?: string;

	@ApiProperty()
	@ApiPropertyOptional()
	@IsOptional()
	@IsString()
	picture?: string;

	@ApiProperty()
	@ApiPropertyOptional()
	@IsOptional()
	@IsString()
	about?: string;

	@ApiProperty()
	@ApiPropertyOptional()
	@IsOptional()
	@IsString()
	birthDate?: Date;

	@ApiProperty()
	@ApiPropertyOptional()
	@IsOptional()
	@IsString()
	breed?: string;

	@ApiProperty()
	@ApiPropertyOptional()
	@IsOptional()
	@IsString()
	city?: string;

	@ApiProperty()
	@ApiPropertyOptional()
	@IsOptional()
	@IsNumber()
	price?: number;
}
