FROM node:14.17.0-alpine as build-step
RUN mkdir -p /app
WORKDIR /app
COPY package.json /app
RUN npm install
RUN npm install -g @angular/cli
COPY . /app
CMD ng serve --host 0.0.0.0 --prod --disableHostCheck true


# CMD ["npm" , "start"]