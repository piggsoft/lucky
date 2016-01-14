# piggsoft-spring
Spring MVC Maven archetype  
Spring MVC Maven 模板文件

使用下面的命令，即可创建一个全新的带依赖，带部分配置的项目

####使用方法
````
1. mvn clean archetype:create-from-project
2. cd target/generated-sources/archetype/
3. mvn clean install
4. mvn archetype:generate -DarchetypeCatalog=local
````
####在一行内使用命令
````
mvn clean archetype:create-from-project & cd target/generated-sources/archetype/ & mvn clean install & mvn archetype:generate -DarchetypeCatalog=local
````