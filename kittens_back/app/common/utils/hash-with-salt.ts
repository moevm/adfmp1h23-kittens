import * as crypto from 'crypto';

export function hashWithSalt(password: string, salt: string) {
  const hash = crypto.createHash('sha256');
  hash.update(password + salt);
  return hash.digest('hex');
}
