insert into cliente (id,nome,cpf,senha) values (1,'Bob','49178412336','senha123');
insert into cliente (id,nome,cpf,senha) values (2,'Jack','1234567890','senha12345');
insert into cliente (id,nome,cpf,senha) values (3,'Hilley','0987654321','senha123678');

insert into produto(id,nome,descricao,preco) values (1, "Teclado Mecanico", "Switchs azuis", 299.99)
insert into produto(id,nome,descricao,preco) values (2, "Tv", "55 polegadas", 1499)
insert into produto(id,nome,descricao,preco) values (3, "Mouse", "dpi 16000", 200)

insert into forma_pagamento(id,tipo) values (1,'PIX')
insert into forma_pagamento(id,tipo) values (2,'DEBITO')
insert into forma_pagamento(id,tipo) values (3,'BOLETO')

insert into pedido(id,cliente_id,forma_pagamento_id,valor_compra) values(1,2,1,499)
insert into pedido(id,cliente_id,forma_pagamento_id,valor_compra) values(2,1,2,1499)
insert into pedido(id,cliente_id,forma_pagamento_id,valor_compra) values(3,3,3,499)

insert into pedido_produto(pedido_id,produto_id) values(1,1)
insert into pedido_produto(pedido_id,produto_id) values(1,3)
insert into pedido_produto(pedido_id,produto_id) values(2,2)
insert into pedido_produto(pedido_id,produto_id) values(3,1)
insert into pedido_produto(pedido_id,produto_id) values(3,3)