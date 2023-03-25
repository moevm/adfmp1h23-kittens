FROM node:18-alpine AS base
RUN npm i -g pnpm
WORKDIR /app
COPY package*.json ./
RUN pnpm install
RUN pnpm prisma generate
COPY . .
CMD ["npm", "start"]
