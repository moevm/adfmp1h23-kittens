import { Body, Controller, Get, Post, Query, Req, UseGuards } from '@nestjs/common';
import { KittenDto } from './dto/kitten.dto';
import { KittenRepository } from '#core/kitten/repositories/kitten.repository';
import { DefaultGuard } from '#core/auth/guards/default.guard';
import { KittenFilterDto } from '#core/kitten/controllers/dto/filter.dto';
import { UpdateKittenDto } from '#core/kitten/controllers/dto/update-kitten.dto';
import { ApiOkResponse } from '@nestjs/swagger';
import { KittenEntity } from '#common/entities/kitten.entity';

@Controller('kitten')
export class KittenController {
	constructor(private readonly kittenRepository: KittenRepository) {}

	@Get('get-one')
	@UseGuards(DefaultGuard)
	@ApiOkResponse({
		type: KittenEntity,
	})
	async getKitten(@Query('id') id: number) {
		return this.kittenRepository.getKitten(id);
	}

	@Post('create')
	@UseGuards(DefaultGuard)
	@ApiOkResponse({
		type: KittenEntity,
	})
	async createKitten(@Body() data: KittenDto, @Req() req) {
		return this.kittenRepository.create(data, req.user.id);
	}

	@Post('get')
	@UseGuards(DefaultGuard)
	@ApiOkResponse({
		type: [KittenEntity],
	})
	async getKittens(@Body() filter: KittenFilterDto) {
		return this.kittenRepository.getKittens(filter);
	}

	@Post('get-mine')
	@UseGuards(DefaultGuard)
	@ApiOkResponse({
		type: [KittenEntity],
	})
	async getPersonalKittens(@Body() filter: KittenFilterDto, @Req() req) {
		return this.kittenRepository.getKittens(filter, req.user.id);
	}

	@Get('get-breeds')
	@UseGuards(DefaultGuard)
	@ApiOkResponse({
		type: [String],
	})
	async getBreeds() {
		return this.kittenRepository.getBreeds();
	}

	@Post('update')
	@UseGuards(DefaultGuard)
	@ApiOkResponse({
		type: KittenEntity,
	})
	async updateKitten(@Body() data: UpdateKittenDto) {
		return this.kittenRepository.updateKitten(data);
	}
}
