INSERT INTO user_roles (id,type) VALUES (1,'ROOT'),(2,'ADMIN'),(3,'USER');

INSERT INTO users (id,username,email,full_name,password) VALUES
(1,'aegon','aegon.targaryen@example.com','Aegon Targaryen','aaaaaa'),
(2,'tywin','tywin.lannister@example.com','Tywin Lannister','aaaaaa'),
(3,'ned','ned.stark@example.com','Eddard Stark','aaaaaa'),
(4,'arya','arya.stark@example.com','Arya Stark','aaaaaa'),
(5,'jon','jon.snow@example.com','Jon Snow','aaaaaa'),
(6,'daenerys','daenerys.targaryen@example.com','Daenerys Targaryen','aaaaaa'),
(7,'bran','bran.stark@example.com','Bran Stark','aaaaaa'),
(8,'sansa','sansa.stark@example.com','Sansa Stark','aaaaaa'),
(9,'cersei','cersei.lannister@example.com','Cersei Lannister','aaaaaa'),
(10,'jaime','jaime.lannister@example.com','Jaime Lannister','aaaaaa'),
(11,'samwell','samwell.tarly@example.com','Samwell Tarly','aaaaaa'),
(12,'brienne','brienne.tarth@example.com','Brienne of Tarth','aaaaaa'),
(13,'sandor','sandor.clegane@example.com','Sandor Clegane','aaaaaa'),
(14,'jorah','jorah.mormont@example.com','Jorah Mormont','aaaaaa'),
(15,'theon','theon.greyjoy@example.com','Theon Greyjoy','aaaaaa'),
(16,'yara','yara.greyjoy@example.com','Yara Greyjoy','aaaaaa'),
(17,'melisandre','melisandre@example.com','Melisandre','aaaaaa'),
(18,'varys','varys@example.com','Varys','aaaaaa'),
(19,'drogo','khal.drogo@example.com','Khal Drogo','aaaaaa'),
(20,'missandei','missandei@example.com','Missandei','aaaaaa');

INSERT INTO users_roles (user_id,role_id) VALUES
(1,1),(2,2),(3,2),(4,3),(5,3),(6,3),(7,3),(8,3),(9,3),(10,3),
(11,3),(12,3),(13,3),(14,3),(15,3),(16,3),(17,3),(18,3),(19,3),(20,3);

INSERT INTO users (id,username,email,full_name,password) VALUES
(21,'robert','robert.baratheon@example.com','Robert Baratheon','aaaaaa'),
(22,'stannis','stannis.baratheon@example.com','Stannis Baratheon','aaaaaa'),
(23,'renly','renly.baratheon@example.com','Renly Baratheon','aaaaaa'),
(24,'joffrey','joffrey.baratheon@example.com','Joffrey Baratheon','aaaaaa'),
(25,'tyrion','tyrion.lannister@example.com','Tyrion Lannister','aaaaaa'),
(26,'catelyn','catelyn.tully@example.com','Catelyn Stark','aaaaaa'),
(27,'rob','rob.stark@example.com','Robb Stark','aaaaaa'),
(28,'rickon','rickon.stark@example.com','Rickon Stark','aaaaaa'),
(29,'eddard','eddard.stark@example.com','Eddard Stark','aaaaaa'),
(30,'lyanna','lyanna.stark@example.com','Lyanna Stark','aaaaaa'),
(31,'marillion','marillion.tarth@example.com','Marillion Tarth','aaaaaa'),
(32,'doran','doran.martell@example.com','Doran Martell','aaaaaa'),
(33,'oberyn','oberyn.martell@example.com','Oberyn Martell','aaaaaa'),
(34,'aresa','aresa.martell@example.com','Aresa Martell','aaaaaa'),
(35,'aron','aron.greyjoy@example.com','Aron Greyjoy','aaaaaa'),
(36,'aeron','aeron.greyjoy@example.com','Aeron Greyjoy','aaaaaa'),
(37,'theon2','theon2.greyjoy@example.com','Theon Greyjoy II','aaaaaa'),
(38,'yara2','yara2.greyjoy@example.com','Yara Greyjoy II','aaaaaa'),
(39,'garlan','garlan.tyrell@example.com','Garlan Tyrell','aaaaaa'),
(40,'margaery','margaery.tyrell@example.com','Margaery Tyrell','aaaaaa');

