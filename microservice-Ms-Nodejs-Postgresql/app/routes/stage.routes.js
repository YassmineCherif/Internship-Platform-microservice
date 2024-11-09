module.exports = app => {
  const stages = require("../controllers/stage.controller.js");

  var router = require("express").Router();

  // Create a new Stage
  router.post("/", stages.create);

  // Retrieve all Stages
  router.get("/", stages.findAll);

  // Retrieve all archived Stages
  router.get("/archived", stages.findAllArchived);

  // Retrieve a single Stage with id
  router.get("/:id", stages.findOne);

  // Update a Stage with id
  router.put("/:id", stages.update);

  // Delete a Stage with id
  router.delete("/:id", stages.delete);

  // Delete all Stages
  router.delete("/", stages.deleteAll);

  app.use("/api/stages", router);
};
