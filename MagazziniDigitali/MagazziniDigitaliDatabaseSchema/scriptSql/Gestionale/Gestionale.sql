
INSERT INTO `MDSoftware` (`ID`, `NOME`, `LOGIN`, `PASSWORD`, `IP_AUTORIZZATI`, `BIBLIOTECA_DEPOSITARIA`, `ID_ISTITUZIONE`, `ID_RIGTHS`, `NOTE`) VALUES
('51557609-f3b5-4cd9-9210-62ef51177536', 'Gestionale Magazzini Digitali', 'GS_MD', '36d4c3e2b842797fa1edfe5f396b896e08cd8f0dc7db16bd473a189a9b063504', '127.0.0.1', 1, '5640ee96-3a39-40f0-b6e8-74d568a551fa-AG', NULL, '');

INSERT INTO `MDSoftwareConfig` (`ID`, `ID_SOFTWARE`, `NOME`, `DESCRIZIONE`, `VALUE`, `ID_NODO`) VALUES
('4af73962-4f89-45f8-9792-c3b4bd874d19', '51557609-f3b5-4cd9-9210-62ef51177536', 'AuthenticationSoftwarePort', 'Chiamata Wsdl per verificare l\'autenticazione del Software', 'https://md.svil.bncf.lan:8080/MagazziniDigitaliServices/services/AuthenticationSoftwarePort?wsdl', NULL),
('59c2f3e6-50a2-4369-bbd7-1f282ac2de79', '51557609-f3b5-4cd9-9210-62ef51177536', 'send.email.password', 'Indica la password dell\'indirizzo Email con vui verrà inviata la notifica', 'ov6Uojiejai5', NULL),
('743e259b-bc9d-4c4e-947f-12490ae71082', '51557609-f3b5-4cd9-9210-62ef51177536', 'quartzFile', 'nome del file di configurazione di Quartz', 'quartz.properties', NULL),
('898b5663-95e3-4e69-bdfa-0818460c45de', '51557609-f3b5-4cd9-9210-62ef51177536', 'url.homeGestionale', 'Indica l\'URL della Home Page del Gestionale ', 'http://md.svil.bncf.lan:8080/MagazziniDigitaliGestionale/', NULL),
('b0b27535-0913-4ee7-8e31-2b8e6bb36b97', '51557609-f3b5-4cd9-9210-62ef51177536', 'urlAltaRisoluzione', 'Url relativo ala manuale dell\'altra risoluzione', 'https://www.depositolegale.it/index.php/alta-risoluzione/', NULL),
('bcc7dad2-8736-45bf-9b50-45c166f08cf6', '51557609-f3b5-4cd9-9210-62ef51177536', 'send.email.emailAdmin', 'Indirizzo Email dell\'amministratore MD', 'massimiliano.randazzo@gmail.com', NULL),
('d11b40d1-4e91-4f17-a6ab-4079a1bb9c7c', '51557609-f3b5-4cd9-9210-62ef51177536', 'url.validate', 'Indica l\'URL per la validazione delle credenziali dell\'utente', 'http://md.svil.bncf.lan:8080/MagazziniDigitaliGestionale/Home.action?checkId=', NULL),
('f8c4f96a-90ab-4f9f-b940-33255f0ccf1a', '51557609-f3b5-4cd9-9210-62ef51177536', 'path.tmp', 'Path radice in cui verranno archiviate le cartelle Temporanee dei trasferimenti da parte degli istituzioni', '/mnt/areaTemporanea/Ingest', NULL),
('fe4fd98b-2c74-4dfd-8715-5350c5902a92', '51557609-f3b5-4cd9-9210-62ef51177536', 'path.premis', 'Path in cui vengono archiviate tutti tracciati Premis temporaneamente', '/mnt/areaTemporanea/Premis', NULL),
('ff55f953-e2cc-43d7-94e7-21e4bcbd8c4c', '51557609-f3b5-4cd9-9210-62ef51177536', 'send.email.login', 'Indica l\'indirizzo Email con cui verrà inviata la email di notifica', 'noreply@depositolegale.it', NULL);

INSERT INTO `MDUtenti` (`ID`, `LOGIN`, `PASSWORD`, `COGNOME`, `NOME`, `AMMINISTRATORE`, `IP_AUTORIZZATI`, `ID_ISTITUZIONE`, `NOTE`, `CODICEFISCALE`, `EMAIL`, `SUPERADMIN`) VALUES
('da96d0bb-071f-4448-a072-aaf4ad09fbe1', 'massi', '7bef4588f4f3e92a20a4e93025a057fdbfe00c935a62e46dda05d101d6c3ba2d', 'Randazzo', 'Massimiliano', 1, '127.0.0.1', NULL, NULL, NULL, 'massimiliano.randazzo@gmail.com', 1);