INSERT INTO users_roles (user_id,role_id) VALUES
(21,3),(22,3),(23,3),(24,3),(25,3),(26,3),(27,3),(28,3),(29,3),(30,3),
(31,3),(32,3),(33,3),(34,3),(35,3),(36,3),(37,3),(38,3),(39,3),(40,3);

INSERT INTO users (id,username,email,full_name,password) VALUES
(41,'loras','loras.tyrell@example.com','Loras Tyrell','aaaaaa'),
(42,'meera','meera.reed@example.com','Meera Reed','aaaaaa'),
(43,'wylla','wylla.reed@example.com','Wylla Reed','aaaaaa'),
(44,'howland','howland.reed@example.com','Howland Reed','aaaaaa'),
(45,'roose','roose.bolton@example.com','Roose Bolton','aaaaaa'),
(46,'ramsey','ramsey.bolton@example.com','Ramsey Bolton','aaaaaa'),
(47,'walder','walder.frey@example.com','Walder Frey','aaaaaa'),
(48,'lothar','lothar.frey@example.com','Lothar Frey','aaaaaa'),
(49,'rhaegar','rhaegar.targaryen@example.com','Rhaegar Targaryen','aaaaaa'),
(50,'viserys','viserys.targaryen@example.com','Viserys Targaryen','aaaaaa'),
(51,'jon2','jon2.targaryen@example.com','Jon Targaryen II','aaaaaa'),
(52,'aegon2','aegon2.targaryen@example.com','Aegon Targaryen II','aaaaaa'),
(53,'salladhor','salladhor.saan@example.com','Salladhor Saan','aaaaaa'),
(54,'beric','beric.dondarrion@example.com','Beric Dondarrion','aaaaaa'),
(55,'thoros','thoros.dondarrion@example.com','Thoros Dondarrion','aaaaaa'),
(56,'davos','davos.seaworth@example.com','Davos Seaworth','aaaaaa'),
(57,'bronn','bronn@example.com','Bronn','aaaaaa'),
(58,'gilly','gilly@example.com','Gilly','aaaaaa'),
(59,'sam2','sam2.tarly@example.com','Samwell Tarly II','aaaaaa'),
(60,'shae','shae@example.com','Shae','aaaaaa');

INSERT INTO users_roles (user_id,role_id) VALUES
(41,3),(42,3),(43,3),(44,3),(45,3),(46,3),(47,3),(48,3),(49,3),(50,3),
(51,3),(52,3),(53,3),(54,3),(55,3),(56,3),(57,3),(58,3),(59,3),(60,3);

