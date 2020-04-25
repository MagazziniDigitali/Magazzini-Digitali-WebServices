
INSERT INTO `MDSoftware` (`ID`, `NOME`, `LOGIN`, `PASSWORD`, `IP_AUTORIZZATI`, `BIBLIOTECA_DEPOSITARIA`, `ID_ISTITUZIONE`, `ID_RIGTHS`, `NOTE`) VALUES
('df3b4587-a861-4651-ac73-76fdac1b25b5-AG', 'Demone Quartz - Validate', 'DQ_Validate_MD', '36d4c3e2b842797fa1edfe5f396b896e08cd8f0dc7db16bd473a189a9b063504', '127.0.0.1', 1, '5640ee96-3a39-40f0-b6e8-74d568a551fa-AG', NULL, '');

INSERT INTO `MDSoftwareConfig` (`ID`, `ID_SOFTWARE`, `NOME`, `DESCRIZIONE`, `VALUE`, `ID_NODO`) VALUES
(uuid(), 'df3b4587-a861-4651-ac73-76fdac1b25b5-AG', 'path.droid', 'Path e Nome del file relativo al pacchetto Droid per la validazione del materiale', '/Users/massi/bin/droid/droid.sh', NULL),
(uuid(), 'df3b4587-a861-4651-ac73-76fdac1b25b5-AG', 'path.droid.tmp', 'Percorso per i files temporanei per Droid', '/mnt/areaTemporanea/droid/tmp', NULL),
(uuid(), 'df3b4587-a861-4651-ac73-76fdac1b25b5-AG', 'path.tar', 'Percorso in cui vengono archiviati i documenti unzipppati', '/mnt/areaTemporanea/Tar', NULL),
(uuid(), 'df3b4587-a861-4651-ac73-76fdac1b25b5-AG', 'path.premis', 'Percorso in cui vengono archiviati i files Premis', '/mnt/areaTemporanea/Premis', NULL),
(uuid(), 'df3b4587-a861-4651-ac73-76fdac1b25b5-AG', 'socketPort', 'Porta del Software per la chiusura dell\'aplicativo', '9001', NULL),
(uuid(), 'df3b4587-a861-4651-ac73-76fdac1b25b5-AG', 'validate.nodo', 'Sigla che indica il Nodo di Riferimento', 'C', NULL);
