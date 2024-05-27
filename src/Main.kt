import interpreter.Interpreter
import lexer.Lexer
import lexer.Token
import lexer.TokenType
import parser.Parser
import java.io.File

fun main() {
    val currentDir = System.getProperty("user.dir")
    val fileName = "$currentDir/main.uzlang"

    val sourceCode = File(fileName).readText()

    val lexer = Lexer(sourceCode)
    val tokens = mutableListOf<Token>()
    var token = lexer.getNextToken()
    while (token.type != TokenType.EOF) {
        tokens.add(token)
        token = lexer.getNextToken()
    }

    println("Tokens: $tokens") // Debug

    val parser = Parser(tokens)
    val program = parser.parse()

    println("Parsed program: $program") // Debug

    val interpreter = Interpreter(program)
    interpreter.interpret()
}
