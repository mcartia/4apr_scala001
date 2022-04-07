val commandExecutor = Map(
  "cleanup" -> {()=> println("cleanup successfully")}
)
val command="cleanup"

commandExecutor(command).apply
