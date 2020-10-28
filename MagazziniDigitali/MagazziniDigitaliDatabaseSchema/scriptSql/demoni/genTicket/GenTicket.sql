INSERT INTO `MDSoftware` (`ID`, `NOME`, `LOGIN`, `PASSWORD`, `IP_AUTORIZZATI`, `BIBLIOTECA_DEPOSITARIA`, `ID_ISTITUZIONE`, `ID_RIGTHS`, `NOTE`) VALUES
('1f10cbba-251e-49c3-9625-0a0d73820cbf-AG', 'Demone Quartz - Generazione Ticket', 'DQ_GenTicket_MD', '36d4c3e2b842797fa1edfe5f396b896e08cd8f0dc7db16bd473a189a9b063504', '127.0.0.1', 1, '5640ee96-3a39-40f0-b6e8-74d568a551fa-AG', NULL, '');

INSERT INTO `MDSoftwareConfig` (`ID`, `ID_SOFTWARE`, `NOME`, `DESCRIZIONE`, `VALUE`, `ID_NODO`) VALUES
('6014fff2-ed76-49f8-9764-b09d8998d0f5', '1f10cbba-251e-49c3-9625-0a0d73820cbf-AG', 'path.scambioCoda', 'Path Temporaneo per la Coda di pubblicazione', '/mnt/materiale/Scambio/Amm/Coda', NULL),
('878440da-73b9-46ab-9ebf-81be20959ac2', '1f10cbba-251e-49c3-9625-0a0d73820cbf-AG', 'path.scambioTgz', 'Path Temporaneo di Scambio dati ', '/mnt/materiale/Scambio/Amm/Tgz', NULL),
('ebc6968e-171b-4f53-9c9a-5ea5a02dc132', '1f10cbba-251e-49c3-9625-0a0d73820cbf-AG', 'socketPort', 'Porta del Software per la chiusura dell\'aplicativo', '9008', NULL);
