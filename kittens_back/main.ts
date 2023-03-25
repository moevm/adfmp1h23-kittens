// eslint-disable-next-line no-console
console.time('app_start');

import { ValidationPipe } from '@nestjs/common';
import { NestApplication, NestFactory } from '@nestjs/core';
import { DocumentBuilder, SwaggerModule } from '@nestjs/swagger';
import { AppModule } from './app/app.module';
import * as cookieParser from 'cookie-parser';

async function bootstrap() {
	const app: NestApplication = await NestFactory.create(AppModule);
	const swaggerConfig = new DocumentBuilder()
		.setTitle('meow-api')
		.setDescription('kittens meow')
		.setVersion('1.0')
		.build();

	const document = SwaggerModule.createDocument(app, swaggerConfig);
	SwaggerModule.setup('api', app, document);

	app.useGlobalPipes(new ValidationPipe());
	app.use(cookieParser());
	const port = process.env.PORT;
	console.log(`PORT: ${port}`);
	await app.listen(port);
	// eslint-disable-next-line no-console
	console.timeEnd('app_start');
}

bootstrap();
