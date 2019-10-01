# Spring使い方

## 1.開発環境
ecripseのヘルプ→マーケットプライスでSTSを検索してインストール

インストールしたら新規→その他→Springスタータープロジェクトを選択して名前、パッケージ名等を設定する。

依存関係の選択についてはSpringの開発で必要なものを選択する

〇Spring Data JPA</br>
〇Tymeleaf</br>
〇Spring Web</br>
上記はSpring開発をするうえでほぼ必須になる</br>

あとから依存関係を変更する場合にはPOM.xmlに追加記述する必要がある

新規でプロジェクトを作成した場合にはmavenのバージョンの記載がないため、エラーになっていることがあるので
<properties>タグ内に
<maven-jar-plugin.version>3.1.1</maven-jar-plugin.version>
  を追加する
  
インメモリでH2DBを使うこともできるが、mysqlなどを使う場合には依存関係に設定するのと同時にapplication.propertiesに設定をする。

spring.datasource.url=jdbc:mysql://localhost:3306/データベース名?characterEncoding=UTF-8&serverTimezone=JST
spring.datasource.username=DBのusername
spring.datasource.password= DBのpassword
spring.datasource.driverClassName=com.mysql.jdbc.Driver
spring.jpa.database=MYSQL
spring.jpa.hibernate.ddl-auto=update //アプリケーション実行時にテーブルが存在しない場合自動で生成
