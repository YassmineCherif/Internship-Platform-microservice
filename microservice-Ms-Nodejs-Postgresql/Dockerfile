# Use the official Node.js image as a base
FROM node:14

# Create and set the working directory
WORKDIR /app

# Copy package.json and package-lock.json
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the rest of the application code
COPY . .

# Expose the application's port (adjust if necessary)
EXPOSE 8088

# Start the Node.js application
CMD ["node", "server.js"]
