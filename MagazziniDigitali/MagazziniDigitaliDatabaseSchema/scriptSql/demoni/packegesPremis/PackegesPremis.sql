INSERT INTO `MDSoftware` (`ID`, `NOME`, `LOGIN`, `PASSWORD`, `IP_AUTORIZZATI`, `BIBLIOTECA_DEPOSITARIA`, `ID_ISTITUZIONE`, `ID_RIGTHS`, `NOTE`) VALUES
('f38c729f-30b3-4863-95d0-c8b24cdb0345-AG', 'Demone Quartz - Generazione Pacchetti Premis', 'DQ_GenPackagesPremis_MD', '36d4c3e2b842797fa1edfe5f396b896e08cd8f0dc7db16bd473a189a9b063504', '127.0.0.1', 1, '5640ee96-3a39-40f0-b6e8-74d568a551fa-AG', NULL, '');

INSERT INTO `MDSoftwareConfig` (`ID`, `ID_SOFTWARE`, `NOME`, `DESCRIZIONE`, `VALUE`, `ID_NODO`) VALUES
('0fe072c3-a7e7-4bc2-a699-673e7e949845', 'f38c729f-30b3-4863-95d0-c8b24cdb0345-AG', 'path.scambioTgz', 'Path Temporaneo di Scambio dati ', '/mnt/materiale/Scambio/Amm/Tgz', NULL),
('15ce573d-751c-4442-a840-e5e181bd36e7', 'f38c729f-30b3-4863-95d0-c8b24cdb0345-AG', 'socketPort', 'Porta del Software per la chiusura dell\'aplicativo', '9006', NULL),
('cee52426-5a9b-4e1d-ae14-85be7541cfb9', 'f38c729f-30b3-4863-95d0-c8b24cdb0345-AG', 'path.premis', 'Percorso in cui vengono archiviati i files Premis', '/mnt/areaTemporanea/Premis', NULL),
('fa0c611e-c7db-40db-8e25-9e4861766a0b', 'f38c729f-30b3-4863-95d0-c8b24cdb0345-AG', 'path.scambioCoda', 'Path Temporaneo per la Coda di pubblicazione', '/mnt/materiale/Scambio/Amm/Coda', NULL);
