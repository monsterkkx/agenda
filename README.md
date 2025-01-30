# Sistema de Agendamento de Serviços


## Sobre o Projeto
Este é um aplicativo Android desenvolvido em **Java** com integração ao **Firebase Realtime Database**, permitindo que usuários agendem e gerenciem serviços de forma intuitiva e eficiente. O app conta com um design moderno e funcionalidades pensadas para melhorar a experiência do usuário, incluindo uma **tela de splash screen** e um sistema de **modo claro e escuro**.

## Funcionalidades Principais
- **Cadastro e Login**: Permite que os usuários criem contas e façam login de forma segura.
- **Agendamento de Serviços**: Escolha do serviço desejado, data e hora da marcação.
- **Lista de Agendamentos**: Visualização de agendamentos realizados e próximos.
- **Integração com WhatsApp**: Facilidade para contatar prestadores de serviços diretamente.
- **Modo Claro e Escuro**: Alternância entre temas para melhor experiência do usuário.
- **Splash Screen Personalizada**: Tela de abertura para uma transição suave ao iniciar o app.

## Tecnologias Utilizadas
- **Linguagem**: Java
- **Banco de Dados**: Firebase Realtime Database
- **Interface Gráfica**: Android XML Layouts
- **Arquitetura**: Fragments e Activities para modularização do app

## Estrutura do Projeto
O aplicativo está organizado em **Fragments e Activities**, com um **Navigation Drawer** para facilitar a navegação entre as telas. A estrutura principal inclui:
- `MainActivity.java`: Tela principal com menu lateral de navegação.
- `SettingsFragment.java`: Tela de configurações com opção de modo escuro.
- `AppointmentActivity.java`: Gerenciamento de agendamentos.
- `ServiceSelectionActivity.java`: Escolha do serviço antes da marcação.

## Como Executar o Projeto
1. Clone este repositório:  
   ```bash
   https://github.com/monsterkkx/agenda.git
   ```
2. Abra o projeto no **Android Studio**.
3. Configure o **Firebase** no app seguindo as instruções da [documentação do Firebase](https://firebase.google.com/docs/android/setup?hl=pt-br).
4. Construa e execute o app em um dispositivo ou emulador Android.

## Considerações Finais
Este projeto foi desenvolvido com foco em **usabilidade** e **eficiência**, aproveitando as vantagens do **Firebase** para sincronização de dados em tempo real. Possui um **design responsivo** e funcionalidades pensadas para um fluxo intuitivo de agendamentos.



