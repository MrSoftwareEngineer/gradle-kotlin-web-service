rootProject.name = "gradle-kotlin-web-service"

include("components:common")
findProject(":components:common")?.name = "common"

include("components:server")
findProject(":components:server")?.name = "server"

include("components:client")
findProject(":components:client")?.name = "client"

include("components:integration-test")
findProject(":components:integration-test")?.name = "integration-test"
