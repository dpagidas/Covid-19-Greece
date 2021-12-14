FROM node:14 as node
WORKDIR /app
COPY . .
RUN npm install
COPY ./usr/src/app

CMD ng serve --prod

# CMD ["npm" , "start"]