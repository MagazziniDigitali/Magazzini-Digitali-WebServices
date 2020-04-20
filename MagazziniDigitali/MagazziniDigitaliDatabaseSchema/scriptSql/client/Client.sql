
INSERT INTO `MDIstituzione` (`ID`, `LOGIN`, `PASSWORD`, `IP_AUTORIZZATI`, `NOME`, `INDIRIZZO`, `TELEFONO`, `NOME_CONTATTO`, `BIBLIOTECA_DEPOSITARIA`, `ISTITUTO_CENTRALE`, `IP_ACCREDITATI`, `INTERFACCIA_API_UTENTE`, `LIBRERIA_API_UTENTE`, `EMAIL_BAGIT`, `PATH_TMP`, `NOTE`, `URL`, `ID_REGIONE`, `PIVA`, `ALTA_RISOLUZIONE`, `BAGIT`) VALUES
('fed1dc85-2ed9-4c0d-8d69-508b0ef5097c-AG', 'MDC', '280d44ab1e9f79b5cce2dd4f58f5fe91f0fbacdac9f7447dffc318ceb79f2d02', '', 'Magazzini Digitali - depositante', NULL, NULL, NULL, 0, 0, NULL, NULL, NULL, NULL, '/mnt/areaTemporanea/00000000000', NULL, NULL, 16, '00000000000', 0, 0);
                                                    

INSERT INTO `MDSoftware` (`ID`, `NOME`, `LOGIN`, `PASSWORD`, `IP_AUTORIZZATI`, `BIBLIOTECA_DEPOSITARIA`, `ID_ISTITUZIONE`, `ID_RIGTHS`, `NOTE`) VALUES
('0b971fc8-df02-4e6a-9ec1-97162b10d286-AG', 'Client di Test MD', 'TD_MD', '280d44ab1e9f79b5cce2dd4f58f5fe91f0fbacdac9f7447dffc318ceb79f2d02', '127.0.0.1', 0, 'fed1dc85-2ed9-4c0d-8d69-508b0ef5097c-AG', 'a562f969-b81a-4fc2-80ae-9367498271b2', '');

INSERT INTO `MDSoftwareConfig` (`ID`, `ID_SOFTWARE`, `NOME`, `DESCRIZIONE`, `VALUE`, `ID_NODO`) VALUES
(uuid(), '0b971fc8-df02-4e6a-9ec1-97162b10d286-AG', 'wsdlCheckMD', 'Chiamata Wsdl per verificare lo stato dell\'oggetto', 'http://md.svil.bncf.lan:8080/MagazziniDigitaliServices/services/CheckMDPort?wsdl', NULL),
(uuid(), '0b971fc8-df02-4e6a-9ec1-97162b10d286-AG', 'wsdlInitSendMD', 'Chiamata Wsdl per indicare l\'inizio delle operazioni di Trasmissioni dell\'oggetto', 'http://md.svil.bncf.lan:8080/MagazziniDigitaliServices/services/InitSendMDPort?wsdl', NULL),
(uuid(), '0b971fc8-df02-4e6a-9ec1-97162b10d286-AG', 'wsdlEndSendMD', 'Chiamata Wsdl per indicare la fine delle operazioni di Trasmissioni dell\'oggetto', 'http://md.svil.bncf.lan:8080/MagazziniDigitaliServices/services/EndSendMDPort?wsdl', NULL),
(uuid(), '0b971fc8-df02-4e6a-9ec1-97162b10d286-AG', 'wsdlConfirmDelMD', 'Chiamata Wsdl per indicare l\'avvenuta cancellazione del file dall\'area temporanea dell\'istituto', 'http://md.svil.bncf.lan:8080/MagazziniDigitaliServices/services/ConfirmDelMDPort?wsdl', NULL),
(uuid(), '0b971fc8-df02-4e6a-9ec1-97162b10d286-AG', 'sendRsync', 'Dati per il collegamento Rsync verso MD', 'rsync://00000000000@md.svil.bncf.lan/00000000000', NULL),
(uuid(), '0b971fc8-df02-4e6a-9ec1-97162b10d286-AG', 'sendRsyncPwd', 'Password per il collegamento Rsync', 'welcome', NULL),
(uuid(), '0b971fc8-df02-4e6a-9ec1-97162b10d286-AG', 'extFilesUpload', 'Viene indicato la lista delle estensioni files accettati per Upload', 'tar,tar.gz,tgz,warc,warc.gz,mrc', NULL),
(uuid(), '0b971fc8-df02-4e6a-9ec1-97162b10d286-AG', 'removeSource', 'Nel caso in cui il l\'archivio locale e l\'archivio dell\'area Temporanea di MD coincidono è possibile disattivare l\'attività di strasferimento del materiale mettendo il parametro a false', 'true', NULL);
