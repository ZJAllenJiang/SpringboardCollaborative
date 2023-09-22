# Use an official Node runtime as the builder
FROM node:18-alpine as react-builder

# Set the working directory in the container
WORKDIR /app

# Copy the package.json and yarn.lock (or package-lock.json for npm) files to install dependencies
COPY package.json yarn.lock ./

# Install any dependencies
RUN yarn install

# Copy the rest of the application
COPY . .

# Build the React app
RUN yarn build

# Use Nginx to serve the built React app
FROM nginx:1.21-alpine

# Copy the React build from the builder stage
COPY --from=react-builder /app/build /usr/share/nginx/html

# Expose port 80 for the app
EXPOSE 80

# Start Nginx server
CMD ["nginx", "-g", "daemon off;"]