INSERT INTO users (id,username,email,full_name,password) VALUES
(61,'renly2','renly2.baratheon@example.com','Renly Baratheon II','aaaaaa'),
(62,'ellaria','ellaria.sand@example.com','Ellaria Sand','aaaaaa'),
(63,'tyene','tyene.sand@example.com','Tyene Sand','aaaaaa'),
(64,'obara','obara.sand@example.com','Obara Sand','aaaaaa'),
(65,'nymeria','nymeria.sand@example.com','Nymeria Sand','aaaaaa'),
(66,'jaqen','jaqen.hghar@example.com','Jaqen Hghar','aaaaaa'),
(67,'arya2','arya2.stark@example.com','Arya Stark II','aaaaaa'),
(68,'podrick','podrick.payne@example.com','Podrick Payne','aaaaaa'),
(69,'robin','robin.arryn@example.com','Robin Arryn','aaaaaa'),
(70,'lysa','lysa.arryn@example.com','Lysa Arryn','aaaaaa'),
(71,'marillion2','marillion2.tarth@example.com','Marillion Tarth II','aaaaaa'),
(72,'doran2','doran2.martell@example.com','Doran Martell II','aaaaaa'),
(73,'oberyn2','oberyn2.martell@example.com','Oberyn Martell II','aaaaaa'),
(74,'aresa2','aresa2.martell@example.com','Aresa Martell II','aaaaaa'),
(75,'aron2','aron2.greyjoy@example.com','Aron Greyjoy II','aaaaaa'),
(76,'aeron2','aeron2.greyjoy@example.com','Aeron Greyjoy II','aaaaaa'),
(77,'garlan2','garlan2.tyrell@example.com','Garlan Tyrell II','aaaaaa'),
(78,'margaery2','margaery2.tyrell@example.com','Margaery Tyrell II','aaaaaa'),
(79,'loras2','loras2.tyrell@example.com','Loras Tyrell II','aaaaaa'),
(80,'meera2','meera2.reed@example.com','Meera Reed II','aaaaaa');

INSERT INTO users_roles (user_id,role_id) VALUES
(61,3),(62,3),(63,3),(64,3),(65,3),(66,3),(67,3),(68,3),(69,3),(70,3),
(71,3),(72,3),(73,3),(74,3),(75,3),(76,3),(77,3),(78,3),(79,3),(80,3);

INSERT INTO users (id,username,email,full_name,password) VALUES
(81,'wylla2','wylla2.reed@example.com','Wylla Reed II','aaaaaa'),
(82,'howland2','howland2.reed@example.com','Howland Reed II','aaaaaa'),
(83,'roose2','roose2.bolton@example.com','Roose Bolton II','aaaaaa'),
(84,'ramsey2','ramsey2.bolton@example.com','Ramsey Bolton II','aaaaaa'),
(85,'walder2','walder2.frey@example.com','Walder Frey II','aaaaaa'),
(86,'lothar2','lothar2.frey@example.com','Lothar Frey II','aaaaaa'),
(87,'rhaegar2','rhaegar2.targaryen@example.com','Rhaegar Targaryen II','aaaaaa'),
(88,'viserys2','viserys2.targaryen@example.com','Viserys Targaryen II','aaaaaa'),
(89,'jon3','jon3.targaryen@example.com','Jon Targaryen III','aaaaaa'),
(90,'aegon3','aegon3.targaryen@example.com','Aegon Targaryen III','aaaaaa'),
(91,'salladhor2','salladhor2.saan@example.com','Salladhor Saan II','aaaaaa'),
(92,'beric2','beric2.dondarrion@example.com','Beric Dondarrion II','aaaaaa'),
(93,'thoros2','thoros2.dondarrion@example.com','Thoros Dondarrion II','aaaaaa'),
(94,'davos2','davos2.seaworth@example.com','Davos Seaworth II','aaaaaa'),
(95,'bronn2','bronn2@example.com','Bronn II','aaaaaa'),
(96,'gilly2','gilly2@example.com','Gilly II','aaaaaa'),
(97,'sam3','sam3.tarly@example.com','Samwell Tarly III','aaaaaa'),
(98,'shae2','shae2@example.com','Shae II','aaaaaa'),
(99,'ellaria2','ellaria2.sand@example.com','Ellaria Sand II','aaaaaa'),
(100,'tyene2','tyene2.sand@example.com','Tyene Sand II','aaaaaa');

INSERT INTO users_roles (user_id,role_id) VALUES
(81,3),(82,3),(83,3),(84,3),(85,3),(86,3),(87,3),(88,3),(89,3),(90,3),
(91,3),(92,3),(93,3),(94,3),(95,3),(96,3),(97,3),(98,3),(99,3),(100,3);