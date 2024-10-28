const express = require("express");
const cors = require("cors");
const session = require('express-session');
const Keycloak = require('keycloak-connect');

const app = express();

var corsOptions = {
    origin: "http://localhost:8081"
};

app.use(cors(corsOptions));

// Parse JSON and URL-encoded requests
app.use(express.json());
app.use(express.urlencoded({ extended: true }));

// Configure session for Keycloak
const memoryStore = new session.MemoryStore();
app.use(session({
    secret: 'some-secret-key',
    resave: false,
    saveUninitialized: true,
    store: memoryStore
}));

// Initialize Keycloak with memory store and config
const keycloakConfig = {
    "realm": "your-realm",
    "auth-server-url": "http://localhost:8080/auth",
    "ssl-required": "external",
    "resource": "your-client-id",
    "public-client": true,
    "confidential-port": 0
};
const keycloak = new Keycloak({ store: memoryStore }, keycloakConfig);

// Use Keycloak middleware
app.use(keycloak.middleware());

// Import the database and sync
const db = require("./app/models");
db.sequelize.sync()
    .then(() => {
        console.log("Synced db.");
    })
    .catch((err) => {
        console.log("Failed to sync db: " + err.message);
    });

// Import routes and apply Keycloak protection
const stageRoutes = require("./app/routes/stage.routes");
app.use('/api/stages', keycloak.protect(), (req, res, next) => stageRoutes(app, req, res, next));

// Set port and listen for requests
const PORT = process.env.PORT || 8080;
app.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}.`);
});
