INSERT INTO `MDSoftware` (`ID`, `NOME`, `LOGIN`, `PASSWORD`, `IP_AUTORIZZATI`, `BIBLIOTECA_DEPOSITARIA`, `ID_ISTITUZIONE`, `ID_RIGTHS`, `NOTE`) VALUES
('70d0f168-207a-4ef9-941e-5d6608b9c74a-AG', 'Demone Quartz - Solr Index', 'DQ_SolrIndex_MD', '36d4c3e2b842797fa1edfe5f396b896e08cd8f0dc7db16bd473a189a9b063504', '127.0.0.1', 1, '5640ee96-3a39-40f0-b6e8-74d568a551fa-AG', NULL, '');

INSERT INTO `MDSoftwareConfig` (`ID`, `ID_SOFTWARE`, `NOME`, `DESCRIZIONE`, `VALUE`, `ID_NODO`) VALUES
('0d2e9b46-cd04-4fcd-b24b-d64838494116', '70d0f168-207a-4ef9-941e-5d6608b9c74a-AG', 'path.premis', 'Percorso in cui vengono archiviati i files Premis', '/mnt/areaTemporanea/Premis', NULL),
('33229e55-4d6b-44b7-b392-5a4c395c10f8', '70d0f168-207a-4ef9-941e-5d6608b9c74a-AG', 'solr.Cloud', 'Indica se il server Solr è in modalità Cloud ', 'false', NULL),
('34a19404-90cc-4a49-99c0-f198d9fcdeef', '70d0f168-207a-4ef9-941e-5d6608b9c74a-AG', 'solr.collection', 'Indica il nome del Core Solr su cui lavorare', 'mdProd', NULL),
('362a476c-40d3-461c-bfd7-f8d487a3051f', '70d0f168-207a-4ef9-941e-5d6608b9c74a-AG', 'solr.clientTimeOut', 'TimeOut del client verso il server Solr', '100000', NULL),
('5d56481b-8a02-4b2f-82f7-f161928b6f36', '70d0f168-207a-4ef9-941e-5d6608b9c74a-AG', 'solr.connectionTimeOut', 'TimeOut di connessione con il server Solr', '60000', NULL),
('69867dac-d6c7-48ee-9c88-ae750b02659c', '70d0f168-207a-4ef9-941e-5d6608b9c74a-AG', 'rights.closedAccess.id', 'Chiave ID di default per Protetto da licenza', '51374a86-7785-436e-8070-50f7518a45a1', NULL),
('cbdc7a76-f17d-4e2e-9abf-e848188f1348', '70d0f168-207a-4ef9-941e-5d6608b9c74a-AG', 'rights.openAccess.id', 'Chiave ID di default per Accesso Libero', '491cc878-83d3-4a27-a7a7-7413730387b1', NULL),
('ce60a391-1831-4653-981b-eb8376071fb6', '70d0f168-207a-4ef9-941e-5d6608b9c74a-AG', 'socketPort', 'Porta da utilizzare per la gestione del modulo', '9005', NULL),
('edec9e53-aa86-4bcd-b92e-a1f51cedfdd5', '70d0f168-207a-4ef9-941e-5d6608b9c74a-AG', 'solr.URL', 'Lista dei server Zookeeper i quali permettono di sapere la struttura dei server Solr', 'http://md-solr01.prod.bncf.lan:8983/solr/mdProd', NULL),
('f3626c64-76a7-4284-a9aa-68f30eebebcb', '70d0f168-207a-4ef9-941e-5d6608b9c74a-AG', 'solrIndex.tmpPath', 'Path temporanea per l\'analisi del materiale nel Modulo di indicizzazione', '/mnt/areaTemporanea/solr/tmp', NULL),
('63acdf95-1892-11eb-9512-08002725514e', '70d0f168-207a-4ef9-941e-5d6608b9c74a-AG', 'solr.optional','','',NULL),
('fc19f832-374a-495f-8d75-2e9f7c35cc6c', '70d0f168-207a-4ef9-941e-5d6608b9c74a-AG', 'nodo', 'Nodo a cui l\'applicazione fa riferimento per l\'archiviazione del Materiale ', '', '58d62ec4-342c-4282-b51e-e36577c9909c');
