/****** Object:  Database [libraryMS] ******/
CREATE DATABASE [libraryMS]

USE [libraryMS]
GO

/****** Object:  Table [dbo].[Country]  ******/

CREATE TABLE roli(
[Roli_ID] int IDENTITY(1,1)not null,
[Emertimi] varchar(max)not null,
PRIMARY KEY ([Roli_ID]),
CONSTRAINT [Roli_ID] UNIQUE  ([Roli_ID])
)

INSERT into [Roli] ([Emertimi]) VALUES (N'Admini')
INSERT into [Roli] ([Emertimi]) VALUES (N'Useri')

CREATE TABLE [Perdoruesit] (
  [Perdoruesit_ID] int NOT NULL IDENTITY(1,1),
  [Emri] varchar(max) NOT NULL,
  [Mbiemri] varchar(max) NOT NULL,
  [Email] varchar(max) NOT NULL,
  [Password] varchar(max) NOT NULL,
  [Roli_ID]int not null,
  [Foto]varchar(max),
  [Online]bit not null,
  PRIMARY KEY ([Perdoruesit_ID]),
  CONSTRAINT [Perdoruesit_ID] UNIQUE  ([Perdoruesit_ID]),
  CONSTRAINT [perdoruesit_chk_1] CHECK (([Email] like '%@%.%')),
  CONSTRAINT [perderuesit_ibfk_1] FOREIGN KEY ([Roli_ID]) REFERENCES roli ([Roli_ID])
) 

CREATE INDEX [Roli_ID] ON Perdoruesit ([Roli_ID]); 

INSERT INTO Perdoruesit ( [Emri], [Mbiemri], [Email], [Password],[Roli_ID],[Online]) VALUES
('Elon',	'Musk',	'ax@gmail.com',	'pw',1,0);
INSERT INTO Perdoruesit ( [Emri], [Mbiemri], [Email], [Password],[Roli_ID],[Online]) VALUES
('Asdren',	'Xhafa',	'asd@gmail.com',	'password',2,0);





CREATE TABLE Klientet (
  [Klientet_ID] int NOT NULL IDENTITY(1,1),
  [Emri] varchar(max) NOT NULL,
  [Mbiemri] varchar(max) NOT NULL,
  [Email] varchar(max) NOT NULL,
  [NumriTel] varchar(max) NOT NULL,
  [Aktiv] bit not null,
  PRIMARY KEY ([Klientet_ID]),
  CONSTRAINT [Klientet_ID] UNIQUE  ([Klientet_ID]),
  CONSTRAINT [Klientet_chk_1] CHECK (([Email] like '%@%.%'))
) 

