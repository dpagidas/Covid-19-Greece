FROM node:14

RUN mkdir /usr/src/app
WORKDIR /usr/src/app

RUN npm install -g angular/cli@1.7.1

COPY ./usr/src/app

CMD ng serve --prod

# CMD ["npm" , "start"]