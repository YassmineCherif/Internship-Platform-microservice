const db = require("../models");
const Stage = db.stages;
const Op = db.Sequelize.Op;

// Create and Save a new Stage
exports.create = (req, res) => {
  if (!req.body.sujetStage) {
    res.status(400).send({
      message: "Content cannot be empty!"
    });
    return;
  }

  const stage = {
    sujetStage: req.body.sujetStage,
    notestage: req.body.notestage,
    archived: req.body.archived || false,
    nomFichierRapport: req.body.nomFichierRapport
  };

  Stage.create(stage)
      .then(data => res.send(data))
      .catch(err => res.status(500).send({
        message: err.message || "Some error occurred while creating the Stage."
      }));
};

// Retrieve all Stages
exports.findAll = (req, res) => {
  const sujetStage = req.query.sujetStage;
  const condition = sujetStage ? { sujetStage: { [Op.iLike]: `%${sujetStage}%` } } : null;

  Stage.findAll({ where: condition })
      .then(data => res.send(data))
      .catch(err => res.status(500).send({
        message: err.message || "Some error occurred while retrieving stages."
      }));
};

// Find a single Stage by id
exports.findOne = (req, res) => {
  const id = req.params.id;

  Stage.findByPk(id)
      .then(data => res.send(data))
      .catch(err => res.status(500).send({
        message: "Error retrieving Stage with id=" + id
      }));
};

// Update a Stage by id
exports.update = (req, res) => {
  const id = req.params.id;

  Stage.update(req.body, { where: { idStage: id } })
      .then(num => {
        if (num == 1) {
          res.send({ message: "Stage was updated successfully." });
        } else {
          res.send({ message: `Cannot update Stage with id=${id}.` });
        }
      })
      .catch(err => res.status(500).send({
        message: "Error updating Stage with id=" + id
      }));
};

// Delete a Stage by id
exports.delete = (req, res) => {
  const id = req.params.id;

  Stage.destroy({ where: { idStage: id } })
      .then(num => {
        if (num == 1) {
          res.send({ message: "Stage was deleted successfully!" });
        } else {
          res.send({ message: `Cannot delete Stage with id=${id}.` });
        }
      })
      .catch(err => res.status(500).send({
        message: "Could not delete Stage with id=" + id
      }));
};

// Delete all Stages
exports.deleteAll = (req, res) => {
  Stage.destroy({ where: {}, truncate: false })
      .then(nums => res.send({ message: `${nums} Stages were deleted successfully!` }))
      .catch(err => res.status(500).send({
        message: err.message || "Some error occurred while removing all stages."
      }));
};

// Find all archived Stages
exports.findAllArchived = (req, res) => {
  Stage.findAll({ where: { archived: true } })
      .then(data => res.send(data))
      .catch(err => res.status(500).send({
        message: err.message || "Some error occurred while retrieving archived stages."
      }));
};