INSERT INTO Klientet ( [Emri], [Mbiemri], [Email], [NumriTel],[Aktiv]) VALUES ('Asdren',	'Xhafa',	'asdrenxhafa@gmail.com',	'014123123','0');
INSERT INTO Klientet ( [Emri], [Mbiemri], [Email], [NumriTel],[Aktiv]) VALUES ('Emma',	'Sith',	'emma@gmail.com',	'024223123','0');
INSERT INTO Klientet ( [Emri], [Mbiemri], [Email], [NumriTel],[Aktiv]) VALUES ('Liam',	'Hall',	'liam@gmail.com',	'034323123','0');
INSERT INTO Klientet ( [Emri], [Mbiemri], [Email], [NumriTel],[Aktiv]) VALUES ('Noah',	'Johnson',	'noah@gmail.com',	'044423123','0');
INSERT INTO Klientet ( [Emri], [Mbiemri], [Email], [NumriTel],[Aktiv]) VALUES ('Olivia',	'Stewart',	'olivia@gmail.com',	'054523123','0');
INSERT INTO Klientet ( [Emri], [Mbiemri], [Email], [NumriTel],[Aktiv]) VALUES ('Ava',	'Jones',	'ava@gmail.com',	'064623123','0');
INSERT INTO Klientet ( [Emri], [Mbiemri], [Email], [NumriTel],[Aktiv]) VALUES ('Sofia',	'Brown',	'sofia@gmail.com',	'074723123','0');
INSERT INTO Klientet ( [Emri], [Mbiemri], [Email], [NumriTel],[Aktiv]) VALUES ('Charlote',	'Davis',	'charlote@gmail.com',	'084823123','0');
INSERT INTO Klientet ( [Emri], [Mbiemri], [Email], [NumriTel],[Aktiv]) VALUES ('Oliver',	'Miller ',	'oliver@gmail.com',	'094923123','0');
INSERT INTO Klientet ( [Emri], [Mbiemri], [Email], [NumriTel],[Aktiv]) VALUES ('Benjamin',	'Wilson',	'benjamin@gmail.com',	'041113123','0');
INSERT INTO Klientet ( [Emri], [Mbiemri], [Email], [NumriTel],[Aktiv]) VALUES ('Lucas',	'Moore',	'lucas@gmail.com',	'042133123','0');
INSERT INTO Klientet ( [Emri], [Mbiemri], [Email], [NumriTel],[Aktiv]) VALUES ('Mason',	'Taylor',	'mason@gmail.com',	'043143123','0');
INSERT INTO Klientet ( [Emri], [Mbiemri], [Email], [NumriTel],[Aktiv]) VALUES ('Logan',	'Anderson',	'logan@gmail.com',	'044153123','0');
INSERT INTO Klientet ( [Emri], [Mbiemri], [Email], [NumriTel],[Aktiv]) VALUES ('Alexander',	'Thomas',	'alexander@gmail.com',	'045163123','0');
INSERT INTO Klientet ( [Emri], [Mbiemri], [Email], [NumriTel],[Aktiv]) VALUES ('Emily',	'Jackson',	'emily@gmail.com',	'026173123','0');
INSERT INTO Klientet ( [Emri], [Mbiemri], [Email], [NumriTel],[Aktiv]) VALUES ('Abigail',	'White',	'abigail@gmail.com',	'027183123','0');
INSERT INTO Klientet ( [Emri], [Mbiemri], [Email], [NumriTel],[Aktiv]) VALUES ('Elizabeth',	'Harris',	'elizabeth@gmail.com',	'028193123','0');
INSERT INTO Klientet ( [Emri], [Mbiemri], [Email], [NumriTel],[Aktiv]) VALUES ('Mila',	'Martin',	'mila@gmail.com',	'021121123','0');
INSERT INTO Klientet ( [Emri], [Mbiemri], [Email], [NumriTel],[Aktiv]) VALUES ('Daniel',	'Thomson',	'daniel@gmail.com',	'022122123','0');
INSERT INTO Klientet ( [Emri], [Mbiemri], [Email], [NumriTel],[Aktiv]) VALUES ('Henry',	'Garcia',	'henry@gmail.com',	'023124123','0');
INSERT INTO Klientet ( [Emri], [Mbiemri], [Email], [NumriTel],[Aktiv]) VALUES ('Jackson',	'Martinez',	'jackson@gmail.com',	'024125123','0');
INSERT INTO Klientet ( [Emri], [Mbiemri], [Email], [NumriTel],[Aktiv]) VALUES ('Sebastian',	'Lewis',	'sebastian@gmail.com',	'025126123','0');
INSERT INTO Klientet ( [Emri], [Mbiemri], [Email], [NumriTel],[Aktiv]) VALUES ('Aiden',	'Lee',	'aiden@gmail.com',	'026127123','0');
INSERT INTO Klientet ( [Emri], [Mbiemri], [Email], [NumriTel],[Aktiv]) VALUES ('Matthew',	'Walker',	'matthew@gmail.com',	'027128123','0');
INSERT INTO Klientet ( [Emri], [Mbiemri], [Email], [NumriTel],[Aktiv]) VALUES ('Samuel',	'Young',	'samuel@gmail.com',	'028129123','0');
INSERT INTO Klientet ( [Emri], [Mbiemri], [Email], [NumriTel],[Aktiv]) VALUES ('David',	'King',	'david@gmail.com',	'029123223','0');
INSERT INTO Klientet ( [Emri], [Mbiemri], [Email], [NumriTel],[Aktiv]) VALUES ('Luna',	'Lopez',	'luna@gmail.com',	'031123323','0');


