FROM node:14 as node
WORKDIR /app
COPY . .
RUN npm install
RUN npm run build --prod

FROM nginx:1.15.8-alpine

COPY --from=builder /usr/src/app/dist/Lafarge-Client-UI/ /usr/share/nginx/html


# CMD ["npm" , "start"]