module.exports = (sequelize, Sequelize) => {
  const Stage = sequelize.define("Stage", {
    idStage: {
      type: Sequelize.BIGINT,
      autoIncrement: true,
      primaryKey: true,
    },
    sujetStage: {
      type: Sequelize.STRING,
    },
    notestage: {
      type: Sequelize.FLOAT,
    },
    archived: {
      type: Sequelize.BOOLEAN,
    },
    nomFichierRapport: {
      type: Sequelize.STRING,
    },
  });

  return Stage;
};