CREATE TABLE rafti (
  [Rafti_ID] int NOT NULL IDENTITY(1,1),
  [Rreshti] Integer NOT NULL,
  PRIMARY KEY ([Rafti_ID]),
  CONSTRAINT [Rafti_ID] UNIQUE  ([Rafti_ID])
) 

insert into rafti (Rreshti) values (1);
insert into rafti (Rreshti) values (2);
insert into rafti (Rreshti) values (3);
insert into rafti (Rreshti) values (4);


CREATE TABLE librat (
  [Librat_ID] int NOT NULL IDENTITY(1,1),
  [Titulli] varchar(max) NOT NULL,
  [Rafti_ID] int NOT NULL,
  PRIMARY KEY ([Librat_ID]),
  [ELire] bit not null,
  CONSTRAINT [Librat_ID] UNIQUE  ([librat_ID])
 ,
  CONSTRAINT [librat_ibfk_1] FOREIGN KEY ([Rafti_ID]) REFERENCES rafti ([Rafti_ID])
)

CREATE INDEX [Rafti_ID] ON librat ([Rafti_ID]); 

INSERT INTO librat ( [Titulli], [Rafti_ID],[ELire]) VALUES (	'Keshtjella',	1,1);
INSERT INTO librat ( [Titulli], [Rafti_ID],[ELire]) VALUES (	'Tatuisit i Aushvicit',	1,1);
INSERT INTO librat ( [Titulli], [Rafti_ID],[ELire]) VALUES (	'Shtate Motrat',	1,1);
INSERT INTO librat ( [Titulli], [Rafti_ID],[ELire]) VALUES (	'Presidenti eshte zhdukur',	1,1);
INSERT INTO librat ( [Titulli], [Rafti_ID],[ELire]) VALUES (	'Lulet e Veres',	1,1);
INSERT INTO librat ( [Titulli], [Rafti_ID],[ELire]) VALUES (	'Guret e Vetmise',	1,1);
INSERT INTO librat ( [Titulli], [Rafti_ID],[ELire]) VALUES (	'I huaji',	1,1);
INSERT INTO librat ( [Titulli], [Rafti_ID],[ELire]) VALUES (	'Krim dhe Ndeshkim',	1,1);
INSERT INTO librat ( [Titulli], [Rafti_ID],[ELire]) VALUES (	'Nen uje',	1,1);
INSERT INTO librat ( [Titulli], [Rafti_ID],[ELire]) VALUES (	'Rojtari i Padukshem',	1,1);
INSERT INTO librat ( [Titulli], [Rafti_ID],[ELire]) VALUES (	'Gjenerali i Ushtrise',	2,1);
INSERT INTO librat ( [Titulli], [Rafti_ID],[ELire]) VALUES (	'Bostet e Tiranes',	2,1);
INSERT INTO librat ( [Titulli], [Rafti_ID],[ELire]) VALUES (	'Tri bijat e Eves',	2,1);
INSERT INTO librat ( [Titulli], [Rafti_ID],[ELire]) VALUES (	'Arka e Djallit',	2,1);
INSERT INTO librat ( [Titulli], [Rafti_ID],[ELire]) VALUES (	'Fausti',	2,1);
INSERT INTO librat ( [Titulli], [Rafti_ID],[ELire]) VALUES (	'Historia e vajzes se humbur',	2,1);
INSERT INTO librat ( [Titulli], [Rafti_ID],[ELire]) VALUES (	'Gjyqi',	2,1);
INSERT INTO librat ( [Titulli], [Rafti_ID],[ELire]) VALUES (	'Momente jete',	2,1);
INSERT INTO librat ( [Titulli], [Rafti_ID],[ELire]) VALUES (	'Blu Safiri',	2,1);
INSERT INTO librat ( [Titulli], [Rafti_ID],[ELire]) VALUES (	'Dekorata',	3,1);
INSERT INTO librat ( [Titulli], [Rafti_ID],[ELire]) VALUES (	'Renia',	3,1);
INSERT INTO librat ( [Titulli], [Rafti_ID],[ELire]) VALUES (	'City',	3,1);
INSERT INTO librat ( [Titulli], [Rafti_ID],[ELire]) VALUES (	'Loja e Riperit',	3,1);
INSERT INTO librat ( [Titulli], [Rafti_ID],[ELire]) VALUES (	'Dita e mbetur',	3,1);
INSERT INTO librat ( [Titulli], [Rafti_ID],[ELire]) VALUES (	'Kamera e fshehte',	3,1);
INSERT INTO librat ( [Titulli], [Rafti_ID],[ELire]) VALUES (	'Funeral',	3,1);
INSERT INTO librat ( [Titulli], [Rafti_ID],[ELire]) VALUES (	'Bibollasit',	3,1);
INSERT INTO librat ( [Titulli], [Rafti_ID],[ELire]) VALUES (	'Njeqind vjet vetmi',	3,1);
INSERT INTO librat ( [Titulli], [Rafti_ID],[ELire]) VALUES (	'Te jetosh ne paqe',	3,1);
INSERT INTO librat ( [Titulli], [Rafti_ID],[ELire]) VALUES (	'Sekreti i nje nate vere',	3,1);
INSERT INTO librat ( [Titulli], [Rafti_ID],[ELire]) VALUES (	'Arbenita e zhdukur',	4,1);
INSERT INTO librat ( [Titulli], [Rafti_ID],[ELire]) VALUES (	'Lekura e Shagrenit',	4,1);
INSERT INTO librat ( [Titulli], [Rafti_ID],[ELire]) VALUES (	'Nje kange feste',	4,1);
INSERT INTO librat ( [Titulli], [Rafti_ID],[ELire]) VALUES (	'Toke e Premtuar',	4,1);
INSERT INTO librat ( [Titulli], [Rafti_ID],[ELire]) VALUES (	'Dashuri dhe pleqeri',	4,1);
INSERT INTO librat ( [Titulli], [Rafti_ID],[ELire]) VALUES (	'Zhurma e bardhe',	4,1);
INSERT INTO librat ( [Titulli], [Rafti_ID],[ELire]) VALUES (	'Xha gorio',	4,1);
INSERT INTO librat ( [Titulli], [Rafti_ID],[ELire]) VALUES (	'Kerol',	4,1);
INSERT INTO librat ( [Titulli], [Rafti_ID],[ELire]) VALUES (	'Me Befaso!',	4,1);
INSERT INTO librat ( [Titulli], [Rafti_ID],[ELire]) VALUES (	'Suite Franceze',	4,1);
INSERT INTO librat ( [Titulli], [Rafti_ID],[ELire]) VALUES (	'Zonja e funeraleve',	4,1);
INSERT INTO librat ( [Titulli], [Rafti_ID],[ELire]) VALUES (	'Vajza e letres',	4,1);
INSERT INTO librat ( [Titulli], [Rafti_ID],[ELire]) VALUES (	'Mall per ty',	4,1);
INSERT INTO librat ( [Titulli], [Rafti_ID],[ELire]) VALUES (	'Me bej te dua',	4,1);
INSERT INTO librat ( [Titulli], [Rafti_ID],[ELire]) VALUES (	'Misteri i trenit blue',	4,1);
INSERT INTO librat ( [Titulli], [Rafti_ID],[ELire]) VALUES (	'Motra tjeter',	4,1);
INSERT INTO librat ( [Titulli], [Rafti_ID],[ELire]) VALUES (	'Uni i tij i ndalur',	4,1);
INSERT INTO librat ( [Titulli], [Rafti_ID],[ELire]) VALUES (	'Libri i Baltimoreve',	4,1);
INSERT INTO librat ( [Titulli], [Rafti_ID],[ELire]) VALUES (	'Metamorfoza-Albas',	4,1);
INSERT INTO librat ( [Titulli], [Rafti_ID],[ELire]) VALUES (	'Marrja e gjakut',	4,1);
INSERT INTO librat ( [Titulli], [Rafti_ID],[ELire]) VALUES (	'Fabula Rasa',	4,1);
INSERT INTO librat ( [Titulli], [Rafti_ID],[ELire]) VALUES (	'Hipi',	4,1);
INSERT INTO librat ( [Titulli], [Rafti_ID],[ELire]) VALUES (	'Dielli i ulet',	4,1);



