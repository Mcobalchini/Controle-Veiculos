insert into tipo (id,descricao) select 1,'Outros' where not exists(select id from tipo where id = 1);
insert into tipo (id,descricao) select 2,'Multas' where not exists(select id from tipo where id = 2);
insert into tipo (id,descricao) select 3,'Abastecimentos' where not exists(select id from tipo where id = 3);