
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

INSERT INTO `MDConfigDefaults` (`ID`, `NAME`, `TIPO_ISTITUTO`) VALUES
('16e51d65-3979-4fb3-89b6-67b8dab27a13-CD', 'Demone Validazione', 'B'),
('5fa74374-9846-4c4a-be6a-160ae46b3552-CD', 'Upload Materiale', 'E'),
('6658fa5f-6c92-4fc2-9b13-f54ff4c3fc78-CD', 'Interfaccia API', 'B'),
('877b26ba-1a20-4079-97d9-8e05f4b542d0-CD', 'Show Object', 'B'),
('e575c31e-ecea-488b-84ee-b8a0b5e45055-CD', 'Gestionale', 'B');

INSERT INTO `MDConfigDefaultsRow` (`ID`, `ID_CONFIGDEFAULTS`, `NAME`, `VALUE`, `DESCRIZIONE`) VALUES
('06ce9044-2859-491e-8c69-8ccffe6a1ac5-CDS', '6658fa5f-6c92-4fc2-9b13-f54ff4c3fc78-CD', 'solr.collection', 'mdProd', 'Indica il nome del Core Solr su cui lavorare'),
('1acaff26-28bd-4cc3-8206-d48206a5a39a-CDS', 'e575c31e-ecea-488b-84ee-b8a0b5e45055-CD', 'send.email.login', 'example@example.it', 'Indica l\'indirizzo Email con cui verrà inviata la email di notifica'),
('23dd40f1-1e2a-4bac-bfcb-39c0d55f374b-CDS', 'e575c31e-ecea-488b-84ee-b8a0b5e45055-CD', 'urlAltaRisoluzione', 'https://www.depositolegale.it/index.php/alta-risoluzione/', 'Url relativo al manuale dell\'altra risoluzione'),
('51c9ecf1-a9b3-45fd-a072-1b07ecadb4cb-CDS', '5fa74374-9846-4c4a-be6a-160ae46b3552-CD', 'wsdlCheckMD', '$URL_SERVICES$/MagazziniDigitaliServices/services/CheckMDPort?wsdl', 'Chiamata Wsdl per verificare lo stato dell\'oggetto'),
('5327155c-e044-4960-8023-6672d9084fa4-CDS', 'e575c31e-ecea-488b-84ee-b8a0b5e45055-CD', 'quartzFile', 'quartz.properties', 'nome del file di configurazione di Quartz'),
('5ce7e861-5393-41d0-ba5f-9ca2d8d6799d-CDS', 'e575c31e-ecea-488b-84ee-b8a0b5e45055-CD', ' path.premis', '/mnt/areaTemporanea/Premis', ' Path in cui vengono archiviate tutti tracciati Premis temporaneamente'),
('65cf10c1-9338-48d3-b8e7-6a15c881ae5f-CDS', 'e575c31e-ecea-488b-84ee-b8a0b5e45055-CD', ' AuthenticationSoftwarePort', '$URL_SERVICES$/MagazziniDigitaliServices/services/AuthenticationSoftwarePort?wsdl', ' Chiamata Wsdl per verificare l\'autenticazione del Software'),
('6be2a27b-30b9-4fe2-b434-dc294c67080b-CDS', '877b26ba-1a20-4079-97d9-8e05f4b542d0-CD', 'istituzioneMD.id', '$ID_ISTITUTOMD$', 'Identificativo Istituzione MD per l\'autenticazione'),
('6e9b3405-0d20-429a-8738-1fca76a179a4-CDS', '6658fa5f-6c92-4fc2-9b13-f54ff4c3fc78-CD', 'solr.Cloud', 'false', 'Indica se il server Solr è in modalità Cloud'),
('79ed0c26-c010-46f6-b79c-6a76dee589cd-CDS', '16e51d65-3979-4fb3-89b6-67b8dab27a13-CD', ' validate.nodo', 'C', ' Sigla che indica il Nodo di Riferimento'),
('834ecb3b-2d7f-4044-866c-b69f93063bb5-CDS', '5fa74374-9846-4c4a-be6a-160ae46b3552-CD', 'sendRsyncPwd', '$PASSWORD_RSYNC$', 'Password per il collegamento Rsync'),
('87656814-44bc-46d5-a5eb-712b9d3bbebe-CDS', '5fa74374-9846-4c4a-be6a-160ae46b3552-CD', 'wsdlConfirmDelMD', '$URL_SERVICES$/MagazziniDigitaliServices/services/ConfirmDelMDPort?wsdl', 'Chiamata Wsdl per indicare l\'avvenuta cancellazione del file dall\'area temporanea dell\'istituto'),
('878ae75c-dac2-4b7c-b2f4-bc2347107153-CDS', '5fa74374-9846-4c4a-be6a-160ae46b3552-CD', 'wsdlInitSendMD', '$URL_SERVICES$/MagazziniDigitaliServices/services/InitSendMDPort?wsdl', 'Chiamata Wsdl per indicare l\'inizio delle operazioni di Trasmissioni dell\'oggetto'),
('8cde8f6c-dfd7-4768-a2ae-2d592e61ec85-CDS', '6658fa5f-6c92-4fc2-9b13-f54ff4c3fc78-CD', 'solr.URL', 'http://md-solr01.prod.bncf.lan:8983/solr/mdProd', 'Lista dei server Zookeeper i quali permettono di sapere la struttura dei server Solr'),
('965e24b6-000b-4c87-80e2-41b481cd071f-CDS', '16e51d65-3979-4fb3-89b6-67b8dab27a13-CD', 'path.premis', '/mnt/areaTemporanea/Premis', ' Percorso in cui vengono archiviati i files Premis'),
('992bfd62-8966-416c-b4f8-ef06e1f51dc2-CDS', '5fa74374-9846-4c4a-be6a-160ae46b3552-CD', 'wsdlEndSendMD', '$URL_SERVICES$/MagazziniDigitaliServices/services/EndSendMDPort?wsdl', 'Chiamata Wsdl per indicare la fine delle operazioni di Trasmissioni dell\'oggetto'),
('9d8950fb-20bc-4b0c-a275-ab10395031ad-CDS', '16e51d65-3979-4fb3-89b6-67b8dab27a13-CD', 'path.droid', ' /home/massi/bin/droid/droid.sh', ' Path e Nome del file relativo al pacchetto Droid per la validazione del materiale'),
('a0e49a96-b3db-4ce4-b47f-9c26052f2b1c-CDS', '877b26ba-1a20-4079-97d9-8e05f4b542d0-CD', 'validate.nodo', '$NODO_MASTER$', 'Sigla che indica il Nodo di Riferimento'),
('a2b5b14a-a0e9-47f1-8860-4cb5985c69e9-CDS', '6658fa5f-6c92-4fc2-9b13-f54ff4c3fc78-CD', 'pathStorage', '/mnt/MDStorage', 'Path in cui si Trovano Archiviate le immagini'),
('a3ad0d44-9a4f-47b2-983d-28645b6174f1-CDS', '16e51d65-3979-4fb3-89b6-67b8dab27a13-CD', ' path.droid.tmp', '/mnt/areaTemporanea/droid/tmp', 'Percorso per i files temporanei per Droid'),
('a80626c2-2ae6-4cd0-b127-53053be8c349-CDS', 'e575c31e-ecea-488b-84ee-b8a0b5e45055-CD', 'send.email.emailAdmin', 'example@example.it', 'Indirizzo Email dell\'amministratore MD'),
('b45cea89-ee66-4eec-8cca-a87ec1b37c92-CDS', '6658fa5f-6c92-4fc2-9b13-f54ff4c3fc78-CD', 'ticket.internalUrl', '$URL_SERVICES$/MagazziniDigitaliServices/showObject', 'Indirizzo UOL interno del visualizzatore oggetti digitali'),
('b981d3bb-a2ba-4cf3-87ac-2dbaaca34bfe-CDS', '16e51d65-3979-4fb3-89b6-67b8dab27a13-CD', ' path.tar', ' /mnt/areaTemporanea/Tar', ' Percorso in cui vengono archiviati i documenti unzipppati'),
('c37af34e-9514-4c05-95a7-a45366c01bf4-CDS', '6658fa5f-6c92-4fc2-9b13-f54ff4c3fc78-CD', 'path.premis', '/mnt/Storage/Temporaneo/Premis', 'Percorso in cui vengono archiviati i files Premis'),
('c3b6270e-1778-4bba-8f42-ca948aaa4090-CDS', 'e575c31e-ecea-488b-84ee-b8a0b5e45055-CD', 'send.email.password', 'password', 'Indica la password dell\'indirizzo Email con cui verrà inviata la notifica'),
('c5cb7e23-83cf-4387-8690-011b1b375274-CDS', '6658fa5f-6c92-4fc2-9b13-f54ff4c3fc78-CD', 'ticket.dockerUrl', 'https://docker.depositolegale.it/index.php', 'Indirizzo URL dell\'interfaccia Docker'),
('c65ae216-e805-4234-90e0-ce5141ef50d8-CDS', 'e575c31e-ecea-488b-84ee-b8a0b5e45055-CD', 'url.validate', '$URL_SERVICES$/MagazziniDigitaliGestionale/Home.action?checkId=', 'Indica l\'URL per la validazione delle credenziali dell\'utente'),
('cff5f1a2-e78f-489f-b85b-e9f8be2cb057-CDS', 'e575c31e-ecea-488b-84ee-b8a0b5e45055-CD', ' path.tmp', ' /mnt/areaTemporanea/Ingest', ' Path radice in cui verranno archiviate le cartelle Temporanee dei trasferimenti da parte degli istituzioni'),
('d27b50dc-4fd2-45fb-b8cc-36d9647f60d4-CDS', '16e51d65-3979-4fb3-89b6-67b8dab27a13-CD', 'socketPort', '9001', ' Porta del Software per la chiusura dell\'applicativo'),
('d30743b5-9574-41e6-8401-8ef7b2a8ea6b-CDS', '6658fa5f-6c92-4fc2-9b13-f54ff4c3fc78-CD', 'ticket.memorieUrl', 'http://memoria.depositolegale.it/*/', 'Indirizzo URL interno del sito memorie deposito legale'),
('d41e7c90-220e-49de-abf0-47edece3ea34-CDS', '6658fa5f-6c92-4fc2-9b13-f54ff4c3fc78-CD', 'ticket.externalUrl', '$URL_SERVICES$/MagazziniDigitaliServices/showObject', 'Indirizzo URL Esterno per scaricare gli oggetti'),
('d4b31e15-a1ee-40be-90dd-7b549803ca82-CDS', '6658fa5f-6c92-4fc2-9b13-f54ff4c3fc78-CD', 'solr.connectionTimeOut', '60000', 'TimeOut di connessione con il server Solr'),
('e55193be-df31-49ca-ba60-9f394abfad30-CDS', '5fa74374-9846-4c4a-be6a-160ae46b3552-CD', 'sendRsync', 'rsync://$P_IVA$@$URL_RSYNC$/$P_IVA$', 'Dati per il collegamento Rsync verso MD'),
('e676fd0f-2f1a-4cc3-a82d-ea1aeef212c0-CDS', '6658fa5f-6c92-4fc2-9b13-f54ff4c3fc78-CD', 'nodo', '$NODO_MASTER$', 'Nodo di Riferimento Master'),
('eb4b80cd-f3b5-4332-aaba-6ba4a7fbe221-CDS', '5fa74374-9846-4c4a-be6a-160ae46b3552-CD', 'extFilesUpload', 'tar,tar.gz,tgz,warc,warc.gz,mrc', 'Viene indicato la lista delle estensioni files accettati per Upload'),
('f116af1c-b831-4729-8a96-6b0b3e9cf6e1-CDS', '6658fa5f-6c92-4fc2-9b13-f54ff4c3fc78-CD', 'istituzioneMD.id', '$ISTITUTO_ID$', 'Identificativo Istituzione MD per l\'autenticazione'),
('f96ec3a6-1575-4d93-b86e-006107a52224-CDS', '6658fa5f-6c92-4fc2-9b13-f54ff4c3fc78-CD', 'ticket.period', '60', 'Periodo di validazione di un Ticket in Minuti'),
('fa9cbb4a-b8db-4bd1-8269-1288b5ac89ed-CDS', 'e575c31e-ecea-488b-84ee-b8a0b5e45055-CD', 'url.homeGestionale', '$URL_GESTIONALE$/MagazziniDigitaliGestionale/', 'Indica l\'URL della Home Page del Gestionale'),
('faec760b-4458-4e45-833d-d17c38545ae2-CDS', '6658fa5f-6c92-4fc2-9b13-f54ff4c3fc78-CD', 'solr.clientTimeOut', '100000', 'TimeOut del client verso il server Solr'),
('fd6cc741-6265-49a8-bd1d-3a00c5c870b4-CDS', '5fa74374-9846-4c4a-be6a-160ae46b3552-CD', 'removeSource', 'false', 'Nel caso in cui il l\'archivio locale e l\'archivio dell\'area Temporanea di MD coincidono è possibile disattivare l\'attività di trasferimento del materiale mettendo il parametro a false'),
('fe8c0e32-5425-4760-8fb8-b0b43336a57a-CDS', '877b26ba-1a20-4079-97d9-8e05f4b542d0-CD', 'docker.ip', '192.168.254.5', 'Indirizzo IP del server Docker');
