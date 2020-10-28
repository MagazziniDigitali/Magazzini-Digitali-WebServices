
INSERT INTO `MDSoftware` (`ID`, `NOME`, `LOGIN`, `PASSWORD`, `IP_AUTORIZZATI`, `BIBLIOTECA_DEPOSITARIA`, `ID_ISTITUZIONE`, `ID_RIGTHS`, `NOTE`) VALUES
('d9a41529-289b-4695-98b5-5f95f978c749', 'Interfaccia API', 'IA_MD', '36d4c3e2b842797fa1edfe5f396b896e08cd8f0dc7db16bd473a189a9b063504', '192.168.5.8,127.0.0.1,192.168.7.153', 1, '5640ee96-3a39-40f0-b6e8-74d568a551fa-AG', NULL, '');

INSERT INTO `MDSoftwareConfig` (`ID`, `ID_SOFTWARE`, `NOME`, `DESCRIZIONE`, `VALUE`, `ID_NODO`) VALUES
('107a1e69-5456-4659-8b00-b1f0a83daff0', 'd9a41529-289b-4695-98b5-5f95f978c749', 'solr.connectionTimeOut', 'TimeOut di connessione con il server Solr', '60000', NULL),
('16fc4878-f66a-4614-a43f-ce74e95eedbb', 'd9a41529-289b-4695-98b5-5f95f978c749', 'solr.collection', 'Indica il nome del Core Solr su cui lavorare', 'mdProd', NULL),
('23a1d509-378a-43d8-8d05-273f09f15c03', 'd9a41529-289b-4695-98b5-5f95f978c749', 'pathStorage', 'Path in cui si Trovano Archiviate le immagini', '/mnt/MDStorage', NULL),
('2924f4d9-ffd9-4408-b214-d6b52ce366ca', 'd9a41529-289b-4695-98b5-5f95f978c749', 'solr.Cloud', 'Indica se il server Solr è in modalità Cloud ', 'false', NULL),
('41801e1e-2016-4ac1-bc55-c1dfa37ed4ba', 'd9a41529-289b-4695-98b5-5f95f978c749', 'solr.URL', 'Lista dei server Zookeeper i quali permettono di sapere la struttura dei server Solr', 'http://md-solr01.prod.bncf.lan:8983/solr/mdProd', NULL),
('3d01fea7-1893-11eb-9512-08002725514e', 'd9a41529-289b-4695-98b5-5f95f978c749', 'solr.optional','','',NULL),
('62e91b95-199d-441f-8a1a-1f541349e1e3', 'd9a41529-289b-4695-98b5-5f95f978c749', 'ticket.internalUrl', 'Indirizzo UOL interno del visualizzatore oggetti digitali', 'https://services.depositolegale.it/MagazziniDigitaliServices/showObject', NULL),
('9159b469-094d-4126-97b6-2e0b2b56d7ee', 'd9a41529-289b-4695-98b5-5f95f978c749', 'solr.clientTimeOut', 'TimeOut del client verso il server Solr', '100000', NULL),
('983f700b-c3b2-4dca-899b-61c20d458823', 'd9a41529-289b-4695-98b5-5f95f978c749', 'istituzioneMD.id', 'Identificativo Istituzione MD per l\'autenticazione', '5640ee96-3a39-40f0-b6e8-74d568a551fa-AG', NULL),
('a7b6dd07-93dd-4018-87ea-f558c7a5f6bc', 'd9a41529-289b-4695-98b5-5f95f978c749', 'ticket.dockerUrl', 'Indirizzo URL dell\'interfaccia Docker', 'https://docker.depositolegale.it/index.php', NULL),
('c851145b-ba2b-4387-a4f3-f1fedd441e5d', 'd9a41529-289b-4695-98b5-5f95f978c749', 'nodo', 'Nodo di Riferimento Master', '', 'a0e875de-4d1b-11e6-8414-f01fafe387cf'),
('d2777b50-b0da-4d98-8895-7785ed1e6dc8', 'd9a41529-289b-4695-98b5-5f95f978c749', 'ticket.externalUrl', 'Indirizzo URL Esterno per scaricare gli oggetti', 'https://services.depositolegale.it/MagazziniDigitaliServices/showObject', NULL),
('dbcdda9c-77f2-4697-9186-cae5bbce88ff', 'd9a41529-289b-4695-98b5-5f95f978c749', 'path.premis', 'Percorso in cui vengono archiviati i files Premis', '/mnt/Storage/Temporaneo/Premis', NULL),
('e2d7e744-9ee5-4153-a2d2-338c40e50644', 'd9a41529-289b-4695-98b5-5f95f978c749', 'ticket.period', 'Periodo di validazione di un Ticket in Minuti', '60', NULL),
('f92636c5-3df2-4e94-83e4-ca4ff84a4562', 'd9a41529-289b-4695-98b5-5f95f978c749', 'ticket.memorieUrl', 'Indirizzo URL interno del sito memorie depositolegale', 'http://memoria.depositolegale.it/*/', NULL);

INSERT INTO `MDSoftware` (`ID`, `NOME`, `LOGIN`, `PASSWORD`, `IP_AUTORIZZATI`, `BIBLIOTECA_DEPOSITARIA`, `ID_ISTITUZIONE`, `ID_RIGTHS`, `NOTE`) VALUES
('6d17fef6-5b6b-437c-8ee2-b21b0c444244-AG', 'Show Object', 'SO_MD', '36d4c3e2b842797fa1edfe5f396b896e08cd8f0dc7db16bd473a189a9b063504', '127.0.0.1', 1, '5640ee96-3a39-40f0-b6e8-74d568a551fa-AG', NULL, '');

INSERT INTO `MDSoftwareConfig` (`ID`, `ID_SOFTWARE`, `NOME`, `DESCRIZIONE`, `VALUE`, `ID_NODO`) VALUES
('1d123698-9828-48bc-9658-2b45295e62a7', '6d17fef6-5b6b-437c-8ee2-b21b0c444244-AG', 'docker.ip', 'Indirizzo IP del server Docker', '192.168.254.5', NULL),
('43492dc7-c40a-4da3-9d87-2af2d2b541f0', '6d17fef6-5b6b-437c-8ee2-b21b0c444244-AG', 'istituzioneMD.id', 'Identificativo Istituzione MD per l\'autenticazione', '5640ee96-3a39-40f0-b6e8-74d568a551fa-AG', NULL),
('8420aa9f-55d6-4852-a60d-b08e61953760', '6d17fef6-5b6b-437c-8ee2-b21b0c444244-AG', 'validate.nodo', 'Sigla che indica il Nodo di Riferimento', '', 'a0e875de-4d1b-11e6-8414-f01fafe387cf');
