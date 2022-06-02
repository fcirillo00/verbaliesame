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
    constraint check_scadenza check (scadenza >= data)
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
)