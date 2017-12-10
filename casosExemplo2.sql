/*Inserir em ELEICAO: título, descrição, data início, data fim, votos nulos, votos em branco, abstenções, tipo, id, UO nome*/
insert into ELEICAO
    values('Eleicao de Conselho Geral II', 'Uma pequena eleicao 5', TO_DATE('9-12-2017 08:30','DD-MM-YYYY HH24:MI'), TO_DATE('10-12-2017 21:45','DD-MM-YYYY HH24:MI'), 0, 0, 0, 'Conselho Geral', 5, (select nome from UNIDADEORGANICA where nome = 'DEI'));

/*Inserir em LISTA: nº votos, nome lista, id eleição, tipo lista*/
insert into LISTA
    values(0, 'Lista G', (select id from ELEICAO where id = '5'), 'Aluno');
    
insert into LISTA
    values(0, 'Lista H', (select id from ELEICAO where id = '5'), 'Professor');
    
/*Inserir em MESA_VOTO: UO nome, id*/
insert into MESA_VOTO
    values((select nome from UNIDADEORGANICA where nome = 'DEI'), 5);
    
/*Inserir em MESA_VOTO_ELEICAO: Mesa_Voto UO nome, Mesa_Voto id, Eleicao id*/
insert into MESA_VOTO_ELEICAO
    values((select UONome from MESA_VOTO where UONome = (select nome from UNIDADEORGANICA where nome = 'DEI' and id = 5)), (select id from MESA_VOTO where id = 5), (select id from ELEICAO where id = 5));
    