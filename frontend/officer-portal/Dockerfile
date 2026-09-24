FROM node:20-alpine AS build
WORKDIR /app
COPY frontend/officer-portal/package*.json ./
RUN npm install
COPY frontend/officer-portal/ ./
RUN npm run build
FROM nginx:alpine
COPY --from=build /app/dist/officer-portal/browser /usr/share/nginx/html
COPY frontend/officer-portal/nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
