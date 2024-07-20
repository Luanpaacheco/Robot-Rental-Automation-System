
<h1>ACMERobots - Sistema de Automação para Locação de Robôs </h1>


Este projeto implementa uma aplicação com interface gráfica de usuário (GUI) para automatizar o negócio da ACMERobots. O sistema possui uma tela cíclica que permite ao usuário realizar diversas operações relacionadas ao cadastro de robôs, clientes, locações e gerenciamento dessas informações.

Funcionalidades do Sistema

Cadastrar Novo Robô: Permite cadastrar os dados de um novo robô. O sistema verifica se já existe um robô com o mesmo id e mantém os robôs em ordem crescente de id. Em caso de duplicidade, exibe uma mensagem de erro.

Cadastrar Novo Cliente: Solicita e cadastra os dados de um novo cliente. Verifica se o código do cliente já existe e mantém os clientes em ordem crescente de código. Em caso de código duplicado, mostra uma mensagem de erro.

Cadastrar Nova Locação: Mostra a lista de clientes cadastrados para que o usuário selecione um cliente. Em seguida, solicita e cadastra os dados de uma nova locação para o cliente selecionado, incluindo a seleção de um ou mais robôs disponíveis. As locações são colocadas em uma fila de locações pendentes. Se o número da locação já existir, é exibida uma mensagem de erro.

Processar Locações: Processa automaticamente as locações pendentes da fila. Verifica se todos os robôs solicitados estão disponíveis. Se sim, a locação é marcada como EXECUTANDO. Caso contrário, os robôs já alocados são liberados e a locação retorna para a fila de pendentes. Se não houver locações na fila, exibe uma mensagem de erro.

Mostrar Relatório Geral: Exibe todos os dados cadastrados de robôs, clientes e locações. Se não houver dados cadastrados, mostra uma mensagem de erro.

Consultar Todas as Locações: Mostra todos os detalhes das locações cadastradas, incluindo informações sobre os clientes e, se houver, os robôs alocados junto com o valor final da locação. Se não houver locações cadastradas, exibe uma mensagem de erro.

Alterar Situação de uma Locação: Permite alterar a situação de uma locação existente. Solicita o número da locação, exibe seus dados e solicita a nova situação. Não permite alterar locações na situação FINALIZADA ou CANCELADA. Em caso de locação não encontrada, mostra uma mensagem de erro.

Realizar Carga de Dados Iniciais do Sistema: Solicita o nome do arquivo (sem extensão) para carregar os dados iniciais do sistema. As locações são carregadas em uma fila de locações pendentes. Ao final da carga, exibe todos os dados de robôs, clientes e locações. Em caso de problemas na carga de dados, mostra uma mensagem de erro.

Salvar Dados: Solicita ao usuário um nome de arquivo (sem extensão) e salva todos os dados cadastrados no sistema em um ou mais arquivos. Em caso de problema no salvamento, exibe uma mensagem de erro.

Carregar Dados: Solicita ao usuário um nome de arquivo (sem extensão) para carregar os dados de um ou mais arquivos para o sistema. Em caso de problema no carregamento, mostra uma mensagem de erro.

Finalizar Sistema: Encerra a execução do sistema.

Tecnologias Utilizadas
Linguagem de Programação: Java
Interface Gráfica de Usuário (GUI): Implementada para facilitar a interação do usuário com o sistema.
Armazenamento de Dados: Utiliza arquivos CSV e JSON para armazenar os dados de robôs, clientes e locações.
Metodologia de Desenvolvimento: Utilização de técnicas como pair programming para colaboração entre os desenvolvedores.
Desenvolvedores


Este projeto foi desenvolvido por:

Arthur Blasi
Luan Pacheco Lima
Luis Trein
