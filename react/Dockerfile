FROM node:18-alpine

WORKDIR /app

# Copy package.json and package-lock.json/yarn.lock
COPY package*.json ./
COPY yarn.lock ./

# Install dependencies
RUN yarn install

# Copy the rest of the React app files
COPY . .

# Expose the development server's port
EXPOSE 3000

CMD ["yarn", "start"]
