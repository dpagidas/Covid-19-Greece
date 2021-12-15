FROM node:14 as node
WORKDIR /app
COPY . .
RUN npm install
RUN npm run build --prod

FROM nginx:alpine
COPY --from=node /app/dist/Lafarge-Client-UI /usr/share/nginx/html


# CMD ["npm" , "start"]