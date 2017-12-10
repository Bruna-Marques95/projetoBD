/*Inserir em ELEICAO: título, descrição, data início, data fim, votos nulos, votos em branco, abstenções, tipo, id, UO nome*/
insert into ELEICAO
    values('Eleicao 1', 'Uma pequena eleicao 1', TO_DATE('9-12-2017 10:30','DD-MM-YYYY HH24:MI'), TO_DATE('11-12-2017 18:30','DD-MM-YYYY HH24:MI'), 0, 0, 0, 'Nucleo', 1, (select nome from UNIDADEORGANICA where nome = 'DEI'));

insert into ELEICAO
    values('Eleicao 2', 'Uma pequena eleicao 2', TO_DATE('23-2-2018 10:30','DD-MM-YYYY HH24:MI'), TO_DATE('16-2-2018 18:30','DD-MM-YYYY HH24:MI'), 0, 0, 0, 'Conselho Geral', 2, (select nome from UNIDADEORGANICA where nome = 'FCTUC'));

/*Inserir em LISTA: nº votos, nome lista, id eleição, tipo lista*/
insert into LISTA
    values(0, 'Lista A', (select id from ELEICAO where id = '1'), 'Aluno');

insert into LISTA
    values(0, 'Lista B', (select id from ELEICAO where id = '1'), 'Aluno');
    
insert into LISTA
    values(0, 'Lista C', (select id from ELEICAO where id = '2'), 'Aluno');
    
insert into LISTA
    values(0, 'Lista D', (select id from ELEICAO where id = '2'), 'Professor');