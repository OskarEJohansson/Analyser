
import io.modelcontextprotocol.kotlin.sdk.CallToolResult
import io.modelcontextprotocol.kotlin.sdk.Implementation
import io.modelcontextprotocol.kotlin.sdk.ReadResourceResult
import io.modelcontextprotocol.kotlin.sdk.ServerCapabilities
import io.modelcontextprotocol.kotlin.sdk.TextContent
import io.modelcontextprotocol.kotlin.sdk.TextResourceContents
import io.modelcontextprotocol.kotlin.sdk.Tool
import io.modelcontextprotocol.kotlin.sdk.server.Server
import io.modelcontextprotocol.kotlin.sdk.server.ServerOptions
import io.modelcontextprotocol.kotlin.sdk.server.StdioServerTransport
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking
import kotlinx.io.asSink
import kotlinx.io.asSource
import kotlinx.io.buffered
import org.slf4j.LoggerFactory
import java.io.File
import java.lang.System.*

private val LOG = LoggerFactory.getLogger("dev.oskarjohansson.mcp.Main")

fun main(){
     val server = createServer()

    val transport = StdioServerTransport(
        inputStream = `in`.asSource().buffered(),
        outputStream = out.asSink().buffered()
    )

    runBlocking {
        server.connect(transport)
        val done = Job()
        server.onClose { done.complete() }
        done.join()
        LOG.info("Server closed")
    }
}

fun createServer(): Server {
    LOG.info("Configuring MCP Server...")
    val server = Server(
        Implementation(
            name = "Mcp Server",
            version = "0.0.1"
        ),
        ServerOptions(
            capabilities = ServerCapabilities(
                resources = ServerCapabilities.Resources(subscribe = true, listChanged = true),
                tools = ServerCapabilities.Tools(true)
            )
        )
    )

    server.addTool(
        name = "kotlin-sdk-tool",
        description = "A test tool",
        inputSchema = Tool.Input()
    ) { request ->
        CallToolResult(
            content = listOf(TextContent("Hello, world!"))
        )
    }
    server.addResource(
        uri = "resources/testfile.txt",
        name = "Web Search",
        description = "Web search engine",
        mimeType = "plain/text"
    ) { request ->
        ReadResourceResult(
            contents = listOf(
                TextResourceContents(File(request.uri).readText(), request.uri, "text/plain")
            )
        )
    }
    return server
}
