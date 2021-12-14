FROM node:14 as node
WORKDIR /app
COPY . .
RUN npm install
RUN npm run build --prod

# CMD ["npm" , "start"]