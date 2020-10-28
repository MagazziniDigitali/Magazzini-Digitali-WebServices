INSERT INTO `MDSoftware` (`ID`, `NOME`, `LOGIN`, `PASSWORD`, `IP_AUTORIZZATI`, `BIBLIOTECA_DEPOSITARIA`, `ID_ISTITUZIONE`, `ID_RIGTHS`, `NOTE`) VALUES
('db821e14-8609-489c-9b7b-78e0cee5a58e-AG', 'Demone Quartz - Coda Geo Replica', 'DQ_CodaGeoReplica_MD', '36d4c3e2b842797fa1edfe5f396b896e08cd8f0dc7db16bd473a189a9b063504', '127.0.0.1', 1, '5640ee96-3a39-40f0-b6e8-74d568a551fa-AG', NULL, '');

INSERT INTO `MDSoftwareConfig` (`ID`, `ID_SOFTWARE`, `NOME`, `DESCRIZIONE`, `VALUE`, `ID_NODO`) VALUES
('d2da4e1f-73ed-4419-83bd-32b104868e6b', 'db821e14-8609-489c-9b7b-78e0cee5a58e-AG', 'coda.path', 'Indica la Path in cui verranno registrati i file Coda per la geoReplica', '/mnt/materiale/coda', NULL),
('67c698ec-bae8-4896-9934-1ee759b1f58a', 'db821e14-8609-489c-9b7b-78e0cee5a58e-AG', 'socketPort', 'Porta da utilizzare per la gestione del modulo', '9003', NULL);
