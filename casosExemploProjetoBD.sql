/*Inserir em UNIDADE ORGANICA: nome*/
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

/*Inserir em FACULDADE:*/
insert into Faculdade
  values('FCTUC', (select nome from UNIDADEORGANICA where nome = 'FCTUC'));

insert into Faculdade
  values('FMUC', (select nome from UNIDADEORGANICA where nome = 'FMUC'));

/*Inserir em DEPARTAMENTO:*/
insert into DEPARTAMENTO
    values('DEI',(select nome from FACULDADE where nome = 'FCTUC'), (select nome from UNIDADEORGANICA where nome= 'FCTUC'));

/*Inserir em NUCLEOESTUDANTES*/
insert into NUCLEOESTUDANTES
    values((select nome from faculdade where nome='FCTUC'), 'NEI', (select nome from departamento where nome = 'DEI'));

insert into NUCLEOESTUDANTES
    values((select nome from faculdade where nome='FMUC'), 'NEMD', null);

/*Inserir em PESSOA
Argumentos: NOME, NOMEUTILIZADOR, PASSWORD, NUMTELEFONE, MORADA, VALIDADECC, NUMCC, UNIDADEORGANICANOME, TIPO*/
insert into pessoa
    values('Maria_Pedroso', 'mfp', 'mfp', 987654321,'Ribas', TO_DATE('25-2-2018','DD-MM-YYYY'), 12345678,(select nome from unidadeorganica where nome = 'DEI'), 'aluno');

insert into pessoa
    values('Bruna Marques', 'barm', 'barm', 876543219, 'Coimbra', to_date('25-12-2018','DD-MM-YYYY'),23456789,(select nome from unidadeorganica where nome = 'FCTUC'), 'aluno');
    
insert into pessoa
    values('Roberto Carlos', 'robcar', 'robcar', 765432198, 'Coimbra', to_date('25-12-2018','DD-MM-YYYY'),34567891,(select nome from unidadeorganica where nome = 'FCTUC'), 'professor');

insert into pessoa
    values('Carlos Alves', 'caralv', 'caralv', 654321987, 'Miranda do Corvo', to_date('25-1-2018','DD-MM-YYYY'),45678912,(select nome from unidadeorganica where nome = 'FCTUC'), 'funcionario');

/*Inserir em ELEICAO: título, descrição, data início, data fim, votos nulos, votos em branco, abstenções, tipo, id, UO nome*/
insert into ELEICAO
    values('Eleicao 1', 'Uma pequena eleicao 1', TO_TIMESTAMP('2017-12-09 10:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2017-12-11 18:30:00', 'YYYY-MM-DD HH24:MI:SS'), 0, 0, 0, 'Nucleo', 1, (select nome from UNIDADEORGANICA where nome = 'DEI'));

insert into ELEICAO
    values('Eleicao 2', 'Uma pequena eleicao 2', TO_TIMESTAMP('2017-12-08 11:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2017-12-12 19:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0, 0, 0, 'Conselho Geral', 2, (select nome from UNIDADEORGANICA where nome = 'FCTUC'));

/*Inserir em LISTA: nº votos, nome lista, id eleição, tipo lista*/
insert into LISTA
    values(0, 'Lista A', (select id from ELEICAO where id = '1'), 'Aluno');

insert into LISTA
    values(0, 'Lista B', (select id from ELEICAO where id = '1'), 'Aluno');
    
insert into LISTA
    values(0, 'Lista C', (select id from ELEICAO where id = '2'), 'Aluno');
    
insert into LISTA
    values(0, 'Lista D', (select id from ELEICAO where id = '2'), 'Professor');

/*Inserir em MESA_VOTO: UO nome, id*/
insert into MESA_VOTO
    values((select nome from UNIDADEORGANICA where nome = 'DEI'), 1);
    
insert into MESA_VOTO
    values((select nome from UNIDADEORGANICA where nome = 'FCTUC'), 2);
    
/*Inserir em MESA_VOTO_ELEICAO: Mesa_Voto UO nome, Mesa_Voto id, Eleicao id*/
insert into MESA_VOTO_ELEICAO
    values((select UONome from MESA_VOTO where UONome = (select nome from UNIDADEORGANICA where nome = 'DEI')), (select id from MESA_VOTO where id = 1), (select id from ELEICAO where id = 1));
    
insert into MESA_VOTO_ELEICAO
    values((select UONome from MESA_VOTO where UONome = (select nome from UNIDADEORGANICA where nome = 'FCTUC')), (select id from MESA_VOTO where id = 2), (select id from ELEICAO where id = 2));