CREATE Table huazimet(
[Huazimet_ID] int NOT NULL IDENTITY(1,1),
  [DataMarrjes] date NOT NULL,
  [DataPritjes] date NOT NULL,
  [DataKthimit] date,
  [Klientet_ID] int NOT NULL,
  [Librat_ID] int NOT NULL,
  [Aktiv] bit not null,
  PRIMARY KEY ([Huazimet_ID]),
  CONSTRAINT [Huazimet_ID] UNIQUE  ([Huazimet_ID])
,
  CONSTRAINT [huazimet_ibfk_1] FOREIGN KEY ([Klientet_ID]) REFERENCES Klientet ([Klientet_ID]),
  CONSTRAINT [huazimet_ibfk_2] FOREIGN KEY ([Librat_ID]) REFERENCES librat ([Librat_ID])
)

CREATE INDEX [huazimet_ibfk_1] ON Klientet ([Klientet_ID]);
CREATE INDEX [huazimet_ibfk_2] ON librat ([Librat_ID]); 
 


CREATE TABLE pagesat(
[Pagesat_ID] int NOT NULL IDENTITY(1,1),
[DataPageses] date not null,
[DataSkadimit] date not null,
[ShumaPageses] int not null,
[Klientet_ID] int not null,
[Aktiv] bit not null,
PRIMARY KEY ([Pagesat_ID]),
  CONSTRAINT [Pagesat_ID] UNIQUE  ([Pagesat_ID])
,
CONSTRAINT [pagesat_ibfk_2] FOREIGN KEY ([Klientet_ID]) REFERENCES Klientet ([Klientet_ID])
)

