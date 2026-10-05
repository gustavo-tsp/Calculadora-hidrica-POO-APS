# 💧 Calculadora Hídrica — POO / APS

[![Java](https://img.shields.io/badge/Java-17-ed8b00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![JavaFX](https://img.shields.io/badge/JavaFX-17-blue?style=for-the-badge&logo=java&logoColor=white)](https://openjfx.io/)
[![SQLite](https://img.shields.io/badge/SQLite-3-003B57?style=for-the-badge&logo=sqlite&logoColor=white)](https://www.sqlite.org/)
[![Maven](https://img.shields.io/badge/Maven-3.8+-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)](https://maven.apache.org/)

> **Projeto Acadêmico de Atividades Práticas Supervisadas (APS)** — Universidade Paulista (UNIP)  
> Alinhado ao **Objetivo de Desenvolvimento Sustentável 6 (ODS 6) da ONU** (Água Potável e Saneamento).

Aplicação desktop desenvolvida em **Java 17** com interface gráfica em **JavaFX** e banco de dados **SQLite**. O sistema auxilia no consumo consciente de água, permitindo calcular gastos mensais em m³, analisar faixas tarifárias e comparar hábitos de consumo com indicadores de sustentabilidade da ONU.

---

## 📌 Funcionalidades

- 📊 **Cálculo de Consumo:** Registro e cálculo do consumo hídrico mensal em m³ e em litros.
- 🏢 **Análise Tarifária:** Leitura de faixas e coeficientes tarifários das concessionárias.
- 🌿 **Meta Sustentável:** Comparativo direto entre o consumo medido e as recomendações da ONU.
- 💾 **Persistência de Dados:** Armazenamento local do histórico via banco de dados SQLite (`agua.db`).
- 🎨 **Interface Gráfica:** Telas organizadas via FXML para navegação simples e intuitiva.

---

## 🛠️ Tecnologias e Conceitos

- **Linguagem:** Java 17
- **Interface Gráfica:** JavaFX (FXML)
- **Gerenciador de Dependências:** Apache Maven
- **Banco de Dados:** SQLite (`sqlite-jdbc`)
- **Conceitos Chave:** Programação Orientada a Objetos (Encapsulamento, Abstração, Herança e Polimorfismo) e arquitetura MVC/DAO.

---

## 📁 Estrutura do Projeto

```text
POO-APS CODIGO/
├── src/
│   ├── main/
│   │   ├── java/         # Classes do modelo, controllers e conexão JDBC
│   │   └── resources/    # Telas FXML e estilos CSS
├── agua.db               # Banco de dados local SQLite
├── pom.xml               # Dependências e plugins do Maven
└── README.md             # Documentação do projeto
