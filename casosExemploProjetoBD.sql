/*INSERIR EM UNIDADE ORGANICA: nome*/

insert into UNIDADEORGANICA
    values('FCTUC');


insert into UNIDADEORGANICA
    values('DEI');

insert into UNIDADEORGANICA
    values('FCDEFUC');


insert into UNIDADEORGANICA
    values('FDUC');


insert into UNIDADEORGANICA
    values('FMUC');

insert into UNIDADEORGANICA
    values('FLUC');

/*Inserir em faculdades:*/
insert into Faculdade
  values('FCTUC', (select nome from UNIDADEORGANICA where nome = 'FCTUC'));


insert into Faculdade
  values('FMUC', (select nome from UNIDADEORGANICA where nome = 'FMUC'));



/*Inserir departamentos:*/
insert into DEPARTAMENTO
    values('DEI',(select nome from FACULDADE where nome = 'FCTUC'), (select nome from UNIDADEORGANICA where nome= 'FCTUC'));

/*Inserir nucleos*/
insert into NUCLEOESTUDANTES
    values((select nome from faculdade where nome='FCTUC'), 'NEI', (select nome from departamento where nome = 'DEI'));


insert into NUCLEOESTUDANTES
    values((select nome from faculdade where nome='FMUC'), 'NEMD', null);



/*Inserir em pessoa: argumentos: NOME, NOMEUTILIZADOR, PASSWORD, NUMTELEFONE, MORADA, VALIDADECC, NUMCC, UNIDADEORGANICANOME, TIPO*/

insert into pessoa
    values('Maria_Pedroso', 'mfp', 'mfp', 987654321,'Ribas', TO_DATE('25-2-2018','DD-MM-YYYY'), 12345678,(select nome from unidadeorganica where nome = 'DEI'), 'aluno');


insert into pessoa
    values ('Bruna Marques', 'barm', 'barm', 876543219, 'Coimbra', to_date('25-12-2018','DD-MM-YYYY'),23456789,(select nome from unidadeorganica where nome = 'FCTUC'), 'aluno');
    

insert into pessoa
    values ('Roberto Carlos', 'robcar', 'robcar', 765432198, 'Coimbra', to_date('25-12-2018','DD-MM-YYYY'),34567891,(select nome from unidadeorganica where nome = 'FCTUC'), 'professor');


insert into pessoa
    values ('Carlos Alves', 'caralv', 'caralv', 654321987, 'Miranda do Corvo', to_date('25-1-2018','DD-MM-YYYY'),45678912,(select nome from unidadeorganica where nome = 'FCTUC'), 'funcionario');


