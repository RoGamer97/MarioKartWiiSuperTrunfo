# Mario Kart Wii Super Trunfo

Mario Kart Wii Super Trunfo é um projeto desenvolvido em Java, utilizando a arquitetura MVC e banco de dados SQLite. O jogo é baseado em um baralho contendo todos os 36 veículos presentes em Mario Kart Wii.

![Imagem do jogo](https://github.com/RoGamer97/MarioKartWiiSuperTrunfo/blob/main/mkwst.png))


## Objetivo

Este projeto foi desenvolvido como parte de uma avaliação da disciplina de Programação Orientada a Objetos, na universidade UniLasalle-RJ.

## Funcionalidades do Jogo

No menu inicial, você define o número de rodadas da partida. Ao início de cada rodada, você seleciona a carta da sua mão e clica em "Jogar"; a máquina escolhe uma carta automaticamente e o sistema compara os atributos, contabilizando os pontos. Se algum atributo empatar, nenhum ponto é contabilizado.

Se empatar os pontos da partida na última rodada, cada jogador recebe uma carta extra do baralho e uma rodada final de desempate é jogada.

## Lógica dos Atributos

Os valores das cartas seguem as estatísticas oficiais do jogo. Como alguns atributos são formados por múltiplos dados físicos combinados, foi necessário realizar cálculos de normalização para transformá-los em índices únicos e comparáveis.

O objetivo desses ajustes foi criar valores visualmente adequados para o jogo de cartas, mantendo a máxima fidelidade possível ao comportamento real dos veículos. Os cálculos foram baseados na engenharia reversa da física do jogo, e os atributos foram extrados diretamente dos arquivos originais.

**Fontes:**
* [kartParam.bin Vehicle Statistics (Custom Mario Kart Wiiki)](https://wiki.tockdom.com/wiki/KartParam.bin#Vehicle_Statistics)
* [Kinoko - Engenharia Reversa da Física (GitHub)](https://github.com/vabold/Kinoko)

**Cálculos Aplicados:**
* **Speed:** Valor original.
* **Acceleration:** Média dos quatro estágios de arrancada (A0 a A3) multiplicada por 10.
* **Handling:** Manual Handling Tightness multiplicado por 1000.
* **Drift:** Manual Drift Tightness multiplicado por 1000.
* **Offroad:** Média das três categorias de offroad (Weak, Normal e Heavy) multiplicada por 10.
* **Weight:** Valor original multiplicado por 10.
* **Miniturbo:** Valor original.

## Banco de Dados

O jogo utiliza o arquivo `veiculos.db`, localizado na pasta `/db`, para armazenar todas as informações dos veículos, incluindo as imagens. Para a manipulação dos dados, foi utilizado o software [DB Browser for SQLite](https://sqlitebrowser.org/), que facilitou a conversão de scripts SQL para o formato de banco de dados (`.db`).

O arquivo `veiculos.sql`, presente na pasta `/db`, não é utilizado em tempo de execução e está incluído apenas como referência para futuras modificações na estrutura das tabelas.

## Easter Egg

O jogo tem dois textos de easter egg que aparecem quando você e a máquina jogam um combo especifico de cartas, sendo eles:

* `Blast off to Quacker Island!!`: Aparece quando a carta dos veículos `Quacker` e `Piranha Prowler` são jogadas ao mesmo tempo em uma rodada. Referência a um [vídeo classico do Youtuber famoso Troy (TWD98)](https://www.youtube.com/watch?v=HVyMpJ_1YIs) onde os jogadores usam um veículo pesado (Piranha Prowler) para bater e lançar um veículo leve (Quacker) para partes inacessíveis das pistas.

* `The Meta Duo`: Aparece quando a carta dos veículos `Flame Runner` e `Mach Bike` são jogadas ao mesmo tempo em uma rodada. Referência aos veículos meta do jogo.

## Como Rodar

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/RoGamer97/MarioKartWiiSuperTrunfo
2. Abra o repositório clonado em sua IDE
3. Execute o arquivo Main.java
