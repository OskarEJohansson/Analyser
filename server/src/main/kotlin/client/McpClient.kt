package client

import io.modelcontextprotocol.kotlin.sdk.CallToolResultBase
import io.modelcontextprotocol.kotlin.sdk.Implementation
import io.modelcontextprotocol.kotlin.sdk.client.Client
import io.modelcontextprotocol.kotlin.sdk.client.StdioClientTransport
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory
import java.lang.AutoCloseable


private val LOG = LoggerFactory.getLogger("dev.oskarjohansson.mcp.McpClient")

class McpClient : AutoCloseable {
    private val mcp: Client = Client(clientInfo = Implementation(name = "Mcp Test Client", version = "0.0.1") )
    private var transport: StdioClientTransport? = null
    private var isConnected: Boolean = false

    fun connect(transportToUse: StdioClientTransport) {
        if(isConnected) {
            LOG.info("Client is already connected")
        }
        this.transport = transportToUse
        runBlocking {
            mcp.connect(transportToUse)
        }
        isConnected = true
        LOG.info("Client connected")
    }

    fun callTool(toolName: String, input: Any?): CallToolResultBase? {
        if(!isConnected){
            throw IllegalArgumentException("Client is not Connected")
        }
        return runBlocking {
            mcp.callTool(
                name = toolName,
                arguments = mapOf("Input" to input)
            )
        }
    }


    override fun close() {
        if(isConnected){
            LOG.info("Closing MCP Client connection...")
            runBlocking {
                mcp.close()
            }
            isConnected = false
            transport = null
            LOG.info("MCP Client connection closed")
        } else {
            LOG.info("Client is already closed")
            runBlocking { mcp.close() }
        }
    }
}
