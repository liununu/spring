insert into projects (id, name, start_date, end_date)
values ('eae57244-99e6-4298-b3b5-c6ae20e701be', 'Blick-Gleichner', '2022-02-28', '2023-11-30'),
       ('9ef8bea3-2af5-45cb-9dbe-6cb22dacc7e0', 'Parker, Rohan and Langworth', '2022-12-04', '2023-08-12'),
       ('00c7238f-6d8a-47b0-82b8-55ca4ad0d83c', 'Sauer Inc', '2022-11-02', '2023-08-04'),
       ('b8c096d5-a87d-4a5c-81fa-001068e30e08', 'Cronin-Yundt', '2022-05-21', '2023-08-12'),
       ('6cfbad1b-fd20-43d5-99de-66ac7d221055', 'Deckow Group', '2022-10-10', '2023-08-04'),
       ('8f0fd6c6-4a56-464f-9605-7a9ca34dd1bf', 'Ward, Pouros and Grant', '2023-06-25', '2022-08-14'),
       ('e176b30c-938f-4915-8850-225806846886', 'Keeling-Kulas', '2023-02-21', '2023-09-19'),
       ('5b546e1b-4288-4e12-8099-e005766ea9a9', 'Farrell, Thompson and Cronin', '2022-03-29', '2023-09-14'),
       ('2f110297-cb8d-426a-a9c6-67d5d826f377', 'Howell, Cummings and McLaughlin', '2022-07-31', '2022-11-18'),
       ('d18328d1-8e48-4ffc-92eb-76a63ff8ccd6', 'Weber-Rutherford', '2023-02-25', '2023-09-22'),
       ('ccf9073b-63fb-459c-870f-c3131f634c32', 'Lowe-Hayes', '2022-05-30', null),
       ('97c3093b-5432-4415-bb94-fee7e0ec87d2', 'Haag, Erdman and Carroll', '2022-12-27', '2023-10-27'),
       ('0f89d88a-977a-417a-8e58-8d4330fda092', 'Reinger-Cremin', '2022-01-16', '2022-12-30'),
       ('a17d1fd8-0403-443c-a5c9-e44a66e5565c', 'Tromp, Renner and Howell', '2022-03-09', '2023-11-20'),
       ('250b5be9-b79a-48a3-9ef5-0e3978f94256', 'Hills-Reichert', '2022-07-01', '2023-12-07'),
       ('4f7b17bf-26c3-4e0c-8f92-5c78b49f1f2a', 'Farrell, Thompson and Cronin', '2022-10-29', null),
       ('a8dbd263-aede-4cbe-8c81-02dbe81f87ad', 'Simonis Group', '2022-08-17', '2023-02-10'),
       ('7c698677-32a6-48aa-8f73-e28fe1716ba4', 'Heaney, Lubowitz and Bernhard', '2022-07-05', null),
       ('96bc8719-92b0-41d2-8f95-dbe85912b0bc', 'Ryan and Sons', '2022-03-14', null),
       ('8de5fb63-635c-45c1-a4af-8be589b98909', 'Hodkiewicz-Jacobi', '2022-06-11', null);

