FROM node:16-alpine
WORKDIR /frontend
COPY . .
RUN npm install serve
RUN npm install
RUN npm run build
ENV NODE_ENV production
EXPOSE 3000
CMD [ "npx", "serve", "build" ]