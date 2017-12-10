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
insert into PESSOA
    values('Maria_Pedroso', 'mfp', 'mfp', 987654321,'Ribas', TO_DATE('20-2-2018','DD-MM-YYYY'), 12345678,(select nome from unidadeorganica where nome = 'DEI'), 'aluno');

insert into PESSOA
    values('Bruna Marques', 'barm', 'barm', 876543219, 'Coimbra', to_date('21-2-2018','DD-MM-YYYY'), 23456789,(select nome from unidadeorganica where nome = 'FCTUC'), 'aluno');
    
insert into PESSOA
    values('Roberto Carlos', 'robcar', 'robcar', 765432198, 'Coimbra', to_date('22-2-2018','DD-MM-YYYY'), 34567891,(select nome from unidadeorganica where nome = 'FCTUC'), 'professor');

insert into PESSOA
    values('Carlos Alves', 'caralv', 'caralv', 654321987, 'Miranda do Corvo', to_date('23-2-2018','DD-MM-YYYY'), 45678912,(select nome from unidadeorganica where nome = 'FCTUC'), 'funcionario');

insert into PESSOA
    values('Tiago Gomes', 'tigo', 'tigo', 543219876, 'Coimbra', to_date('24-2-2018','DD-MM-YYYY'), 56789123,(select nome from unidadeorganica where nome = 'DEI'), 'professor');

/*Inserir em ELEICAO: título, descrição, data início, data fim, votos nulos, votos em branco, abstenções, tipo, id, UO nome*/
insert into ELEICAO
    values('Eleicao de Nucleo', 'Uma pequena eleicao 1', TO_TIMESTAMP('2017-12-08 10:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2017-12-12 18:30:00', 'YYYY-MM-DD HH24:MI:SS'), 0, 0, 0, 'Nucleo', 1, (select nome from UNIDADEORGANICA where nome = 'DEI'));

insert into ELEICAO
    values('Eleicao de Conselho Geral', 'Uma pequena eleicao 2', TO_TIMESTAMP('2017-12-08 11:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2017-12-12 19:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0, 0, 0, 'Conselho Geral', 2, (select nome from UNIDADEORGANICA where nome = 'FCTUC'));

insert into ELEICAO
    values('Eleicao de Direcao de Departamento', 'Uma pequena eleicao 3', TO_TIMESTAMP('2017-12-08 09:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2017-12-12 18:00:00', 'YYYY-MM-DD HH24:MI:SS'), 0, 0, 0, 'Direcao Departamento', 3, (select nome from UNIDADEORGANICA where nome = 'DEI'));

insert into ELEICAO
    values('Eleicao de Direcao de Faculdade', 'Uma pequena eleicao 4', TO_TIMESTAMP('2017-12-08 10:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2017-12-12 17:30:00', 'YYYY-MM-DD HH24:MI:SS'), 0, 0, 0, 'Direcao Faculdade', 4, (select nome from UNIDADEORGANICA where nome = 'FCTUC'));

insert into ELEICAO
    values('Eleicao de Conselho Geral II', 'Uma pequena eleicao 5', TO_DATE('9-12-2017 08:30','DD-MM-YYYY HH24:MI'), TO_DATE('10-12-2017 21:45','DD-MM-YYYY HH24:MI'), 0, 0, 0, 'Conselho Geral', 5, (select nome from UNIDADEORGANICA where nome = 'DEI'));

/*Inserir em LISTA: nº votos, nome lista, id eleição, tipo lista*/
insert into LISTA
    values(0, 'Lista A', (select id from ELEICAO where id = '1'), 'Aluno');

insert into LISTA
    values(0, 'Lista B', (select id from ELEICAO where id = '1'), 'Aluno');
    
insert into LISTA
    values(0, 'Lista C', (select id from ELEICAO where id = '2'), 'Aluno');
    
insert into LISTA
    values(0, 'Lista D', (select id from ELEICAO where id = '2'), 'Professor');
    
insert into LISTA
    values(0, 'Lista E', (select id from ELEICAO where id = '3'), 'Professor');
    
insert into LISTA
    values(0, 'Lista F', (select id from ELEICAO where id = '4'), 'Professor');
    
insert into LISTA
    values(0, 'Lista G', (select id from ELEICAO where id = '5'), 'Aluno');
    
insert into LISTA
    values(0, 'Lista H', (select id from ELEICAO where id = '5'), 'Professor');

/*Inserir em MESA_VOTO: UO nome, id*/
insert into MESA_VOTO
    values((select nome from UNIDADEORGANICA where nome = 'DEI'), 1);

insert into MESA_VOTO
    values((select nome from UNIDADEORGANICA where nome = 'DEI'), 2);
    
insert into MESA_VOTO
    values((select nome from UNIDADEORGANICA where nome = 'DEI'), 3);

insert into MESA_VOTO
    values((select nome from UNIDADEORGANICA where nome = 'DEI'), 4);
    
insert into MESA_VOTO
    values((select nome from UNIDADEORGANICA where nome = 'DEI'), 5);
    
/*Inserir em MESA_VOTO_ELEICAO: Mesa_Voto UO nome, Mesa_Voto id, Eleicao id*/
insert into MESA_VOTO_ELEICAO
    values((select UONome from MESA_VOTO where UONome = (select nome from UNIDADEORGANICA where nome = 'DEI' and id = 1)), (select id from MESA_VOTO where id = 1), (select id from ELEICAO where id = 1));
    
insert into MESA_VOTO_ELEICAO
    values((select UONome from MESA_VOTO where UONome = (select nome from UNIDADEORGANICA where nome = 'DEI' and id = 2)), (select id from MESA_VOTO where id = 2), (select id from ELEICAO where id = 2));

insert into MESA_VOTO_ELEICAO
    values((select UONome from MESA_VOTO where UONome = (select nome from UNIDADEORGANICA where nome = 'DEI' and id = 3)), (select id from MESA_VOTO where id = 3), (select id from ELEICAO where id = 3));
    
insert into MESA_VOTO_ELEICAO
    values((select UONome from MESA_VOTO where UONome = (select nome from UNIDADEORGANICA where nome = 'DEI' and id = 4)), (select id from MESA_VOTO where id = 4), (select id from ELEICAO where id = 4));

insert into MESA_VOTO_ELEICAO
    values((select UONome from MESA_VOTO where UONome = (select nome from UNIDADEORGANICA where nome = 'DEI' and id = 5)), (select id from MESA_VOTO where id = 5), (select id from ELEICAO where id = 5));
