import { IsDate, IsNumber, IsOptional, IsString } from 'class-validator';
import { ApiProperty } from '@nestjs/swagger';

export class KittenDto {
	@ApiProperty()
	@IsString()
	name: string;

	@ApiProperty()
	@IsOptional()
	@IsString()
	picture?: string;

	@ApiProperty()
	@IsString()
	about: string;

	@ApiProperty()
	@IsString()
	birthDate: Date;

	@ApiProperty()
	@IsString()
	breed: string;

	@ApiProperty()
	@IsString()
	city: string;

	@ApiProperty()
	@IsNumber()
	price: number;
}
