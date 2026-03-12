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
                sh "mkdir -p .mvn/wrapper"
                sh "echo 'distributionUrl=https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/3.9.11/apache-maven-3.9.11-bin.zip' > .mvn/wrapper/maven-wrapper.properties"
                sh "echo 'wrapperVersion=3.3.4' >> .mvn/wrapper/maven-wrapper.properties"

                sh 'chmod +x mvnw'
                sh './mvnw clean compile'
            }
        }

        stage('Run Tests') {
            parallel {
                stage('Job 1: API Tests') {
                    steps {
                        echo '🚀 Запуск API тестов...'
                        sh 'chmod +x mvnw'
                        sh './mvnw test -DsuiteXmlFile=api-suite.xml'
                    }
                }
                stage('Job 2: UI Tests') {
                    steps {
                        echo "🖥 Запуск UI тестов в браузере: ${params.BROWSER}"
                        sh 'chmod +x mvnw'
                        sh './mvnw test -DsuiteXmlFile=ui-suite.xml -Dheadless=true'
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