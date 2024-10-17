Log de Possíveis Melhorias:
 
Os métodos baseados em INSERT, UPDATE e DELETE, podem retornar -1 tanto no executeUpdate(); e em uma exceção, tudo bem?
As classes DAOs não utilizam o retorno do método conectar(); e do desconectar(); também.
Os métodos baseados no SELECT retornam um ResultSet vazio se não encontrarem um registro, e um ResultSet nulo em caso de exceção, tudo bem?
Devemos adicionar métodos baseados no SELECT mais funcionais, por exemplo, que mostram o usuários com senha menor que seis dígitos?
Os métodos baseados no SELECT são muito repetitivos, eu devo manter a diferenciação por nome, ou encapsular para possibilitar uma fácil manutenção.
Devemos fazer métodos baseados no UPDATE e DELETE com condições mais inteligentes, ou que sejam mais abrangentes, por exemplo, no caso de alterar ou deletar todos os usuários com senha menor que oito dígitos?
Devemos rever o funcionamento dos métodos baseados no UPDATE, uma vez que eles atualizam todos os campos de uma vez, ao invés de atualizar só os solicitados.
Tudo bem todos praticamente todos os métodos receberem um objeto como parâmetro?
