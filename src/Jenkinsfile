pipeline {
    agent any

    parameters {
        string(name: 'BROWSER', defaultValue: 'chrome', description: 'Браузер для UI тестов')
    }

    stages {
        stage('Checkout') {
            steps {
                echo '📦 Клонирование репозитория...'
                checkout scm
            }
        }

        stage('Build & Compile') {
            steps {
                echo '🛠 Компиляция проекта...'
                sh 'mvn clean compile'
            }
        }

        stage('Run Tests') {
            parallel {
                stage('Job 1: API Tests') {
                    steps {
                        echo '🚀 Запуск API тестов...'
                        sh 'mvn test -DsuiteXmlFile=api-suite.xml'
                    }
                }
                stage('Job 2: UI Tests') {
                    steps {
                        echo "🖥 Запуск UI тестов в браузере: ${params.BROWSER}"
                        sh "mvn test -DsuiteXmlFile=ui-suite.xml -Dbrowser=${params.BROWSER}"
                    }
                }
            }
        }
    }

    post {
        always {
            echo '📊 Генерация Allure отчета...'
            allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
        }
        failure {
            echo '❌ Пайплайн завершился с ошибкой. Проверьте скриншоты в Allure'
        }
    }
}