CREATE INDEX [pagesat_ibfk_1] ON Klientet ([Klientet_ID]);


CREATE TABLE autoret (
  [Autoret_ID] int NOT NULL IDENTITY(1,1),
  [Emri] varchar(max) NOT NULL,
  [Mbiemri] varchar(max) NOT NULL,
  PRIMARY KEY ([Autoret_ID]),
  CONSTRAINT [Autoret_ID] UNIQUE  ([Autoret_ID])
) 

INSERT INTO autoret ( [Emri], [Mbiemri]) VALUES ('Ismail',	'Kadare');
INSERT INTO autoret ( [Emri], [Mbiemri]) VALUES ('Sigmund',	'Freud');
INSERT INTO autoret ( [Emri], [Mbiemri]) VALUES ('Pjeter',	'Budi');
INSERT INTO autoret ( [Emri], [Mbiemri]) VALUES ('Gjon',	'Buzuku');
INSERT INTO autoret ( [Emri], [Mbiemri]) VALUES ('Pjeter',	'Bogdani');
INSERT INTO autoret ( [Emri], [Mbiemri]) VALUES ('Nezim',	'Frakulla');
INSERT INTO autoret ( [Emri], [Mbiemri]) VALUES ('Lasgush',	'Poradeci');
INSERT INTO autoret ( [Emri], [Mbiemri]) VALUES ('Dritero',	'Agolli');
INSERT INTO autoret ( [Emri], [Mbiemri]) VALUES ('Gjergj',	'Fishta');
INSERT INTO autoret ( [Emri], [Mbiemri]) VALUES ('Ndre',	'Mjeda');
INSERT INTO autoret ( [Emri], [Mbiemri]) VALUES ('Fan',	'Noli');
INSERT INTO autoret ( [Emri], [Mbiemri]) VALUES ('Mitrush',	'Kuteli');
INSERT INTO autoret ( [Emri], [Mbiemri]) VALUES ('Ali',	'Podrimja');
INSERT INTO autoret ( [Emri], [Mbiemri]) VALUES ('Kasem',	'Trebeshina');
INSERT INTO autoret ( [Emri], [Mbiemri]) VALUES ('Martin',	'Camaj');
INSERT INTO autoret ( [Emri], [Mbiemri]) VALUES ('Guilio',	'Variboba');
INSERT INTO autoret ( [Emri], [Mbiemri]) VALUES ('Azem',	'Shkreli');
INSERT INTO autoret ( [Emri], [Mbiemri]) VALUES ('Filip',	'Shiroka');
INSERT INTO autoret ( [Emri], [Mbiemri]) VALUES ('Ernest',	'Koliqi');
INSERT INTO autoret ( [Emri], [Mbiemri]) VALUES ('Esad',	'Mekuli');
INSERT INTO autoret ( [Emri], [Mbiemri]) VALUES ('Arshi',	'Pipa');
INSERT INTO autoret ( [Emri], [Mbiemri]) VALUES ('Frang',	'Bardhi');
INSERT INTO autoret ( [Emri], [Mbiemri]) VALUES ('Vaso',	'Pasha');
INSERT INTO autoret ( [Emri], [Mbiemri]) VALUES ('Marlin',	'Barleti');
INSERT INTO autoret ( [Emri], [Mbiemri]) VALUES ('Fator',	'Arapi');
INSERT INTO autoret ( [Emri], [Mbiemri]) VALUES ('Drin',	'Mehmeti');
INSERT INTO autoret ( [Emri], [Mbiemri]) VALUES ('Petro',	'Marko');
INSERT INTO autoret ( [Emri], [Mbiemri]) VALUES ('Rrahman',	'Dedaj');
INSERT INTO autoret ( [Emri], [Mbiemri]) VALUES ('Dhimiter',	'Xhovanni');





