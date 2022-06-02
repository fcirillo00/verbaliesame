drop table corso, docente, studente, titolarita, appello, verbale, valutazione, prenotazione;

create table CORSO (
    codice INTEGER primary key,
    denominazione VARCHAR(50) not null,
    CFU INTEGER not null
);

create table DOCENTE (
    matricola CHAR(9) primary key,
    cognome VARCHAR(50) not null,
    nome VARCHAR(50) not null,
    username VARCHAR(50) not null unique,
    password VARCHAR(50) not null
);

create table STUDENTE (
    matricola CHAR(9) primary key,
    cognome VARCHAR(50) not null,
    nome VARCHAR(50) not null,
    username VARCHAR(50) not null unique,
    password VARCHAR(50) not null,
    pin INTEGER not null,
    constraint check_pin check(pin >= 0 and pin <= 99999)
);

create table TITOLARITA (
    codiceCorso INTEGER references CORSO(codice) ON DELETE cascade ON UPDATE cascade,
    matricolaDocente CHAR(9) references DOCENTE(matricola) ON DELETE cascade ON UPDATE cascade,
    annoAccademico INTEGER not null,
    constraint pk_acd primary key (codiceCorso, matricolaDocente)
);

create table APPELLO (
    id INTEGER primary key,
    data DATE not null,
    scadenza DATE not null,
    note VARCHAR(500),
    sede VARCHAR(50) check (sede in ('AULA', 'LABORATORIO', 'ALTRO')),
    codiceCorso INTEGER references CORSO(codice) ON DELETE cascade ON UPDATE cascade,
    matricolaDocente CHAR(9) references DOCENTE(matricola) ON DELETE cascade ON UPDATE cascade,
    constraint check_scadenza check (scadenza <= data)
);

create table VERBALE (
    id INTEGER primary key,
    appello INTEGER references APPELLO(id)
);

create table VALUTAZIONE (
    voto INTEGER not null,
    argomenti_trattati VARCHAR(500),
    appello INTEGER references APPELLO(id) ON DELETE cascade ON UPDATE cascade,
    matricolaStudente CHAR(9) references STUDENTE(matricola) ON DELETE cascade ON UPDATE cascade,
    constraint check_voto check (voto >= 0 and voto <= 30),
    constraint pk_esame primary key (appello, matricolaStudente)
);

create table PRENOTAZIONE (
    matricolaStudente CHAR(9) references STUDENTE(matricola) ON DELETE cascade ON UPDATE cascade,
    appello INTEGER references APPELLO(id) ON DELETE cascade ON UPDATE cascade,
    constraint pk_prenotazione primary key (matricolaStudente, appello)
);

insert into STUDENTE values ('N46004793', 'Cirillo', 'Francesco', 'SirFrigo', 'password', 11111);
insert into STUDENTE values ('N46004825', 'Buonomano', 'Giuseppe', 'Pepp', 'password', 22222);
insert into STUDENTE values ('N46004818', 'Amoruso', 'Emanuele Cuono', 'Persona5', 'password', 33333);

insert into DOCENTE values ('A00000001', 'Natella', 'Roberto', 'rnatella', 'so');

insert into CORSO values ('1', 'Sistemi operativi', 9);

insert into TITOLARITA values (1, 'A00000001', 2022);
