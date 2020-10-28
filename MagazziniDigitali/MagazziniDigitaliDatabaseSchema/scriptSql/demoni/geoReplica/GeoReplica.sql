INSERT INTO `MDSoftware` (`ID`, `NOME`, `LOGIN`, `PASSWORD`, `IP_AUTORIZZATI`, `BIBLIOTECA_DEPOSITARIA`, `ID_ISTITUZIONE`, `ID_RIGTHS`, `NOTE`) VALUES
('2c04a47a-1a5b-49ea-a371-23a7966b7a03-AG', 'Demone Quartz - Geo Replica', 'DQ_GeoReplica_MD', '36d4c3e2b842797fa1edfe5f396b896e08cd8f0dc7db16bd473a189a9b063504', '127.0.0.1', 1, '5640ee96-3a39-40f0-b6e8-74d568a551fa-AG', NULL, '');

INSERT INTO `MDSoftwareConfig` (`ID`, `ID_SOFTWARE`, `NOME`, `DESCRIZIONE`, `VALUE`, `ID_NODO`) VALUES
('7bf4b83a-4a86-4206-b9fb-48c8c88cbe25', '2c04a47a-1a5b-49ea-a371-23a7966b7a03-AG', 'coda.path', 'Indica la Path in cui verranno registrati i file Coda per la geoReplica', '/mnt/materiale/coda', NULL),
('a677acc7-bc2e-4390-89aa-b880db0497a9', '2c04a47a-1a5b-49ea-a371-23a7966b7a03-AG', 'path.premis', 'Percorso in cui vengono archiviati i files Premis', '/mnt/areaTemporanea/Premis', NULL),
('b5242198-966f-4692-9f34-98ba41df9d0c', '2c04a47a-1a5b-49ea-a371-23a7966b7a03-AG', 'nodo', 'Nodo a cui l\'applicazione fa riferimento per l\'archiviazione del Materiale ', '', '58d62ec4-342c-4282-b51e-e36577c9909c'),
('f400703a-e6d0-4dd9-aa70-f3088ea3e719', '2c04a47a-1a5b-49ea-a371-23a7966b7a03-AG', 'socketPort', 'Porta da utilizzare per la gestione del modulo', '9004', NULL);
