INSERT INTO `MDSoftware` (`ID`, `NOME`, `LOGIN`, `PASSWORD`, `IP_AUTORIZZATI`, `BIBLIOTECA_DEPOSITARIA`, `ID_ISTITUZIONE`, `ID_RIGTHS`, `NOTE`) VALUES
('5d35ca22-14b5-4bbe-b78e-49507983135c-AG', 'Demone Quartz - Generazione Registro Ingresso', 'DQ_GenRegistroIngresso_MD', '36d4c3e2b842797fa1edfe5f396b896e08cd8f0dc7db16bd473a189a9b063504', '127.0.0.1', 1, '5640ee96-3a39-40f0-b6e8-74d568a551fa-AG', NULL, '');

INSERT INTO `MDSoftwareConfig` (`ID`, `ID_SOFTWARE`, `NOME`, `DESCRIZIONE`, `VALUE`, `ID_NODO`) VALUES
('462628d1-813c-4763-93b0-6fc73a4a4a8e', '5d35ca22-14b5-4bbe-b78e-49507983135c-AG', 'socketPort', 'Porta del Software per la chiusura dell\'aplicativo', '9007', NULL),
('5431a78e-33be-41bb-b16d-d817c917f69e', '5d35ca22-14b5-4bbe-b78e-49507983135c-AG', 'path.scambioCoda', 'Path Temporaneo per la Coda di pubblicazione', '/mnt/materiale/Scambio/Amm/Coda', NULL),
('ef3cef54-c658-4953-8f6f-d0a5f57eea50', '5d35ca22-14b5-4bbe-b78e-49507983135c-AG', 'path.scambioTgz', 'Path Temporaneo di Scambio dati ', '/mnt/materiale/Scambio/Amm/Tgz', NULL);
