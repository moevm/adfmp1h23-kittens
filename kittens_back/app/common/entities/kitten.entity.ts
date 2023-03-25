import { ApiProperty, ApiPropertyOptional } from '@nestjs/swagger';

export class KittenEntity {
	@ApiProperty()
	id: number;

	@ApiProperty()
	login: string;

	@ApiProperty()
	@ApiPropertyOptional()
	picture?: string;

	@ApiProperty()
	firstName: string;

	@ApiProperty()
	@ApiPropertyOptional()
	middleName?: string;

	@ApiProperty()
	lastName: string;

	@ApiProperty()
	@ApiPropertyOptional()
	about?: string;

	@ApiProperty()
	birthDate: Date;

	@ApiProperty()
	created: Date;

	@ApiProperty()
	updated: Date;

	@ApiProperty()
	suspended: boolean;

	@ApiProperty()
	authId: number;
}
