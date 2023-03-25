import { Injectable, OnModuleInit } from '@nestjs/common';
import { V2 } from 'paseto';
import * as crypto from 'crypto';
import { KeyObject } from 'crypto';

@Injectable()
export class PasetoService implements OnModuleInit {
  privateKey: KeyObject;
  publicKey: KeyObject;
  async onModuleInit() {
    const keys = crypto.generateKeyPairSync('ed25519');
    this.publicKey = keys.publicKey;
    this.privateKey = keys.privateKey;
  }

  async sign(payload: any): Promise<string> {
    if (!payload) return;
    return V2.sign(payload, this.privateKey);
  }

  async verify(token: string): Promise<any> {
    return V2.verify(token, this.publicKey);
  }
}
