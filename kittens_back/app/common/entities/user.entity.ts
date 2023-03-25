import type { User } from '@prisma/client';

export class UserEntity implements Omit<User, 'authId' | 'staffId' | 'languageId'> {
  constructor(data: Partial<UserEntity>) {
    Object.assign(this, data);
  }
  id: number;
  login: string;
  picture: string;
  firstName: string;
  middleName: string;
  lastName: string;
  about: string;
  birthDate: Date;
  created: Date;
  updated: Date;
  suspended: boolean;
}
