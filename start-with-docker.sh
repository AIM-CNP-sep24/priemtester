#!/bin/sh

# Build the front-end
cd priemtester-ui
npx vite build
cd ..

# Build the back-end
mvn clean package

# Start the Docker daemon
docker compose up --no-recreate

echo "App started on http://localhost:9000"