insert into employees (id, name, email, project_name, active)
values ('539a1e00-60b4-4c5f-800d-9295db614dee', 'Fanchette McCard', 'fmccard0@globo.com',
        'Farrell, Thompson and Cronin', true),
       ('0f246f5a-5a42-4644-9d56-0fd43a16a65d', 'Francesca Allain', 'fallain1@cmu.edu', null, false),
       ('5a4d25ee-5d67-4389-b770-198aa581e203', 'Christel Shelmardine', 'cshelmardine2@linkedin.com',
        'Ritchie-Mohr', true),
       ('5c0677d1-75b1-4a03-8a0e-5de8fc29fbfc', 'Rhett Goulbourn', 'rgoulbourn3@paginegialle.it',
        'Farrell, Thompson and Cronin', true),
       ('0bfd2639-26ed-4e50-ac0e-2c9257af778d', 'Maximo Ovise', 'movise4@google.it',
        'Cronin-Yundt', true),
       ('ad67ba77-8769-471d-8960-089128b4366c', 'Alejoa Sousa', 'asousa5@cloudflare.com',
        'Farrell, Thompson and Cronin', true),
       ('644caa9a-3d56-440c-940b-1a3c1ca8950c', 'Lloyd Tie', 'ltie6@artisteer.com', null, false),
       ('d178fc9a-c261-4dae-9160-4035dd05378b', 'Alexander Lockton', 'alockton7@chronoengine.com',
        'Ritchie-Mohr', true),
       ('34460892-edf7-4b21-ac8c-86b15c33de44', 'Yankee Ferneyhough', 'yferneyhough8@intel.com', null, false),
       ('4ee9b092-3241-46d7-aadb-a49fea34cdb5', 'Emmalynn Haggleton', 'ehaggleton9@cdbaby.com',
        'Ritchie-Mohr', true),
       ('7496a049-fe14-424c-8e9e-7973cf47732a', 'Odelia Yakobowitz', 'oyakobowitza@blogtalkradio.com',
        'Blick-Gleichner', true),
       ('d7e173e6-883d-4268-b4cb-7d8dde6d52b8', 'Elenore Dimitrescu', 'edimitrescub@ted.com',
        'Ritchie-Mohr', true),
       ('46269506-66cb-4843-b458-395543e2efec', 'Wadsworth Yesson', 'wyessonc@berkeley.edu', null, false),
       ('05b04418-a3b9-4248-b953-5493ec6b4f5a', 'See McTiernan', 'smctiernand@issuu.com',
        'Farrell, Thompson and Cronin', true),
       ('38ea6473-1be3-48d6-a1d0-adceeaa49108', 'Hyacinth Milella', 'hmilellae@naver.com', null, false),
       ('16c64dcc-b96b-46d5-a6f7-eb138e622196', 'Abra Stoving', 'astovingf@odnoklassniki.ru', null, false),
       ('13783f86-5219-457b-89d0-00091e13af96', 'Milli Hambatch', 'mhambatchg@nytimes.com', null, false),
       ('2eee166e-3bc7-4ffc-b942-4a17883d8e81', 'Arline Mc Queen', 'amch@1und1.de', null, false),
       ('552fafb4-a5eb-4164-bf49-fef8a4a550f4', 'Imojean Poate', 'ipoatei@globo.com',
        'Blick-Gleichner', true),
       ('e957105e-d617-48d0-8613-5a9238f52fdb', 'Thaxter MacHostie', 'tmachostiej@google.it', null, false),
       ('d87cd61f-3530-46ef-9189-08dd169acd7e', 'Ephrayim Capener', 'ecapenerk@shareasale.com',
        'Ritchie-Mohr', true),
       ('dc1cac14-2536-44bc-94f1-be56a2b84ef6', 'Lucia Lahive', 'llahivel@buzzfeed.com', null, false),
       ('46472e92-5e01-4efb-a120-0cacf8830496', 'Storm Bread', 'sbreadm@stanford.edu',
        'Cronin-Yundt', true),
       ('905cba18-9ee3-44a5-8517-c7dcb3ee61b7', 'Kirsteni Fiander', 'kfiandern@typepad.com',
        'Farrell, Thompson and Cronin', true),
       ('073c2936-2920-46b0-86bd-2c4169f96d7c', 'Muffin Hasted', 'mhastedo@a8.net', null, false),
       ('ffbbfa12-313e-4ce6-9f62-acc8f718c7e5', 'Pearce Rutt', 'pruttp@wikispaces.com',
        'Farrell, Thompson and Cronin', true),
       ('56413d88-31a3-400f-874d-953181a27054', 'Royce Wycliffe', 'rwycliffeq@upenn.edu', null, false),
       ('f697eed6-0426-4c0d-b4ef-ae56a9aa7bb4', 'Kathe Scourgie', 'kscourgier@cyberchimps.com',
        'Cronin-Yundt', true),
       ('9578eaf1-2db9-4b9c-bbeb-7c07e7a2234f', 'Lynnelle Iapico', 'liapicos@hugedomains.com',
        'Blick-Gleichner', true),
       ('5841cf7e-4c9f-424c-b401-b21a56b691e1', 'Bessy Cockroft', 'bcockroftt@slate.com',
        'Cronin-Yundt', true),
       ('aeada9de-89ca-4cc3-8271-a62b9994d7c5', 'Roderic Burdon', 'rburdonu@forbes.com', null, false),
       ('79213bd4-9632-41b6-bde2-4f3d47e45cf8', 'Gannie Rawls', 'grawlsv@nymag.com',
        'Ritchie-Mohr', true),
       ('87df6997-5fe6-4d37-bfeb-edf326a96bc6', 'Jerrie Carlett', 'jcarlettw@xrea.com', null, false),
       ('fb56dff1-84aa-45a9-b381-f9b16f56e8dc', 'Rene Clarycott', 'rclarycottx@cbc.ca', null, false),
       ('3a84f983-aa65-461d-82b6-90bcd1b42286', 'Ulises Lufkin', 'ulufkiny@ucsd.edu',
        'Farrell, Thompson and Cronin', true),
       ('9093efc0-0e5e-4928-8b55-73dca0347c41', 'Newton Stook', 'nstookz@newsvine.com', null, false),
       ('44965fc3-389b-49f2-bc70-ec2783a2936e', 'Charissa Wilshaw', 'cwilshaw10@hubpages.com',
        'Farrell, Thompson and Cronin', true),
       ('5cbf4a18-6942-49db-9815-b5c307db85e3', 'Garnet Shinfield', 'gshinfield11@exblog.jp', null, false),
       ('aea94790-51df-42da-a5a0-ca2b3a00dbf5', 'Jacquie Jobling', 'jjobling12@amazon.co.uk',
        'Blick-Gleichner', true),
       ('7b8da5c8-1439-424b-80a4-9dfb377cd485', 'Burt Titterton', 'btitterton13@blogs.com', null, false),
       ('956840d9-d48f-4886-b24f-0c77f401226a', 'Farra Antliff', 'fantliff14@google.cn',
        'Ritchie-Mohr', true),
       ('6637187a-43ab-4492-a0f9-0957a30fa61c', 'Martyn Marchington', 'mmarchington15@mapy.cz',
        'Blick-Gleichner', true),
       ('babd1069-f933-46d8-9a90-81f17e62b599', 'Brendis Buckoke', 'bbuckoke16@ed.gov',
        'Cronin-Yundt', true),
       ('7ba4e9b3-cea4-4070-8a20-ce273764e7fc', 'Patsy McCudden', 'pmccudden17@yellowbook.com',
        'Farrell, Thompson and Cronin', true),
       ('2e181ff1-7f64-4460-9010-ff45e81c74ee', 'Diego Idell', 'didell18@hubpages.com',
        'Farrell, Thompson and Cronin', true),
       ('342b0c17-69dc-41ae-b928-7fa67e08e63e', 'Ammamaria Peaddie', 'apeaddie19@netscape.com',
        'Blick-Gleichner', true),
       ('f338a2b8-6b6f-4f97-8bf5-11e2cefbbcba', 'Gabie Dodridge', 'gdodridge1a@shutterfly.com',
        'Ritchie-Mohr', true),
       ('7fc1bbb4-58d7-477a-bea9-273ca2992652', 'Garland Ivanchov', 'givanchov1b@washington.edu',
        'Farrell, Thompson and Cronin', true),
       ('e7016bff-89be-44fe-b5bb-e14977356a5c', 'Conan Seson', 'cseson1c@altervista.org', null, false),
       ('6b5b0a82-76ae-4a6b-b2b8-f1db13ac7c89', 'Reese Evason', 'revason1d@si.edu', null, false);


