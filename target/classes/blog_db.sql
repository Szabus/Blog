DROP DATABASE IF EXISTS blog;

CREATE DATABASE IF NOT EXISTS blog;

USE blog;

CREATE TABLE IF NOT EXISTS user (
id INT UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT,
user_name VARCHAR(50) NOT NULL UNIQUE,
password VARCHAR(14) NOT NULL,
e_mail VARCHAR(50) NOT NULL UNIQUE,
access ENUM('user','admin','moderator') DEFAULT 'user',
PRIMARY KEY(id));

INSERT INTO user(user_name, password, e_mail, access)
VALUES
('p_sanyi', 'pilvax48', 'petofi@gmail.com', 'user'),
('arany_jános', 'kisfaludy0302', 'aranusery.janos@gmail.com', 'admin'),
('vörösmarty', 'mta44', 'v.m@gmail.com', 'moderator'),
('júlia', 'andersen17', 'szendrey.j@gmail.com', 'user'),
('karinthy', 'krnthy3344', 'k.frigyes@gmail.com', 'user'),
('kosztolányi', 'kosztid11', 'k.d@gmail.com', 'user');

CREATE TABLE IF NOT EXISTS blog_template(
id INT UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT,
blog_name VARCHAR(50) NOT NULL UNIQUE,
category ENUM('personal', 'business', 'other'),
color ENUM('beige', 'dark', 'light_rose','blue_dream','flamingo_pink', 'sunrise'),
wallpaper LONGBLOB,
PRIMARY KEY(id));

INSERT INTO blog_template(blog_name, category, color)
VALUES
('Helyiség kalapácsa', 'business', 'sunrise'),
('Egy nő több, mint csak asszony', 'other', 'beige'),
('Bohém költők társasága', 'other', 'blue_dream'),
('Ha eldobod egykor az özvegyi fátyolt...', 'other', 'dark');

CREATE TABLE IF NOT EXISTS blog_entry(
id INT UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT,
entry VARCHAR(800) NOT NULL,
status ENUM('draft','shared', 'deleted', 'hidden'),
blog_id INT UNSIGNED,
PRIMARY KEY(id),
FOREIGN KEY (blog_id) REFERENCES blog_template(id));

INSERT INTO blog_entry(entry, status, blog_id)
VALUES
('Tudom, a költőnek szabadnak kell lenni, mint a gondolat, hogy álmodozó lelkét kiélhesse.
De a rabláncot, mely ábrándjaidat gúzsba köti, te oly szépen, oly magasztosan szabod el szavaid élével, hogy ha
 eddig nem perzselődtem volna meg szenvedélyed lángjától, hát most az porlasztana hamuvá, amit én táplálok irántad.', 'shared', 2),
 ('Vándortárs!
Igy kellett Pestre jönnöd? Lásd, nem győztelek bevárni, végre magamnak kellett elindulnom a
roppant utra. Iparkodjál utánam, még ott lehetsz Párizsban, mire én ott leszek. Hanem egy
dolog igen meglepett: hát te már a Pesther Zeitunghoz is odaszegődtél dolgozótársnak?
Olvastam benne művedet... a sequestrumról. Servus öcsém', 'shared', 1),
('Karinthy Frigyesnek, úri-magának, az embernyi embernek, de kicsit talán a Kálomistának is küldöm, 
azzal az Instanciával, hogy ne átallaná elolvasni ezt a nekem-kedves Poémát, minden irányban.
Nyár,
A régi vágyam egyre jobban
Lobban,
De vár még, egyre vár.
Kár
Így késlekedned, mert az éj setétül.
Az élet
Siralmas és sivár
Enélkül.
Gigászi vágyam éhes, mint a hörcsög,
Görcsök
Emésztik s forró titkom mélye szörcsög.
Mostan hajolj feléje.
Közel a lázak kéjes éje.
Akarod?
Remegve nyújtsd a szájad és karod.
Itt ez ital illatja tégedet vár.
Nektár.
Te
Hűtelen, boldog leszel majd újra, hidd meg.
Idd meg.', 'shared', 3 ),
('Sötét titkoknak bölcsője a jelen, megfejthetetlen talány a jövő, széttéphetetlen a fátyol, mellyet
reá a sors vetett. Rövid időn előttem fog állani a való egész meztelenségében; de ha sejtésem
nem csal, jaj nekem!','draft', 4);
 

CREATE TABLE IF NOT EXISTS comments(
id INT UNSIGNED AUTO_INCREMENT,
comment VARCHAR(400) NOT NULL,
entry_id INT UNSIGNED,
user_id INT UNSIGNED,
reply_id INT UNSIGNED,
PRIMARY KEY(id),
FOREIGN KEY (entry_id) REFERENCES blog_entry(id),
FOREIGN KEY (user_id) REFERENCES user(id),
FOREIGN KEY (reply_id) REFERENCES comments(id));

INSERT INTO comments(id, comment, entry_id, user_id)
VALUES
(1,'...', 3, 5),
(2,'Irni fogok, valahányszor lehet. Légy nyugott és béketürő, amennyire lehetsz. Higy! Remélj! Szeress!', 1, 1);

INSERT INTO comments(id, comment, entry_id, user_id, reply_id)
VALUES
(3,'Azért nem kötöm még most le magam, nem igérek semmit, csak azt mondom, hogy ha eljő ön tavasszal s mindketten úgy érezzük, 
magunk iránt kötelességnek tartom egymásban keresni fel jövendőnk bolodgságának alapját.', 1, 4, 2),
(4,'<3', 3, 6, 1);

CREATE TABLE IF NOT EXISTS user2blogs(
id INT UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT,
user_id INT UNSIGNED NOT NULL,
blog_id INT UNSIGNED NOT NULL,
FOREIGN KEY (user_id) REFERENCES user(id),
FOREIGN KEY (blog_id) REFERENCES blog_template(id));

INSERT INTO user2blogs(user_id, blog_id)
VALUES
(1, 1),
(4, 2),
(6, 3),
(4, 4);