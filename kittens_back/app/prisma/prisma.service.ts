import { Injectable, OnModuleDestroy, OnModuleInit } from '@nestjs/common';
import { PrismaClient } from '@prisma/client';

@Injectable()
export class PrismaService extends PrismaClient implements OnModuleInit, OnModuleDestroy {
	constructor() {
		super({
			log: [
				{
					emit: 'stdout',
					level: 'query',
				},
			],
		});
	}
	async onModuleInit(): Promise<void> {
		await this.$connect();
		//@ts-ignore
		await this.$on('query', (e) => {
			//@ts-ignore
			console.log('Query: ' + e.query);
		});
	}

	async onModuleDestroy(): Promise<void> {
		await this.$disconnect();
	}
}
