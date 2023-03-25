import { IsNumber, IsOptional, IsString, ValidateNested } from 'class-validator';
import { type } from 'os';
import { Type } from 'class-transformer';
import { ApiProperty, ApiPropertyOptional } from '@nestjs/swagger';

export class DateFilter {
	@ApiProperty()
	@IsString()
	gt: string;

	@ApiProperty()
	@IsString()
	lt: string;
}

export class KittenFilterDto {
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
	@IsString()
	name?: string;

	@ApiProperty()
	@ApiPropertyOptional()
	@IsOptional()
	@ValidateNested()
	@Type(() => DateFilter)
	birthDate: DateFilter;
}
