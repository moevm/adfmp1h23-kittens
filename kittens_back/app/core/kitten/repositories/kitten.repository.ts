import { Injectable } from '@nestjs/common';
import { PrismaService } from '#prisma/prisma.service';
import { KittenDto } from '#core/kitten/controllers/dto/kitten.dto';
import { KittenFilterDto } from '#core/kitten/controllers/dto/filter.dto';
import { UpdateKittenDto } from '#core/kitten/controllers/dto/update-kitten.dto';

@Injectable()
export class KittenRepository {
	constructor(private readonly prismaService: PrismaService) {}

	async getBreeds() {
		const breeds = await this.prismaService.kitten.findMany({
			distinct: ['breed'],
			select: {
				breed: true,
			},
		});
		return breeds.map((x) => x.breed);
	}

	async create(data: KittenDto, userId: number) {
		return this.prismaService.kitten.create({
			data: {
				...data,
				birthDate: new Date(data.birthDate),
				user: {
					connect: {
						id: userId,
					},
				},
			},
		});
	}

	async getKittens(filter: KittenFilterDto, userId?: number) {
		const { birthDate, city, breed, name } = filter;
		return this.prismaService.kitten.findMany({
			where: {
				city: city
					? {
							equals: city,
					  }
					: undefined,
				breed: breed
					? {
							equals: breed,
					  }
					: undefined,
				birthDate: birthDate
					? {
							gte: new Date(birthDate.gt),
							lte: new Date(birthDate.lt),
					  }
					: undefined,
				name: name
					? {
							contains: name.toLowerCase(),
							mode: 'insensitive',
					  }
					: undefined,
				userId: userId
					? {
							equals: userId,
					  }
					: undefined,
			},
		});
	}

	async getKitten(id: number) {
		return this.prismaService.kitten.findFirst({
			where: {
				id: Number(id),
			},
		});
	}

	async updateKitten(data: UpdateKittenDto) {
		return this.prismaService.kitten.update({
			data: { ...data, birthDate: new Date(data.birthDate) },
			where: { id: data.id },
		});
	}
}
