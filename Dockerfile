# 使用 Maven 构建镜像
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# 复制所有项目文件
COPY . .

# 安装依赖并构建
# 注意：因为是多模块项目，我们需要先安装父工程和其他依赖模块
# -DskipTests 跳过测试以加快构建速度
RUN mvn clean install -DskipTests

# 运行阶段
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# 从构建阶段复制生成的 jar 包
# 注意路径：tlias-web-management/target/tlias-web-management-0.0.1-SNAPSHOT.jar
COPY --from=build /app/tlias-web-management/target/tlias-web-management-0.0.1-SNAPSHOT.jar app.jar

# 暴露端口
EXPOSE 8080

# 启动命令
ENTRYPOINT ["java", "-jar", "app.jar"]