CREATE TABLE aut_lib (
  [Autoret_ID] int NOT NULL,
  [Librat_ID] int NOT NULL
 ,
  CONSTRAINT [aut_lib_ibfk_1] FOREIGN KEY ([Autoret_ID]) REFERENCES autoret ([Autoret_ID]),
  CONSTRAINT [aut_lib_ibfk_2] FOREIGN KEY ([Librat_ID]) REFERENCES librat ([Librat_ID])
)

CREATE INDEX [Autoret_ID] ON aut_lib ([Autoret_ID]);
CREATE INDEX [Librat_ID] ON aut_lib ([Librat_ID]); 

INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(1,	1);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(2,	2);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(3,	3);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(4,	4);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(20,	4);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(5,	5);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(6,	6);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(10,	6);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(6,	7);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(7,	8);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(8,	9);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(2,	9);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(8,	10);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(8,	11);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(1,	11);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(9,	12);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(10,	13);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(10,	14);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(17,	14);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(11,	15);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(12,	16);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(12,	17);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(11,	18);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(3,	18);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(12,	19);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(13,	20);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(14,	21);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(15,	22);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(16,	23);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(16,	24);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(17,	25);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(17,	26);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(17,	27);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(22,	27);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(18,	28);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(19,	29);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(20,	30);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(21,	31);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(22,	32);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(23,	33);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(24,	34);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(25,	35);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(26,	36);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(27,	37);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(28,	38);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(27,	39);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(28,	40);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(27,	41);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(10,	42);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(11,	43);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(12,	44);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(13,	45);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(14,	46);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(15,	47);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(16,	48);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(17,	49);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(18,	50);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(19,	51);
INSERT INTO aut_lib ([Autoret_ID], [librat_ID]) VALUES(20,	52);






CREATE TABLE rezervimet (
  [Rezervimet_ID] int NOT NULL IDENTITY(1,1),
  [DRez] date NOT NULL,
  [Klientet_ID] int NOT NULL,
  [Librat_ID] int NOT NULL,
  [Aktiv] bit not null,
  PRIMARY KEY ([Rezervimet_ID]),
  CONSTRAINT [Rezervimet_ID] UNIQUE  ([Rezervimet_ID]),
  CONSTRAINT [rezervimet_ibfk_1] FOREIGN KEY ([Klientet_ID]) REFERENCES Klientet ([Klientet_ID]),
  CONSTRAINT [rezervimet_ibfk_2] FOREIGN KEY ([Librat_ID]) REFERENCES librat ([Librat_ID])
)

CREATE INDEX [rezervimet_ibfk_1] ON Klientet ([Klientet_ID]);
CREATE INDEX [rezervimet_ibfk_2] ON librat ([Librat_ID]);



USE [master]
GO

ALTER DATABASE [libraryMS] SET  READ_WRITE 
GO

