# Etapa 1: Compilar la aplicación de Angular
FROM node:20-alpine AS build
WORKDIR /cursos
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build -- --configuration production

# Etapa 2: Servir con Nginx
FROM nginx:alpine
# Copia los archivos compilados al directorio por defecto de Nginx
# (Nota: Verifica si tu versión de Angular genera la ruta en dist/browser o directamente en dist/)
COPY --from=build /cursos/dist/*/browser /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]