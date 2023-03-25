import { Module } from '@nestjs/common';
import { PasetoService } from './paseto.service';

@Module({
  imports: [],
  exports: [PasetoService],
  providers: [PasetoService],
})
export class PasetoModule {}
