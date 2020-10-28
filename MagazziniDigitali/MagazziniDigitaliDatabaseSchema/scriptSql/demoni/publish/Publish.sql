INSERT INTO `MDSoftware` (`ID`, `NOME`, `LOGIN`, `PASSWORD`, `IP_AUTORIZZATI`, `BIBLIOTECA_DEPOSITARIA`, `ID_ISTITUZIONE`, `ID_RIGTHS`, `NOTE`) VALUES
('b23982c9-1aa2-4217-bdb3-cc5a825b6d08-AG', 'DemoneQuartz - Publish', 'DQ_Publish_MD', '36d4c3e2b842797fa1edfe5f396b896e08cd8f0dc7db16bd473a189a9b063504', '127.0.0.1,192.168.14.11', 1, '5640ee96-3a39-40f0-b6e8-74d568a551fa-AG', NULL, '');

INSERT INTO `MDNodi` (`ID`, `NOME`, `DESCRIZIONE`, `TIPO`, `RSYNC`, `RSYNCPASSWORD`, `URLCHECKSTORAGE`, `PATH_STORAGE`, `S3_URL`, `S3_REGION`, `S3_ACCESSKEY`, `S3_SECRETKEY`, `S3_BUCKETNAME`, `ACTIVE`, `CODE`) VALUES
('58d62ec4-342c-4282-b51e-e36577c9909c', 'MD-Roma', 'Storage S3 Roma', 'S', NULL, NULL, NULL, NULL, 'https://cs2.cloudspc.it:8079', 'us-east-1', '465ffb75913244be96a292672ba7850c', '5662f5554926477584a0c0b6998fdec2', 'Storage', 1, 'A');

INSERT INTO `MDSoftwareConfig` (`ID`, `ID_SOFTWARE`, `NOME`, `DESCRIZIONE`, `VALUE`, `ID_NODO`) VALUES
(uuid(), 'b23982c9-1aa2-4217-bdb3-cc5a825b6d08-AG', 'socketPort', 'Porta del Software per la chiusura dell\'aplicativo', '9002', NULL),
(uuid(), 'b23982c9-1aa2-4217-bdb3-cc5a825b6d08-AG', 'path.premis', 'Percorso in cui vengono archiviati i files Premis', '/mnt/areaTemporanea/Premis', NULL),
(uuid(), 'b23982c9-1aa2-4217-bdb3-cc5a825b6d08-AG', 'path.tar', 'Percorso in cui vengono archiviati i documenti unzipppati', '/mnt/areaTemporanea/Tar', NULL),
(uuid(), 'b23982c9-1aa2-4217-bdb3-cc5a825b6d08-AG', 'validate.nodo', 'Sigla che indica il Nodo di Riferimento', 'C', NULL),
(uuid(), 'b23982c9-1aa2-4217-bdb3-cc5a825b6d08-AG', 'path.droid', 'Path e Nome del file relativo al pacchetto Droid per la validazione del materiale', '/Users/massi/bin/droid/droid.sh', NULL),
(uuid(), 'b23982c9-1aa2-4217-bdb3-cc5a825b6d08-AG', 'path.droid.tmp', 'Percorso per i files temporanei per Droid', '/mnt/areaTemporanea/droid/tmp', NULL),
(uuid(), 'b23982c9-1aa2-4217-bdb3-cc5a825b6d08-AG', 'nodo', 'Nodo a cui l\'applicazione fa riferimento per l\'archiviazione del Materiale ', '', '58d62ec4-342c-4282-b51e-e36577c9909c');
